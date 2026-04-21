package pett.iat.domain.estoque.embalagem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pett.iat.enums.StatusMunicao;

public record EmbalagemCreateDto(

      @NotBlank(message = "O código da embalagem é obrigatório") @Size(min = 3, message = "O código deve ter no mínimo 3 caracteres") String codigo,

      @NotNull(message = "O lote é obrigatório") Long idLote,

      StatusMunicao statusMunicao,

      LocalDateTime dataEntrada) {
}
