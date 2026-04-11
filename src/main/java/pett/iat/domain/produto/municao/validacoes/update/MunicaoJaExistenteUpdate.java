package pett.iat.domain.produto.municao.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.produto.ProdutoRepository;
import pett.iat.domain.produto.municao.dtos.MunicaoUpdateDto;

@Component
public class MunicaoJaExistenteUpdate implements ValidarUpdateMunicao {

   @Autowired
   private ProdutoRepository produtoRepository;

   @Override
   public void validar(MunicaoUpdateDto dados) {
      if (produtoRepository.existsBySkuIgnoreCase(dados.sku())
            || produtoRepository.existsByNomeIgnoreCase(dados.nome())) {
         throw new ValidacaoExecption(
               "Ja existe um produto com esse SKU: " + dados.sku() + " ou com esse nome: " + dados.nome());
      }

   }
}
