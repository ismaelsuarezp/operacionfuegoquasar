package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public enum DesfaseExceptionType {
    ERROR_DATA_INDONSISTENTE(1, "Error: data inconsistente debe haber más de un mensaje"),
    ERROR_QUITAR_DESFASE(2,"Error: no se puede quitar el desfase");
    private int code;
    private String message;

    DesfaseExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
