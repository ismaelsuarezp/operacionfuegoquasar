package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeExceptionType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static edu.iasp.mercadolibre.challenge.operacionfuegoquasar.mother.MensajeMother.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
@ExtendWith(MockitoExtension.class)
class MensajeBoundaryTest {

    @Mock
    private static IDesfaseBoundary desfaseBoundary;
    @InjectMocks
    private static MensajeBoundary mensajeBoundary;

    private String[][] mensajes;
    private String mensajeDesifrado;
    private MensajeException exception;

    @BeforeAll
    static void init(){
    }

    @BeforeEach
    void initTests(){
        ReflectionTestUtils.setField(mensajeBoundary, "palabraVacia", " ");
        mensajes = null;
        mensajeDesifrado = null;
        exception = null;
    }

    @Test
    void desifrarMensajesInconsistentes() throws DesfaseException {
        dadoMensajesInconsistentes();
        cuandoDesifraMensaje();
        entoncesMensajeExceptionDatosInconsistentes();
    }

    //<editor-fold desc="Métodos de desifrarMensajesInconsistentes" defaultstate="collapsed">
    private void dadoMensajesInconsistentes() throws DesfaseException {
        mensajes = new String[][]{
                MSJ_SATELITE_1_NO_LEGIBLE,
                MSJ_SATELITE_2_SIN_DESFASE,
                MSJ_SATELITE_3};
        when(desfaseBoundary.quitarDesfase(any())).thenThrow(DesfaseException.class);
    }

    private void entoncesMensajeExceptionDatosInconsistentes() {
        assertNotNull(mensajes);
        assertNull(mensajeDesifrado);
        assertNotNull(exception);
        assertEquals(MensajeExceptionType.MSJS_INCONSISTENTES, exception.getType());
    }
    //</editor-fold>

    @Test
    void desifrarMensajesConDesfaseMensajeDesifrado() throws DesfaseException {
        dadoMensajesConAlgunDesfase();
        cuandoDesifraMensaje();
        entoncesMensajeEsDesifrado();
    }

    //<editor-fold desc="Metodos de desifrarMensajesConDesfaseMensajeDesifrado">
    private void dadoMensajesConAlgunDesfase() throws DesfaseException {
        mensajes = new String[][]{MSJ_SATELITE_1,MSJ_SATELITE_2_SIN_DESFASE,MSJ_SATELITE_3};
        List<String[]> mensajesResultMock = Arrays.asList(MSJ_SATELITE_1,MSJ_SATELITE_2,MSJ_SATELITE_3);
        when(desfaseBoundary.quitarDesfase(mensajes)).thenReturn(mensajesResultMock);
    }
    //</editor-fold>

    @Test
    void desifrarMensajesSinDesfaseMensajeDesifrado() throws DesfaseException {
        dadoMensajesSinDesfase();
        cuandoDesifraMensaje();
        entoncesMensajeEsDesifrado();
    }

    //<editor-fold desc="Métodos de desifrarMensajesSinDesfaseMensajeDesifrado">
    private void dadoMensajesSinDesfase() throws DesfaseException {
        mensajes = new String[][]{MSJ_SATELITE_1_SIN_DESFASE,MSJ_SATELITE_2_SIN_DESFASE,MSJ_SATELITE_3_SIN_DESFASE};
        List<String[]> mensajesResultMock = Arrays.asList(MSJ_SATELITE_1_SIN_DESFASE,MSJ_SATELITE_2_SIN_DESFASE,MSJ_SATELITE_3_SIN_DESFASE);
        when(desfaseBoundary.quitarDesfase(mensajes)).thenReturn(mensajesResultMock);
    }
    //</editor-fold>

    @Test
    void desifrarMensajesNoDesifrable() throws DesfaseException {
        dadoMensajesNoDesifrables();
        cuandoDesifraMensaje();
        entoncesMensajeExceptionPalabraNoCoincide();
    }

    //<editor-fold desc="Métodos de desifrarMensajesNoDesifrable">
    private void dadoMensajesNoDesifrables() throws DesfaseException {
        mensajes = new String[][]{MSJ_SATELITE_1_NO_LEGIBLE, MSJ_SATELITE_2_SIN_DESFASE, MSJ_SATELITE_3_SIN_DESFASE};
        List<String[]> mensajesMock = Arrays.asList(MSJ_SATELITE_1_NO_LEGIBLE, MSJ_SATELITE_2_SIN_DESFASE, MSJ_SATELITE_3_SIN_DESFASE);
        when(desfaseBoundary.quitarDesfase(mensajes)).thenReturn(mensajesMock);
    }

    private void entoncesMensajeExceptionPalabraNoCoincide() {
        assertNotNull(mensajes);
        assertNull(mensajeDesifrado);
        assertNotNull(exception);
        assertEquals(MensajeExceptionType.MSJS_PALABRAS_INIDICE_NO_COINCIDEN, exception.getType());
    }
    //</editor-fold>

    @Test
    void desifrarMensajesNoDesifrablePalbrasVacias() throws DesfaseException {
        dadoMensajesNoDesifrablesPalabrasVacias();
        cuandoDesifraMensaje();
        entoncesMensajeExceptionTodasLasPalabrasVacias();
    }

    private void dadoMensajesNoDesifrablesPalabrasVacias() throws DesfaseException {
        mensajes = new String[][]{MSJ_SATELITE_1_PALABRA_VACIA, MSJ_SATELITE_2_PALABRA_VACIA, MSJ_SATELITE_3_PALABRA_VACIA};
        List<String[]> mensajesMock = Arrays.asList(MSJ_SATELITE_1_PALABRA_VACIA, MSJ_SATELITE_2_PALABRA_VACIA, MSJ_SATELITE_3_PALABRA_VACIA);
        when(desfaseBoundary.quitarDesfase(mensajes)).thenReturn(mensajesMock);
    }

    private void entoncesMensajeExceptionTodasLasPalabrasVacias() {
        assertNotNull(mensajes);
        assertNull(mensajeDesifrado);
        assertNotNull(exception);
        assertEquals(MensajeExceptionType.MSJS_TODAS_LAS_PALABRAS_SON_ESPACIOS, exception.getType());
    }

    private void entoncesMensajeEsDesifrado() {
        assertNotNull(mensajes);
        assertNotNull(mensajeDesifrado);
        assertNull(exception);
        assertEquals(MSJ_ESTE_ES_UN_MENSAJE, mensajeDesifrado);
    }

    private void cuandoDesifraMensaje() {
        try {
            mensajeDesifrado= mensajeBoundary.desifrar(mensajes);
        } catch (MensajeException e) {
            log.error(e);
            exception = e;
        }
    }

}
