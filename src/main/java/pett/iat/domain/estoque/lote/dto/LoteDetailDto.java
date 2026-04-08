package pett.iat.domain.estoque.lote.dto;

import pett.iat.domain.estoque.lote.Lote;

public record LoteDetailDto(
      Long id,
      String codigo,
      String nomeMunicao) {

   public LoteDetailDto(Lote lote) {
      this(lote.getId(), lote.getCodigo(), lote.getMunicao().getNome());
   }

}
