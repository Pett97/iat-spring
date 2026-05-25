package pett.iat.domain.auth.dtos;

import jakarta.validation.constraints.NotNull;

public record DtoLogin(

            @NotNull String login,

            @NotNull String senha) {

}
