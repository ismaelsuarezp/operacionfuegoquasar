package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity;

import edu.iasp.mercadolibre.challenge.operacionfuegoquasar.entity.converter.ListStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "tbl_informacion_satelite")
public class InfoSatelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_satelite", nullable = false)
    private String nombreSatelite;
    @Column(name = "distancia")
    private double distancia;
    @Column(name = "mensaje")
    @Convert(converter = ListStringConverter.class)
    private List<String> mensaje;
    @Column(name = "fecha_registro", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @PrePersist
    public void prePersist(){
        fechaRegistro = new Date();
    }

}
