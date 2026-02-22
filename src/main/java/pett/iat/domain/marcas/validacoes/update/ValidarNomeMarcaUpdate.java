package pett.iat.domain.marcas.validacoes.update;

import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;

@Component
public class ValidarNomeMarcaUpdate implements ValidarUpdateMarca {

   public void validarUpdateMarca(MarcaUpdateDto marca) {
      if (marca.nome().length() < 2) {
         throw new ValidacaoExecption("Nome da Marca Precisa ter 2 ou mais caracteres");
      }
   }
}
