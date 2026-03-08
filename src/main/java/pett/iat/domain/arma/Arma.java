package pett.iat.domain.arma;

import java.sql.Date;
import java.time.LocalDateTime;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.marcas.Marca;
import pett.iat.enums.LocalRegistroArma;
import pett.iat.enums.SentidoRaiasArma;
import pett.iat.enums.TipoAlmaArma;
import pett.iat.enums.TipoUsoArma;

@Entity(name = "Arma")
@Table(name = "armas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Arma {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "local_registro_arma")
   @Enumerated(EnumType.STRING)
   private LocalRegistroArma localRegistroArma;

   @Column(name = "numero_craf")
   private String numeroCraf;

   @Column(name = "numero_serie")
   private String numeroSerie;   

   @Column(name = "numero_cano")
   private String numeroCano;

   private String modelo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "calibre.id", nullable = false)
   private Calibre calibre;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "marca.id", nullable = false)
   private Marca marca;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_alma_arma")
   private TipoAlmaArma tipoAlmaArma;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_uso_arma")
   private TipoUsoArma tipoUsoArma;

   @Column(name = "numero_raias")
   private int numeroRaias;

   @Enumerated(EnumType.STRING)
   @Column(name = "sentido_raia_arma")
   private SentidoRaiasArma sentidoRaiasArma;

   @Column(name ="vencimento_indeterminado")
   // indeterminado 1 armas de militar
   private Boolean vencimentoIndeterminado;

   @Column(name = "data_vencimento_craf")
   private Date dataVencimentoCraf;

   private Boolean deletada;

   @Column(name = "delete_at")
   private LocalDateTime deletedAt;
   
   public Arma(ArmaCreateDto dto, Calibre calibre, Marca marca) {
      this.localRegistroArma = dto.localRegistroArma();
      this.numeroCraf = dto.numeroCraf();
      this.numeroSerie = dto.numeroSerie().toUpperCase().trim();
      this.numeroCano = dto.numeroCano().toUpperCase().trim();
      this.modelo = dto.modelo();
      this.tipoAlmaArma = dto.tipoAlmaArma();
      this.tipoUsoArma = dto.tipoUsoArma();
      this.numeroRaias = dto.numeroRaias();
      this.sentidoRaiasArma = dto.sentidoRaiasArma();
      this.calibre = calibre;
      this.marca = marca;
      this.vencimentoIndeterminado = dto.vencimentoIndeterminado();
      this.dataVencimentoCraf = dto.dataVencimentoCraf();
      this.deletada = false;
   }

   public void deletar(){
      this.deletada = true;
      this.deletedAt = LocalDateTime.now();
   }

}
