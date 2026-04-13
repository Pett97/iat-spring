package pett.iat.domain.calibre.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.calibre.dtos.CalibreCreateDto;

@Component
public class ValidarSeCalibeJaExiste implements ValidacaoCriarCalibre {

   @Autowired
   private CalibreRespository calibreRespository;

   public void validar(CalibreCreateDto dados) {
      var nome = dados.nome().trim().toUpperCase();
      var check = calibreRespository.existsByNome(nome);
      if (check) {
         throw new ValidacaoExecption(
               "calibre %s já existe".formatted(nome));
      }
   }

}
