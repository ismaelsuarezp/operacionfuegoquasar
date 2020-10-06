package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.converter;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InfoSenalSateliteDtoAInfoSateliteConverter implements Converter<InfoSenalSateliteDto, InfoSatelite> {
    @Override
    public InfoSatelite convert(InfoSenalSateliteDto infoSatelite) {
        return InfoSatelite.builder()
                .nombreSatelite(infoSatelite.getName())
                .distancia(infoSatelite.getDistance())
                .mensaje(Arrays.asList(infoSatelite.getMessage()))
                .build();
    }
}
