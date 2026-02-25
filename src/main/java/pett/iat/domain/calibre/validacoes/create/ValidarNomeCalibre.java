package pett.iat.domain.calibre.validacoes.create;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import pett.iat.domain.calibre.dtos.CalibreCreateDto;

@Component
public class ValidarNomeCalibre implements ValidacaoCriarCalibre {

   public void validar(CalibreCreateDto dados) {
      var nome = dados.nome();
      if(nome.trim().length() <2){
         throw new ValidationException("nome calibre ter dois ou mais caracteres");
      }
   }
}
