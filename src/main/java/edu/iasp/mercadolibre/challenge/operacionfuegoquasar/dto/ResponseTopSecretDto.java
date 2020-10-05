package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ResponseTopSecretDto {

    private Coordenada position;
    private String message;

}
