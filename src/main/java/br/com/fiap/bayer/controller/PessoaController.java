package br.com.fiap.bayer.controller;

import br.com.fiap.bayer.dto.CasoDTO;
import br.com.fiap.bayer.dto.DoencaDto;
import br.com.fiap.bayer.dto.PessoaDto;
import br.com.fiap.bayer.model.Doenca;
import br.com.fiap.bayer.model.Pessoa;
import br.com.fiap.bayer.repository.DoencaRepository;
import br.com.fiap.bayer.repository.PessoaRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository pesssoaRepository;

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listar() {
        List<Pessoa> listaDeCasos = pesssoaRepository.findAll();
        var listaPessoaDtoConvertido = PessoaDto.converterLista(listaDeCasos);
        return ResponseEntity.ok(listaPessoaDtoConvertido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> consultar(@PathVariable Long id) {
        var pessoa = pesssoaRepository.findById(id).orElseThrow(CasoDTO.NaoEncontradaException("Pessoa NÃO ENCONTRADA"));
        PessoaDto pessoaDto = new PessoaDto(pessoa);

        return ResponseEntity.ok(pessoaDto);
    }

    @PostMapping
    public ResponseEntity<DoencaDto> cadastrar(@RequestBody @Valid PessoaDto pessoaDto) {
        var dto = pessoaDto.converter();
        pesssoaRepository.save(dto);
        return ResponseEntity.accepted().build();
    }
}
