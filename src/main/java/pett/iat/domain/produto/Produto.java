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
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Produto")
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // class abstrata não se instancia sozinha
@EqualsAndHashCode(of = "id")
public abstract class Produto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   protected String nome;
   protected String sku;

   @Column(precision = 19, scale = 2)
   private BigDecimal preco;

   @Min(0)
   private int quantidade;

   protected Produto(String nome, String sku, BigDecimal preco,int quantidade) {
      this.nome = nome;
      this.sku = sku;
      this.preco = preco;
      this.quantidade = quantidade;
   }

   public void atualizarProdutoBase(String nome, String sku, BigDecimal preco) {
      if (nome != null) {
         this.nome = nome.toUpperCase();
      }
      if (sku != null) {
         this.sku = sku.toUpperCase().trim();
      }
      if (preco != null) {
         this.preco = preco;
      }
   }

}
