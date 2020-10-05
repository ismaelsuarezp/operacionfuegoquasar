package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.ISateliteControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Circunferencia;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.CircunferenciaUtils;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.SateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PosicionBoundary implements IPosicionBoundary {

    @Value("${operacionfuegoquasar.posicion.distancias.cantidadMinimaParaCalcular}")
    private int cantidadDistanciasMinimasParaCalcular;

    @Autowired
    private ISateliteControl sateliteControl;

    @Override
    public Coordenada calcularPosicion(List<DistanciaSateliteDto> distancias) throws PosicionException {
        Map<String, Coordenada> satelites = sateliteControl.getSatelites();
        validarDatosDistancias(distancias, satelites.keySet());
        List<Coordenada> puntosIntercpcionIniciales = puntosInterseccionSenalesPrimerosDosSatelites(distancias, satelites);
        validarPuntosIntercpcionIniciales(puntosIntercpcionIniciales);
        Coordenada hubicacionNave = puntosIntercpcionIniciales.stream()
                .filter(pi -> completaTriangulacion(pi, distancias.get(2), satelites))
                .findFirst()
                .orElse(null);
        if(Objects.isNull(hubicacionNave)){
            throw new PosicionException(PosicionExceptionType.ERROR_NO_SE_COMPLETA_TRIANGULACION);
        }
        return hubicacionNave;
    }

    private boolean completaTriangulacion(Coordenada puntosAEvaluar, DistanciaSateliteDto distanciaSatelite3, Map<String, Coordenada> satelites) {
        Circunferencia circunferenciaSatelite3 = crearCircunferanciaSatelite(distanciaSatelite3, satelites);
        return CircunferenciaUtils.puntoEnCircunferencia(puntosAEvaluar, circunferenciaSatelite3);
    }

    private List<Coordenada> puntosInterseccionSenalesPrimerosDosSatelites(
            List<DistanciaSateliteDto> distancias, Map<String, Coordenada> satelites) {
        Circunferencia circunferenciaSatelite1 = crearCircunferanciaSatelite(distancias.get(0), satelites);
        Circunferencia circunferenciaSatelite2 = crearCircunferanciaSatelite(distancias.get(1), satelites);
        return CircunferenciaUtils.calcularPuntosIntercepcion(circunferenciaSatelite1, circunferenciaSatelite2);
    }

    private Circunferencia crearCircunferanciaSatelite(DistanciaSateliteDto distanciaSateliteDto, Map<String, Coordenada> satelites) {
        return Circunferencia.builder()
                .centro(satelites.get(distanciaSateliteDto.getNombre()))
                .radio(distanciaSateliteDto.getDistancia())
                .build();
    }

    private void validarPuntosIntercpcionIniciales(List<Coordenada> puntosIntercpcionIniciales) throws PosicionException {
        if (puntosIntercpcionIniciales.isEmpty()) {
            throw new PosicionException(PosicionExceptionType.ERROR_NO_HAY_PUNTOS_INTERSEPCION_SENAL);
        }
    }

    private void validarDatosDistancias(List<DistanciaSateliteDto> distancias, final Set<String> nombresSatelites) throws PosicionException {
        if (Objects.isNull(distancias) || distancias.size() < cantidadDistanciasMinimasParaCalcular) {
            throw new PosicionException(PosicionExceptionType.ERROR_DISTANCIAS_MINIMAS);
        }
        if (!distancias.stream().allMatch(d -> datosDistaciaValidos(d, nombresSatelites))) {
            throw new PosicionException(PosicionExceptionType.ERROR_DATOS_DISTANCIAS_INVALIDOS);
        }
    }

    private boolean datosDistaciaValidos(final DistanciaSateliteDto distancia, final Set<String> nombresSatelites) {
        return Objects.nonNull(distancia)
                && !StringUtils.isEmpty(distancia.getNombre())
                && nombresSatelites.contains(distancia.getNombre().toUpperCase())
                && distancia.getDistancia() >= 0;

    }
}
