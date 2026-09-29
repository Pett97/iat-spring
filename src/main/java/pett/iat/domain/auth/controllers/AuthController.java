package pett.iat.domain.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import pett.iat.domain.auth.User;
import pett.iat.domain.auth.UserRepository;
import pett.iat.domain.auth.dtos.DtoCreateLogin;
import pett.iat.domain.auth.dtos.DtoLogin;
import pett.iat.infra.security.DtoTokenJWT;
import pett.iat.infra.security.TokenService;

@RestController
@Tag(name = "Autenticação")
@RequestMapping("/login")
public class AuthController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private TokenService tokenService;

   @Autowired
   private UserRepository userRepository;

   @PostMapping
   public ResponseEntity login(@RequestBody @Valid DtoLogin dtoLogin) {
      var authenticationToken = new UsernamePasswordAuthenticationToken(dtoLogin.login(), dtoLogin.senha());
      var authenticate = authenticationManager.authenticate(authenticationToken);
      var tokenJWT = tokenService.gerarToken((User) authenticate.getPrincipal());
      return ResponseEntity.ok(new DtoTokenJWT(tokenJWT));
   }

   @PostMapping("/register")
   @PreAuthorize("hasRole('MASTER')")
   public ResponseEntity register(@RequestBody @Valid DtoCreateLogin dtoCreateLogin) {
      if (this.userRepository.findByLogin(dtoCreateLogin.login()) != null) {
         return ResponseEntity.badRequest().build();
      }
      String hashPassword = new BCryptPasswordEncoder().encode(dtoCreateLogin.password());

      User user = new User(dtoCreateLogin.name(), dtoCreateLogin.login(), hashPassword, dtoCreateLogin.role());

      this.userRepository.save(user);
      return ResponseEntity.ok().build();
   }
}
