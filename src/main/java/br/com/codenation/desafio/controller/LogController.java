package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
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
    private Log save(@RequestBody LogRequest log){ return  service.save(log); }

}
