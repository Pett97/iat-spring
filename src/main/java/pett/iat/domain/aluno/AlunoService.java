package pett.iat.domain.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pett.iat.domain.aluno.dto.AlunoCreateDto;
import pett.iat.domain.aluno.dto.AlunoDetailDto;

@Service
public class AlunoService {

   @Autowired
   private AlunoRepository alunoRepository;

   public List<AlunoDetailDto> listar() {
      return this.alunoRepository.findAll().stream().map(AlunoDetailDto::new).toList();
   }

   public List<AlunoDetailDto> alunosComEmailPrincipalValidos() {
      return this.alunoRepository.findByEmailPrincipalValidadoTrue().stream().map(AlunoDetailDto::new).toList();
   }

   public List<AlunoDetailDto> alunosComEmailSecundariosValidos() {
      return this.alunoRepository.finbByEmailSecundarioValidadoTrue().stream().map(AlunoDetailDto::new).toList();
   }

   public AlunoDetailDto cadastrar(AlunoCreateDto dto) {

      Aluno aluno = new Aluno(dto);

      this.alunoRepository.save(aluno);

      return new AlunoDetailDto(aluno);
   }
}
