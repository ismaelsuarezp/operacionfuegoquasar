package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public enum PosicionExceptionType {
    ERROR_DISTANCIAS_MINIMAS(1, "Error: No se cumplen las distancias mínimas para calcular la posición."),
    ERROR_DATOS_DISTANCIAS_INVALIDOS(2,"Error: La información de la distancia son incorrectas. Valide los nombres y su respectiva distancia."),
    ERROR_NO_HAY_PUNTOS_INTERSEPCION_SENAL(3, "Error: No hay puntos de intercepción para la información de las señales."),
    ERROR_NO_SE_COMPLETA_TRIANGULACION(4, "Error: No se puede completar la triangulación.");
    private int code;
    private String message;

    PosicionExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
