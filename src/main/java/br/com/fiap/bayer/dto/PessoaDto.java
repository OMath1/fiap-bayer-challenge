package br.com.fiap.bayer.dto;

import br.com.fiap.bayer.model.Pessoa;
import br.com.fiap.bayer.model.Regiao;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PessoaDto implements Serializable {

    @NotNull(message = "Nome é obrigatório!" )
    private String nome;
    @NotNull(message = "Telefone é obrigatório!" )
    private String telefone;

    @NotNull(message = "Data de nascimento é obrigatório!" )
    @PastOrPresent(message = "A data de nascimento deve estar no passado ou presente;")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate nascimento;

    @NotNull(message = "Email é obrigatório!" )
    private String email;

    public PessoaDto(Pessoa pessoa) {
        this.nome = pessoa.getNome();;
        this.telefone = pessoa.getTelefone();
        this.nascimento = pessoa.getNascimento();
        this.email = pessoa.getEmail();
    }

    public static List<PessoaDto> converterLista(List<Pessoa> listaDePessoas) {
        List<PessoaDto> pessoaDtoList = new ArrayList<>();
        listaDePessoas.forEach(f -> pessoaDtoList.add(new PessoaDto(f)));
        return pessoaDtoList;
    }

    public Pessoa converter() {
        return new Pessoa(nome, telefone, nascimento, email);
    }

}
