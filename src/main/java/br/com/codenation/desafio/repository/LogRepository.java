package br.com.codenation.desafio.repository;

import br.com.codenation.desafio.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {
    Optional<Log> findByDescriptionAndOriginAndTitle(String description, String origin, String title);
}
