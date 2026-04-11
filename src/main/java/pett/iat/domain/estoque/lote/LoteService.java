package pett.iat.domain.estoque.lote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.ValidationException;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.embalagem.EmbalagemRepository;
import pett.iat.domain.estoque.lote.dto.LoteCreateDto;
import pett.iat.domain.estoque.lote.dto.LoteDetailDto;
import pett.iat.domain.estoque.lote.dto.LoteUpdateDto;
import pett.iat.domain.estoque.lote.validacoes.create.ValidarLoteCreate;
import pett.iat.domain.estoque.lote.validacoes.update.ValidarLoteUpdate;

@Service
public class LoteService {

   @Autowired
   private LoteRepository loteRepository;

   @Autowired
   private EmbalagemRepository embalagemRepository;

   @Autowired
   private List<ValidarLoteCreate> validarLoteCreate;

   @Autowired
   private List<ValidarLoteUpdate> validarLoteUpdate;

   public List<LoteDetailDto> listar() {
      return this.loteRepository.findAll().stream().map(LoteDetailDto::new).toList();
   }

   public LoteDetailDto cadastrarLote(LoteCreateDto dto) {

      this.validarLoteCreate.forEach(regra -> regra.validar(dto));

      Lote lote = Lote.builder().codigo(dto.codigo()).build();
      this.loteRepository.save(lote);

      return new LoteDetailDto(lote);
   }

   public LoteDetailDto atualizarLote(LoteUpdateDto dto) {
      this.validarLoteUpdate.forEach(regra -> regra.validar(dto));

      Lote lote = Lote.builder().codigo(dto.codigo()).build();
      lote.atualizar(dto);

      return new LoteDetailDto(lote);
   }

   public List<LoteDetailDto> listarPorProduto(Long id) {
      if (id == null) {
         throw new ValidacaoExecption("Para Listar as municoes por produto informe um produto");
      }
      return this.loteRepository.findByMunicaoId(id).stream().map(LoteDetailDto::new).toList();
   }

   public void deletar(Lote lote) {

      if (!this.loteRepository.existsById(lote.getId())) {
         throw new ValidacaoExecption("Nenhum lote encontrado para o id: " + lote.getId());
      }

      if (this.embalagemRepository.existsByLoteId(lote.getId())) {
         throw new ValidacaoExecption(
               "Não è possivel deletar o lote com id: " + lote.getId() + "existem embalagens vinculadas");
      }
      this.loteRepository.deleteById(lote.getId());
   }
}
