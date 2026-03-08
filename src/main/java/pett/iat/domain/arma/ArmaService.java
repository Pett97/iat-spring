package pett.iat.domain.arma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.domain.arma.dtos.ArmaDetailDto;
import pett.iat.domain.arma.dtos.ArmaUpdateDto;
import pett.iat.domain.arma.validacoes.create.ValidacaoCriarArma;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.marcas.Marca;
import pett.iat.domain.marcas.MarcasARepository;

@Service
public class ArmaService {

   @Autowired
   private CalibreRespository calibreRespository;

   @Autowired
   private MarcasARepository marcasARepository;

   @Autowired
   private ArmaRepository armaRepository;

   @Autowired
   private List<ValidacaoCriarArma> validacoesAoCriarArma;

   public List<ArmaDetailDto> listar() {
      return armaRepository.findAll().stream().map(ArmaDetailDto::new).toList();
   }

   public ArmaDetailDto atualizar(ArmaUpdateDto dto) {
      var arma = armaRepository.findById(dto.id())
            .orElseThrow(() -> new ValidacaoExecption("nenhuma arma encontrada com esse id"));

            
   }

   public ArmaDetailDto salvar(ArmaCreateDto dto) {
      var calibre = this.getCalibreById(dto.calibreId());
      var marca = this.getMarcaById(dto.marcaId());

      if (calibre == null) {
         throw new ValidacaoExecption("não foi encontrado o calibre informado");
      }

      if (marca == null) {
         throw new ValidacaoExecption("não foi encontrado a marca informada");
      }

      if (dto.localRegistroArma() == null) {
         throw new ValidacaoExecption("local de resgitro da arma é obrigatorio sinarm/sigma");
      }

      if (dto.modelo() == null) {
         throw new ValidacaoExecption("modelo da arma é obrgatorio");
      }

      validacoesAoCriarArma.forEach(regra -> regra.validar(dto));
      var arma = new Arma(dto, calibre, marca);
      armaRepository.save(arma);

      return new ArmaDetailDto(arma);
   }

   public void deletar(Long id) {
      var arma = armaRepository.findById(id)
            .orElseThrow(() -> new ValidacaoExecption("Nunca arma encontrada com esse ID:" + id));

      arma.deletar();
   }

   private Calibre getCalibreById(Long id) {
      return calibreRespository.getReferenceById(id);
   }

   private Marca getMarcaById(Long id) {
      return marcasARepository.getReferenceById(id);
   }

}
