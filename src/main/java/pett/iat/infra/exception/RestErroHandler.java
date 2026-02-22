package pett.iat.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import pett.iat.domain.ValidacaoExecption;

@RestControllerAdvice
public class RestErroHandler {

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<Void> tratarErro404() {
      return ResponseEntity.notFound().build();
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
      var erros = ex.getFieldErrors();
      return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
   }

   @ExceptionHandler(ValidacaoExecption.class)
   public ResponseEntity tratarErroRegraDeNegocio(ValidacaoExecption ex) {
      return ResponseEntity.badRequest().body(ex.getMessage());
   }

   private record DadosErrosValidacao(String campo, String messagem) {

      public DadosErrosValidacao(FieldError error) {
         this(error.getField(), error.getDefaultMessage());
      }
   }
}
