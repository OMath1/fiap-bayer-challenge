package br.com.fiap.bayer.controller;

import br.com.fiap.bayer.dto.CasoDTO;
import br.com.fiap.bayer.dto.DoencaDto;
import br.com.fiap.bayer.dto.PessoaDto;
import br.com.fiap.bayer.dto.RegiaoDto;
import br.com.fiap.bayer.model.Pessoa;
import br.com.fiap.bayer.model.Regiao;
import br.com.fiap.bayer.repository.PessoaRepository;
import br.com.fiap.bayer.repository.RegiaoRepository;
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
@RequestMapping("/regioes")
public class RegiaoController {

    @Autowired
    RegiaoRepository regiaoRepository;

    @GetMapping
    public ResponseEntity<List<RegiaoDto>> listar() {
        List<Regiao> listaDeRegiao = regiaoRepository.findAll();
        var listaRegiaoConvertido = RegiaoDto.converterLista(listaDeRegiao);
        return ResponseEntity.ok(listaRegiaoConvertido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoDto> consultar(@PathVariable Long id) {
        var regiao = regiaoRepository.findById(id).orElseThrow(CasoDTO.NaoEncontradaException("REGIAO NÃO ENCONTRADA"));
        RegiaoDto regiaoDto = new RegiaoDto(regiao);

        return ResponseEntity.ok(regiaoDto);
    }

    @PostMapping
    public ResponseEntity<DoencaDto> cadastrar(@RequestBody @Valid RegiaoDto regiaoDto) {
        var dto = regiaoDto.converter();
        regiaoRepository.save(dto);
        return ResponseEntity.accepted().build();
    }
}
