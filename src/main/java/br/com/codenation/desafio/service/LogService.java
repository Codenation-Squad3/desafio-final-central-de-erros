package br.com.codenation.desafio.service;

import br.com.codenation.desafio.model.LogReturnDTO;
import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.OcurrenceRepository;
import br.com.codenation.desafio.repository.UserRepository;
import br.com.codenation.desafio.dtos.request.LogRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.service.interfaces.LogServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("log")
@AllArgsConstructor
public class LogService implements LogServiceInterface {

    private LogRepository logRepository;
    private UserRepository userRepository;
    private OcurrenceRepository ocurrenceRepository;

    public Optional<Log> toLog(LogRequestDTO logRequest) {
        return logRepository.findByDescriptionAndOriginAndTitle(logRequest.getDescription(), logRequest.getOrigin(), logRequest.getTitle());
    }

    public Ocurrence LogRequestToOccurrence(LogRequestDTO logRequest, String idLog) {
        return Ocurrence.builder()
                .dtCreated(logRequest.getLastOccurrence())
                .user(userRepository.findById(logRequest.getUserId()).get())
                .log(logRepository.findById(idLog).get())
                .build();
    }

    public LogReturnDTO save(LogRequestDTO logRequest) {
        Optional<Log> existingLog = this.toLog(logRequest);
        return LogReturnDTO.builder()
                .ocurrence(findOcurrence(logRequest, existingLog))
                .log(findLog(logRequest, existingLog))
                .build();
    }

    private Log findLog(LogRequestDTO logRequest, Optional<Log> existingLog) {
        return existingLog.orElseGet(() -> logRepository.save(
                Log.builder().description(logRequest.getDescription())
                        .environment(logRequest.getEnvironment())
                        .lastOccurrence(logRequest.getLastOccurrence())
                        .level(logRequest.getLevel())
                        .origin(logRequest.getOrigin())
                        .status(logRequest.getStatus())
                        .title(logRequest.getTitle())
                        .user(userRepository.findById(logRequest.getUserId()).get())
                        .occurrences(new ArrayList<>()).build()));
    }

    private Ocurrence findOcurrence(LogRequestDTO logRequest, Optional<Log> existingLog) {
        return existingLog.map(log ->
                ocurrenceRepository.save(this.LogRequestToOccurrence(logRequest, existingLog.get().getId())))
                .orElse(null);
    }


    public Page<Log> findByExample(Log logExample, Integer page, String orderBy, String direction) {
        Pageable pageOptions = PageRequest.of(page, 20, Sort.Direction.valueOf(direction), orderBy);
        return logRepository.findAll(Example.of(logExample), pageOptions);
    }

    public List<Log> saveAll(List<Log> newlogs) {
        return logRepository.saveAll(newlogs);
    }

    public Page<Log> findAll() {
        Pageable firstPageWithTreeElements = PageRequest.of(0, 3, Sort.by("origin").descending());
        return logRepository.findAll(firstPageWithTreeElements);
    }
}
