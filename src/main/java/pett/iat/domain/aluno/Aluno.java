package pett.iat.domain.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import pett.iat.domain.aluno.dto.AlunoCreateDto;
import pett.iat.domain.aluno.dto.AlunoUpdateDto;
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

   private boolean deletado;

   @Column(name = "deletado_at")
   private LocalDateTime deletadoAt;

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

   public Aluno(AlunoCreateDto dto) {
      this.nome = dto.nome();
      this.cpf = dto.cpf();
      this.rg = dto.rg();
      this.rgDataExpedicao = dto.rgDataExpedicao();

      this.telefonePrincipal = dto.telefonePrincipal();
      this.telefonePrincipalTemWhatsapp = Boolean.TRUE.equals(dto.telefonePrincipalTemWhatsapp());

      this.telefoneSecundario = dto.telefoneSecundario();
      this.telefoneSecundarioTemWhatsapp = Boolean.TRUE.equals(dto.telefoneSecundarioTemWhatsapp());

      this.emailPrincipal = dto.emailPrincipal();
      this.emailPrincipalValidado = Boolean.TRUE.equals(dto.emailPrincipalValidado());

      this.emailSecundario = dto.emailSecundario();
      this.emailSecundarioValidado = Boolean.TRUE.equals(dto.emailSecundarioValidado());

      this.cep = dto.cep();
      this.estado = dto.estado();
      this.cidade = dto.cidade();
      this.logradouro = dto.logradouro();
      this.numeroLogradouro = dto.numeroLogradouro();
      this.complemento = dto.complemento();
   }

   public void deletar(){
      this.deletado = true;
      this.deletadoAt = LocalDateTime.now();
   }

   public void recuperar(){
      this.deletado = false;
      this.deletadoAt = null;
   }


   public void atualizar(AlunoUpdateDto dto) {

      if (dto.nome() != null) {
         this.nome = dto.nome();
      }

      if (dto.cpf() != null) {
         this.cpf = dto.cpf();
      }

      if (dto.rg() != null) {
         this.rg = dto.rg();
      }

      if (dto.rgDataExpedicao() != null) {
         this.rgDataExpedicao = dto.rgDataExpedicao();
      }

      if (dto.telefonePrincipal() != null) {
         this.telefonePrincipal = dto.telefonePrincipal();
      }

      if (dto.telefonePrincipalTemWhatsapp() != null) {
         this.telefonePrincipalTemWhatsapp = dto.telefonePrincipalTemWhatsapp();
      }

      if (dto.telefoneSecundario() != null) {
         this.telefoneSecundario = dto.telefoneSecundario();
      }

      if (dto.telefoneSecundarioTemWhatsapp() != null) {
         this.telefoneSecundarioTemWhatsapp = dto.telefoneSecundarioTemWhatsapp();
      }

      if (dto.emailPrincipal() != null) {
         this.emailPrincipal = dto.emailPrincipal();
      }

      if (dto.emailPrincipalValidado() != null) {
         this.emailPrincipalValidado = dto.emailPrincipalValidado();
      }

      if (dto.emailSecundario() != null) {
         this.emailSecundario = dto.emailSecundario();
      }

      if (dto.emailSecundarioValidado() != null) {
         this.emailSecundarioValidado = dto.emailSecundarioValidado();
      }

      if (dto.cep() != null) {
         this.cep = dto.cep();
      }

      if (dto.estado() != null) {
         this.estado = dto.estado();
      }

      if (dto.cidade() != null) {
         this.cidade = dto.cidade();
      }

      if (dto.logradouro() != null) {
         this.logradouro = dto.logradouro();
      }

      if (dto.numeroLogradouro() >= 0) {
         this.numeroLogradouro = dto.numeroLogradouro();
      }

      if (dto.complemento() != null) {
         this.complemento = dto.complemento();
      }
   } 
}
