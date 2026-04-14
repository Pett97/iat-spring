package  iat.domain.armas.controllers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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
public class ArmaControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ArmaRepository armaRepository;

   @Autowired
   private MarcasARepository marcasRepository;

   @Autowired
   private CalibreRespository calibreRepository;

   @Autowired
   private JacksonTester<ArmaCreateDto> armaCreateDtoJson;

   @Autowired
   private JacksonTester<ArmaUpdateDto> armaUpdateDtoJson;
   //TODO ajustar
   private Marca marca;
   private Calibre calibre;

   @BeforeEach
   void limparBanco() {
      armaRepository.deleteAll();
      marcasRepository.deleteAll();
      calibreRepository.deleteAll();
      marca = marcasRepository.save(new Marca("GLOCK"));
      calibre = calibreRepository.save(new Calibre("9mm"));
   }

   @Test
   @DisplayName("Deveria cadastrar uma arma com sucesso")
   void cadastrar_cenario1() throws Exception {
      var dto = new ArmaCreateDto(
            LocalRegistroArma.SIGMA,
            "CRAF123",
            "SERIE001",
            "CANO001",
            "G17 Gen5",
            calibre.getId(),
            marca.getId(),
            TipoAlmaArma.RAIADA,
            TipoUsoArma.PERMITIDA,
            6,
            SentidoRaiasArma.DIREITA,
            false,
            Date.valueOf("2030-01-01"));

      var response = mockMvc.perform(
            post("/armas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(armaCreateDtoJson.write(dto).getJson()))
            .andReturn().getResponse();

      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getContentAsString()).contains("SERIE001");
   }

   @Test
   @DisplayName("Deveria listar todas as armas")
   void listar_cenario1() throws Exception {
      armaRepository.save(new Arma(criarDtoExemplo("SERIE_LISTA"), calibre, marca));

      mockMvc.perform(get("/armas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(1));
   }

   @Test
   @DisplayName("Deveria atualizar dados da arma")
   void atualizar_cenario1() throws Exception {
      var arma = armaRepository.save(new Arma(criarDtoExemplo("ORIGINAL"), calibre, marca));
      var dtoUpdate = new ArmaUpdateDto(
            arma.getId(), // ID obrigatório
            null, // localRegistroArma
            null, // numeroCraf
            "SERIE_NOVA", // numeroSerie (o que vamos testar)
            null, // numeroCano
            "Modelo Novo", // modelo (o que vamos testar)
            null, // calibreId
            null, // marcaId
            null, // tipoAlmaArma
            null, // tipoUsoArma
            2, // numeroRaias (agora aceita null!)
            null, // sentidoRaiasArma
            null, // vencimentoIndeterminado
            null // dataVencimentoCraf
      );

      mockMvc.perform(
            put("/armas")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(armaUpdateDtoJson.write(dtoUpdate).getJson()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numeroSerie").value("SERIE_NOVA"))
            .andExpect(jsonPath("$.modelo").value("Modelo Novo"));
   }

   @Test
   @DisplayName("Deveria realizar soft delete (ou delete conforme service)")
   void deletar_cenario1() throws Exception {
      var arma = armaRepository.save(new Arma(criarDtoExemplo("DELETE_ME"), calibre, marca));

      mockMvc.perform(delete("/armas/" + arma.getId()))
            .andExpect(status().isNoContent());

      // Se o seu service fizer Soft Delete, verifique se o campo 'deletada' é true
      // Se for delete real, verifique se existsById é false
   }

   private ArmaCreateDto criarDtoExemplo(String serie) {
      return new ArmaCreateDto(
            LocalRegistroArma.SINARM, "CRAF" + serie, serie, "CANO" + serie, "Modelo",
            calibre.getId(), marca.getId(), TipoAlmaArma.RAIADA, TipoUsoArma.RESTRITA,
            6, SentidoRaiasArma.DIREITA, false, Date.valueOf("2028-12-31"));
   }
}