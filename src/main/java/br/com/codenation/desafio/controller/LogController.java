package br.com.codenation.desafio.controller;

import javax.json.JsonMergePatch;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codenation.desafio.constants.PatchMediaType;
import br.com.codenation.desafio.exceptions.ObjectCreationException;
import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;
import br.com.codenation.desafio.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogController {

    private LogService service;
    
    private UserService userService;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    private Log save(@RequestBody @Valid LogRequest log){
        userService.findById(log.getUserId())
            .orElseThrow(() -> new ObjectCreationException("User not found"));

        return  service.save(log);
    }

    @PatchMapping(path = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Log update(@PathVariable String id, @RequestBody JsonMergePatch mergePatchDocument) {
        return service.update(id, mergePatchDocument);
    }
}