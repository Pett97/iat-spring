package pett.iat.domain.produto.municao;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.produto.Produto;
import pett.iat.domain.produto.municao.dtos.MunicaoUpdateDto;

@Entity
@Getter
@DiscriminatorValue("MUNICAO") // define o where tipo de produto
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Municao extends Produto {

   public Municao(String nome, String sku, BigDecimal preco, Calibre calibre) {
      super(nome, sku, preco);
      this.calibre = calibre;
   }

   public void atualizar(MunicaoUpdateDto dados,Calibre novoCalibre) {
      super.atualizarProdutoBase(dados.nome(), dados.sku(), dados.preco());
      if(dados.calibreId() !=null && novoCalibre !=null){
         this.calibre = novoCalibre;
      }
   }

   @ManyToOne
   @JoinColumn(name = "calibre_id", nullable = false)
   private Calibre calibre;
}
