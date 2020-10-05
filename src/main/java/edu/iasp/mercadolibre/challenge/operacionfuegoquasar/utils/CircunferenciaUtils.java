package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils;

import java.util.ArrayList;
import java.util.List;

import static edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.TrianguloRectanguloUtils.calcularCateto;
import static edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.TrianguloRectanguloUtils.calcularHipotenusa;

public class CircunferenciaUtils {

    private static final double DESVIACION_ACEPTABLE = 0.0001;

    private CircunferenciaUtils() {

    }

    public static List<Coordenada> calcularPuntosIntercepcion(Circunferencia c1, Circunferencia c2) {
        List<Coordenada> puntosInterseccion = new ArrayList<>();
        double distancia = distanciaEntrePuntos(c1.getCentro(), c2.getCentro());
        if (tienenPuntosDeInterseccion(c1.getRadio(), c2.getRadio(), distancia)) {
            puntosInterseccion = calcularPuntosIntercepcion(c1, c2, distancia);
        }
        return puntosInterseccion;
    }

    private static List<Coordenada> calcularPuntosIntercepcion(Circunferencia c1, Circunferencia c2, double distancia) {
        double distanciaX = c2.getCentro().getX() - c1.getCentro().getX();
        double distanciaY = c2.getCentro().getY() - c1.getCentro().getY();
        double distanciaSecanteC1 = calcularDistanciaRectaSecanteC1(c1.getRadio(), c2.getRadio(), distancia);

        double distanciaPuntoSecanteAPuntoIntercepcion = calcularCateto(c1.getRadio(), distanciaSecanteC1);

        double puntoSecanteX = c1.getCentro().getX() + (distanciaX * distanciaSecanteC1 / distancia);
        double puntoSecanteY = c1.getCentro().getY() + (distanciaY * distanciaSecanteC1 / distancia);

        double raizDistanciIntersecionX = -distanciaY * distanciaPuntoSecanteAPuntoIntercepcion / distancia;
        double raizDistanciIntersecionY = distanciaX * distanciaPuntoSecanteAPuntoIntercepcion / distancia;

        return List.of(
                getPuntoInterseccion1(puntoSecanteX, puntoSecanteY, raizDistanciIntersecionX, raizDistanciIntersecionY),
                getPuntoInterseccion2(puntoSecanteX, puntoSecanteY, raizDistanciIntersecionX, raizDistanciIntersecionY)
        );
    }

    private static Coordenada getPuntoInterseccion1(double puntoSecanteX, double puntoSecanteY, double raizDistanciaIntersecionX, double raizDistanciIntersecionY) {
        return Coordenada.builder()
                .x(puntoSecanteX + raizDistanciaIntersecionX)
                .y(puntoSecanteY + raizDistanciIntersecionY)
                .build();
    }

    private static Coordenada getPuntoInterseccion2(double puntoSecanteX, double puntoSecanteY, double raizDistanciaIntersecionX, double raizDistanciIntersecionY) {
        return Coordenada.builder()
                .x(puntoSecanteX - raizDistanciaIntersecionX)
                .y(puntoSecanteY - raizDistanciIntersecionY)
                .build();
    }

    private static double calcularDistanciaRectaSecanteC1(double radioC1, double radioC2, double distanciaC1C2) {
        return ((radioC1 * radioC1) - (radioC2 * radioC2) + (distanciaC1C2 * distanciaC1C2)) / (2.0 * distanciaC1C2);
    }

    private static boolean tienenPuntosDeInterseccion(double radioC1, double radioC2, double distanciaC1C2) {
        return !sonCircunferenciasExteriores(radioC1, radioC2, distanciaC1C2)
                && !sonCircunferenciasInteriores(radioC1, radioC2, distanciaC1C2);
    }

    private static boolean sonCircunferenciasExteriores(double radioC1, double radioC2, double distanciaC1C2) {
        return distanciaC1C2 > (radioC1 + radioC2);
    }

    private static boolean sonCircunferenciasInteriores(double radioC1, double radioC2, double distanciaC1C2) {
        return (distanciaC1C2 > 0) && (distanciaC1C2 < Math.abs(radioC1 - radioC2));
    }

    private static double distanciaEntrePuntos(Coordenada p1, Coordenada p2) {
        double distanciaX = p2.getX() - p1.getX();
        double distanciaY = p2.getY() - p1.getY();
        return calcularHipotenusa(distanciaX, distanciaY);
    }


    public static boolean puntoEnCircunferencia(Coordenada p, Circunferencia c) {
        double distancia = distanciaEntrePuntos(p,c.getCentro());
        return Math.abs(distancia - c.getRadio()) < DESVIACION_ACEPTABLE;
    }
}
