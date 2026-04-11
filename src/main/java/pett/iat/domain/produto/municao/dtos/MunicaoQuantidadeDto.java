package pett.iat.domain.produto.municao.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MunicaoQuantidadeDto(
      @NotNull Long id,

      @NotNull @Min(0) int quantidade) {

}
