package com.afl.micvback.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.afl.micvback.models.entity.Experiencia;

public interface IExperienciaService {
	
	public List<Experiencia> findAll();
	public Page<Experiencia> findAll(Pageable page);
	
	public Experiencia save (Experiencia experiencia);
	
	public Experiencia findById(Long id);
	
	public Experiencia update (Experiencia experiencia, Long id);
	
	public List<Experiencia> findByNombre (String term);
}
