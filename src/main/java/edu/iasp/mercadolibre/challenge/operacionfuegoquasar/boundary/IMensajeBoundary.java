package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;


import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;

public interface IMensajeBoundary {

     public String desifrar(String[]... mensajes) throws MensajeException;
}
