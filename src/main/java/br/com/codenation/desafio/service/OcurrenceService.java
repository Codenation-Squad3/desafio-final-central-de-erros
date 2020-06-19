package br.com.codenation.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.OcurrenceRepository;
import br.com.codenation.desafio.service.interfaces.OcurrenceServiceInterface;

@Service("ocurrence")
public class OcurrenceService implements OcurrenceServiceInterface {

	@Autowired
	private OcurrenceRepository ocurrenceRepository;
	
	@Override
	public Ocurrence save(Ocurrence ocurrence) {
		return this.ocurrenceRepository.save(ocurrence);
	}


}
