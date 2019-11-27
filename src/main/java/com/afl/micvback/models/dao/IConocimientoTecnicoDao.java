package com.afl.micvback.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.afl.micvback.models.entity.ConocimientoTecnico;

public interface IConocimientoTecnicoDao extends JpaRepository<ConocimientoTecnico, Long> {

    public List<ConocimientoTecnico> findByNombreContainingIgnoreCaseOrTipoOrComentarioContainingIgnoreCase(String searchTerm, String searcTerm, String searhTerm);
    
	public List<ConocimientoTecnico> findByNombreContainingIgnoreCase(String term);
	
	// public List<ConocimientoTecnico> findAll();
}
