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

@Service
public class EmbalagemService {

   @Autowired
   private EmbalagemRepository embalagemRepository;

   @Autowired
   private LoteRepository loteRepository;

   private List<ValidarCriarEmbalagem> validacoesAoCriarEmbalagem;
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

      return new EmbalagemDetailDto(embalagem);
   }

   public List<EmbalagemDetailDto> listar() {
      return this.embalagemRepository.findAll().stream().map(EmbalagemDetailDto::new).toList();
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
   public EmbalagemDetailDto atualizar(Long id, EmbalagemUpdateDto dados) {
      this.validacoesAtualizarEmbalagem.forEach(regras->regras.validar(dados));
      var embalagem = this.getEmbalagemById(id);

      var lote = this.getLoteById(dados.idLote());

      embalagem.atualizar(dados, lote);
      return new EmbalagemDetailDto(embalagem);
   }

   private Embalagem getEmbalagemById(Long id) {
      return this.embalagemRepository.findById(id).get();
   }

   private Lote getLoteById(Long id) {
      return this.loteRepository.findById(id).get();
   }
}
