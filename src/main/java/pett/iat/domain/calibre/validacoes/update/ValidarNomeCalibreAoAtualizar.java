package pett.iat.domain.calibre.validacoes.update;

import org.springframework.stereotype.Component;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.dtos.CalibreUpdateDto;

@Component
public class ValidarNomeCalibreAoAtualizar implements ValidacaoesAtualizarCalibre {

   public void validar(CalibreUpdateDto dados) {
      var nome = dados.nome();
      if (nome.trim().length() < 2) {
         throw new ValidacaoExecption("nome calibre ter dois ou mais caracteres");
      }
   }
}
