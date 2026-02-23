package pett.iat.domain.calibre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalibreRespository extends JpaRepository<Calibre, Long> {

   boolean existsByNome(String nome);
}
