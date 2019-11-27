package com.afl.micvback.models.entity;
import lombok.*;
import java.util.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "conocimientostecnicos")

public class ConocimientoTecnico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String nombre;
    private String tipo;
    private String nivel;
    @Column(columnDefinition="MEDIUMTEXT")
    private String comentario;

    public ConocimientoTecnico(){}
}

