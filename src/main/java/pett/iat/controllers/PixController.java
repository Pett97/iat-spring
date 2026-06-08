package pett.iat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import pett.iat.pix.MontarPix;

@RestController
@RequestMapping("/pix")
@SecurityRequirement(name = "bearer-key")
public class PixController {

   @Autowired
   private MontarPix montarPix;

   @GetMapping()
   public ResponseEntity<String> gerarPixPagamento() {
      return ResponseEntity.ok(montarPix.dadosEnviados());
   }

}
