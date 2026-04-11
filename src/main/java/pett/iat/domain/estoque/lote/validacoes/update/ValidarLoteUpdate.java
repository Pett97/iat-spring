package pett.iat.domain.estoque.lote.validacoes.update;

import pett.iat.domain.estoque.lote.dto.LoteUpdateDto;

public interface ValidarLoteUpdate {
   public void validar(LoteUpdateDto dto);
}
