package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.posicion;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;

import java.util.List;

public interface IPosicionCountrol {

     Coordenada calcularPosicion(List<DistanciaSateliteDto> distancias) throws PosicionException;
}
