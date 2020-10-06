package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public enum InfoSateliteExceptionType implements IExceptionType {
    ERROR_REGISTRO_INFO_MINIMA_NECESARIA(1, "Error: La información para registrar no tiene lo mínimo necesario."),
    ERROR_CONVERSION_INFO_SATELITE(1, "Error interno: No se pudo hacer la conversión de la información del satélite.");
    private int code;
    private String message;

    InfoSateliteExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
