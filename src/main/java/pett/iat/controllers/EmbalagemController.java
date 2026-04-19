package pett.iat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import pett.iat.domain.estoque.embalagem.EmbalagemService;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemCreateDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemDetailDto;
import pett.iat.domain.estoque.embalagem.dto.EmbalagemUpdateDto;

@RestController
@RequestMapping("/embalagem")
public class EmbalagemController {

    @Autowired
    private EmbalagemService embalagemService;

    @GetMapping
    public ResponseEntity<List<EmbalagemDetailDto>> listar() {
        var embalagens = this.embalagemService.listar();

        return ResponseEntity.ok(embalagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmbalagemDetailDto> getById(@PathVariable Long id) {
        var embalagem = this.embalagemService.getBydId(id);

        return ResponseEntity.ok(embalagem);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmbalagemDetailDto> cadastrar(@RequestBody @Valid EmbalagemCreateDto dto) {
        var embalagem = this.embalagemService.cadastrar(dto);
        return ResponseEntity.ok(embalagem);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EmbalagemDetailDto> atualizar(@RequestBody @Valid EmbalagemUpdateDto dto) {
        var embalagem = this.embalagemService.atualizar(dto);
        return ResponseEntity.ok(embalagem);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.embalagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
