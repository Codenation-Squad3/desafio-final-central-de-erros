package br.com.codenation.desafio.service.interfaces;

import br.com.codenation.desafio.model.LogReturnDTO;
import br.com.codenation.desafio.dtos.request.LogRequestDTO;

public interface LogServiceInterface {

    LogReturnDTO save(LogRequestDTO object);

}
