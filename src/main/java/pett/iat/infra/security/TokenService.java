package pett.iat.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import pett.iat.domain.auth.User;

@Service
public class TokenService {
   @Value("${api.security.token.secret}")
   private String secret;

   public String gerarToken(User user) {
      try {
         var algoritmo = Algorithm.HMAC256(secret);

         return JWT.create()
               .withIssuer("API IAT.iat")
               .withSubject(user.getLogin())
               .withExpiresAt(dataExpira())
               .withClaim("id", user.getLogin())// aqui eu posso fazer tipo de acesso e etc
               .sign(algoritmo);

      } catch (Exception e) {
         throw new RuntimeException("erro ao gerar token JWT", e);
      }
   }

   private Instant dataExpira() {
      return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
   }

   public String getSubject(String tokenJWT) {
      try {
         var algoritimo = Algorithm.HMAC256(secret);
         return JWT.require(algoritimo)
               .withIssuer("API Voli.api")
               .build()
               .verify(tokenJWT)
               .getSubject();
      } catch (JWTVerificationException e) {
         throw new RuntimeException("TOKEN JWT INVALIDO ou EXPIRADO");
      }
   }

}
