package com.afl.micvback.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.micvback.models.dao.ICursoDao;
import com.afl.micvback.models.entity.Curso;

@Service

public class CursoServiceImpl implements ICursoService{
    @Autowired ICursoDao cursoDao;
    
    @Transactional(readOnly = true)
    @Override
	public List<Curso> findAll() {
		return  (List<Curso>)cursoDao.findAll();
	}
    
	@Override
    @Transactional(readOnly = true)
	public Page<Curso> findAll(Pageable pageable) {
		return cursoDao.findAll(pageable);
	}
	
	@Override
    @Transactional(readOnly=true)
	public List<Curso> findByNombre(String term) {
		return cursoDao.findByNombreContainingIgnoreCase(term);
	}

}
