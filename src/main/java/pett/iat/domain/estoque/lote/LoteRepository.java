package pett.iat.domain.estoque.lote;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

   List<Lote> findByMunicaoId(Long id);

   Optional<Lote>findByCodigo(String codigo);
}
