package com.desafiojava.restauranteapi.model.dto;

import com.desafiojava.restauranteapi.model.Situacao;
import lombok.Builder;
import lombok.Data;

@Data
public class PedidoDTO {

    private Long id;

    private String item;

    private Double valor;

    private Situacao situacao;

    private Long mesa;

    @Builder
    public PedidoDTO(Long id, String item, Double valor, Situacao situacao, Long mesa) {
        this.id = id;
        this.item = item;
        this.valor = valor;
        this.situacao = situacao;
        this.mesa = mesa;
    }
}
