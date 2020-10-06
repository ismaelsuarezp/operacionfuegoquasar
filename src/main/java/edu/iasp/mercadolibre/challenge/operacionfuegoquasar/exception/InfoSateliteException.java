package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception;

import lombok.Getter;

@Getter
public class InfoSateliteException extends Exception implements IException {

    private InfoSateliteExceptionType type;

    public InfoSateliteException(InfoSateliteExceptionType type) {
        this.type = type;
    }

    public InfoSateliteException(InfoSateliteExceptionType type, Throwable cause) {
        super(cause);
        this.type = type;
    }
}
