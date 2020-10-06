package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.repository.InfoSateliteRepository;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultaInfoSateliteControl implements IConsultaInfoSateliteControl {

    @Value("${operacionfuegoquasar.posicion.distancias.cantidadMinimaParaCalcular}")
    private int cantidadDistanciasMinimasParaCalcular;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private InfoSateliteRepository infoSateliteRepository;

    @Override
    public InfoSenalSateliteDto[] ultimasInformacionesDeSateliteParaTrinagularSenal() throws InfoSateliteException {
        List<InfoSatelite> infoSatelites = consultarUltmasInformacionesSatelites();
        validaInfoSatelitesRegistradaCompletaParaTriangular(infoSatelites);
        return infoSatelites.stream()
                .map(is -> conversionService.convert(is, InfoSenalSateliteDto.class))
                .toArray(InfoSenalSateliteDto[]::new);
    }

    private List<InfoSatelite> consultarUltmasInformacionesSatelites() throws InfoSateliteException {
        try {
            Pageable pageable = construirPaginadoSegunCantidadMinima();
            return infoSateliteRepository.findAllByOrderByFechaRegistroDesc(pageable);
        } catch (Exception e) {
            throw new InfoSateliteException(InfoSateliteExceptionType.ERROR_INESPERADO, e);
        }
    }

    private Pageable construirPaginadoSegunCantidadMinima() {
        return PageRequest.of(0, cantidadDistanciasMinimasParaCalcular);
    }

    private void validaInfoSatelitesRegistradaCompletaParaTriangular(List<InfoSatelite> infoSatelites) throws InfoSateliteException {
        long cantidadNombresDistintos = getCantidadInfoSateliteDistintos(infoSatelites);
        if (cantidadNombresDistintos < (long) cantidadDistanciasMinimasParaCalcular) {
            throw new InfoSateliteException(InfoSateliteExceptionType.ERROR_INFORMACION_REGITRADA_INCOMPLETA);
        }
    }

    private long getCantidadInfoSateliteDistintos(List<InfoSatelite> infoSatelites) {
        return infoSatelites.stream()
                .map(InfoSatelite::getNombreSatelite)
                .distinct()
                .count();
    }
}
