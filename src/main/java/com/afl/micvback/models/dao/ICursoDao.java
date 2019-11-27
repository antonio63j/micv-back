package com.afl.micvback.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afl.micvback.models.entity.Curso;

public interface ICursoDao extends JpaRepository<Curso, Long> {

	@Query("select c from Curso c where c.nombre like %?1%")
	public List<Curso> findByNombre(String term);
	
	public List<Curso> findByNombreContainingIgnoreCase(String term);
}
