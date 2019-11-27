package com.afl.micvback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afl.micvback.models.entity.ConocimientoTecnico;
import com.afl.micvback.models.entity.Curso;
import com.afl.micvback.models.services.IConocimientoTecnicoService;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class ConocimientoTecnicoController {

	List<ConocimientoTecnico> conocimientos; 
    @Autowired private IConocimientoTecnicoService conocimientoTecnicoService;

    @RequestMapping(value = "/conocimientostecnicos", method = RequestMethod.GET)
    public Page<ConocimientoTecnico> index(
        @RequestParam(value = "page"  ,  defaultValue="0"   ,  required = false) Integer page,
        @RequestParam(value = "size"  ,  defaultValue="12"  ,  required = false) Integer size,
        @RequestParam(value = "tipo"  , required = false) String tipo) {
    	ConocimientoTecnico qry = new ConocimientoTecnico();
        if (tipo != null) 
        	if (tipo.equals("TODOS"))
        		tipo = null; 
        qry.setTipo(tipo);
        return  conocimientoTecnicoService.findAll(Example.of(qry), PageRequest.of(page, size));
    }
    
    @RequestMapping(value = "/conocimientotecnico-filtro", method = RequestMethod.GET)
    public List<ConocimientoTecnico> index(@RequestParam(value = "strBusca", required = true) String term) {  
        conocimientos = conocimientoTecnicoService.findByNombre(term);
        System.out.println(conocimientos.toString());
    	return conocimientos;
    }
    
}