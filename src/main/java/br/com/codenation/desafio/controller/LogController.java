package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.constants.PatchMediaType;
import br.com.codenation.desafio.mappers.LogMapper;
import br.com.codenation.desafio.mappers.LogResourceInput;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.repository.LogRepository;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.request.LogUpdateRequest;
import br.com.codenation.desafio.service.LogService;
import br.com.codenation.desafio.util.PatchHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@AllArgsConstructor
public class LogController {

    @Autowired
    private LogService service;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper mapper;

    @Autowired
    private PatchHelper patchHelper;

    @GetMapping("example")
    public Page<Log> findByExample(@RequestBody Log logExample){
        return service.findByExample(logExample);
    }

    @GetMapping
    public Page<Log> findAll(){
        return service.findAll();
    }

    @PostMapping
    private Log save(@RequestBody LogRequest log){ return  service.save(log); }


    @PatchMapping(path = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Log update(@PathVariable String id,
                       @RequestBody JsonMergePatch mergePatchDocument) {

        Log log = logRepository.findById(id).orElseThrow(RuntimeException::new);
        LogResourceInput contactResource = mapper.asInput(log);
        LogResourceInput contactResourcePatched = patchHelper.mergePatch(mergePatchDocument, contactResource, LogResourceInput.class);

        mapper.update(log, contactResourcePatched);
        logRepository.save(log);

        return log;
    }

    @PatchMapping(path = "/{id}/v2")
    public void updatev2(@PathVariable String id) {

        Log log = logRepository.findById(id).orElseThrow(RuntimeException::new);
        LogResourceInput contactResource = mapper.asInput(log);
        logRepository.save(log);
    }


}
