package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;

import java.util.List;

public interface IDesfaseBoundary {

     List<String[]> quitarDesfase(String[]... mensajes) throws DesfaseException;
}
