package pett.iat.domain.marcas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;

@Entity(name = "Marca")
@Table(name = "marcas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Marca {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   public void atualizarNome(MarcaUpdateDto dados) {
      if (dados.nome() != null) {
         this.nome = dados.nome().toUpperCase();
      }
   }
}
