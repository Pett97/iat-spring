package pett.iat.domain.arma.dtos;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pett.iat.enums.LocalRegistroArma;
import pett.iat.enums.SentidoRaiasArma;
import pett.iat.enums.TipoAlmaArma;
import pett.iat.enums.TipoUsoArma;

public record ArmaUpdateDto(

      @NotNull Long id,
      @NotNull LocalRegistroArma localRegistroArma,

      @NotBlank String numeroCraf,

      @NotBlank String numeroSerie,

      @NotBlank String numeroCano,

      @NotBlank String modelo,

      @NotNull Long calibreId, // ID para buscar a entidade Calibre

      @NotNull Long marcaId, // ID para buscar a entidade Marca

      @NotNull TipoAlmaArma tipoAlmaArma,

      @NotNull TipoUsoArma tipoUsoArma,

      int numeroRaias,

      SentidoRaiasArma sentidoRaiasArma,

      @NotNull Boolean vencimentoIndeterminado,

      Date dataVencimentoCraf) {
}
