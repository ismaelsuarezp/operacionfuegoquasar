package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DistanciaSateliteDto {
    private String nombre;
    private double distancia;
}
