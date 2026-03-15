package pett.iat.domain.arma.dtos;

import pett.iat.enums.LocalRegistroArma;
import pett.iat.enums.SentidoRaiasArma;
import pett.iat.enums.TipoAlmaArma;
import pett.iat.enums.TipoUsoArma;
import pett.iat.domain.arma.Arma;
import pett.iat.domain.calibre.dtos.CalibreDetailDto;
import pett.iat.domain.marcas.dtos.MarcaDetailDto;

public record ArmaDetailDto(
      Long id,
      LocalRegistroArma localRegistroArma,
      String numeroCraf,
      String numeroSerie,
      String numeroCano,
      String modelo,
      TipoAlmaArma tipoAlmaArma,
      TipoUsoArma tipoUsoArma,
      int numeroRaias,
      SentidoRaiasArma sentidoRaiasArma,

      CalibreDetailDto calibre,
      MarcaDetailDto marca) {

   public ArmaDetailDto(Arma arma) {
      this(
            arma.getId(),
            arma.getLocalRegistroArma(),
            arma.getNumeroCraf(),
            arma.getNumeroSerie(),
            arma.getNumeroCano(),
            arma.getModelo(),
            arma.getTipoAlmaArma(),
            arma.getTipoUsoArma(),
            arma.getNumeroRaias(),
            arma.getSentidoRaiasArma(),
            new CalibreDetailDto(arma.getCalibre().getId(), arma.getCalibre().getNome()),
            new MarcaDetailDto(arma.getMarca().getId(), arma.getMarca().getNome()));
   }
}