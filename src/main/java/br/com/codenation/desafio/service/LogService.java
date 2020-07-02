package br.com.codenation.desafio.service;

import br.com.codenation.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.service.interfaces.LogServiceInterface;

import java.util.List;

@Service("log")
public class LogService implements LogServiceInterface{

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Log save(Log log) {
		userRepository.save(log.getUser());
		return this.logRepository.save(log);
	}

    public Page<List<Log>> findByExample(Log logExample) {

		Pageable firstPageWithTreeElements =
				PageRequest.of(0, 3, Sort.by("price").descending());

		Example<Log> example =  Example.of(logExample);


		logRepository.findAll(example, firstPageWithTreeElements);
		return null;
    }

    public List<Log> saveAll(List<Log> newlogs){
		return logRepository.saveAll(newlogs);
	}
}
