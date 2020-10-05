package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.mensaje;


import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;

public interface IMensajeControl {

     String desifrar(String[]... mensajes) throws MensajeException;
}
