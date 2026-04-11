package pett.iat.domain.estoque.lote.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.lote.LoteRepository;
import pett.iat.domain.estoque.lote.dto.LoteCreateDto;

@Component
public class ValidarCodigoExiste implements ValidarLoteCreate {

   @Autowired
   private LoteRepository loteRepository;

   public void validar(LoteCreateDto dto) {

      if (dto.codigo() == null) {
         throw new ValidacaoExecption("Para cadastrar um novo lote é necessário informar um codigo de lote");
      }

      String codigo = dto.codigo().toUpperCase().trim();
      if (this.loteRepository.findByCodigo(codigo).isPresent()) {
         throw new ValidacaoExecption("Ja existe um lote cadastrado com esse codigo: " + codigo);
      }
   }
}
