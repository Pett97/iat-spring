package pett.iat.domain.estoque.embalagem;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemCreateDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemDetailDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;
import pett.iat.domain.estoque.embalagem.validacoes.create.ValidarCriarEmbalagem;
import pett.iat.domain.estoque.embalagem.validacoes.update.ValidarAtualizarEmbalagem;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.domain.estoque.lote.LoteRepository;
import pett.iat.enums.StatusMunicao;
import pett.iat.helpers.FormatarData;

@Service
public class EmbalagemService {

   @Autowired
   private EmbalagemRepository embalagemRepository;

   @Autowired
   private LoteRepository loteRepository;

   @Autowired
   private List<ValidarCriarEmbalagem> validacoesAoCriarEmbalagem;
   @Autowired
   private List<ValidarAtualizarEmbalagem> validacoesAtualizarEmbalagem;

   public EmbalagemDetailDto cadastrar(EmbalagemCreateDto dados) {
      validacoesAoCriarEmbalagem.forEach(regras -> regras.validar(dados));
      Lote loteEncontrado = this.getLoteById(dados.idLote());
      Embalagem embalagem = Embalagem.builder()
            .codigo(dados.codigo())
            .statusMunicao(dados.statusMunicao())
            .dataEntrada(LocalDateTime.now())
            .lote(loteEncontrado)
            .build();

      this.embalagemRepository.save(embalagem);

      return new EmbalagemDetailDto(embalagem);
   }

   public List<EmbalagemDetailDto> listar() {
      return this.embalagemRepository.findAll().stream().map(EmbalagemDetailDto::new).toList();
   }

   public EmbalagemDetailDto getBydId(Long id) {
      var embalagem = this.getEmbalagemById(id);
      return new EmbalagemDetailDto(embalagem);
   }

   public List<EmbalagemDetailDto> listarEmabalagemSemDataDeSaida() {
      return this.embalagemRepository.findByDataSaidaIsNull().stream().map(EmbalagemDetailDto::new).toList();
   }

   public List<EmbalagemDetailDto> listarEmbalagemComDataDeSaida() {
      return this.embalagemRepository.findByDataSaidaIsNotNull().stream().map(EmbalagemDetailDto::new).toList();
   }

   public List<EmbalagemDetailDto> listarEmabalagemPorStatus(StatusMunicao status) {
      return this.embalagemRepository.findByStatusMunicao(status).stream().map(EmbalagemDetailDto::new).toList();
   }

   public EmbalagemDetailDto atualizar(EmbalagemUpdateDto dados) {
      this.validacoesAtualizarEmbalagem.forEach(regras -> regras.validar(dados));

      var embalagem = this.getEmbalagemById(dados.id());

      var lote = this.getLoteById(dados.idLote());

      embalagem.atualizar(dados, lote);
      return new EmbalagemDetailDto(embalagem);
   }

   public void deletar(Long id) {

      var embalagem = this.getEmbalagemById(id);

      boolean embalagemJaSaiu = this.embalagemRepository.existsByCodigoAndDataSaidaIsNotNull(embalagem.getCodigo());

      if (embalagemJaSaiu) {
         throw new ValidacaoExecption("Essa embalagem não pode ser deletada pois ja foi registrada uma saida:"
               + this.converterData(embalagem.getDataSaida())+" "+embalagem.getStatusMunicao());
      }

      this.embalagemRepository.deleteById(id);
   }

   private String converterData(LocalDateTime data) {
      return FormatarData.converterData(data);
   }

   private Embalagem getEmbalagemById(Long id) {
      return this.embalagemRepository.findById(id)
            .orElseThrow(() -> new ValidacaoExecption("Não Foi encontrada nenhuma Embalagem com esse id:" + id));
   }

   private Lote getLoteById(Long id) {
      return this.loteRepository.findById(id)
            .orElseThrow(() -> new ValidacaoExecption("Não Foi encontrado nenhum lote com esse id:" + id));
   }
}
