package pett.iat.domain.estoque.embalagem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pett.iat.enums.StatusMunicao;

public record EmbalagemCreateDto(

   @NotBlank
   @Size(min = 3)
   String codigo,
   @NotNull
   Long idLote,
   StatusMunicao statusMunicao,
   LocalDateTime dataEntrada
) {}
