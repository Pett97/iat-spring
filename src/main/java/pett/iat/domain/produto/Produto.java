package pett.iat.domain.produto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.produto.dtos.ProdutoUpdateDto;

@Entity(name = "Produto")
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //class abstrata não se instancia sozinha 
@EqualsAndHashCode(of = "id")
public abstract class Produto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String nome;
   private String sku;

   @Column(precision = 19,scale = 2)
   private BigDecimal preco;

   protected Produto(String nome, String sku, BigDecimal preco) {
      this.nome = nome;
      this.sku = sku;
      this.preco = preco;
   }

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
