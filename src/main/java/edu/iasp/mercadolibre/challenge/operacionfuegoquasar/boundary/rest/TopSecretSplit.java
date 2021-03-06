package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary.rest;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.ResponseTopSecretDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite.IConsultaInfoSateliteControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite.IRegistroInfoSateliteControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplit {

    @Autowired
    private IRegistroInfoSateliteControl registroInfoSateliteControl;
    @Autowired
    private IConsultaInfoSateliteControl consultaInfoSateliteControl;
    @Autowired
    private DesifraInfoSatelitesBoundary desifraInfoSatelitesBoundary;

    @PostMapping("/{nombreSatelite}")
    public ResponseEntity<String> registrarInformacionSatelite(
            @PathVariable(name = "nombreSatelite") String nombreSatelite,
            @RequestBody InfoSenalSateliteDto infoSenalSateliteDto){
        infoSenalSateliteDto.setName(nombreSatelite);
        try{
            registroInfoSateliteControl.registrarInformacioSenalSatelite(infoSenalSateliteDto);
            return new ResponseEntity<>("informarción guardada correctamente", HttpStatus.CREATED);
        } catch (InfoSateliteException e){
            return new ResponseEntity<>(e.getType().getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> consultarInfoSenalSatelite(){
        try {
            InfoSenalSateliteDto[] infoSenalSatelites = consultaInfoSateliteControl
                    .ultimasInformacionesDeSateliteParaTrinagularSenal();
            ResponseTopSecretDto responseDto = desifraInfoSatelitesBoundary.desifrar(infoSenalSatelites);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (PosicionException | MensajeException | InfoSateliteException e) {
            log.error(e);
            return new ResponseEntity<>(e.getType().getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
