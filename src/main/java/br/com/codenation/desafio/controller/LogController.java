package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService service;

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

}
