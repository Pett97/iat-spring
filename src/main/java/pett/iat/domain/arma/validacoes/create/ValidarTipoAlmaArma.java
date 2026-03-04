package pett.iat.domain.arma.validacoes.create;

import org.springframework.stereotype.Component;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.enums.TipoAlmaArma;

@Component
public class ValidarTipoAlmaArma implements ValidacaoCriarArma {

   public void validar(ArmaCreateDto dto) {
      if (dto.tipoAlmaArma().equals(TipoAlmaArma.RAIADA)) {

         if (dto.sentidoRaiasArma() == null) {
            throw new ValidacaoExecption("sentido raia é obrigatorio para armas raiadas");
         }

         if (dto.numeroRaias() == 0) {
            throw new ValidacaoExecption("numero de raias para arma raiada é obrigatorio");
         }
      }
   }
}
