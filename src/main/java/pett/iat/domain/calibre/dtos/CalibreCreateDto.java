package pett.iat.domain.calibre.dtos;

import jakarta.validation.constraints.NotNull;

public record CalibreCreateDto(

      @NotNull String nome) {
}
