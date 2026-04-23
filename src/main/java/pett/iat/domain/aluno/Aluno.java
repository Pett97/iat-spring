package pett.iat.domain.aluno;

import java.time.LocalDate;

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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pett.iat.enums.Estado;

@Entity(name = "Aluno")
@Table(name = "alunos")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nome;

   private String cpf;

   private String rg;

   @Column(name = "rg_data_expedicao")
   private LocalDate rgDataExpedicao;

   @Column(name = "telefone_principal")
   private String telefonePrincipal;

   @Column(name = "telefone_principal_whatsapp")
   private boolean telefonePrincipalTemWhatsapp;

   @Column(name = "telefone_secundario")
   private String telefoneSecundario;

   @Column(name = "telefone_secundario_whatsapp")
   private boolean telefoneSecundarioTemWhatsapp;

   @Column(name = "email_principal")
   private String emailPrincipal;

   @Column(name = "email_principal_validado")
   private boolean emailPrincipalValidado;

   @Column(name = "email_secundario")
   private String emailSecundario;

   @Column(name = "email_secundario_validado")
   private boolean emailSecundarioValidado;

   private String cep;

   @Enumerated(EnumType.STRING)
   private Estado estado;

   private String cidade;

   private String logradouro;

   @Column(name = "numero_logradouro")
   private int numeroLogradouro;

   private String complemento;

   @PrePersist
   private void prePersist() {
      this.emailPrincipal = this.emailPrincipal != null ? this.emailPrincipal.toUpperCase().trim() : null;

      this.emailSecundario = this.emailSecundario != null ? this.emailSecundario.toUpperCase().trim() : null;
   }

   @PreUpdate
   private void preUpdate() {
      this.emailPrincipal = this.emailPrincipal != null ? this.emailPrincipal.toUpperCase().trim() : null;

      this.emailSecundario = this.emailSecundario != null ? this.emailSecundario.toUpperCase().trim() : null;
   }

   public void atualizar(){
      
   }
}
