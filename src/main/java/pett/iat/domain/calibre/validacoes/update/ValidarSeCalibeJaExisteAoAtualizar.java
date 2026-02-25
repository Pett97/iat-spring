package pett.iat.domain.calibre.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.calibre.dtos.CalibreUpdateDto;

@Component
public class ValidarSeCalibeJaExisteAoAtualizar implements ValidacaoesAtualizarCalibre {

   @Autowired
   private CalibreRespository calibreRespository;

   public void validar(CalibreUpdateDto dados) {
      var nome = dados.nome().trim().toUpperCase();
      var check = calibreRespository.existsByNome(nome);
      if (check) {
         throw new ValidationException(
               "calibre %s já existe".formatted(nome));
      }
   }

}
