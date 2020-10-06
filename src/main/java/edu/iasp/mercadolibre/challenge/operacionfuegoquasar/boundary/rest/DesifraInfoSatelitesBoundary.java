package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.boundary.rest;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.DistanciaSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.ResponseTopSecretDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.mensaje.IMensajeControl;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.posicion.IPosicionCountrol;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.MensajeException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.PosicionException;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.Coordenada;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils.InfoSenalSateliteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesifraInfoSatelitesBoundary {

    @Autowired
    private IPosicionCountrol posicionBoundary;
    @Autowired
    private IMensajeControl mensajeBoundary;

    public ResponseTopSecretDto desifrar(InfoSenalSateliteDto[] infoSenalSatelites) throws PosicionException, MensajeException {
        List<DistanciaSateliteDto> distanciaSateliteDtos = InfoSenalSateliteUtils.getDistaanciasSatelitesDe(infoSenalSatelites);

        Coordenada posicionNave = posicionBoundary.calcularPosicion(distanciaSateliteDtos);
        String mensajeDesifrado = mensajeBoundary.desifrar(InfoSenalSateliteUtils.getTodosLosMensajes(infoSenalSatelites));
        return ResponseTopSecretDto.builder()
                .position(posicionNave)
                .message(mensajeDesifrado)
                .build();

    }

}
