package pett.iat.domain.marcas.controllers;

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
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.domain.marcas.ServiceMarcas;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;
import pett.iat.domain.marcas.dtos.MarcaDetailDto;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;

@RestController
@Tag(name = "Marcas de Armas")
@RequestMapping("marcas")
@SecurityRequirement(name = "bearer-key")
public class MarcaController {

   @Autowired
   private ServiceMarcas serviceMarcas;

   @Autowired
   private MarcasARepository marcasARepository;

   @GetMapping
   public ResponseEntity<List<MarcaDetailDto>> listar() {
      var listaMarcas = serviceMarcas.listar();
      return ResponseEntity.ok(listaMarcas);
   }

   @GetMapping("/{id}")
   public ResponseEntity<MarcaDetailDto> getMarca(@PathVariable Long id) {
      var marca = marcasARepository.getReferenceById(id);
      return ResponseEntity.ok(new MarcaDetailDto(marca));
   }

   @PostMapping
   @Transactional
   public ResponseEntity<MarcaDetailDto> cadastrar(@RequestBody @Valid MarcaCreateDto dados) {
      var marcaArmaNova = serviceMarcas.salvar(dados);
      return ResponseEntity.ok(marcaArmaNova);
   }

   @PutMapping()
   @Transactional
   public ResponseEntity<MarcaDetailDto> atualizarMarca(@RequestBody MarcaUpdateDto dados) {
      var marca = serviceMarcas.update(dados);
      return ResponseEntity.ok(marca);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
      if (!marcasARepository.existsById(id)) {
         throw new ValidacaoExecption("Marca não encontrada com o ID: " + id);
      }
      marcasARepository.deleteById(id);
      return ResponseEntity.noContent().build();
   }

}
