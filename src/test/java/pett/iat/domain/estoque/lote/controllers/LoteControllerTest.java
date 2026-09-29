package pett.iat.domain.estoque.lote.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

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
import pett.iat.domain.estoque.lote.Lote;
import pett.iat.domain.estoque.lote.dto.LoteCreateDto;
import pett.iat.domain.estoque.lote.dto.LoteDetailDto;
import pett.iat.domain.estoque.lote.dto.LoteUpdateDto;
import pett.iat.domain.produto.municao.Municao;
import pett.iat.domain.produto.municao.MunicaoRepository;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class LoteControllerTest extends BaseTest {

      @Autowired
      private MockMvc mockMvc;

      @Autowired
      private JacksonTester<LoteCreateDto> createJson;

      @Autowired
      private JacksonTester<LoteUpdateDto> updateJson;

      @Autowired
      private CalibreRespository calibreRespository;

      @Autowired
      private MunicaoRepository municaoRepository;

      private Calibre calibreControle;
      private Municao municaoControle;
      private Lote loteControle;

      @BeforeEach
      void setup() {
            calibreControle = calibreRespository.save(new Calibre("calibrecontrole"));

            municaoControle = municaoRepository.save(new Municao(
                        "Munição Point Hollow",
                        "MUNI-9MM-001",
                        new BigDecimal("150.50"),
                        50,
                        calibreControle));

            Lote lote = Lote.builder().codigo("CONTROLE").municao(municaoControle).build();
            loteControle = loteRepository.save(lote);
      }

      @Test
      @Transactional
      void deveCadastrarUmNovoLote() throws Exception {
            var loteDto = new LoteCreateDto(
                        "PETT",
                        municaoControle.getId());

            var response = mockMvc.perform(post("/lote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson.write(loteDto).getJson()))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse();

            var json = response.getContentAsString();
            var loteResponse = new ObjectMapper().readValue(json, LoteDetailDto.class);

            assertThat(loteResponse.codigo()).isEqualTo("PETT");
            assertThat(loteResponse.nomeMunicao()).isEqualTo(municaoControle.getNome());

            var loteSalvo = loteRepository.findByCodigo("PETT");

            assertThat(loteSalvo).isPresent();
            assertThat(loteSalvo.get().getCodigo()).isEqualTo("PETT");
      }

      @Test
      @Transactional
      void devoConseguirAtualizarUmCodigo() throws Exception {
            var dto = new LoteUpdateDto(
                        loteControle.getId(),
                        "ATUALIZADO",
                        municaoControle.getId());

            var response = mockMvc.perform(put("/lote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson.write(dto).getJson()))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse();

            var json = response.getContentAsString();
            var loteResponse = new ObjectMapper().readValue(json, LoteDetailDto.class);

            assertThat(loteResponse.codigo()).isEqualTo("ATUALIZADO");

            var loteAtualizado = loteRepository.findById(loteControle.getId());

            assertThat(loteAtualizado).isPresent();
            assertThat(loteAtualizado.get().getCodigo()).isEqualTo("ATUALIZADO");
      }

      @Test
      @Transactional
      void naoDevoConseguirAtualizarUmCodigoParaUmJaExistente() throws Exception {
            var dto = new LoteUpdateDto(loteControle.getId(), "CONTROLE", municaoControle.getId());

            var response = mockMvc.perform(put("/lote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson.write(dto).getJson()))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResponse();

            assertThat(response.getContentAsString())
                        .contains("Ja existe um lote cadastrado com esse codigo");

      }

      @Test
      @Transactional
      void devoConseguirDeletarUmLoteQueNaoTenhaEmbalagensVinculadas() throws Exception {
            mockMvc.perform(delete("/lote/" + loteControle.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
      }

      @Test
      @Transactional
      void naoDevoConseguirDeletarUmLoteQueNaoExista() throws Exception {
            var response = mockMvc.perform(delete("/lote/" + 9876)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .getResponse();

            assertThat(response.getContentAsString())
                        .contains("Nenhum lote encontrado para o id:");
      }

      @Test
      void devoConseguirListarOsLotesPorIdProdutoMunicao() throws Exception {

            mockMvc.perform(get("/lote/produtos/" + municaoControle.getId() + "/lotes"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$[0].id").exists())
                        .andExpect(jsonPath("$[0].codigo").value("CONTROLE"))
                        .andExpect(jsonPath("$[0].nomeMunicao").value("Munição Point Hollow"));
      }
}
