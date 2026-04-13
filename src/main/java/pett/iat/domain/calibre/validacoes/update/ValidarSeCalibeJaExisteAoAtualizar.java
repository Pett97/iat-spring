package pett.iat.domain.calibre.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
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
         throw new ValidacaoExecption(
               "calibre %s já existe".formatted(nome));
      }
   }

}
