package com.afl.micvback.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.micvback.models.dao.IConocimientoTecnicoDao;
import com.afl.micvback.models.entity.ConocimientoTecnico;

@Service
public class ConocimientoTecnicoServiceImpl implements IConocimientoTecnicoService{

	@Autowired IConocimientoTecnicoDao conocimientoTecnicoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<ConocimientoTecnico> findAll() {
		return (List<ConocimientoTecnico>) conocimientoTecnicoDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<ConocimientoTecnico> findAll(Pageable pageable) {
		return conocimientoTecnicoDao.findAll(pageable);
	}

	@Override
	public Page<ConocimientoTecnico> findAll(Example<ConocimientoTecnico> example, Pageable pageable) {
		return conocimientoTecnicoDao.findAll(example, pageable);
	}

	@Override
	public List<ConocimientoTecnico> findByNombre(String term) {
		return conocimientoTecnicoDao.findByNombreContainingIgnoreCaseOrTipoOrComentarioContainingIgnoreCase(term, term, term);
	}
	
	

}
