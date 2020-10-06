package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite.RegistroInfoSateliteControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.repository.InfoSateliteRepository;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteExceptionType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Log4j2
@ExtendWith(MockitoExtension.class)
class RegistroInfoSateliteControlTest {

    @Mock
    private ConversionService conversionService;
    @Mock
    private static InfoSateliteRepository infoSateliteRepository;
    @InjectMocks
    private static RegistroInfoSateliteControl infoSateliteControl;

    private InfoSenalSateliteDto infoSenalSateliteDto;
    private InfoSateliteException exception;

    @BeforeAll
    static void init(){
    }

    @BeforeEach
    void initTests(){
        infoSenalSateliteDto = null;
        exception = null;
    }

    @Test
    void registrarInformacionSateliteNula() {
        dadaInformacionSenalSateliteNula();
        cunadoRegistraInformacionSatelite();
        entoncesInfoSateliteExceptionDatosInconsistentes();
    }

    //<editor-fold desc="Métodos de registrarInformacionSateliteNula" defaultstate="collapsed">
    private void dadaInformacionSenalSateliteNula() {
        infoSenalSateliteDto = null;
    }

    private void entoncesInfoSateliteExceptionDatosInconsistentes() {
        assertNull(infoSenalSateliteDto);
        assertNotNull(exception);
        assertEquals(InfoSateliteExceptionType.ERROR_REGISTRO_INFO_MINIMA_NECESARIA, exception.getType());
    }
    //</editor-fold>

    @Test
    void registrarInformacionSateliteSinMinimaInformacion() {
        dadaInformacionSateliteSinMinimaInformacion();
        cunadoRegistraInformacionSatelite();
        entoncesInfoSateliteExceptionSinMinimaInformacion();
    }

    //<editor-fold desc="Metodos de registrarInformacionSateliteSinMinimaInformacion" defaultstate="collapsed">
    private void dadaInformacionSateliteSinMinimaInformacion() {
        infoSenalSateliteDto = InfoSenalSateliteDto.builder().build();
    }

    private void entoncesInfoSateliteExceptionSinMinimaInformacion() {
        assertNotNull(infoSenalSateliteDto);
        assertNotNull(exception);
        assertEquals(InfoSateliteExceptionType.ERROR_REGISTRO_INFO_MINIMA_NECESARIA, exception.getType());
    }
    //</editor-fold>

    @Test
    void desifrarInfoSenalSateliteConMinimaInformacion() {
        dadoInformacionSateliteConMinimaInformacion();
        cunadoRegistraInformacionSatelite();
        entoncesRegistraInformacionSatelite();
    }

    //<editor-fold desc="Métodos de desifrarInfoSenalSateliteConMinimaInformacion" defaultstate="collapsed">
    private void dadoInformacionSateliteConMinimaInformacion() {
        infoSenalSateliteDto = InfoSenalSateliteDto.builder().name("SATELITE_1").build();
        InfoSatelite infoSateliteMock = InfoSatelite.builder().nombreSatelite("SATELITE_1").build();
        when(conversionService.convert(infoSenalSateliteDto, InfoSatelite.class)).thenReturn(infoSateliteMock);
        when(infoSateliteRepository.save(infoSateliteMock)).thenReturn(infoSateliteMock);
    }

    private void entoncesRegistraInformacionSatelite() {
        assertNotNull(infoSenalSateliteDto);
        assertNull(exception);
        verify(conversionService,times(1)).convert(infoSenalSateliteDto,InfoSatelite.class);
        verify(infoSateliteRepository,times(1)).save(any());
    }
    //</editor-fold>

    private void cunadoRegistraInformacionSatelite() {
        try {
            infoSateliteControl.registrarInformacioSenalSatelite(infoSenalSateliteDto);
        } catch (InfoSateliteException e) {
            log.error(e);
            exception = e;
        }
    }

}
