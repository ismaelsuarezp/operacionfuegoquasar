package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.posicion.PosicionCountrol;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.posicion.SateliteMemoControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionExceptionType;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static edu.iasp.mercadolibre.challenge.operacionfuegoquasar.mother.DistanciaSateliteMother.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@ExtendWith(MockitoExtension.class)
class PosicionControlTest {

    @Spy
    private static SateliteMemoControl sateliteControl;
    @InjectMocks
    private static PosicionCountrol posicionBoundary;

    private List<DistanciaSateliteDto> distanciasSatelites;
    private Coordenada posicion;
    private PosicionException exception;

    @BeforeAll
    static void init(){
    }

    @BeforeEach
    void initTest(){
        ReflectionTestUtils.setField(posicionBoundary,"cantidadDistanciasMinimasParaCalcular", 3);
        distanciasSatelites = null;
        posicion = null;
        exception = null;
    }

    @Test
    void calcularPosicionDistanciasVacias(){
        dadasDistanciasVacias();
        cuandoCalculaPosicion();
        entoncesPresentaExcptionDistanciasVacias();
    }

    //<editor-fold desc="Métodos de calcularPosicionDistanciasVacias" defaultstate="collapsed">
    private void dadasDistanciasVacias() {
        distanciasSatelites = Collections.emptyList();
    }

    private void entoncesPresentaExcptionDistanciasVacias() {
        assertTrue(Objects.isNull(distanciasSatelites) || distanciasSatelites.isEmpty());
        assertNull(posicion);
        assertNotNull(exception);
        assertEquals(PosicionExceptionType.ERROR_DISTANCIAS_MINIMAS, exception.getType());
    }
    //</editor-fold>

    @Test
    void calcularPosicionConDatosIncorrectos(){
        dadasDistanciasConDatosIncorrectos();
        cuandoCalculaPosicion();
        entoncesPresentaExcptionDistanciasInvalidas();
    }

    //<editor-fold desc="Métodos de calcularPosicionDistanciasVacias" defaultstate="collapsed">
    private void dadasDistanciasConDatosIncorrectos() {
        distanciasSatelites = getDistanciasConDatosIncorrectos();
    }

    private void entoncesPresentaExcptionDistanciasInvalidas() {
        assertNotNull(distanciasSatelites);
        assertNull(posicion);
        assertNotNull(exception);
        assertTrue(distanciasSatelites.size() >= 3);
        assertEquals(PosicionExceptionType.ERROR_DATOS_DISTANCIAS_INVALIDOS, exception.getType());
    }
    //</editor-fold>

    @Test
    void calcularPosicionConTodasDistanciasIncorrectas(){
        dadasTodasLasDistanciasIncorrectas();
        cuandoCalculaPosicion();
        entoncesPresentaExceptionSinIntercepcionSenal();
    }

    //<editor-fold desc="Métodos de calcularPosicionConTodasDistanciasIncorrectas">
    private void dadasTodasLasDistanciasIncorrectas() {
        distanciasSatelites = getTodasLasDistanciasIncorrectas();
    }

    private void entoncesPresentaExceptionSinIntercepcionSenal() {
        assertNotNull(distanciasSatelites);
        assertNull(posicion);
        assertNotNull(exception);
        assertTrue(distanciasSatelites.size() >= 3);
        assertEquals(PosicionExceptionType.ERROR_NO_HAY_PUNTOS_INTERSEPCION_SENAL, exception.getType());
    }
    //</editor-fold>

    @Test
    void calcularPosicionConUnaIncorrecta(){
        dadasDistanciasConUnaIncorrecta();
        cuandoCalculaPosicion();
        entoncesPresentaExceptionNoSePuedeCompletarTriangulacion();
    }

    //<editor-fold desc="Método de calcularPosicionConUnaIncorrecta">
    private void dadasDistanciasConUnaIncorrecta() {
        distanciasSatelites = getDistanciasConUnaIncorrectas();
    }

    private void entoncesPresentaExceptionNoSePuedeCompletarTriangulacion() {
        assertNotNull(distanciasSatelites);
        assertNull(posicion);
        assertNotNull(exception);
        assertTrue(distanciasSatelites.size() >= 3);
        assertEquals(PosicionExceptionType.ERROR_NO_SE_COMPLETA_TRIANGULACION, exception.getType());
    }
    //</editor-fold>

    @Test
    void calcularPosicionConDistanciasCorrectas(){
        dadosDistanciasCorrectas();
        cuandoCalculaPosicion();
        entoncesPosicionCalculadaCorrectamente();
    }

    //<editor-fold desc="Métodos de calcularPosicionConDistanciasCorrectas">
    private void dadosDistanciasCorrectas() {
        distanciasSatelites = getDistanciasConTriangulacionCorrecta();
    }

    private void entoncesPosicionCalculadaCorrectamente() {
        assertNotNull(distanciasSatelites);
        assertNotNull(posicion);
        assertNull(exception);
        log.info(posicion.getX() + " - " + posicion.getY());
    }
    //</editor-fold>

    private void cuandoCalculaPosicion() {
        try{
            posicion = posicionBoundary.calcularPosicion(distanciasSatelites);
        } catch (PosicionException e){
            log.error(e);
            exception = e;
        }
    }

}
