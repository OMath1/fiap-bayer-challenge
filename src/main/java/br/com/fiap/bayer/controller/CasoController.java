package br.com.fiap.bayer.controller;

import br.com.fiap.bayer.dto.CasoDTO;
import br.com.fiap.bayer.model.Caso;
import br.com.fiap.bayer.repository.CasoRepository;
import br.com.fiap.bayer.repository.DoencaRepository;
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
@RequestMapping("/casos")
public class CasoController {

    @Autowired
    CasoRepository casoRepository;
    @Autowired
    DoencaRepository doencaRepository;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    RegiaoRepository regiaoRepository;

    @GetMapping
    public ResponseEntity<List<CasoDTO>> listar() {
        List<Caso> listaDeCasos = casoRepository.findAll();
        var listaCasoDtoConvertido = CasoDTO.converteDTO(listaDeCasos);
        return ResponseEntity.ok(listaCasoDtoConvertido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoDTO> consultar(@PathVariable Long id) {
        var caso = casoRepository.findById(id).orElseThrow(CasoDTO.NaoEncontradaException("CASO NÃO ENCONTRADO"));
        CasoDTO casoDTO = new CasoDTO(caso);

        return ResponseEntity.ok(casoDTO);
    }

    @PostMapping
    public ResponseEntity<CasoDTO> cadastrar(@RequestBody @Valid CasoDTO casoDTO) {
        var irrelevante = casoDTO.converter(pessoaRepository, regiaoRepository, doencaRepository);
        casoRepository.save(irrelevante);

        return ResponseEntity.accepted().build();
    }



}

