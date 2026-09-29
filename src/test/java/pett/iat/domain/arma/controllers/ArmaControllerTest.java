package pett.iat.domain.arma.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import pett.iat.domain.BaseTest;
import jakarta.transaction.Transactional;
import pett.iat.IatApplication;
import pett.iat.domain.arma.Arma;
import pett.iat.domain.arma.ArmaRepository;
import pett.iat.domain.arma.dtos.ArmaCreateDto;
import pett.iat.domain.arma.dtos.ArmaUpdateDto;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.marcas.Marca;
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.enums.*;

import java.sql.Date;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ArmaControllerTest extends BaseTest {

      @Autowired
      private MockMvc mockMvc;

      @Autowired
      private JacksonTester<ArmaCreateDto> createJson;

      @Autowired
      private JacksonTester<ArmaUpdateDto> updateJson;

      @Autowired
      private ArmaRepository armaRepository;

      @Autowired
      private MarcasARepository marcasRepository;

      @Autowired
      private CalibreRespository calibreRepository;

      private Marca marca;
      private Calibre calibre;

      @BeforeEach
      void setup() {
            marca = marcasRepository.save(new Marca("GLOCK"));
            calibre = calibreRepository.save(new Calibre("9mm"));
      }

      // =========================
      // CREATE
      // =========================
      @Test
      @Transactional
      void deveriaCadastrarArma() throws Exception {

            var dto = new ArmaCreateDto(
                        LocalRegistroArma.SIGMA,
                        "CRAF123",
                        "SERIE001",
                        "CANO001",
                        "G17",
                        calibre.getId(),
                        marca.getId(),
                        TipoAlmaArma.RAIADA,
                        TipoUsoArma.PERMITIDA,
                        6,
                        SentidoRaiasArma.DIREITA,
                        false,
                        Date.valueOf("2030-01-01"));

            mockMvc.perform(post("/armas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson.write(dto).getJson()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.numeroSerie").value("SERIE001"));
      }

      // =========================
      // LIST
      // =========================
      @Test
      @Transactional
      void deveriaListarArmas() throws Exception {

            armaRepository.save(new Arma(
                        LocalRegistroArma.SINARM,
                        "CRAF",
                        "SERIE_LISTA",
                        "CANO",
                        "MODEL",
                        calibre,
                        marca,
                        TipoAlmaArma.RAIADA,
                        TipoUsoArma.PERMITIDA,
                        6,
                        SentidoRaiasArma.DIREITA,
                        false,
                        Date.valueOf("2030-01-01")));

            mockMvc.perform(get("/armas"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$.length()").value(1));
      }

      // =========================
      // UPDATE
      // =========================
      @Test
      @Transactional
      void deveriaAtualizarArma() throws Exception {

            var arma = armaRepository.save(new Arma(
                        LocalRegistroArma.SINARM,
                        "CRAF",
                        "ORIGINAL",
                        "CANO",
                        "MODEL",
                        calibre,
                        marca,
                        TipoAlmaArma.RAIADA,
                        TipoUsoArma.PERMITIDA,
                        6,
                        SentidoRaiasArma.DIREITA,
                        false,
                        Date.valueOf("2030-01-01")));

            var dto = new ArmaUpdateDto(
                        arma.getId(),
                        null,
                        null,
                        "SERIE_NOVA",
                        null,
                        "Modelo Novo",
                        null,
                        null,
                        null,
                        null,
                        2,
                        null,
                        null,
                        null);

            mockMvc.perform(put("/armas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson.write(dto).getJson()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.numeroSerie").value("SERIE_NOVA"))
                        .andExpect(jsonPath("$.modelo").value("Modelo Novo"));
      }

      // =========================
      // DELETE
      // =========================
      @Test
      @Transactional
      void deveriaDeletarArma() throws Exception {

            var arma = armaRepository.save(new Arma(
                        LocalRegistroArma.SINARM,
                        "CRAF",
                        "DELETE",
                        "CANO",
                        "MODEL",
                        calibre,
                        marca,
                        TipoAlmaArma.RAIADA,
                        TipoUsoArma.PERMITIDA,
                        6,
                        SentidoRaiasArma.DIREITA,
                        false,
                        Date.valueOf("2030-01-01")));

            mockMvc.perform(delete("/armas/" + arma.getId()))
                        .andExpect(status().isNoContent());

            var armaBanco = armaRepository.findById(arma.getId()).orElseThrow();

            assertThat(armaBanco.getDeletada()).isTrue();
      }
}