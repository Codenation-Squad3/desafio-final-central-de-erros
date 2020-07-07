package br.com.codenation.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.json.JsonMergePatch;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.exceptions.ObjectNotFoundException;
import br.com.codenation.desafio.mappers.LogMapper;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.repository.OcurrenceRepository;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.request.LogUpdateRequestDTO;
import br.com.codenation.desafio.service.interfaces.LogServiceInterface;
import br.com.codenation.desafio.util.PatchHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service("log")
@AllArgsConstructor
@NoArgsConstructor
public class LogService implements LogServiceInterface{

	private LogRepository logRepository;
	private UserRepository userRepository;
	private OcurrenceRepository ocurrenceRepository;
	private LogMapper logMapper;
	private PatchHelper patchHelper;

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

	public Log save(Log object) {
		// TODO Auto-generated method stub
		return null;
	}

	public Log update(String id, JsonMergePatch mergePatchDocument){
		Log log = logRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Log with " + id + " id does not exist"));
		LogUpdateRequestDTO logDto = logMapper.toDto(log);
		LogUpdateRequestDTO logRequestDto = patchHelper.mergePatch(mergePatchDocument,
				logDto, LogUpdateRequestDTO.class);

		logMapper.update(log, logRequestDto);
		logRepository.save(log);

		return log;
	}
}
