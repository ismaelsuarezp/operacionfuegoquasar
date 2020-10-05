package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public class PosicionException extends Exception implements IException{

    private final PosicionExceptionType type;

    public PosicionException(PosicionExceptionType type) {
        super(type.getMessage());
        this.type = type;
    }

    public PosicionException(Throwable cause, PosicionExceptionType type) {
        super(type.getMessage(), cause);
        this.type = type;
    }
}
