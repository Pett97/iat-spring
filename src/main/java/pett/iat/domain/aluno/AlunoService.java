package pett.iat.domain.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.aluno.dto.AlunoCreateDto;
import pett.iat.domain.aluno.dto.AlunoDetailDto;
import pett.iat.domain.aluno.dto.AlunoUpdateDto;

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

   public AlunoDetailDto atualizar(Long id, AlunoUpdateDto dto) {
      Aluno aluno = this.getAlunoById(id);

      aluno.atualizar(dto);

      return new AlunoDetailDto(aluno);
   }

   public void deletar(Long id) {

      Aluno aluno = this.getAlunoById(id);

      this.alunoRepository.deleteById(aluno.getId());
   }

   public List<AlunoDetailDto> alunosEmailPrincipalValidado() {
      return this.alunoRepository.findByEmailPrincipalValidadoTrue().stream().map(AlunoDetailDto::new).toList();
   }

   public List<AlunoDetailDto> alunosEmailSecundarioValidado() {
      return this.alunoRepository.finbByEmailSecundarioValidadoTrue().stream().map(AlunoDetailDto::new).toList();
   }

   private Aluno getAlunoById(Long id) {
      return this.alunoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoExecption("Não foi contrado nenhum aluno com o id: " + id));
   }
}
