package pett.iat.controllers;

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
import pett.iat.domain.estoque.lote.LoteService;
import pett.iat.domain.estoque.lote.dto.LoteCreateDto;
import pett.iat.domain.estoque.lote.dto.LoteDetailDto;
import pett.iat.domain.estoque.lote.dto.LoteUpdateDto;

@RestController
@Tag(name = "Lotes de Munições ")
@RequestMapping("/lote")
@SecurityRequirement(name = "bearer-key")
public class LoteController {

   @Autowired
   private LoteService loteService;

   @GetMapping
   public ResponseEntity<List<LoteDetailDto>> listarLotes() {
      var lotes = this.loteService.listar();
      return ResponseEntity.ok(lotes);
   }

   @GetMapping("/{id}")
   public ResponseEntity<LoteDetailDto> getLoteById(@PathVariable Long id) {
      var lote = this.loteService.getLoteById(id);
      return ResponseEntity.ok(lote);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<LoteDetailDto> cadastrar(@RequestBody @Valid LoteCreateDto dto) {
      var lote = this.loteService.cadastrarLote(dto);
      return ResponseEntity.ok(lote);
   }

   @PutMapping()
   @Transactional
   public ResponseEntity<LoteDetailDto> atualizar(@RequestBody @Valid LoteUpdateDto dto) {
      var lote = this.loteService.atualizarLote(dto);
      return ResponseEntity.ok(lote);
   }

   @GetMapping("/produtos/{produtoId}/lotes")
   public ResponseEntity<List<LoteDetailDto>> listarLotePorIdProdutoMunicao(@PathVariable Long produtoId) {
      var lotes = this.loteService.listarPorProduto(produtoId);
      return ResponseEntity.ok(lotes);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletaLote(@PathVariable Long id) {
      this.loteService.deletar(id);
      return ResponseEntity.noContent().build();
   }

}
