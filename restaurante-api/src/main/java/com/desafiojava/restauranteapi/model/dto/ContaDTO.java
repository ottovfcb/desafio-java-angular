package com.desafiojava.restauranteapi.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ContaDTO {

    private List<PedidoDTO> pedidos;

    private Double valorTotal;

    @Builder
    public ContaDTO(List<PedidoDTO> pedidos, Double valorTotal) {
        this.pedidos = pedidos;
        this.valorTotal = valorTotal;
    }
}
