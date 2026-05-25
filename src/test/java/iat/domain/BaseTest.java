package iat.domain;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import pett.iat.domain.aluno.AlunoRepository;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.estoque.embalagem.EmbalagemRepository;
import pett.iat.domain.estoque.lote.LoteRepository;
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.domain.produto.municao.MunicaoRepository;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTest {

   @Autowired
   protected ArmaRepository armaRepository;

   @Autowired
   protected CalibreRespository calibreRepository;

   @Autowired
   protected MarcasARepository marcasRepository;

   @Autowired
   protected MunicaoRepository municaoRepository;

   @Autowired
   protected LoteRepository loteRepository;

   @Autowired
   protected EmbalagemRepository embalagemRepository;

   @Autowired
   protected AlunoRepository alunoRepository;

   @BeforeEach
   void limparBanco() {
      embalagemRepository.deleteAllInBatch();
      loteRepository.deleteAllInBatch();  
      municaoRepository.deleteAllInBatch();
      armaRepository.deleteAllInBatch();
      calibreRepository.deleteAllInBatch();
      marcasRepository.deleteAllInBatch();
      alunoRepository.deleteAllInBatch();
   }
}