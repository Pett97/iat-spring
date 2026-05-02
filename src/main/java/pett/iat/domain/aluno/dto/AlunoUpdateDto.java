package pett.iat.domain.aluno.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import pett.iat.enums.Estado;

public record AlunoUpdateDto(

            @NotNull Long id,

            @Size(min = 3) String nome,

            @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números") String cpf,

            @Size(min = 7, max = 20) String rg,

            @Past LocalDate rgDataExpedicao,

            @Pattern(regexp = "\\d{10,11}") String telefonePrincipal,

            Boolean telefonePrincipalTemWhatsapp,

            String telefoneSecundario,

            Boolean telefoneSecundarioTemWhatsapp,

            @Email String emailPrincipal,

            Boolean emailPrincipalValidado,

            @Email String emailSecundario,

            Boolean emailSecundarioValidado,

            @Size(min = 8) String cep,

             Estado estado,

            @Size(min = 4) String cidade,

            @Size(min = 4) String logradouro,

            @Min(0) int numeroLogradouro,

            String complemento

) {

}
