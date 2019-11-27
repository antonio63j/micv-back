package com.afl.micvback.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.afl.micvback.models.entity.Experiencia;

public interface IExperienciaDao extends JpaRepository <Experiencia, Long>{

    public List<Experiencia> findByActividadesContainingIgnoreCaseOrExperienciaContainingIgnoreCase(String searchTerm, String searcTerm);
}
