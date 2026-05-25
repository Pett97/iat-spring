package pett.iat.domain.auth.dtos;

import jakarta.validation.constraints.NotNull;

public record DtoCreateLogin(

      @NotNull String login,

      @NotNull String password,

      @NotNull String checkPassword

) {

}
