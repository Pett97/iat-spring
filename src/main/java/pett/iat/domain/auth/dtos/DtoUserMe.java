package pett.iat.domain.auth.dtos;

import pett.iat.enums.UsuarioRole;

public record DtoUserMe(String login, String name, UsuarioRole role) {
}
