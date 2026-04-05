package pett.iat.domain.estoque.embalagem.dto.create;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pett.iat.enums.StatusMunicao;

public record EmbalagemCreateDto(

   @NotBlank
   String codigo,
   @NotNull
   Long idLote,
   StatusMunicao statusMunicao,
   LocalDateTime dataEntrada
) {}
