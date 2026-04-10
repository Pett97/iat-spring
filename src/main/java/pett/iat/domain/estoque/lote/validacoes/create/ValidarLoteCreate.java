package pett.iat.domain.estoque.lote.validacoes.create;

import pett.iat.domain.estoque.lote.dto.LoteCreateDto;

public interface ValidarLoteCreate {
   public void validar(LoteCreateDto dto);
}
