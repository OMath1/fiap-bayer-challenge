package br.com.fiap.bayer.controller;

import br.com.fiap.bayer.dto.CasoDTO;
import br.com.fiap.bayer.dto.DoencaDto;
import br.com.fiap.bayer.model.Caso;
import br.com.fiap.bayer.model.Doenca;
import br.com.fiap.bayer.repository.DoencaRepository;
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
@RequestMapping("/doencas")
public class DoencaController {

    @Autowired
    DoencaRepository doencaRepository;

    @GetMapping
    public ResponseEntity<List<DoencaDto>> listar() {
        List<Doenca> listaDeCasos = doencaRepository.findAll();
        var listaCasoDtoConvertido = DoencaDto.converterLista(listaDeCasos);
        return ResponseEntity.ok(listaCasoDtoConvertido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoencaDto> consultar(@PathVariable Long id) {
        var doenca = doencaRepository.findById(id).orElseThrow(CasoDTO.NaoEncontradaException("DOENÇA NÃO ENCONTRADO"));
        DoencaDto casoDTO = new DoencaDto(doenca);

        return ResponseEntity.ok(casoDTO);
    }

    @PostMapping
    public ResponseEntity<DoencaDto> cadastrar(@RequestBody @Valid DoencaDto doencaDTO) {
        var dto = doencaDTO.converter();
        doencaRepository.save(dto);
        return ResponseEntity.accepted().build();
    }
}
