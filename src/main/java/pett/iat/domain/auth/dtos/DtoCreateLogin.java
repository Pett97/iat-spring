package pett.iat.domain.auth.dtos;

import jakarta.validation.constraints.NotNull;
import pett.iat.enums.UsuarioRole;

public record DtoCreateLogin(

            @NotNull String name,

            @NotNull String login,

            @NotNull String password,

            @NotNull String checkPassword,
            
            @NotNull 
            UsuarioRole role

) {

}
