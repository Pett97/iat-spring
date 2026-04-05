package pett.iat.domain.estoque.embalagem.dto.update;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pett.iat.enums.StatusMunicao;

public record EmbalagemUpdateDto(

   @NotNull
   Long id,
   @NotBlank
   String codigo,
   Long idLote,
   @NotNull
   StatusMunicao statusMunicao,
   LocalDateTime dataEntrada,
   LocalDateTime dataSaida
) {}
