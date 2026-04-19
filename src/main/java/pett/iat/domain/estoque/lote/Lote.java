package pett.iat.domain.estoque.lote;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.estoque.lote.dto.LoteUpdateDto;
import pett.iat.domain.produto.municao.Municao;

@Entity(name = "Lote")
@Table(name = "lotes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = "id")
public class Lote {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String codigo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "produto_id", nullable = false)
   private Municao municao;

   @PrePersist
   @PreUpdate
   private void formatarDados() {
      this.codigo = this.codigo != null ? this.codigo.toUpperCase().trim() : null;
   }

   public void atualizar(LoteUpdateDto dto, Municao municaoAtualizada) {
      if (dto.codigo() != null) {
         this.codigo = dto.codigo().toUpperCase().trim();
      }
      if (municaoAtualizada != null) {
         this.municao = municaoAtualizada;
      }
   }
}
