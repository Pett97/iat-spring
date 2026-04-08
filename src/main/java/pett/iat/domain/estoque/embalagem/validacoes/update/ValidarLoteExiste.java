package pett.iat.domain.estoque.embalagem.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.domain.estoque.lote.LoteRepository;

@Component
public class ValidarLoteExiste implements ValidarAtualizarEmbalagem {

   @Autowired
   private LoteRepository loteRepository;

   public void validar(EmbalagemUpdateDto dados) {

      if (dados.idLote() == null) {
         throw new ValidacaoExecption("Para cadastrar um codigo de embalagem é ncessario informar um lote ");
      }

      Lote lote = loteRepository.findById(dados.idLote()).get();

      if (lote == null) {
         throw new ValidacaoExecption("Não foi encontrado nenhum lote com o id:" + dados.idLote());
      }
   }
}
