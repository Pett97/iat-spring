package pett.iat.domain.produto.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoCreateDto(

      @NotBlank @Size(min = 3,max = 74) String nome,

      @NotBlank @Size(min = 3,max = 74) String sku,

      @NotNull @DecimalMin(value = "0.00", inclusive = true) BigDecimal preco

) {

}
