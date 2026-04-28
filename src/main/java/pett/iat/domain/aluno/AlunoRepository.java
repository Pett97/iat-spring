package pett.iat.domain.aluno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
   boolean existsByCpf(String cpf);

   boolean existsByRg(String rg);

   boolean existsByEmailPrincipal(String email);

   boolean existsByEmailSecundario(String email);

   List<Aluno> findByDeletadoIsFalse();

   List<Aluno> findByDeletadoIsTrue();

   List<Aluno> findByEmailPrincipalValidadoTrue();

   List<Aluno> findByEmailSecundarioValidadoTrue();
}
