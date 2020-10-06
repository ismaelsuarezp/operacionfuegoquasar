package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary.rest;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.RequestInfoTopSecretDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.ResponseTopSecretDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/topsecret")
class TopSecretRest {

    @Autowired
    private DesifraInfoSatelitesBoundary desifraInfoSatelitesBoundary;

    @PostMapping("/")
    public ResponseEntity<?> defigrarPosicionNave(@RequestBody RequestInfoTopSecretDto info) {
        try {
            ResponseTopSecretDto response = desifraInfoSatelitesBoundary.desifrar(info.getSatellites());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PosicionException | MensajeException e) {
            log.error(e);
            return new ResponseEntity<>(e.getType().getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
