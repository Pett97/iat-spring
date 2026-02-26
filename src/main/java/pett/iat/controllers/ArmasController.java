package pett.iat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pett.iat.domain.arma.ArmaService;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.domain.arma.dtos.ArmaDetailDto;

@RestController
@RequestMapping("armas")
public class ArmasController {

   @Autowired
   private ArmaService armaService;

   @PostMapping
   public ResponseEntity<ArmaDetailDto> cadastrar(@RequestBody @Valid ArmaCreateDto dados) {
      var arma = armaService.salvar(dados);
      return ResponseEntity.ok(arma);
   }
}
