package pett.iat.domain.arma.validacoes.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.arma.dtos.ArmaCreateDto;

@Component
public class ValidarCrafArma implements ValidacaoCriarArma {

   @Autowired
   private ArmaRepository armaRepository;

   public void validar(ArmaCreateDto dto) {
      if (dto.numeroCraf() == null || dto.numeroCraf().trim().length() < 3) {
         throw new ValidacaoExecption("O número do CRAF é obrigatório e deve ter no mínimo 3 caracteres");
      }

      var armaExistente = this.armaRepository.findByNumeroCraf(dto.numeroCraf());

      if (armaExistente.isPresent()) {
         throw new ValidacaoExecption(
               "Este número de CRAF já está registrado no sistema. Por favor, confira os dados.");
      }

      if (!dto.vencimentoIndeterminado() && dto.dataVencimentoCraf() == null) {
         throw new ValidacaoExecption(
               "Para armas com vencimento determinado, a data de vencimento do CRAF é obrigatória.");
      }

   }
}
