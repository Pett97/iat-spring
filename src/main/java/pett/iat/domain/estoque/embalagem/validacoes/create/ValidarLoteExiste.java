package pett.iat.domain.estoque.embalagem.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemCreateDto;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.domain.estoque.lote.LoteRepository;

@Component
public class ValidarLoteExiste implements ValidarCriarEmbalagem {

   @Autowired
   private LoteRepository loteRepository;

   public void validar(EmbalagemCreateDto dados) {

      if (dados.idLote() == null) {
         throw new ValidacaoExecption("Para cadastrar um codigo de embalagem é ncessario informar um lote ");
      }

      loteRepository.findById(dados.idLote())
            .orElseThrow(() -> new ValidacaoExecption("Não foi encontrado nenhum lote com o id:" + dados.idLote()));
   }
}
