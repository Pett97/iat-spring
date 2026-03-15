package pett.iat.domain.arma.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.arma.dtos.ArmaCreateDto;

@Component
public class ValidarNumeroSerieArma implements ValidacaoCriarArma {

   @Autowired
   private ArmaRepository armaRepository;

   public void validar(ArmaCreateDto dto) {
      var numeroSerieExiste = armaRepository.findByNumeroSerie(dto.numeroSerie().toUpperCase().trim());
      var numeroSerieCano = armaRepository.findByNumeroCano(dto.numeroCano().toUpperCase().trim());
      if (numeroSerieExiste.isPresent()) {
         throw new ValidacaoExecption("numero de arma informado ja existe no sistema");
      }

      if (numeroSerieCano.isPresent()) {
         throw new ValidacaoExecption("numero de cano informado ja existe no sistema");
      }
   }
}
