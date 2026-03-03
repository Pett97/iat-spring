package pett.iat.domain.arma.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.arma.dtos.ArmaCreateDto;

@Component
public class ValidarNumeroCrafArma implements ValidacaoCriarArma{

   @Autowired
   private ArmaRepository armaRepository;

   public void validar(ArmaCreateDto dto){
      var checkNumeroCrafJaExiste = armaRepository.findByNumeroCraf(dto.numeroCraf());
      if(checkNumeroCrafJaExiste !=null){
         throw new ValidacaoExecption("esse numero craf já está registrado no sistema por favor conifira os dados");
      }

      if(dto.numeroCraf().length()<3){
         throw new ValidacaoExecption("numero do craf tem valor minimo de 3 caracteres");
      }

      //TODO terminar de implementar aqui 
   }
}
