package br.com.codenation.desafio.service.interfaces;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;

import javax.json.JsonMergePatch;

public interface LogServiceInterface{

    Log save(LogRequest object);
    Log update(String id, JsonMergePatch mergePatchDocument);
}
