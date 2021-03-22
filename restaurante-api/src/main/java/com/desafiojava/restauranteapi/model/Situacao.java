package com.desafiojava.restauranteapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Situacao {

    @Id
    @Column
    private Long id;

    @Column
    private String descricao;

    @Builder
    public Situacao(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
