package pett.iat.domain.produto.municao.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.produto.municao.dtos.MunicaoUpdateDto;

@Component
public class CalibreExisteUpdate implements ValidarUpdateMunicao {

   @Autowired
   private CalibreRespository calibreRespository;

   public void validar(MunicaoUpdateDto dados) {
      if (dados.calibreId() == null) {
         throw new ValidacaoExecption("Para atualizar um produto munição é necessario informar o calibre");
      }

      var calibre = calibreRespository.findById(dados.calibreId());

      if (calibre == null) {
         throw new ValidacaoExecption("Nao foi encontrado nenhum calibre com esse id: " + dados.calibreId());
      }
   }
}
