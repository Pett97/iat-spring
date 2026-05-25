package pett.iat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pett.iat.domain.auth.User;
import pett.iat.domain.auth.dtos.DtoCreateLogin;
import pett.iat.domain.auth.dtos.DtoLogin;
import pett.iat.infra.security.DtoTokenJWT;
import pett.iat.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private TokenService tokenService;

   @PostMapping
   public ResponseEntity login(@RequestBody @Valid DtoLogin dtoLogin) {
      var authenticationToken = new UsernamePasswordAuthenticationToken(dtoLogin.login(), dtoLogin.senha());
      var authenticate = authenticationManager.authenticate(authenticationToken);
      var tokenJWT = tokenService.gerarToken((User) authenticate.getPrincipal());
      return ResponseEntity.ok(new DtoTokenJWT(tokenJWT));
   }

   @PostMapping("/create")
   public ResponseEntity createLogin(@RequestBody @Valid DtoCreateLogin dtoCreateLogin){

      return ResponseEntity.noContent().build();
   }
}
