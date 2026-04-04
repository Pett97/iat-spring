package pett.iat.domain.produto.municao.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.produto.ProdutoRepository;
import pett.iat.domain.produto.municao.dtos.MunicaoCreateDto;

@Component
public class MunicaoJaExistente implements ValidarCreateMunicao {

   @Autowired
   private ProdutoRepository produtoRepository;

   @Override
   public void validar(MunicaoCreateDto dados) {
      if (produtoRepository.existsBySkuIgnoreCase(dados.sku())
            || produtoRepository.existsByNomeIgnoreCase(dados.nome())) {
         throw new ValidacaoExecption(
               "Ja existe um produto com esse SKU: " + dados.sku() + "ou com esse nome" + dados.nome());
      }

   }
}
