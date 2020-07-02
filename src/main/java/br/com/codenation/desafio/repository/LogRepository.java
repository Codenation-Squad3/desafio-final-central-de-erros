package br.com.codenation.desafio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codenation.desafio.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
