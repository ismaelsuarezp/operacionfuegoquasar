package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.mensaje;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.DesfaseException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeControl implements IMensajeControl {
    
    @Value("${operacionfuegoquasar.mensaje.palabra.vacia}")
    private String palabraVacia;

    @Autowired
    private IDesfaseControl desfaseBoundary;

    @Override
    public String desifrar(String[]... mensajes) throws MensajeException {
        try {
            List<String[]> mensajesSinDesfase = desfaseBoundary.quitarDesfase(mensajes);
            return desifrarMensaje(mensajesSinDesfase);
        } catch (DesfaseException desfaseException) {
            throw new MensajeException(MensajeExceptionType.MSJS_INCONSISTENTES, desfaseException);
        }
    }

    private String desifrarMensaje(List<String[]> mensajes) throws MensajeException {
        int cantidadPalabras = mensajes.get(0).length;
        StringBuilder mensajeBuilder = new StringBuilder();
        for (int i = 0; i < cantidadPalabras; i++) {
            boolean esPosibleEspacios = esPosibleQueTadasLasPalabrasSeanEspacios(i, cantidadPalabras);
            List<String> palabrasInidice = getPalabrasPorInidice(i, mensajes);
            mensajeBuilder.append(palabraVacia)
                    .append(getPalabra(esPosibleEspacios, palabrasInidice));
        }
        return mensajeBuilder.toString().trim();
    }

    private List<String> getPalabrasPorInidice(int inidice, List<String[]> mensajes) {
        return mensajes.stream().map(m -> m[inidice]).collect(Collectors.toList());
    }

    private String getPalabra(boolean esPosibleEspacios, List<String> palabras) throws MensajeException {
        List<String> palabrasReales = getPalabrasReales(palabras);
        if (palabrasReales.isEmpty() && !esPosibleEspacios) {
            throw new MensajeException(MensajeExceptionType.MSJS_TODAS_LAS_PALABRAS_SON_ESPACIOS);
        } else if (palabrasReales.isEmpty()) {
            return "";
        }
        final String primeraPalabra = palabrasReales.get(0);
        if (palabrasReales.stream().anyMatch(p -> !p.equals(primeraPalabra))) {
            throw new MensajeException(MensajeExceptionType.MSJS_PALABRAS_INIDICE_NO_COINCIDEN);
        }
        return primeraPalabra;
    }

    private boolean esPosibleQueTadasLasPalabrasSeanEspacios(int indicePalabra, int cantidadPalabras) {
        return (indicePalabra == 0 || indicePalabra == cantidadPalabras - 1);
    }

    private List<String> getPalabrasReales(List<String> palabras) {
        return palabras.stream().filter(p -> !p.equals(palabraVacia)).collect(Collectors.toList());
    }
}
