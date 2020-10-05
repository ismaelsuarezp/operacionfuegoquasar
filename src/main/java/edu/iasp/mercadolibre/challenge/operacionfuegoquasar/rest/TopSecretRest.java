package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.rest;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary.IMensajeBoundary;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary.IPosicionBoundary;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.RequestInfoTopSecret;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.dto.ResponseTopSecretDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/topsecret")
public class TopSecretRest {

    @Autowired
    private IPosicionBoundary posicionBoundary;
    @Autowired
    private IMensajeBoundary mensajeBoundary;

    @PostMapping("/")
    public ResponseEntity defigrarPosicionNave(@RequestBody RequestInfoTopSecret info) {
        List<DistanciaSateliteDto> distanciaSateliteDtos = convertirInfoADistanciasSatelites(info);
        StringBuilder mensaje = new StringBuilder();
        try {
            Coordenada posicionNave = posicionBoundary.calcularPosicion(distanciaSateliteDtos);
            mensaje.append(mensajeBoundary.desifrar(getTodosLosMensajes(info)));
            ResponseTopSecretDto response = ResponseTopSecretDto.builder()
                    .position(posicionNave)
                    .message(mensaje.toString())
                    .build();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (PosicionException e) {
            log.error(e);
            mensaje.append(e.getType().getMessage());
        } catch (MensajeException e) {
            log.error(e);
            mensaje.append(e.getType().getMessage());
        }
        return new ResponseEntity<>(mensaje.toString(), HttpStatus.NOT_FOUND);
    }

    private String[][] getTodosLosMensajes(RequestInfoTopSecret info) {
        return Arrays.stream(info.getSatellites())
                .map(InfoSenalSateliteDto::getMessage)
                .toArray(String[][]::new);
    }

    private List<DistanciaSateliteDto> convertirInfoADistanciasSatelites(RequestInfoTopSecret info) {
        return Arrays.stream(info.getSatellites())
                .map(i -> DistanciaSateliteDto.builder()
                        .nombre(i.getName().toUpperCase())
                        .distancia(i.getDistance())
                        .build())
                .collect(Collectors.toList());
    }
}
