package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public enum InfoSateliteExceptionType implements IExceptionType {
    ERROR_REGISTRO_INFO_MINIMA_NECESARIA(1, "Error: La información para registrar no tiene lo mínimo necesario."),
    ERROR_CONVERSION_INFO_SATELITE(2, "Error interno: No se pudo hacer la conversión de la información del satélite."),
    ERROR_INFORMACION_REGITRADA_INCOMPLETA(3, "Error: La información registrada no está completa para poder calcular la hubicación de la nave."),
    ERROR_INESPERADO(4, "Error interno: Se ha presentado un error inesperado al consultar información de los satélites.");
    private int code;
    private String message;

    InfoSateliteExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
