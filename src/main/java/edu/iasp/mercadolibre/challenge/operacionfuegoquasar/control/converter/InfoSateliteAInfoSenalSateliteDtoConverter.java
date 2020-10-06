package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.converter;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InfoSateliteAInfoSenalSateliteDtoConverter implements Converter<InfoSatelite, InfoSenalSateliteDto> {
    @Override
    public InfoSenalSateliteDto convert(InfoSatelite infoSatelite) {
        return InfoSenalSateliteDto.builder()
                .name(infoSatelite.getNombreSatelite())
                .distance(infoSatelite.getDistancia())
                .message(infoSatelite.getMensaje().toArray(String[]::new))
                .build();
    }
}
