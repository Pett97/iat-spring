package pett.iat.domain.marcas.dtos;

import jakarta.validation.constraints.NotBlank;

public record MarcaCreateDto(
      @NotBlank String nome) {
}
