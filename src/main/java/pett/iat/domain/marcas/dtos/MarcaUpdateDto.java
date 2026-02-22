package pett.iat.domain.marcas.dtos;

import jakarta.validation.constraints.NotNull;

public record MarcaUpdateDto(

      @NotNull Long id,

      @NotNull String nome) {
}
