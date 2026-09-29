package pett.iat.domain.arma.controllers;

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
import pett.iat.domain.arma.ArmaService;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.domain.arma.dtos.ArmaDetailDto;
import pett.iat.domain.arma.dtos.ArmaUpdateDto;

@RestController
@RequestMapping("armas")
@Tag(name = "Armas")
@SecurityRequirement(name = "bearer-key")
public class ArmaController {

   @Autowired
   private ArmaService armaService;

   @GetMapping
   public ResponseEntity<List<ArmaDetailDto>>listar(){
      var armas = armaService.listar();
      return ResponseEntity.ok(armas);
   }

   @PostMapping
   @Transactional
   public ResponseEntity<ArmaDetailDto> cadastrar(@RequestBody @Valid ArmaCreateDto dados) {
      var arma = armaService.salvar(dados);
      return ResponseEntity.ok(arma);
   }

   @PutMapping
   @Transactional
   public ResponseEntity<ArmaDetailDto> atualizar(@RequestBody @Valid ArmaUpdateDto dados){
      var arma = armaService.atualizar(dados);
      return ResponseEntity.ok(arma);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletar(@PathVariable Long id){
      armaService.deletar(id);
      return ResponseEntity.noContent().build();
   }
}
