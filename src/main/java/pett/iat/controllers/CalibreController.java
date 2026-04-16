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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.calibre.CalibreService;
import pett.iat.domain.calibre.dtos.CalibreCreateDto;
import pett.iat.domain.calibre.dtos.CalibreDetailDto;
import pett.iat.domain.calibre.dtos.CalibreUpdateDto;

@RestController
@RequestMapping("calibres")
public class CalibreController {

   @Autowired
   private CalibreRespository calibreRespository;

   @Autowired
   private CalibreService calibreService;

   @GetMapping
   public ResponseEntity<List<CalibreDetailDto>> listar() {
      var calibres = calibreService.listar();
      return ResponseEntity.ok(calibres);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CalibreDetailDto> getCalibre(@PathVariable Long id) {
      //TODO ajustar para ser pelo service
      var calibre = calibreRespository.getReferenceById(id);
      return ResponseEntity.ok(new CalibreDetailDto(calibre));

   }

   @PostMapping
   @Transactional
   public ResponseEntity<CalibreDetailDto> salvar(@RequestBody @Valid CalibreCreateDto dados) {
      var calibre = calibreService.salvar(dados);
      return ResponseEntity.ok(calibre);
   }

   @PutMapping()
   @Transactional
   public ResponseEntity<CalibreDetailDto> atualizar(@RequestBody @Valid CalibreUpdateDto dados) {
      var calibre = calibreService.atualizar(dados);
      return ResponseEntity.ok(calibre);
   }

   @DeleteMapping("/{id}")
   @Transactional
   public ResponseEntity<Void> deletar(@PathVariable Long id) {
      //TODO ajustar n posso deletar um que esta em uma arma ou municao  
      calibreRespository.deleteById(id);
      return ResponseEntity.noContent().build();
   }

}
