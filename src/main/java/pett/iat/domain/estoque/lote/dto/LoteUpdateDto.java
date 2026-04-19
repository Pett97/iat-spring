package pett.iat.domain.estoque.lote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoteUpdateDto(

            @NotNull Long loteId,

            @Size(min = 3) String codigo,

            Long produtoId) {}
