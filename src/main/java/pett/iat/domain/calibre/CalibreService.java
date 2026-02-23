package pett.iat.domain.calibre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.calibre.dtos.CalibreCreateDto;
import pett.iat.domain.calibre.validacoes.create.ValidacaoCriarCalibre;

@Component
public class CalibreService {

   @Autowired
   private CalibreRespository calibreRespository;

   private List<ValidacaoCriarCalibre> validacoesCriarCalibre;

   public void salvar(CalibreCreateDto dados) {
      validacoesCriarCalibre.forEach(regra -> regra.validar(dados));

      var calibre = new Calibre(null,dados.nome().trim().toUpperCase());

      calibreRespository.save(calibre);
   }
}
