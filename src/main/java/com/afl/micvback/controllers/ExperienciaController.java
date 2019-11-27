package com.afl.micvback.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afl.micvback.models.entity.ConocimientoTecnico;
import com.afl.micvback.models.entity.Experiencia;
import com.afl.micvback.models.services.IExperienciaService;
import com.afl.micvback.util.pdf.UtilPdf;

//@CrossOrigin(origins = { "http://localhost:4200", "*" })
@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ExperienciaController {
	
	@Autowired
	private IExperienciaService experienciaService;

	@RequestMapping(value = "/experiencia", method = RequestMethod.GET)
	public Page<Experiencia> index(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "12", required = false) Integer size) {

		return experienciaService.findAll(PageRequest.of(page, size));
	}
	
	@RequestMapping(value = "/experiencia", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@Valid @RequestBody Experiencia experiencia, BindingResult result,
			@RequestParam(value = "id", required = true) Long id) {

		Experiencia experienciaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
				.map(fielderror -> "El campo '"+ fielderror.getField() + "' " + fielderror.getDefaultMessage())
				.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}


		try {
			experienciaUpdated = experienciaService.update(experiencia, id);
			 
		} catch (DataAccessException e) {
			response.put("mensaje", "error al actualizar el cliente con id=".concat(id.toString()));
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "sin error al actualizar el cliente con id=".concat(id.toString()));
		response.put("experiencia", experienciaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
    @RequestMapping(value = "/experiencia-filtro", method = RequestMethod.GET)
    public List<Experiencia> index(@RequestParam(value = "strBusca", required = true) String term) {  
        return experienciaService.findByNombre(term);
    }
}
