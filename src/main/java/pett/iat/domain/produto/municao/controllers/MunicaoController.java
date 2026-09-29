package pett.iat.domain.produto.municao.controllers;

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
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.produto.municao.MunicaoRepository;
import pett.iat.domain.produto.municao.MunicaoService;
import pett.iat.domain.produto.municao.dtos.MunicaoCreateDto;
import pett.iat.domain.produto.municao.dtos.MunicaoDetailDto;
import pett.iat.domain.produto.municao.dtos.MunicaoUpdateDto;

@RestController
@Tag(name = "Produtos Munições")
@RequestMapping("municao")
@SecurityRequirement(name = "bearer-key")
public class MunicaoController {

   @Autowired
   private MunicaoService produtoMunicaoService;

   @Autowired
   private MunicaoRepository produtoMunicaoRepository;

   @GetMapping
   public ResponseEntity<List<MunicaoDetailDto>> listarMunicoes() {
      var municoes = this.produtoMunicaoService.listarMunicoes();
      return ResponseEntity.ok(municoes);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<MunicaoDetailDto> salvar(@RequestBody @Valid MunicaoCreateDto dados) {
      var municaoNova = this.produtoMunicaoService.cadastrar(dados);
      return ResponseEntity.ok(municaoNova);
   }

   @PutMapping
   @Transactional
   public ResponseEntity<MunicaoDetailDto> atualizar(@RequestBody MunicaoUpdateDto dados) {
      var municao = produtoMunicaoService.update(dados);
      return ResponseEntity.ok(municao);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
      if (!produtoMunicaoRepository.existsById(id)) {
         throw new ValidacaoExecption("Munição não encontrada com esse id: " + id);
      }

      produtoMunicaoRepository.deleteById(id);
      return ResponseEntity.noContent().build();
   }

}
