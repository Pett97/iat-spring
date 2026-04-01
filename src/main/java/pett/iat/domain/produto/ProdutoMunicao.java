package pett.iat.domain.produto;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import pett.iat.domain.calibre.Calibre;

@Entity
@DiscriminatorValue("MUNICAO")
public class ProdutoMunicao extends Produto {

   public ProdutoMunicao(String nome, String sku, BigDecimal preco, Calibre calibre) {
      super(nome, sku, preco);
      this.calibre = calibre;
   }

   @ManyToOne
   @JoinColumn(name = "calibre_id", nullable = false)
   private Calibre calibre;
}
