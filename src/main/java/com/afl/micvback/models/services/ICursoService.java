package com.afl.micvback.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.afl.micvback.models.entity.Curso;

public interface ICursoService {
	
   public List<Curso> findAll();
   
   public Page<Curso> findAll(Pageable pageable);
   
   public List<Curso> findByNombre(String term);
   
}
