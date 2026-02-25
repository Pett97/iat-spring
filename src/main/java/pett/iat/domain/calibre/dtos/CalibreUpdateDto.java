package pett.iat.domain.calibre.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CalibreUpdateDto(

      @NotNull Long id,

      @NotNull @NotBlank String nome) {
}
