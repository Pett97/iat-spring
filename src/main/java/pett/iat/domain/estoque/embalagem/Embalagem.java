package pett.iat.domain.estoque.embalagem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.enums.StatusMunicao;

import java.time.LocalDateTime;

@Entity(name = "Embalagem")
@Table(name = "codigos_embalagens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Embalagem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String codigo;

   @Enumerated(EnumType.STRING)
   @Column(name = "status_municao")
   private StatusMunicao statusMunicao;

   private LocalDateTime dataEntrada;

   public LocalDateTime dataSaida;

   @PrePersist
   private void prePersist() {
      this.codigo = this.codigo != null ? this.codigo.toUpperCase().trim() : null;
      if (this.dataEntrada == null) {
         this.dataEntrada = LocalDateTime.now();
      }
   }

   @PreUpdate
   private void preUpdate() {
      this.codigo = this.codigo != null ? this.codigo.toUpperCase().trim() : null;
   }

   public void registrarSaida(StatusMunicao status) {
      this.dataSaida = LocalDateTime.now();
      this.statusMunicao = status;
   }
}
