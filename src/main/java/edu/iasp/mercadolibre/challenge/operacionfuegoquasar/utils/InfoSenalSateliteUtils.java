package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InfoSenalSateliteUtils {

    private InfoSenalSateliteUtils() {

    }

    public static List<DistanciaSateliteDto> getDistaanciasSatelitesDe(InfoSenalSateliteDto[] infoSenalSatelites){
        return Arrays.stream(infoSenalSatelites)
                .map(i -> DistanciaSateliteDto.builder()
                        .nombre(i.getName().toUpperCase())
                        .distancia(i.getDistance())
                        .build())
                .collect(Collectors.toList());
    }

    public static String[][] getTodosLosMensajes(InfoSenalSateliteDto[] infoSenalSatelites){
        return Arrays.stream(infoSenalSatelites)
                .map(InfoSenalSateliteDto::getMessage)
                .toArray(String[][]::new);
    }
}
