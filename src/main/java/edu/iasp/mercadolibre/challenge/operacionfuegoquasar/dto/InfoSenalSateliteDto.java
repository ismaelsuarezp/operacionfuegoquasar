package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoSenalSateliteDto {

    private String name;
    private double distance;
    private String[] message;
}
