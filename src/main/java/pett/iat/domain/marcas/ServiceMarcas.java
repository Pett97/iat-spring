package pett.iat.domain.marcas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;
import pett.iat.domain.marcas.dtos.MarcaDetailDto;
import pett.iat.domain.marcas.dtos.MarcaUpdateDto;
import pett.iat.domain.marcas.validacoes.create.ValidacaoMarcaCreate;
import pett.iat.domain.marcas.validacoes.update.ValidarUpdateMarca;

@Service
public class ServiceMarcas {

   @Autowired
   private MarcasARepository marcasArmasRepository;

   @Autowired
   private List<ValidacaoMarcaCreate> validacoesCreate;

   @Autowired
   private List<ValidarUpdateMarca> validacoesUpdate;

   public List<MarcaDetailDto> listar() {
      return marcasArmasRepository.findAll().stream().map(MarcaDetailDto::new).toList();
   }

   public MarcaDetailDto salvar(MarcaCreateDto dados) {
      var novaMarca = new Marca(null, dados.nome().toUpperCase());

      validacoesCreate.forEach(regras -> regras.validar(dados));

      marcasArmasRepository.save(novaMarca);

      return new MarcaDetailDto(novaMarca);
   }

   public MarcaDetailDto update(MarcaUpdateDto dados) {
      var marca = marcasArmasRepository.findById(dados.id())
            .orElseThrow(() -> new ValidacaoExecption("Marca não encontrada com o ID: " + dados.id()));

      validacoesUpdate.forEach(regras -> regras.validarUpdateMarca(dados));

      marca.atualizarNome(dados);

      return new MarcaDetailDto(marca);
   }
}
