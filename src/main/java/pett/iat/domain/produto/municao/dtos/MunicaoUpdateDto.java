package pett.iat.domain.produto.municao.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MunicaoUpdateDto(

      @NotNull Long id,
      @NotBlank @Size(min = 3, max = 74) String nome,
      @NotBlank @Size(min = 3, max = 74) String sku,
      @NotNull @DecimalMin("0.00") BigDecimal preco,
      @NotNull Long calibreId

) {

}
