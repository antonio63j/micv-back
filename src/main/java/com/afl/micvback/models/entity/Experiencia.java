package com.afl.micvback.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name="experiencia")
public class Experiencia implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private String empresa;
    private String cliente;
	@Temporal(TemporalType.DATE)
	private Date inicio;
	@Temporal(TemporalType.DATE)
	private Date fin;
	private String sectorCliente;
    @Column(columnDefinition="MEDIUMTEXT")
    private String actividades;	
    @Column(columnDefinition="MEDIUMTEXT")
    private String experiencia;	
	
}
