package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SateliteDto {

    private String nombre;
    private Coordenada coordenada;
}
