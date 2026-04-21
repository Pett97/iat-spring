package pett.iat.domain.estoque.embalagem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Setter;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.enums.StatusMunicao;

import java.time.LocalDateTime;

@Entity(name = "Embalagem")
@Table(name = "codigos_embalagens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@EqualsAndHashCode(of = "id")
public class Embalagem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "numero_serie")
   private String codigo;

   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private StatusMunicao statusMunicao;
   
   @Column(name = "data_entrada")
   private LocalDateTime dataEntrada;

   @Column(name = "data_saida")
   public LocalDateTime dataSaida;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "lote_id", nullable = false)
   public Lote lote;

   @PrePersist
   private void prePersist() {
      this.codigo = this.codigo != null ? this.codigo.toUpperCase().trim() : null;
      if (this.dataEntrada == null) {
         this.dataEntrada = LocalDateTime.now();
      }
      if (this.statusMunicao == null) {
         this.statusMunicao = StatusMunicao.DISPONIVEL;
      }
   }

   @PreUpdate
   private void preUpdate() {
      this.codigo = this.codigo != null ? this.codigo.toUpperCase().trim() : null;
   }


   //anotacao builder cuida
   // public Embalagem(String codigo,StatusMunicao statusMunicao,LocalDateTime dataEntrada, LocalDateTime dataSaida,Lote lote){
   //    this.codigo = codigo;this.statusMunicao = statusMunicao;this.dataEntrada = dataEntrada;this.dataSaida = dataSaida;this.lote = lote;
   // }

   public void registrarSaida(StatusMunicao status) {
      this.dataSaida = LocalDateTime.now();
      this.statusMunicao = status;
   }

   public void atualizar(EmbalagemUpdateDto dados, Lote novoLote) {
      if (dados.codigo() != null) {
         this.codigo = dados.codigo();
      }
      if (dados.dataEntrada() != null) {
         this.dataEntrada = dados.dataEntrada();
      }
      if (dados.dataSaida() != null) {
         this.dataSaida = dados.dataSaida();
      }
      if (dados.statusMunicao() != null) {
         this.statusMunicao = dados.statusMunicao();
      }
      if (novoLote != null) {
         this.lote = novoLote;
      }

   }
}
