package pett.iat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pett.iat.domain.produto.ProdutoMunicaoService;
import pett.iat.domain.produto.dtos.ProdutoMunicaoCreateDto;
import pett.iat.domain.produto.dtos.ProdutoMunicaoDetailDto;

@RestController
@RequestMapping("municao")
public class ProdutoMunicaoController {

   @Autowired
   private ProdutoMunicaoService produtoMunicaoService;

   @GetMapping
   public ResponseEntity<List<ProdutoMunicaoDetailDto>> listarMunicoes() {
      var municoes = this.produtoMunicaoService.listarMunicoes();
      return ResponseEntity.ok(municoes);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<ProdutoMunicaoDetailDto> salvar(@RequestBody @Valid ProdutoMunicaoCreateDto dados) {
      var municaoNova = this.produtoMunicaoService.cadastrar(dados);
      return ResponseEntity.ok(municaoNova);
   }

}
