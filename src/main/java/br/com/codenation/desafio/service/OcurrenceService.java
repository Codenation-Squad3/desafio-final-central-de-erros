package br.com.codenation.desafio.service;

import br.com.codenation.desafio.model.Ocurrence;
import br.com.codenation.desafio.repository.OcurrenceRepository;
import br.com.codenation.desafio.service.interfaces.OcurrenceServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("ocurrence")
@AllArgsConstructor
public class OcurrenceService implements OcurrenceServiceInterface {

	private OcurrenceRepository ocurrenceRepository;
	
	@Override
	public Ocurrence save(Ocurrence ocurrence) {
		return this.ocurrenceRepository.save(ocurrence);
	}


}
