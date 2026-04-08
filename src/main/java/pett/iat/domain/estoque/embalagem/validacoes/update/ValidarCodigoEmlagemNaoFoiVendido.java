package pett.iat.domain.estoque.embalagem.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.estoque.embalagem.EmbalagemRepository;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;

@Component
public class ValidarCodigoEmlagemNaoFoiVendido  implements ValidarAtualizarEmbalagem{

   @Autowired
   private EmbalagemRepository embalagemRepository;

   public void validar(EmbalagemUpdateDto dados){

   }
}
