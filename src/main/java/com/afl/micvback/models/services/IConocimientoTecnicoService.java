package com.afl.micvback.models.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.afl.micvback.models.entity.ConocimientoTecnico;
import com.afl.micvback.models.entity.Curso;

public interface IConocimientoTecnicoService {
	public List<ConocimientoTecnico> findAll ();
	public Page<ConocimientoTecnico> findAll(Pageable page);
	public Page<ConocimientoTecnico> findAll(Example<ConocimientoTecnico> example, Pageable pageable);
	
	public List<ConocimientoTecnico> findByNombre(String term);

}
