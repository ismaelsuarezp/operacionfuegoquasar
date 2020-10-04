package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseExceptionType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static edu.iasp.mercadolibre.challenge.operacionfuegoquasar.mother.MensajeMother.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DesfaseBoundaryTest {

    private static IDesfaseBoundary desfaseBoundary;

    private String[][] mensajes;
    private List<String[]> mensajesResultantes;
    private DesfaseException exception;

    @BeforeAll
    public static void init(){
        desfaseBoundary  = new DesfaseBoundary();;
    }

    @BeforeEach
    public void initTest(){
        mensajes = null;
        mensajesResultantes = null;
        exception = null;
    }

    @Test
    void quitarDesfaseConMensajesNulos(){
        dadoMensajesNulos();
        cuandoQuitaDesfase();
        entoncesDesfaseExcpetionDataInconsistenteConMensajesNulos();
    }

    //<editor-fold desc="Métodos de quitarDesfaseConMensajesNulos" defaultstate="collapsed">
    private void dadoMensajesNulos() {
        mensajes = null;
    }

    private void entoncesDesfaseExcpetionDataInconsistenteConMensajesNulos() {
        assertNull(mensajes);
        assertNull(mensajesResultantes);
        assertNotNull(exception);
        assertEquals(DesfaseExceptionType.ERROR_DATA_INDONSISTENTE, exception.getType());
    }
    //</editor-fold>

    @Test
    void quitarDesfaseConMensajeVacio(){
        dadoMensajeVacio();
        cuandoQuitaDesfase();
        entoncesDesfaseExcpetionErrorQuitarDesfaseVacio();
    }

    //<editor-fold desc="Métodos de quitarDesfaseConSoloUnMensaje" defaultstate="collapsed">
    private void dadoMensajeVacio() {
        mensajes = new String[][] {{}};
    }

    private void entoncesDesfaseExcpetionErrorQuitarDesfaseVacio() {
        assertNotNull(mensajes);
        assertNull(mensajesResultantes);
        assertNotNull(exception);
        assertEquals(1,mensajes.length);
        assertEquals(0,mensajes[0].length);
        assertEquals(DesfaseExceptionType.ERROR_QUITAR_DESFASE, exception.getType());
    }
    //</editor-fold>

    @Test
    void quitarDesfaseConMensajesSinDesfase(){
        dadoMensajesSinDesfase();
        cuandoQuitaDesfase();
        entoncesMensajesResultantesIguales();
    }

    //<editor-fold desc="Métodos de quitarDesfaseConMensajesSinDesfase" defaultstate="collapsed">
    private void dadoMensajesSinDesfase() {
        mensajes = new String[][]{
                MSJ_SATELITE_1_SIN_DESFASE,
                MSJ_SATELITE_2_SIN_DESFASE,
                MSJ_SATELITE_3_SIN_DESFASE};
    }

    private void entoncesMensajesResultantesIguales() {
        assertNull(exception);
        assertNotNull(mensajes);
        assertNotNull(mensajesResultantes);
        assertEquals(3, mensajes.length);
        assertEquals(3, mensajesResultantes.size());
        assertEquals(MSJ_SATELITE_1_SIN_DESFASE, mensajes[0]);
        assertEquals(MSJ_SATELITE_2_SIN_DESFASE, mensajes[1]);
        assertEquals(MSJ_SATELITE_3_SIN_DESFASE, mensajes[2]);
        assertTrue(mensajesResultantes.contains(MSJ_SATELITE_1_SIN_DESFASE));
        assertTrue(mensajesResultantes.contains(MSJ_SATELITE_2_SIN_DESFASE));
        assertTrue(mensajesResultantes.contains(MSJ_SATELITE_3_SIN_DESFASE));
    }
    //</editor-fold>

    @Test
    void quitarDesfaseConMensajesConDesfase(){
        dadoMensajesConDesfase();
        cuandoQuitaDesfase();
        entoncesMensajesResultantesSinDesfase();
    }

    //<editor-fold desc="Métodos de quitarDesfaseConMensajesConDesfase" defaultstate="collapsed">
    private void dadoMensajesConDesfase() {
        mensajes = new String[][]{MSJ_SATELITE_1,MSJ_SATELITE_2_SIN_DESFASE,MSJ_SATELITE_3};
    }

    private void entoncesMensajesResultantesSinDesfase() {
        assertNull(exception);
        assertNotNull(mensajes);
        assertNotNull(mensajesResultantes);
        assertEquals(3, mensajes.length);
        assertEquals(3, mensajesResultantes.size());
        assertEquals(MSJ_SATELITE_1, mensajes[0]);
        assertEquals(0, Arrays.compare(MSJ_SATELITE_2, mensajes[1]));
        assertEquals(MSJ_SATELITE_3, mensajes[2]);
        assertTrue(mensajesResultantes.contains(MSJ_SATELITE_1));
        assertEquals(0, Arrays.compare(MSJ_SATELITE_2,mensajesResultantes.get(1)));
        assertTrue(mensajesResultantes.contains(MSJ_SATELITE_3));
    }
    //</editor-fold>


    @Test
    void quitarDesfaseConMensajesQueNoSePueden(){
        dadoMensajesConDesfaseQueNoSepueden();
        cuandoQuitaDesfase();
        entoncesDesfaseExcpetionErrorQuitarDesfaseMensajesQueNoSePueden();
    }

    //<editor-fold desc="Métodos de quitarDesfaseConMensajesQueNoSePueden" defaultstate="collapsed">
    private void dadoMensajesConDesfaseQueNoSepueden() {
        mensajes = new String[][]{MSJ_SATELITE_1_NO_LEGIBLE,MSJ_SATELITE_2,MSJ_SATELITE_3_SIN_DESFASE};
    }

    private void entoncesDesfaseExcpetionErrorQuitarDesfaseMensajesQueNoSePueden() {
        assertNotNull(mensajes);
        assertNull(mensajesResultantes);
        assertNotNull(exception);
        assertTrue(mensajes.length > 1);
        assertEquals(DesfaseExceptionType.ERROR_QUITAR_DESFASE, exception.getType());
    }
    //</editor-fold>

    private void cuandoQuitaDesfase() {
        try{
            mensajesResultantes = desfaseBoundary.quitarDesfase(mensajes);
        } catch (DesfaseException e){
            exception = e;
        }
    }

}