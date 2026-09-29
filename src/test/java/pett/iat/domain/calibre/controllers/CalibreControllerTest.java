package pett.iat.domain.calibre.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import pett.iat.domain.BaseTest;
import pett.iat.IatApplication;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class CalibreControllerTest extends BaseTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private CalibreRespository calibreRespository;
   
   @Test
   @DisplayName("Deve ser possivel listar todos os calibres")
   void deveListarCalibres() throws Exception {

      calibreRespository.save(new Calibre("9mm"));
      calibreRespository.save(new Calibre(".40"));

      mockMvc.perform(get("/calibres"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2));
   }

   @Test
   @DisplayName("Deve retornar calibre por id")
   void deveRetornarCalibrePorId() throws Exception {

      var calibre = calibreRespository.save(new Calibre("12"));

      mockMvc.perform(get("/calibres/" + calibre.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(calibre.getId()))
            .andExpect(jsonPath("$.nome").value("12"));
   }

   @Test
   @DisplayName("Deve criar um calibre")
   void deveCriarCalibre() throws Exception {

      String json = """
               {
                  "nome": "5.56"
               }
            """;

      mockMvc.perform(post("/calibres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("5.56"));
   }

   @Test
   @DisplayName("Deve atualizar um calibre")
   void deveAtualizarCalibre() throws Exception {

      var calibre = calibreRespository.save(new Calibre("Antigo"));

      String json = """
               {
                  "id": %d,
                  "nome": "Novo"
               }
            """.formatted(calibre.getId());

      mockMvc.perform(put("/calibres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value("NOVO"));
   }

   @Test
   @DisplayName("Deve deletar um calibre")
   void deveDeletarCalibre() throws Exception {

      var calibre = calibreRespository.save(new Calibre("Delete"));

      mockMvc.perform(delete("/calibres/" + calibre.getId()))
            .andExpect(status().isNoContent());
   }

   @Test
   @DisplayName("Não devo conseguir cadastrar um calibre que ja existe")
   void naoDeveCadastrarUmCalibreQueJaExiste() throws Exception {

      String json = """
               {
                  "nome": "5.56"
               }
            """;

      // Primeiro cadastro (OK)
      mockMvc.perform(post("/calibres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

      // Segundo cadastro (DEVE FALHAR)
      mockMvc.perform(post("/calibres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
   }

   @Test
   @DisplayName("Não deve atualizar calibre para um nome já existente")
   void naoDeveAtualizarCalibreParaUmExistente() throws Exception {

      var calibre1 = calibreRespository.save(new Calibre("9mm"));
      var calibre2 = calibreRespository.save(new Calibre(".40"));

      String json = """
               {
                  "id": %d,
                  "nome": "9mm"
               }
            """.formatted(calibre2.getId());

      mockMvc.perform(put("/calibres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest());
   }

}