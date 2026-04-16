package iat.domain;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.marcas.MarcasARepository;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTest {

   @Autowired
   protected ArmaRepository armaRepository;

   @Autowired
   protected CalibreRespository calibreRepository;

   @Autowired
   protected MarcasARepository marcasRepository;

   @BeforeEach
   void limparBanco() {
      armaRepository.deleteAllInBatch();
      calibreRepository.deleteAllInBatch();
      marcasRepository.deleteAllInBatch();
   }
}