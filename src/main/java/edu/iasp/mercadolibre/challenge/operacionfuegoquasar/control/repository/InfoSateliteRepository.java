package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.control.repository;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.InfoSatelite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InfoSateliteRepository extends JpaRepository<InfoSatelite, Long> {

    List<InfoSatelite> findAllByOrderByFechaRegistroDesc(Pageable pageable);

}
