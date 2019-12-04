package com.afl.micvback.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afl.micvback.models.entity.Curso;
import com.afl.micvback.models.services.ICursoService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
//@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class CursoController {
	@Autowired
	private ICursoService cursoService;
	
	//List<Curso> miscursos;
	
	/*
	 * @GetMapping ("/cursos") private List<Curso> index() { return
	 * cursoService.findAll(); }
	 */
	
	/*
	 * @GetMapping("/cursos/page/{page}") public Page<Curso> index(@PathVariable
	 * Integer page) { return cursoService.findAll(PageRequest.of(page, 13)); }
	 */
	
    @RequestMapping(value = "/cursos", method = RequestMethod.GET)
    public Page<Curso> index(@RequestParam(value = "page", defaultValue="0", required = false) Integer page,
    						 @RequestParam(value = "size", defaultValue= "12", required = false) Integer size
    		) { 
      return cursoService.findAll(PageRequest.of(page, size));
    }
    
    @RequestMapping(value = "/cursos-filtro", method = RequestMethod.GET)
    public List<Curso> index(@RequestParam(value = "strBusca", required = true) String term) {  
       return cursoService.findByNombre(term);
    }

}