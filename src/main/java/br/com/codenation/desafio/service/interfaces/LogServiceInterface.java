package br.com.codenation.desafio.service.interfaces;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;
import br.com.codenation.desafio.request.LogUpdateRequest;

public interface LogServiceInterface{

    Log save(LogRequest object);
}
