package br.com.codenation.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.codenation.desafio.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {
    Optional<Log> findByDescriptionAndOriginAndTitle(String description, String origin, String title);
}
