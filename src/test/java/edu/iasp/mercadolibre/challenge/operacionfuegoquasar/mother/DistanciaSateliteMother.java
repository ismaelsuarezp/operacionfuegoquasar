package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.mother;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.DistanciaSateliteDto;

import java.util.List;
import java.util.Objects;

public class DistanciaSateliteMother {

    public static List<DistanciaSateliteDto> getDistanciasConDatosIncorrectos(){
        return List.of(
                getDistanciaSateliteKenobi(-100.0),
                getDistanciaSateliteSkywalker(500.0),
                getDistanciaSatelite("NO_EXISTE",-100.0)
        );
    }

    public static List<DistanciaSateliteDto> getDistanciasConTriangulacionCorrecta(){
        return List.of(
                getDistanciaSateliteKenobi(500.0),
                getDistanciaSateliteSkywalker(500.0),
                getDistanciaSateliteSato(778.20940)
        );
    }

    public static List<DistanciaSateliteDto> getTodasLasDistanciasIncorrectas(){
        return List.of(
                getDistanciaSateliteKenobi(100.0),
                getDistanciaSateliteSkywalker(115.5),
                getDistanciaSateliteSato(142.7)
        );
    }

    public static List<DistanciaSateliteDto> getDistanciasConUnaIncorrectas(){
        return List.of(
                getDistanciaSateliteKenobi(500.0),
                getDistanciaSateliteSkywalker(500.0),
                getDistanciaSateliteSato(142.7)
        );
    }


    public static DistanciaSateliteDto getDistanciaSateliteSato(Double distancia){
        return getDistanciaSatelite("SATO", distancia);
    }

    public static DistanciaSateliteDto getDistanciaSateliteSkywalker(Double distancia){
        return getDistanciaSatelite("SKYWALKER", distancia);
    }

    public static DistanciaSateliteDto getDistanciaSateliteKenobi(Double distancia){
        return getDistanciaSatelite("KENOBI", distancia);
    }

    private static DistanciaSateliteDto getDistanciaSatelite(String nombre, Double distancia){
        return DistanciaSateliteDto.builder()
                .nombre(nombre)
                .distancia(Objects.isNull(distancia) ? Math.random()*1000 :distancia)
                .build();
    }
}
