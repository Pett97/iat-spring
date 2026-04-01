package pett.iat.domain.produto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.produto.dtos.ProdutoUpdateDto;
import pett.iat.enums.TipoProduto;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String nome;
   private String sku;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_produto")
   private TipoProduto tipoProduto;

   private BigDecimal preco;

   public void atualizarProduto(ProdutoUpdateDto dados) {
      if (dados.nome() != null) {
         this.nome = dados.nome().toUpperCase();
      }
      if (dados.sku() != null) {
         this.sku = dados.sku().toUpperCase().trim();
      }
      if (dados.preco() != null) {
         this.preco = dados.preco();
      }
   }

}
