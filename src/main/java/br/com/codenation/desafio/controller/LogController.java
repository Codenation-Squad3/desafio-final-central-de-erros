package br.com.codenation.desafio.controller;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService service;

    @GetMapping("/findByExample/page/{page}/orderBy/{sortTarget}/direction/{sortDirection}")
    public Page<Log> findByExample(@RequestBody Log logExample,
                                   @PathVariable(name = "page" , required = false) Integer page,
                                   @PathVariable(name = "sortTarget" , required = false) String sortTarget,
                                   @PathVariable(name = "sortDirection", required = false) String sortDirection){

        return service.findByExample(logExample,page,sortTarget,sortDirection);
    }

    @GetMapping
    public Page<Log> findAll(){
        return service.findAll();
    }

    @PostMapping
    private Log save(@RequestBody LogRequest log){ return  service.save(log); }

}
