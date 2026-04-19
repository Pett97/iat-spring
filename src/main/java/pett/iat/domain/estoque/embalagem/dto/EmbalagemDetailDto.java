package pett.iat.domain.estoque.embalagem.dto;

import java.time.LocalDateTime;

import pett.iat.domain.estoque.embalagem.Embalagem;
import pett.iat.domain.estoque.lote.dto.LoteDetailDto;
import pett.iat.enums.StatusMunicao;

public record EmbalagemDetailDto(
            String codigo,
            StatusMunicao statusMunicao,
            LocalDateTime dataEntrada,
            LocalDateTime dataSaida,
            LoteDetailDto lote

) {
      public EmbalagemDetailDto(Embalagem embalagem) {
            this(
                        embalagem.getCodigo(),
                        embalagem.getStatusMunicao(),
                        embalagem.getDataEntrada(),
                        embalagem.getDataSaida() != null ? embalagem.getDataSaida() : null,
                        new LoteDetailDto(embalagem.getLote()));
      }
}
