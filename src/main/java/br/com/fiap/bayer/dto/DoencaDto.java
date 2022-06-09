package br.com.fiap.bayer.dto;

import br.com.fiap.bayer.model.Doenca;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DoencaDto implements Serializable {

    @NotNull(message = "Doença é obrigatório!" )
    private String doenca;

    public DoencaDto(Doenca doenca) {
        this.doenca = doenca.getDoenca();
    }

    public Doenca converter() {
        return new Doenca(doenca);
    }

    public static List<DoencaDto> converterLista (List<Doenca> listaDeDoenca) {
        List<DoencaDto> doencaDtoList = new ArrayList<>();
        listaDeDoenca.forEach(f -> doencaDtoList.add(new DoencaDto(f.getDoenca())));
        return doencaDtoList;
    }
}
