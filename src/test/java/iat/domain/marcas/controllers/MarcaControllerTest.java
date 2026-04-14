package iat.domain.marcas.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import pett.iat.IatApplication;
import pett.iat.domain.marcas.Marca;
import pett.iat.domain.marcas.MarcasARepository;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class MarcaControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private MarcasARepository marcasARepository;

   @Autowired
   private JacksonTester<MarcaCreateDto> marcaCreateDtoJson;

   @Autowired
   private JacksonTester<MarcaUpdateDto> marcaUpdateDtoJson;

   @BeforeEach
   void limparBanco() {
      marcasARepository.deleteAll();
   }

   @Test
   @DisplayName("Deve ser possivel listar todos as marcas")
   void deveListarMarcas() throws Exception {
      marcasARepository.save(new Marca("CBC"));
      marcasARepository.save(new Marca("BERRETA"));

      mockMvc.perform(get("/marcas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2));
   }

   @Test
   @DisplayName("Devo Conseguir buscar apenas um marca por id")
   void devoConseguirBuscarMarcaPorId() throws Exception {
      var marca = marcasARepository.save(new Marca("CBC"));

      mockMvc.perform(get("/marcas/" + marca.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(marca.getId()))
            .andExpect(jsonPath("$.nome").value("CBC"));
   }

   @Test
   @DisplayName("Deveria devolver código 200 ao cadastrar marca válida")
   void cadastrar_cenario1() throws Exception {
      var nomeMarca = "TAURUS";

      mockMvc.perform(
            post("/marcas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(marcaCreateDtoJson.write(
                        new MarcaCreateDto(nomeMarca)).getJson()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value(nomeMarca));
   }

   @Test
   @DisplayName("Deveria devolver código 400 ao cadastrar marca inválida (sem nome)")
   void cadastrar_cenario2() throws Exception {
      mockMvc.perform(
            post("/marcas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content("{}") 
      )
            .andExpect(status().isBadRequest());
   }

   @Test
   @DisplayName("Deveria atualizar o nome de uma marca existente")
   void atualizar_cenario1() throws Exception {
      var marcaSalva = marcasARepository.save(new Marca("Glock"));
      var novoNome = "LULIPA";
      var dto = new MarcaUpdateDto(marcaSalva.getId(), novoNome);

      mockMvc.perform(
            put("/marcas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(marcaUpdateDtoJson.write(dto).getJson()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nome").value(novoNome));
   }

   @Test
   @DisplayName("Deveria deletar uma marca existente e retornar 204")
   void deletar_cenario1() throws Exception {
      var marca = marcasARepository.save(new Marca("Para deletar"));

      mockMvc.perform(delete("/marcas/" + marca.getId()))
            .andExpect(status().isNoContent());

      // Verifica se realmente sumiu do banco
      var existeNoBanco = marcasARepository.existsById(marca.getId());
      assertThat(existeNoBanco).isFalse();
   }

   @Test
   @DisplayName("Deveria retornar 400 (ou erro customizado) ao tentar deletar marca inexistente")
   void deletar_cenario2() throws Exception {
      mockMvc.perform(delete("/marcas/999"))
            .andExpect(status().isBadRequest());
  
   }
}
