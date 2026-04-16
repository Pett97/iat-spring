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
import pett.iat.domain.produto.municao.Municao;
import pett.iat.domain.produto.municao.MunicaoRepository;

@Service
public class LoteService {

   @Autowired
   private LoteRepository loteRepository;

   @Autowired
   private EmbalagemRepository embalagemRepository;

   @Autowired
   private MunicaoRepository municaoRepository;

   @Autowired
   private List<ValidarLoteCreate> validarLoteCreate;

   @Autowired
   private List<ValidarLoteUpdate> validarLoteUpdate;

   public List<LoteDetailDto> listar() {
      return this.loteRepository.findAll().stream().map(LoteDetailDto::new).toList();
   }

   public LoteDetailDto getLoteById(Long id) {
      Lote lote = this.loteRepository.getReferenceById(id);
      return new LoteDetailDto(lote);
   }

   public LoteDetailDto cadastrarLote(LoteCreateDto dto) {

      this.validarLoteCreate.forEach(regra -> regra.validar(dto));
      var municao = this.getMunicaoById(dto.produtoId());
      Lote lote = Lote.builder()
            .codigo(dto.codigo())
            .municao(municao)
            .build();
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

   public void deletar(Long id) {

      if (!this.loteRepository.existsById(id)) {
         throw new ValidacaoExecption("Nenhum lote encontrado para o id: " + id);
      }

      if (this.embalagemRepository.existsByLoteId(id)) {
         throw new ValidacaoExecption(
               "Não è possivel deletar o lote com id: " + id + "existem embalagens vinculadas");
      }
      this.loteRepository.deleteById(id);
   }

   private Municao getMunicaoById(Long id) {
      return this.municaoRepository.findById(id)
            .orElseThrow(() -> new ValidacaoExecption("Nehuma municao encontrada com o id:" + id));
   }
}
