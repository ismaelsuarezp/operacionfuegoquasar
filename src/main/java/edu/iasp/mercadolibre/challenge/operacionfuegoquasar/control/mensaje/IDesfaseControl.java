package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.mensaje;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;

import java.util.List;

public interface IDesfaseControl {

     List<String[]> quitarDesfase(String[]... mensajes) throws DesfaseException;
}
