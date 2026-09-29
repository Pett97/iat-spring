package pett.iat.domain.estoque.embalagem.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pett.iat.domain.BaseTest;
import jakarta.transaction.Transactional;
import pett.iat.IatApplication;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.estoque.embalagem.Embalagem;
import pett.iat.domain.estoque.embalagem.EmbalagemRepository;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemCreateDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemDetailDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.domain.estoque.lote.LoteRepository;
import pett.iat.domain.produto.municao.Municao;
import pett.iat.domain.produto.municao.MunicaoRepository;
import pett.iat.enums.StatusMunicao;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class EmbalagemControllerTest extends BaseTest {

      @Autowired
      private MockMvc mockMvc;

      @Autowired
      private JacksonTester<EmbalagemCreateDto> createJson;

      @Autowired
      private JacksonTester<EmbalagemUpdateDto> updateJson;

      @Autowired
      private EmbalagemRepository embalagemRepository;

      @Autowired
      private LoteRepository loteRepository;

      @Autowired
      private MunicaoRepository municaoRepository;

      @Autowired
      private CalibreRespository calibreRespository;

      private Lote loteControle;
      private Embalagem embalagemControle;

      @BeforeEach
      void setup() {
            var calibre = calibreRespository.save(new Calibre("9mm"));

            var municao = municaoRepository.save(new Municao(
                        "Munição Treino", "MUNI-TEST-01", new BigDecimal("10.00"), 100, calibre));

            loteControle = loteRepository.save(Lote.builder()
                        .codigo("LOTE-BASE")
                        .municao(municao)
                        .build());
            embalagemControle = embalagemRepository.save(Embalagem.builder()
                        .codigo("EMB-001")
                        .lote(loteControle)
                        .statusMunicao(StatusMunicao.DISPONIVEL)
                        .build());
      }

      @Test
      @Transactional
      void deveCadastrarUmaNovaEmbalagem() throws Exception {
            var dto = new EmbalagemCreateDto("PETT", loteControle.getId(), null, null);

            var response = mockMvc.perform(post("/embalagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson.write(dto).getJson()))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse();

            var json = response.getContentAsString();
            var embalagemResponse = new ObjectMapper()
                        .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
                        .readValue(json, EmbalagemDetailDto.class);

            assertThat(embalagemResponse.codigo()).isEqualTo("PETT");
      }

      @Test
      @Transactional
      void deveAtualizarUmaEmbalagem() throws Exception {
            var dto = new EmbalagemUpdateDto(
                        embalagemControle.getId(),
                        "ATUALIZADO",
                        loteControle.getId(),
                        StatusMunicao.EXTRAVIADO,
                        null,
                        null);

            mockMvc.perform(put("/embalagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson.write(dto).getJson()))
                        .andExpect(status().isOk());

            var atualizada = embalagemRepository.findById(embalagemControle.getId()).get();
            assertThat(atualizada.getCodigo()).isEqualTo("ATUALIZADO");
            assertThat(atualizada.getStatusMunicao()).isEqualTo(StatusMunicao.EXTRAVIADO);
      }

      @Test
      void deveBuscarEmbalagemPorId() throws Exception {
            mockMvc.perform(get("/embalagem/" + embalagemControle.getId()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.codigo").value("EMB-001"));
      }

      @Test
      void deveListarTodasAsEmbalagens() throws Exception {
            mockMvc.perform(get("/embalagem"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$[0].codigo").value(embalagemControle.getCodigo()));
      }

      @Test
      @Transactional
      void deveDeletarUmaEmbalagem() throws Exception {
            mockMvc.perform(delete("/embalagem/" + embalagemControle.getId()))
                        .andExpect(status().isNoContent());

            assertThat(embalagemRepository.findById(embalagemControle.getId())).isEmpty();
      }

      @Test
      @Transactional
      void naoDeveDeletarUmCodigoDeEmbalagemQueJaTemSaidaRegistrada() throws Exception {
            Embalagem embalagemTeste = embalagemRepository.save(Embalagem.builder()
                        .codigo("erro")
                        .lote(loteControle)
                        .statusMunicao(StatusMunicao.DISPONIVEL)
                        .dataSaida(LocalDateTime.now())
                        .build());

            mockMvc.perform(delete("/embalagem/" + embalagemTeste.getId())).andExpect(status().isBadRequest());
      }
}