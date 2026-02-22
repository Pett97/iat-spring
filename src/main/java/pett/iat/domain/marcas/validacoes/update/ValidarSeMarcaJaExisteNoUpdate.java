package pett.iat.domain.marcas.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;

@Component
public class ValidarSeMarcaJaExisteNoUpdate implements ValidarUpdateMarca {

   @Autowired
   private MarcasARepository marcasARepository;

   public void validarUpdateMarca(MarcaUpdateDto dados) {
      System.out.println("TEste");
      var check = marcasARepository.existsByNomeAndIdNot(dados.nome().toUpperCase(),dados.id());
      if(check){
         throw new ValidacaoExecption("Essa Marca Ja existe");
      }
   }
}
