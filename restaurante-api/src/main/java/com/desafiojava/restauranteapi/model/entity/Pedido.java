package com.desafiojava.restauranteapi.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String item;

    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn
    private Situacao situacao;

    @Column
    private Long mesa;

    @Builder
    public Pedido(Long id, String item, Double valor, Situacao situacao, Long mesa) {
        this.id = id;
        this.item = item;
        this.valor = valor;
        this.situacao = situacao;
        this.mesa = mesa;
    }

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(this.situacao)) {
            this.situacao = Situacao.builder().id(1L).descricao("Novo").build();
        }
    }
}
