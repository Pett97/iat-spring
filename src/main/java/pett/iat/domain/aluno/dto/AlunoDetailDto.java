package pett.iat.domain.aluno.dto;

import java.time.LocalDate;

import pett.iat.domain.aluno.Aluno;
import pett.iat.enums.Estado;

public record AlunoDetailDto(

      Long id,
      String nome,
      String cpf,
      String rg,
      LocalDate rgDataExpedicao,

      String telefonePrincipal,
      boolean telefonePrincipalTemWhatsapp,

      String telefoneSecundario,
      boolean telefoneSecundarioTemWhatsapp,

      String emailPrincipal,
      boolean emailPrincipalValidado,

      String emailSecundario,
      boolean emailSecundarioValidado,

      String cep,
      Estado estado,
      String cidade,

      String logradouro,
      int numeroLogradouro,
      String complemento

) {

   public AlunoDetailDto(Aluno aluno) {
      this(
            aluno.getId(),
            aluno.getNome(),
            aluno.getCpf(),
            aluno.getRg(),
            aluno.getRgDataExpedicao(),
            aluno.getTelefonePrincipal(),
            aluno.isTelefonePrincipalTemWhatsapp(),
            aluno.getTelefoneSecundario(),
            aluno.isTelefoneSecundarioTemWhatsapp(),
            aluno.getEmailPrincipal(),
            aluno.isEmailPrincipalValidado(),
            aluno.getEmailSecundario(),
            aluno.isEmailSecundarioValidado(),
            aluno.getCep(),
            aluno.getEstado(),
            aluno.getCidade(),
            aluno.getLogradouro(),
            aluno.getNumeroLogradouro(),
            aluno.getComplemento());
   }
}