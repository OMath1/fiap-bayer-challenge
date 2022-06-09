package br.com.fiap.bayer.dto;

import br.com.fiap.bayer.model.Caso;
import br.com.fiap.bayer.model.Doenca;
import br.com.fiap.bayer.model.Pessoa;
import br.com.fiap.bayer.model.Regiao;
import br.com.fiap.bayer.repository.DoencaRepository;
import br.com.fiap.bayer.repository.PessoaRepository;
import br.com.fiap.bayer.repository.RegiaoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@NoArgsConstructor
@Getter
public class CasoDTO {

    @NotNull(message = "ID PESSOA não pode ser nulo")
    private Long idPessoa;

    @NotNull(message = "ID REGIAO não pode ser nulo")
    private Long idRegiao;

    @NotNull(message = "ID DOENÇA não pode ser nulo")
    private Long idDoenca;

    @PastOrPresent(message = "A data de nascimento deve estar no passado ou presente;")
    @NotNull(message = "Data de Contaminação não pode ser nulo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataContaminacao;

    public CasoDTO(Caso caso) {
        this.idPessoa = caso.getPessoa().getId();
        this.idRegiao = caso.getRegiao().getId();
        this.idDoenca = caso.getDoenca().getId();
        this.dataContaminacao = caso.getDataDaDoenca();
    }

    public CasoDTO(Long idPessoa, Long idRegiao, Long idDoenca, LocalDate dataContaminacao) {
        this.idPessoa = idPessoa;
        this.idRegiao = idRegiao;
        this.idDoenca = idDoenca;
        this.dataContaminacao = dataContaminacao;
    }

    public Caso converter(PessoaRepository pessoaRepository, RegiaoRepository regiaoRepository, DoencaRepository doencaRepository) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(NaoEncontradaException("Pessoa não encontrada!"));
        Regiao regiao = regiaoRepository.findById(idRegiao).orElseThrow(NaoEncontradaException("Regiao não encontrada!"));
        Doenca doenca = doencaRepository.findById(idDoenca).orElseThrow(NaoEncontradaException("Doença não encontrada"));

        return new Caso(pessoa, regiao, doenca, dataContaminacao);
    }

    public static List<CasoDTO> converteDTO(List<Caso> listaDeCaso) {
        List<CasoDTO> casoDTOList = new ArrayList<>();
        listaDeCaso.forEach(l -> {
            var idDoenca = l.getDoenca().getId();
            var idPessoa = l.getPessoa().getId();
            var idRegiao = l.getDoenca().getId();
            var dataContamina = l.getDataDaDoenca();

            casoDTOList.add(new CasoDTO(idPessoa, idRegiao, idDoenca, dataContamina));
        });
        return casoDTOList;
    }

    public static Supplier<ResponseStatusException> NaoEncontradaException(String reason) {
        return () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }
}
