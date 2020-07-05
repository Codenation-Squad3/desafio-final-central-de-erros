package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.constants.PatchMediaType;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.json.JsonMergePatch;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogController {

    private LogService service;

    @GetMapping("/search")
    public Page<Log> findByExample(@RequestBody Log logExample,
                                   @RequestParam(name = "page" , defaultValue = "0") Integer page,
                                   @RequestParam(name = "sortTarget" , defaultValue = "origin") String sortTarget,
                                   @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection){

        return service.findByExample(logExample,page,sortTarget,sortDirection);
    }

    @GetMapping
    public Page<Log> findAll(){
        return service.findAll();
    }

    @PostMapping
    private Log save(@RequestBody LogRequest log){
        return  service.save(log);
    }

    @PatchMapping(path = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Log update(@PathVariable String id, @RequestBody JsonMergePatch mergePatchDocument) {
        return service.update(id, mergePatchDocument);
    }
}
