package pett.iat.domain.estoque.embalagem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pett.iat.enums.StatusMunicao;

@Repository
public interface EmbalagemRepository extends JpaRepository<Embalagem, Long> {

   List<Embalagem> findByLoteId(Long idLote);

   List<Embalagem> findByDataSaidaIsNull();

   List<Embalagem> findByDataSaidaIsNotNull();

   List<Embalagem> findByStatusMunicao(StatusMunicao statusMunicao);
}
