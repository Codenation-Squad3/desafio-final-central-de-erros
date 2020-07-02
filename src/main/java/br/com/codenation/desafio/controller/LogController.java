package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService service;

    public Page<List<Log>> findByExample(@RequestBody Log logExample){
        return service.findByExample(logExample);
    }

    @PostMapping("save")
    private Log save(@RequestBody Log log){
        return  service.save(log);
    }

    @PostMapping
    public List<Log> saveAll(@RequestBody List<Log> newLogs){
        return  service.saveAll(newLogs);
    }
}
