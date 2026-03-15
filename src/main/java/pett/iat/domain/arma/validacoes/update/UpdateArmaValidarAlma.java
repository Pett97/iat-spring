package pett.iat.domain.arma.validacoes.update;

import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.dtos.ArmaUpdateDto;
import pett.iat.enums.TipoAlmaArma;

@Component
public class UpdateArmaValidarAlma implements ValidarUpdateArma {

   public void validaArma(ArmaUpdateDto dto) {
      if (dto.tipoAlmaArma()!= null  && dto.tipoAlmaArma().equals(TipoAlmaArma.RAIADA)) {

         if (dto.sentidoRaiasArma() == null) {
            throw new ValidacaoExecption("sentido raia é obrigatorio para armas raiadas");
         }

         if (dto.numeroRaias() == 0) {
            throw new ValidacaoExecption("numero de raias para arma raiada é obrigatorio");
         }
      }
   }
}
