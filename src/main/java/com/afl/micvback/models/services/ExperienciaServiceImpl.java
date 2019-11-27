package com.afl.micvback.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.afl.micvback.models.dao.IExperienciaDao;
import com.afl.micvback.models.entity.Experiencia;

@Service
public class ExperienciaServiceImpl implements IExperienciaService{

	@Autowired  
	private IExperienciaDao experienciaDao;

	@Transactional(readOnly = true)
	@Override
	public List<Experiencia> findAll() {
		return experienciaDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Page<Experiencia> findAll(Pageable pageable) {
		return experienciaDao.findAll(pageable);
	}
	
	@Transactional
	@Override
	public Experiencia findById(Long id) {
		return experienciaDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public Experiencia save (Experiencia experiencia) {
		return experienciaDao.save (experiencia);
	}
		
	@Transactional
	@Override
	public Experiencia update (Experiencia experiencia, Long id) {
		Experiencia experienciaActual;
		experienciaActual = experienciaDao.findById(id).orElse(null);
		experienciaActual.setEmpresa(experiencia.getEmpresa());
		experienciaActual.setCliente(experiencia.getCliente());
		experienciaActual.setInicio(experiencia.getInicio());
		experienciaActual.setFin(experiencia.getFin());
		experienciaActual.setActividades(experiencia.getActividades());
		experienciaActual.setExperiencia(experiencia.getExperiencia());
		return experienciaActual;
		
	}

	@Override
	public List<Experiencia> findByNombre(String term) {

		return experienciaDao.findByActividadesContainingIgnoreCaseOrExperienciaContainingIgnoreCase(term, term);
	}
	
}
