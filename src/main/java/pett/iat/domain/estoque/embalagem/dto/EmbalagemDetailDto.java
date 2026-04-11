package pett.iat.domain.estoque.embalagem.dto;

import java.time.LocalDateTime;

import pett.iat.domain.estoque.embalagem.Embalagem;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.enums.StatusMunicao;

public record EmbalagemDetailDto(
      String codigo,
      StatusMunicao statusMunicao,
      LocalDateTime dataEntrada,
      LocalDateTime dataSaida,
      Lote lote

) {
   public EmbalagemDetailDto(Embalagem embalagem) {
      this(
            embalagem.getCodigo(),
            embalagem.getStatusMunicao(),
            embalagem.getDataEntrada(),
            embalagem.getDataSaida() != null ? embalagem.getDataSaida() : null,
            embalagem.getLote());
   }
}
