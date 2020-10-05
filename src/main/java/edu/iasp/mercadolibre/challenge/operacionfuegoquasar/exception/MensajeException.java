package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public class MensajeException extends Exception implements IException {

    private final MensajeExceptionType type;

    public MensajeException(MensajeExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public MensajeException(MensajeExceptionType type, Throwable cause) {
        super(type.getMessage(),cause);
        this.type = type;
    }
}
