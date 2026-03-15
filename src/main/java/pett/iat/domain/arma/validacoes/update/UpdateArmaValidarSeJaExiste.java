package pett.iat.domain.arma.validacoes.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.arma.dtos.ArmaUpdateDto;

@Component
public class UpdateArmaValidarSeJaExiste implements ValidarUpdateArma {

   @Autowired
   private ArmaRepository armaRepository;

   public void validaArma(ArmaUpdateDto dto) {
      this.validarCrafJaExiste(dto);
      this.validarSerieJaExiste(dto);
      this.validarNumeroCanoJaExiste(dto);
   }

   private void validarCrafJaExiste(ArmaUpdateDto dto) {
      if (dto.numeroCraf() != null) {
         var numeroDeCrafJaExiste = armaRepository.existsByNumeroCrafAndIdNot(dto.numeroCraf(), dto.id());
         if (numeroDeCrafJaExiste) {
            throw new ValidacaoExecption("Esse Craf ja está cadastrado no sistema");
         }
      }
   }

   private void validarSerieJaExiste(ArmaUpdateDto dto) {
      if (dto.numeroSerie() != null) {
         var numeroDeSerieJaExiste = armaRepository.existsByNumeroSerieAndIdNot(dto.numeroSerie(), dto.id());
         if (numeroDeSerieJaExiste) {
            throw new ValidacaoExecption("Esse Numero de Serie Ja está cadastrado no sistema");
         }
      }
   }

   private void validarNumeroCanoJaExiste(ArmaUpdateDto dto) {
      if (dto.numeroCano() != null) {
         var numeroDoCanoJaExiste = armaRepository.existsByNumeroCanoAndIdNot(dto.numeroCano(), dto.id());
         if (numeroDoCanoJaExiste) {
            throw new ValidacaoExecption("Esse Numero de Cano já está cadastrado no sistema");
         }
      }
   }

}
