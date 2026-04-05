package pett.iat.domain.estoque.lote;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {

}
