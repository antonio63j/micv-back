package com.afl.micvback.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afl.micvback.util.services.DownloadFileService;
import com.afl.micvback.util.services.IDownloadFileService;

@RestController
@RequestMapping("/api")

public class DownloadFileController {

	private Logger log = LoggerFactory.getLogger(DownloadFileController.class);

	@Autowired 
	private IDownloadFileService downloadFileService;
	
	@RequestMapping(value = "download/pdf/{fileName:.+}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> download(@PathVariable("fileName") String fileName) throws IOException {
		System.out.println("Calling Download:- " + fileName);
		ClassPathResource pdfFile = new ClassPathResource(DownloadFileService.DIRECTORIO_DOWNLOAD + fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		//headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		headers.setContentLength(pdfFile.contentLength());
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
		return response;

	}
	
	public Path getPath(String path, String archivo) {
		Path rutaArchivo = Paths.get(path).resolve(archivo).toAbsolutePath();
		return rutaArchivo;
	}
	
	public Resource cargar(String nombrefichero) throws MalformedURLException {
		Path path = getPath(DownloadFileService.DIRECTORIO_DOWNLOAD, nombrefichero);
		
		Resource resource = null;
		resource = new UrlResource(path.toUri());
	    if(!resource.exists() || !resource.isReadable()) {
			log.error("Error, Path para descargas no existe o protegido");
	    };	
		return resource;
	}
	
	@GetMapping("download/cvpdf-alternativo")
	public ResponseEntity<Resource> donwloadCvAlternativo() throws Exception { 
		String filename = downloadFileService.getFilenameGenerado();
		Resource resource = null;
		try {
			resource = cargar(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		/*
		 * HttpHeaders cabecera = new HttpHeaders();
		 * cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
		 * resource.getFilename() + "\""); 
		 * return new ResponseEntity<Resource>(resource,
		 * cabecera, HttpStatus.OK);
		 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + filename);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		return new ResponseEntity<Resource> (resource, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "download/cvpdf", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadCvPdf() throws Exception {
		System.out.println("Calling downloadCvPdf:- ");
		String filename = downloadFileService.getFilenameGenerado();
		ClassPathResource pdfFile = new ClassPathResource(DownloadFileService.DIRECTORIO_DOWNLOAD +  filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		//headers.add("Access-Control-Allow-Origin", "*"); Si se incluye da error CORS
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + filename);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		headers.setContentLength(pdfFile.contentLength());
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
		//downloadFileService.deleteFilename(filename);
		return response;

	}
	
}
