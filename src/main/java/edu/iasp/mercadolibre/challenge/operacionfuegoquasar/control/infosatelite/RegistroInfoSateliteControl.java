package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.repository.InfoSateliteRepository;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RegistroInfoSateliteControl implements IRegistroInfoSateliteControl {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private InfoSateliteRepository infoSateliteRepository;

    @Override
    public void registrarInformacioSenalSatelite(InfoSenalSateliteDto infoSenalSateliteDto) throws InfoSateliteException {
        validaDatosMinimosParaRegistrar(infoSenalSateliteDto);
        InfoSatelite infoSatelite = conversionService.convert(infoSenalSateliteDto,InfoSatelite.class);
        validaPrePersist(infoSatelite);
        infoSateliteRepository.save(infoSatelite);
    }

    private void validaDatosMinimosParaRegistrar(InfoSenalSateliteDto infoSenalSateliteDto) throws InfoSateliteException {
        if (Objects.isNull(infoSenalSateliteDto) ||
                Objects.isNull(infoSenalSateliteDto.getName()) ||
                infoSenalSateliteDto.getName().isBlank()) {
            throw new InfoSateliteException(InfoSateliteExceptionType.ERROR_REGISTRO_INFO_MINIMA_NECESARIA);
        }
    }

    private void validaPrePersist(InfoSatelite infoSatelite) throws InfoSateliteException {
        if(Objects.isNull(infoSatelite)){
            throw new InfoSateliteException(InfoSateliteExceptionType.ERROR_CONVERSION_INFO_SATELITE);
        }
    }
}
