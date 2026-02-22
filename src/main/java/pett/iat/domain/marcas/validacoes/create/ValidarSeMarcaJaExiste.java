package pett.iat.domain.marcas.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;

@Component
public class ValidarSeMarcaJaExiste implements ValidacaoMarcaCreate {

   @Autowired
   private MarcasARepository marcasARepository;

   public void validar(MarcaCreateDto marca) {
      var check = marcasARepository.existsByNome(marca.nome().toUpperCase());
      if (check) {
         throw new ValidacaoExecption("Essa Marca Ja Existe");
      }
   }
}
