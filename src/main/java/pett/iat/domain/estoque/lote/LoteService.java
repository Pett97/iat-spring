package pett.iat.domain.estoque.lote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.ValidationException;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.estoque.lote.dto.LoteDetailDto;

@Service
public class LoteService {

   @Autowired
   private LoteRepository loteRepository;

   public List<LoteDetailDto> listar() {
      return this.loteRepository.findAll().stream().map(LoteDetailDto::new).toList();
   }

   public List<LoteDetailDto> listarPorProduto(Long id) {

      if (id == null) {
         throw new ValidacaoExecption("Para Listar as municoes por produto informe um produto");
      }
      return this.loteRepository.findByMunicao(id).stream().map(LoteDetailDto::new).toList();
   }
}
