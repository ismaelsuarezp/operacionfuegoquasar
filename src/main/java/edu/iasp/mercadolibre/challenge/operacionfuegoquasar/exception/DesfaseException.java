package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public class DesfaseException extends Exception {

    private final DesfaseExceptionType type;

    public DesfaseException(DesfaseExceptionType type) {
        this.type = type;
    }

    public DesfaseException(DesfaseExceptionType type, Throwable cause) {
        super(cause);
        this.type = type;
    }
}
