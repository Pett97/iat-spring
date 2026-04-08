package pett.iat.domain.estoque.embalagem.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.embalagem.Embalagem;
import pett.iat.domain.estoque.embalagem.EmbalagemRepository;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemCreateDto;

@Component
public class ValidarEmbalagemJaExiste implements ValidarCriarEmbalagem {

   @Autowired
   private EmbalagemRepository embalagemRepository;

   public void validar(EmbalagemCreateDto dados) {
      if (dados.codigo() == null) {
         throw new ValidacaoExecption("Nenhum codigo de embalagem foi informado");
      }

      String codigoInformado = dados.codigo().toUpperCase().trim();

      Embalagem embalagem = this.embalagemRepository.findByCodigo(codigoInformado).get();

      if (embalagem != null) {
         throw new ValidacaoExecption(
               "Esse codigo :" + codigoInformado + " ja está cadastrado no sistema, por favor verifique");
      }
   }
}
