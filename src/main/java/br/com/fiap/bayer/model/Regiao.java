package br.com.fiap.bayer.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "regioes")
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Enumerated()
    @Getter
    private RegiaoEnum regiao;

    public Regiao() {
    }

    public Regiao(RegiaoEnum regiao) {
        this.regiao = regiao;
    }

}