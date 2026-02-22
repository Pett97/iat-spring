package pett.iat.domain.marcas;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcasARepository extends JpaRepository<Marca, Long> {
   boolean existsByNome(String nome);
   boolean existsByNomeAndIdNot(String nome, Long id);
}
