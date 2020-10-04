package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;


import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseExceptionType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DesfaseBoundary implements IDesfaseBoundary {

    public List<String[]> quitarDesfase(String[]... mensajes) throws DesfaseException {
        validaMensajes(mensajes);
        int tamanioMensajeMasLargo = getTamanioMensajeMasLargo(mensajes);
        validaMaximoTamanioMensaje(tamanioMensajeMasLargo);
        return getMensajesSinDesfase(tamanioMensajeMasLargo, mensajes);
    }

    private List<String[]> getMensajesSinDesfase(int tamanioMensajeMasLargo, String[]... mensajes) throws DesfaseException {
        for (int i = 0; i < mensajes.length; i++) {
            String[] pibote = mensajes[i];
            if (pibote.length == tamanioMensajeMasLargo) {
                for (int j = 0; j < mensajes.length; j++) {
                    mensajes[j] = igualarTamaniosMensajes(pibote, mensajes[j]);
                }
            }
        }
        validaTamanioMensajesIguales(tamanioMensajeMasLargo, mensajes);
        return Arrays.asList(mensajes);
    }

    /**
     * @param msjRefetencia Mensaje con el tamaño correcto, para igualar el otro mensaje
     * @param msjDesfasado  Mensaje que se quiere igualar.
     * @return Devuelve el mensaje sin desfase, y en los espacios que le hacían falta les coloca un espacio. En caso de no poder quitar el desfase retorna el mensaje sin desfasar.
     */
    private String[] igualarTamaniosMensajes(String[] msjRefetencia, String[] msjDesfasado) {
        if (msjRefetencia.length == msjDesfasado.length) {
            return msjDesfasado;
        }
        for (int i = 0; i < msjDesfasado.length; i++) {
            String palabraABuscar = msjDesfasado[i];
            if (!Objects.equals(palabraABuscar, " ")) {
                int indiceReferencia = Arrays.asList(msjRefetencia).indexOf(palabraABuscar);
                if (hayDesfase(indiceReferencia, i)) {
                    return completarArray(msjDesfasado, esDesfaseAlInicio(i, indiceReferencia));
                }
            }
        }
        return msjDesfasado;
    }

    private int getTamanioMensajeMasLargo(String[]... mensajes) {
        int maxLength = -1;
        for (String[] mensaje : mensajes) {
            maxLength = Math.max(mensaje.length, maxLength);
        }
        return maxLength;
    }

    private String[] completarArray(String[] msjDesfasado, boolean desfaseAlInicio) {
        String[] newArray = new String[msjDesfasado.length + 1];
        int startIndexCopy = desfaseAlInicio ? 1 : 0;
        int spaceIndex = desfaseAlInicio ? 0 : msjDesfasado.length;
        newArray[spaceIndex] = " ";
        System.arraycopy(msjDesfasado, 0, newArray, startIndexCopy, msjDesfasado.length);
        return newArray;
    }

    private boolean esDesfaseAlInicio(int indiceDesfasado, int indiceReferencia) {
        return indiceReferencia > indiceDesfasado;
    }

    public static boolean hayDesfase(int indiceReferencia, int indiceDesfasado) {
        int totalDesfase = Math.abs(indiceReferencia - indiceDesfasado);
        return indiceReferencia >= 0 && (totalDesfase == 1 || totalDesfase == 0);
    }

    private void validaTamanioMensajesIguales(final int tamanioReferencia, final String[]... mensajes) throws DesfaseException {
        if (Arrays.stream(mensajes).anyMatch(msj -> msj.length != tamanioReferencia))
            throw new DesfaseException(DesfaseExceptionType.ERROR_QUITAR_DESFASE);
    }

    private void validaMensajes(String[]... mensajes) throws DesfaseException {
        if (Objects.isNull(mensajes) || mensajes.length == 0)
            throw new DesfaseException(DesfaseExceptionType.ERROR_DATA_INDONSISTENTE);
    }

    private void validaMaximoTamanioMensaje(int tamanioMensaje) throws DesfaseException {
        if (tamanioMensaje <= 0)
            throw new DesfaseException(DesfaseExceptionType.ERROR_QUITAR_DESFASE);
    }
}
