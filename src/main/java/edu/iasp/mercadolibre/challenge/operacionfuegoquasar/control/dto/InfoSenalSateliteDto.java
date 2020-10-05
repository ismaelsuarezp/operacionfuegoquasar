package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class InfoSenalSateliteDto {

    private String name;
    private double distance;
    private String[] message;
}
