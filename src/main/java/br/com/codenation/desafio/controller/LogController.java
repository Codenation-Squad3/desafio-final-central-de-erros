package br.com.codenation.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;

@RestController
@RequestMapping("/log")
public class LogController {
	
	
	@Autowired
	private LogRepository logRepository;

	
	@GetMapping("/logs")
	public List<Log> getAll() {
		return logRepository.findAll();
	}
}
