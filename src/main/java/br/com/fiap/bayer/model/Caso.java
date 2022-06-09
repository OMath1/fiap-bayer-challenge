package br.com.fiap.bayer.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "casos")
@NoArgsConstructor
@Getter
public class Caso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(nullable = false)
    private Regiao regiao;

    @OneToOne
    @JoinColumn(nullable = false)
    private Doenca doenca;

    @Column(nullable = false)
    @PastOrPresent
    @Getter
    private LocalDate dataDaDoenca;

    public Caso(Pessoa pessoa, Regiao regiao, Doenca doenca, LocalDate dataDaDoenca) {
        this.pessoa = pessoa;
        this.regiao = regiao;
        this.doenca = doenca;
        this.dataDaDoenca = dataDaDoenca;
    }
}