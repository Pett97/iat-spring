package pett.iat.domain.produto.municao.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.produto.municao.dtos.MunicaoCreateDto;

@Component
public class CalibreExiste implements ValidarCreateMunicao {

   @Autowired
   private CalibreRespository calibreRespository;

   public void validar(MunicaoCreateDto dados) {
      if (dados.calibreId() == null) {
         throw new ValidacaoExecption("Para cadastrar um produto munição é necessario informar o calibre");
      }

      var calibre = calibreRespository.findById(dados.calibreId());

      if(calibre == null){
         throw new ValidacaoExecption("Nao foi encontrado nenhum calibre com esse id: "+dados.calibreId());
      }
   }
}
