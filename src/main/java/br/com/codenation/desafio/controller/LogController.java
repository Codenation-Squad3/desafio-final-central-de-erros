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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(tags="Api: Log operations")
@RestController
@RequestMapping("/logs")
@AllArgsConstructor
public class LogController {

    private LogService service;
    
    private UserService userService;

    @ApiOperation(
        value = "Find logs provided by a user search criteria",
        notes = "When search criteria don't include any parameters, it will return the last logs sorted by date/time")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found logs")
    })
    @GetMapping("/search")
    public Page<Log> findByExample(@RequestBody Log logExample,
                                   @RequestParam(name = "page" , defaultValue = "0") Integer page,
                                   @RequestParam(name = "sortTarget" , defaultValue = "origin") String sortTarget,
                                   @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection){

        return service.findByExample(logExample,page,sortTarget,sortDirection);
    }

    @ApiOperation(value = "Returns the last logs")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Found logs")
    })
    @GetMapping
    public Page<Log> findAll(){
        return service.findAll();
    }

    @ApiOperation(value="Save a new log")
   	@ApiResponses (value = {
   	    @ApiResponse(code = 201, message = "Log successfully created"),
   	    @ApiResponse(code = 404, message = "User not found"),
   	    @ApiResponse(code = 400, message = "Invalid LogRequest body")
   	})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    private Log save(@RequestBody @Valid LogRequest log){
        userService.findById(log.getUserId())
            .orElseThrow(() -> new ObjectCreationException("User not found"));

        return  service.save(log);
    }

    @ApiOperation(value="Update a existing log")
   	@ApiResponses (value = {
   	    @ApiResponse(code = 201, message = "Log successfully updated"),
   	    @ApiResponse(code = 404, message = "User not found"),
   	    @ApiResponse(code = 400, message = "Invalid JsonMergePatch body")
   	})
    @PatchMapping(path = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Log update(@PathVariable String id, @RequestBody JsonMergePatch mergePatchDocument) {
        return service.update(id, mergePatchDocument);
    }
}