package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;

import java.util.List;

public interface IPosicionBoundary {

     Coordenada calcularPosicion(List<DistanciaSateliteDto> distancias) throws PosicionException;
}
