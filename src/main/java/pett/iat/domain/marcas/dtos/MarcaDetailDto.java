package pett.iat.domain.marcas.dtos;

import pett.iat.domain.marcas.Marca;

public record MarcaDetailDto(Long id, String nome) {

   public MarcaDetailDto(Marca marca) {
      this(marca.getId(), marca.getNome());
   }
}
