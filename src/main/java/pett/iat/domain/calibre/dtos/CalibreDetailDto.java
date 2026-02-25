package pett.iat.domain.calibre.dtos;

import pett.iat.domain.calibre.Calibre;

public record CalibreDetailDto(Long id, String nome) {

   public CalibreDetailDto(Calibre calibre) {
      this(calibre.getId(), calibre.getNome());
   }
}
