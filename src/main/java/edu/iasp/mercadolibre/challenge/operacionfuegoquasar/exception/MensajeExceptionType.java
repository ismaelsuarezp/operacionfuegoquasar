package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public enum MensajeExceptionType {
    MSJS_INCONSISTENTES(1, "Error: La información de los mensajes es inconsistente."),
    MSJS_TODAS_LAS_PALABRAS_SON_ESPACIOS(2, "Error: Todas las palabras son espacios."),
    MSJS_PALABRAS_INIDICE_NO_COINCIDEN(3, "Error: Las palabras del indice %i no coinciden.");

    private int code;
    private String message;

    MensajeExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
