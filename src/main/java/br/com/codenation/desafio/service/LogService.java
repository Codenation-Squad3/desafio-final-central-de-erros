package br.com.codenation.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.service.interfaces.LogServiceInterface;

@Service("log")
public class LogService implements LogServiceInterface{

	@Autowired
	private LogRepository logRepository;
	
	
	@Override
	public Log save(Log log) {
		return this.logRepository.save(log);
	}


}
