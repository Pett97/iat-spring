package pett.iat.domain.calibre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.dtos.CalibreCreateDto;
import pett.iat.domain.calibre.dtos.CalibreDetailDto;
import pett.iat.domain.calibre.dtos.CalibreUpdateDto;
import pett.iat.domain.calibre.validacoes.create.ValidacaoCriarCalibre;
import pett.iat.domain.calibre.validacoes.update.ValidacaoesAtualizarCalibre;

@Component
public class CalibreService {;

   @Autowired
   private CalibreRespository calibreRespository;

   @Autowired
   private List<ValidacaoCriarCalibre> validacoesCriarCalibre;

   @Autowired
   private List<ValidacaoesAtualizarCalibre> validacaoesAtualizarCalibre;

   public List<CalibreDetailDto> listar() {
      return calibreRespository.findAll().stream().map(CalibreDetailDto::new).toList();
   }

   public CalibreDetailDto salvar(CalibreCreateDto dados) {
      validacoesCriarCalibre.forEach(regra -> regra.validar(dados));

      var calibre = new Calibre(null, dados.nome().trim().toUpperCase());

      calibreRespository.save(calibre);

      return new CalibreDetailDto(calibre);
   }

   public CalibreDetailDto atualizar(CalibreUpdateDto dados) {
      var calibre = calibreRespository.findById(dados.id())
            .orElseThrow(() -> new ValidacaoExecption("Calibre não encontrado com o ID: " + dados.id()));

      validacaoesAtualizarCalibre.forEach(regra -> regra.validar(dados));

      calibre.atualizarNome(dados);

      return new CalibreDetailDto(calibre);
   }

}