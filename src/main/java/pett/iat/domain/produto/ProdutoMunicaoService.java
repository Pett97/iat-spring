package pett.iat.domain.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pett.iat.domain.ValidacaoExecption;
import pett.iat.domain.calibre.Calibre;
import pett.iat.domain.calibre.CalibreRespository;
import pett.iat.domain.produto.dtos.ProdutoCreateDto;
import pett.iat.domain.produto.dtos.ProdutoMunicaoCreateDto;
import pett.iat.domain.produto.dtos.ProdutoMunicaoDetailDto;

@Service
public class ProdutoMunicaoService {

    @Autowired
    private ProdutoMunicaoRepository produtoMunicaoRepository;

    @Autowired
    private CalibreRespository calibreRespository;

    public List<ProdutoMunicaoDetailDto> listarMunicoes() {
        return this.produtoMunicaoRepository.findAll().stream().map(ProdutoMunicaoDetailDto::new).toList();
    }

    public ProdutoMunicaoDetailDto cadastrar(ProdutoMunicaoCreateDto dados) {
        var calibre = this.buscarCalibrePorId(dados.calibreId());

        if (calibre == null) {
            throw new ValidacaoExecption("Nao foi encontrado calibre");
        }

        var municao = new ProdutoMunicao(dados.nome(), dados.sku(), dados.preco(), calibre);

        produtoMunicaoRepository.save(municao);

        return new ProdutoMunicaoDetailDto(municao);
    }

    private Calibre buscarCalibrePorId(Long id) {
        return calibreRespository.getReferenceById(id);
    }
}