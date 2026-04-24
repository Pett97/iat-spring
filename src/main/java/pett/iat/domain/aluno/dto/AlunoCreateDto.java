package pett.iat.domain.aluno.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import pett.iat.enums.Estado;

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

      Boolean emailSecundarioValidado,

      @NotBlank
      @Size(min = 8)
      String cep,

      @NotBlank
      @Size(min =2)
      Estado estado,
      
      @NotBlank
      @Size(min =4)
      String cidade,

      @NotBlank
      @Size(min =4)
      String logradouro,

      @NotNull
      @Min(0)
      int numeroLogradouro,

      String complemento



) {}