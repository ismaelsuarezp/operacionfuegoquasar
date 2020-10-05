package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Circunferencia {
    private Coordenada centro;
    private double radio;
}
