package br.com.codenation.desafio.service;

import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.OcurrenceRepository;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.request.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.service.interfaces.LogServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("log")
public class LogService implements LogServiceInterface{

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OcurrenceRepository ocurrenceRepository;

	private static LogRequest logRequestS;

	public Optional<Log> toLog(LogRequest logRequest){
		return logRepository.findByDescriptionAndOriginAndTitle(logRequest.getDescription(), logRequest.getOrigin(), logRequest.getTitle());
	}

	public Ocurrence LogRequestToOccurrence(LogRequest logRequest, String idLog){
		return Ocurrence.builder()
				.dtCreated(logRequest.getLastOccurrence())
				.user(userRepository.findById(logRequest.getUserId()).get())
				.log(logRepository.findById(idLog).get())
				.build();
	}


	public Log save(LogRequest logRequest) {

		Optional<Log> existingLog = this.toLog(logRequest);

		if(existingLog.isPresent()){
			ocurrenceRepository.save(this.LogRequestToOccurrence(logRequest, existingLog.get().getId()));
		}
		else{
			return logRepository.save(
					Log.builder()
					.description(logRequest.getDescription())
					.environment(logRequest.getEnvironment())
					.lastOccurrence(logRequest.getLastOccurrence())
					.level(logRequest.getLevel())
					.origin(logRequest.getOrigin())
					.status(logRequest.getStatus())
					.title(logRequest.getTitle())
					.user(userRepository.findById(logRequest.getUserId()).get())
					.occurrences(new ArrayList<>()).build()
					);
		}

		return null;//Implemntar resposta "genérica"

	}

	public Page<Log> findByExample(Log logExample, Integer page ,String orderBy, String direction) {
		Pageable pageOptions = PageRequest.of(page, 20, Sort.Direction.valueOf(direction), orderBy);
		return logRepository.findAll(Example.of(logExample), pageOptions);
	}

	public List<Log> saveAll(List<Log> newlogs){
		return logRepository.saveAll(newlogs);
	}

	public Page<Log> findAll() {
		Pageable firstPageWithTreeElements =
				PageRequest.of(0, 3, Sort.by("origin").descending());
		return logRepository.findAll(firstPageWithTreeElements);
	}

	@Override
	public Log save(Log object) {
		// TODO Auto-generated method stub
		return null;
	}
}
