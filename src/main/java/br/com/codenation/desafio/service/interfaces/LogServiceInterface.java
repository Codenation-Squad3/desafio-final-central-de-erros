package br.com.codenation.desafio.service.interfaces;

import br.com.codenation.desafio.model.Log;
import br.com.codenation.desafio.request.LogRequest;

public interface LogServiceInterface extends BaseServiceInterface<Log>{

	Log save(LogRequest object);


}
