package pett.iat.domain.aluno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pett.iat.domain.aluno.AlunoService;
import pett.iat.domain.aluno.dto.AlunoCreateDto;
import pett.iat.domain.aluno.dto.AlunoDetailDto;
import pett.iat.domain.aluno.dto.AlunoUpdateDto;

@RestController
@Tag(name = "Alunos")
@RequestMapping("aluno")
@SecurityRequirement(name = "bearer-key")
public class AlunoController {

   @Autowired
   private AlunoService alunoService;

   @GetMapping
   public ResponseEntity<List<AlunoDetailDto>> listarAlunos() {
      var alunos = this.alunoService.listar();
      return ResponseEntity.ok(alunos);
   }

   @GetMapping("/email-principais-validos")
   public ResponseEntity<List<AlunoDetailDto>> listarAlunosComEmailPrincipalValido() {
      var alunos = this.alunoService.alunosEmailPrincipalValidado();
      return ResponseEntity.ok(alunos);
   }

   @GetMapping("/email-secundarios-validos")
   public ResponseEntity<List<AlunoDetailDto>> listarAlunosComEmailSecundarioValido() {
      var alunos = this.alunoService.alunosComEmailSecundariosValidos();
      return ResponseEntity.ok(alunos);
   }

   @GetMapping("/deletados")
   public ResponseEntity<List<AlunoDetailDto>> listarAlunosDeletados() {
      var alunos = this.alunoService.listarAlunosDeletados();
      return ResponseEntity.ok(alunos);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<AlunoDetailDto> cadastrarAlunos(@RequestBody @Valid AlunoCreateDto dto) {
      System.out.println(dto);
      var aluno = this.alunoService.cadastrar(dto);
      return ResponseEntity.ok(aluno);
   }

   @PutMapping("/{id}")
   @Transactional
   public ResponseEntity<AlunoDetailDto> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoUpdateDto dto) {
      var aluno = this.alunoService.atualizar(id, dto);
      return ResponseEntity.ok(aluno);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
      this.alunoService.deletar(id);
      return ResponseEntity.noContent().build();
   }

   @PostMapping("/recuperar-aluno/{id}")
   @Transactional
   public ResponseEntity<Void> recuperarAluno(@PathVariable Long id) {
      this.alunoService.recuperar(id);
      return ResponseEntity.noContent().build();
   }

}
