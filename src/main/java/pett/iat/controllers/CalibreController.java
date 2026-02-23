package pett.iat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.calibre.dtos.CalibreCreateDto;

@RestController
@RequestMapping("calibres")
public class CalibreController {

   @Autowired
   private CalibreRespository calibreRespository;

   @PostMapping
   private void salvar(CalibreCreateDto dados){
      
   }
}
