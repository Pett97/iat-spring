package pett.iat.domain.calibre;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.calibre.dtos.CalibreUpdateDto;

@Entity(name = "Calibre")
@Table(name = "calibres")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Calibre {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   public void atualizarNome(CalibreUpdateDto dados) {
      if (dados.nome() != null) {
         this.nome = dados.nome().toUpperCase().trim();
      }
   }

}
