package pett.iat.domain.arma;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaRepository extends JpaRepository<Arma, Long> {

   Optional<Arma> findByNumeroCraf(String numeroCraf);

   Optional<Arma> findByNumeroSerie(String numeroSerie);

   Optional<Arma> findByNumeroCano(String numeroCano);
}
