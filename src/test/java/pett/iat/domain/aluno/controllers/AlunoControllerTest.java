package pett.iat.domain.aluno.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pett.iat.domain.BaseTest;
import jakarta.transaction.Transactional;
import pett.iat.IatApplication;
import pett.iat.domain.aluno.Aluno;
import pett.iat.domain.aluno.AlunoRepository;
import pett.iat.domain.aluno.dto.AlunoCreateDto;
import pett.iat.domain.aluno.dto.AlunoDetailDto;
import pett.iat.domain.aluno.dto.AlunoUpdateDto;
import pett.iat.enums.Estado;

@SpringBootTest(classes = IatApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AlunoControllerTest extends BaseTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private JacksonTester<AlunoCreateDto> creteJson;

   @Autowired
   private JacksonTester<AlunoUpdateDto> updateJson;

   @Autowired
   private JacksonTester<AlunoDetailDto> detailJson;

   @Autowired
   private AlunoRepository alunoRepository;

   @Autowired
   private ObjectMapper objectMapper;

   private Aluno aluno;

   private Aluno aluno2;

   private Aluno aluno3;

   @BeforeEach
   void setup() {
      this.aluno = Aluno.builder()
            .nome("Homer Simpson")
            .cpf("12345678900")
            .rg("MG123456")
            .rgDataExpedicao(LocalDate.of(2000, 1, 1))

            .telefonePrincipal("49999999999")
            .telefonePrincipalTemWhatsapp(true)

            .telefoneSecundario("48888888888")
            .telefoneSecundarioTemWhatsapp(false)

            .emailPrincipal("homer@simpson.com")
            .emailPrincipalValidado(true)

            .emailSecundario("marge@simpson.com")
            .emailSecundarioValidado(false)

            .cep("85000000")
            .estado(Estado.PR)
            .cidade("Guarapuava")
            .logradouro("Rua Springfield")
            .numeroLogradouro(742)
            .complemento("Casa")

            .deletado(false)
            .build();

      alunoRepository.save(aluno);

      this.aluno2 = Aluno.builder()
            .nome("Bart Simpson")
            .cpf("98765432111")
            .rg("SP654321")
            .rgDataExpedicao(LocalDate.of(2005, 5, 10))

            .telefonePrincipal("47999999999")
            .telefonePrincipalTemWhatsapp(true)

            .telefoneSecundario("46888888888")
            .telefoneSecundarioTemWhatsapp(false)

            .emailPrincipal("bart@simpson.com")
            .emailPrincipalValidado(true)

            .emailSecundario("lisa@simpson.com")
            .emailSecundarioValidado(false)

            .cep("85000001")
            .estado(Estado.PR)
            .cidade("Guarapuava")
            .logradouro("Rua Springfield")
            .numeroLogradouro(744)
            .complemento("Casa 2")

            .deletado(false)
            .build();
      this.alunoRepository.save(aluno2);

      this.aluno3 = Aluno.builder()
            .nome("Lisa Simpson")
            .cpf("1234567834")
            .rg("MG123458")
            .rgDataExpedicao(LocalDate.of(2000, 1, 1))

            .telefonePrincipal("49999999911")
            .telefonePrincipalTemWhatsapp(true)

            .telefoneSecundario("48888888833")
            .telefoneSecundarioTemWhatsapp(false)

            .emailPrincipal("lisa@simpson.com")
            .emailPrincipalValidado(true)

            .emailSecundario("lis3@simpson.com")
            .emailSecundarioValidado(false)

            .cep("85000000")
            .estado(Estado.PR)
            .cidade("Guarapuava")
            .logradouro("Rua Springfield")
            .numeroLogradouro(742)
            .complemento("Casa")

            .deletado(true)
            .build();

      alunoRepository.save(aluno3);
   }

   @Test
   @Transactional
   void deveSerPossivelListarAlunos() throws Exception {
      mockMvc.perform(get("/aluno"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Homer Simpson"))
            .andExpect(jsonPath("$[1].nome").value("Bart Simpson"));
   }

   @Test
   @Transactional
   void deveSerPossivelDeletarUmAluno() throws Exception {
      mockMvc.perform(delete("/aluno/" + this.aluno2.getId()))
            .andExpect(status().isNoContent());
   }

   @Test
   @Transactional
   void deveSerPossivelRecuperarUmAlunoDeletado() throws Exception {
      this.aluno2.deletar();

      mockMvc.perform(post("/aluno/recuperar-aluno/" + this.aluno2.getId()))
            .andExpect(status().isNoContent());
   }

   @Test
   @Transactional
   void deveSerPossivelAtualizarUmAluno() throws Exception {

      var dto = new AlunoUpdateDto(
            this.aluno.getId(),
            "Homer Atualizado",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "novo@simpson.com",
            true,
            null,
            null,
            null,
            null,
            null,
            null,
            999,
            null);

      var response = mockMvc.perform(
            put("/aluno/" + this.aluno.getId())
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(dto)))
            .andReturn().getResponse();

      var json = response.getContentAsString();

      var alunoResponse = objectMapper.readValue(json, AlunoDetailDto.class);

      assertThat(alunoResponse.nome()).isEqualTo("Homer Atualizado");
   }

   @Test
   void deverSerPosssivelListarOsAlunosDeletados() throws Exception {
      var response = mockMvc.perform(get("/aluno/deletados")).andExpect(status().isOk()).andReturn().getResponse();

      var json = response.getContentAsString();

      var alunos = objectMapper.readValue(json, new TypeReference<List<AlunoDetailDto>>() {});
      assertThat(json.length()>=1);
      assertThat(alunos.get(0).nome()).isEqualTo("Lisa Simpson");

   }
}
