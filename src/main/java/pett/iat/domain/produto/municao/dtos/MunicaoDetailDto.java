package pett.iat.domain.produto.municao.dtos;

import java.math.BigDecimal;

import pett.iat.domain.calibre.dtos.CalibreDetailDto;
import pett.iat.domain.produto.municao.Municao;

public record MunicaoDetailDto(
      Long id,
      String nome,
      String sku,
      BigDecimal preco,
      CalibreDetailDto calibre) {

   public MunicaoDetailDto(Municao produto) {
      this(produto.getId(),
            produto.getNome(),
            produto.getSku(),
            produto.getPreco(),
            new CalibreDetailDto(produto.getCalibre()));
   }

}
