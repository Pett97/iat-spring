package pett.iat.domain.estoque.operacao;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.produto.Produto;
import pett.iat.enums.Operacao;
import pett.iat.enums.TipoOperacao;

@Entity(name = "Operacao")
// TODO ajustar nome tabela tem que ser operacoes
@Table(name = "operacao")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class OperacaoEstoque {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   @Enumerated(EnumType.STRING)
   Operacao operacao;

   String descricao;

   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_operacao")
   TipoOperacao tipoOperacao;

   @Column(name = "quantidade_movimentada")
   int quantidadeMovimentada;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "produto_id", nullable = false)
   Produto produto;

   @Column(name = "data_operacao")
   LocalDateTime dataOperacao = LocalDateTime.now();

   public OperacaoEstoque(Produto produto, int quantidade, TipoOperacao tipo, Operacao categoria, String descricao) {
      this.produto = produto;
      this.quantidadeMovimentada = quantidade;
      this.tipoOperacao = tipo;
      this.operacao = categoria;
      this.descricao = descricao;
      this.dataOperacao = LocalDateTime.now();
   }
}
