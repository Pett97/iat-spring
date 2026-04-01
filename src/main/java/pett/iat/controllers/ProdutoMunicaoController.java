package pett.iat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("municao")
public class ProdutoMunicaoController {

   @GetMapping
   public String teste(){
      return "OLA sou produto munição controler";
   }
}
