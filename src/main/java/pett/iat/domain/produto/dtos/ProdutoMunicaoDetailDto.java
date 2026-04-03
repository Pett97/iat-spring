package pett.iat.domain.produto.dtos;

import java.math.BigDecimal;

import pett.iat.domain.calibre.dtos.CalibreDetailDto;
import pett.iat.domain.produto.ProdutoMunicao;

public record ProdutoMunicaoDetailDto(
      Long id,
      String nome,
      String sku,
      BigDecimal preco,
      CalibreDetailDto calibre) {

   public ProdutoMunicaoDetailDto(ProdutoMunicao produto) {
      this(produto.getId(),
            produto.getNome(),
            produto.getSku(),
            produto.getPreco(),
            new CalibreDetailDto(produto.getCalibre()));
   }

}
