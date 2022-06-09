package br.com.fiap.bayer.dto;

import br.com.fiap.bayer.model.Pessoa;
import br.com.fiap.bayer.model.Regiao;
import br.com.fiap.bayer.model.RegiaoEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegiaoDto implements Serializable {

    @NotNull(message = "Região é obrigatório!" )
    private RegiaoEnum regiao;

    public Regiao converter() {
        return new Regiao(regiao);
    }

    public RegiaoDto(Regiao regiao) {
        this.regiao = regiao.getRegiao();
    }

    public static List<RegiaoDto> converterLista(List<Regiao> listaDeRegiao) {
        List<RegiaoDto> regiaoDtoList = new ArrayList<>();
        listaDeRegiao.forEach(f -> regiaoDtoList.add(new RegiaoDto(f)));
        return regiaoDtoList;
    }


}
