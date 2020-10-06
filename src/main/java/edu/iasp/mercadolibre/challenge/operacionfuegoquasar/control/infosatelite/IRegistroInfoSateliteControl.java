package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.infosatelite;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.dto.InfoSenalSateliteDto;
import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.exception.InfoSateliteException;

public interface IRegistroInfoSateliteControl {

    void registrarInformacioSenalSatelite(InfoSenalSateliteDto infoSenalSateliteDto) throws InfoSateliteException;

}
