package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Coordenada {
    private double x;
    private double y;
}
