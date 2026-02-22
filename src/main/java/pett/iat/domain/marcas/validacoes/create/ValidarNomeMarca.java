package pett.iat.domain.marcas.validacoes.create;

import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;

@Component
public class ValidarNomeMarca implements ValidacaoMarcaCreate {

   public void validar(MarcaCreateDto marca) {
      if (marca.nome().length() < 2) {
         throw new ValidacaoExecption("Nome da Marca Precisa ter 2 ou mais caracteres");
      }
   }
}
