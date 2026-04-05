package pett.iat.domain.produto.municao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.produto.municao.dtos.MunicaoCreateDto;
import pett.iat.domain.produto.municao.dtos.MunicaoDetailDto;
import pett.iat.domain.produto.municao.dtos.MunicaoUpdateDto;
import pett.iat.domain.produto.municao.validacoes.create.ValidarCreateMunicao;
import pett.iat.domain.produto.municao.validacoes.update.ValidarUpdateMunicao;

@Service
public class MunicaoService {

    @Autowired
    private MunicaoRepository produtoMunicaoRepository;

    @Autowired
    private CalibreRespository calibreRespository;

    @Autowired
    private List<ValidarCreateMunicao> validacoesCreateMunicao;

    @Autowired
    private List<ValidarUpdateMunicao> validarUpdateMunicaos;

    public List<MunicaoDetailDto> listarMunicoes() {
        return this.produtoMunicaoRepository.findAll().stream().map(MunicaoDetailDto::new).toList();
    }

    public MunicaoDetailDto cadastrar(MunicaoCreateDto dados) {

        validacoesCreateMunicao.forEach(regras -> regras.validar(dados));
        
        var calibre = this.buscarCalibrePorId(dados.calibreId());
        var municao = new Municao(dados.nome(), dados.sku(), dados.preco(), calibre);

        produtoMunicaoRepository.save(municao);

        return new MunicaoDetailDto(municao);
    }

    public MunicaoDetailDto update(MunicaoUpdateDto dados) {

        validarUpdateMunicaos.forEach(regras -> regras.validar(dados));

        var municao = produtoMunicaoRepository.findById(dados.id())
                .orElseThrow(() -> new ValidacaoExecption("Produto Municao não encontrada com o ID: " + dados.id()));

        if (municao == null) {
            throw new ValidacaoExecption("não foi encontrado municao com esse id:" + dados.id());
        }

        var calibre = calibreRespository.findById(dados.calibreId())
                .orElseThrow(() -> new ValidacaoExecption("Nenhum calibre encontrado com o ID:" + dados.calibreId()));

        municao.atualizar(dados, calibre);

        return new MunicaoDetailDto(municao);
    }

    private Calibre buscarCalibrePorId(Long id) {
        return calibreRespository.getReferenceById(id);
    }
}