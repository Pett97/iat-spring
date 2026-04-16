package pett.iat.domain.estoque.lote.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.lote.dto.LoteCreateDto;
import pett.iat.domain.produto.municao.MunicaoRepository;

@Component
public class ValidarProdutoExiste implements ValidarLoteCreate {

   @Autowired
   private MunicaoRepository municaoRepository;

   public void validar(LoteCreateDto dto) {

      if (dto.produtoId() == null) {
         throw new ValidacaoExecption("Para Cadastrar um lote é necesssário informar um produto ");
      }

      this.municaoRepository.findById(dto.produtoId()).orElseThrow(
            () -> new ValidacaoExecption("Não foi encontrada nenhuma munição com o id: " + dto.produtoId()));
   }
}
