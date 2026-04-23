package pett.iat.domain.aluno.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record AlunoCreateDto(

      @NotBlank 
      @Size(min = 3) 
      String nome,

      @NotBlank 
      @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números")
      String cpf,

      @NotBlank 
      @Size(min = 7, max = 20) 
      String rg,

      @NotNull
      @Past 
      LocalDate rgDataExpedicao,

      @NotBlank 
      @Pattern(regexp = "\\d{10,11}")
      String telefonePrincipal,

      Boolean telefonePrincipalTemWhatsapp,

      String telefoneSecundario,

      Boolean telefoneSecundarioTemWhatsapp,

      @NotBlank 
      @Email 
      String emailPrincipal,

      Boolean emailPrincipalValidado,

      @Email 
      String emailSecundario,

      Boolean emailSecundarioValidado

) {}