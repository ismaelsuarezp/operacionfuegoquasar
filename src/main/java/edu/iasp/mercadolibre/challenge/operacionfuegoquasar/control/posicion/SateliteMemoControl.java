package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.posicion;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.SateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("SateliteMemoControl")
public class SateliteMemoControl implements ISateliteControl {

    @Override
    public Map<String, Coordenada> getSatelites() {
        return List.of(getSateliteKenobi(), getSateliteSkywalker(), getSateliteSato())
                .stream()
                .collect(Collectors.toMap(SateliteDto::getNombre, SateliteDto::getCoordenada));
    }

    private SateliteDto getSateliteKenobi() {
        return SateliteDto.builder()
                .nombre("KENOBI")
                .coordenada(Coordenada.builder().x(-500.0).y(-200.0).build())
                .build();
    }

    private SateliteDto getSateliteSkywalker() {
        return SateliteDto.builder()
                .nombre("SKYWALKER")
                .coordenada(Coordenada.builder().x(100.0).y(-100.0).build())
                .build();
    }

    private SateliteDto getSateliteSato() {
        return SateliteDto.builder()
                .nombre("SATO")
                .coordenada(Coordenada.builder().x(500.0).y(100).build())
                .build();
    }
}
