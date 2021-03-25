package com.desafiojava.restauranteapi.service;

import com.desafiojava.restauranteapi.model.Pedido;
import com.desafiojava.restauranteapi.model.Situacao;
import com.desafiojava.restauranteapi.model.dto.ContaDTO;
import com.desafiojava.restauranteapi.model.dto.PedidoDTO;
import com.desafiojava.restauranteapi.respository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDTO criarPedido(PedidoDTO pedido) {

        Pedido novoPedido = Pedido.builder()
                .item(pedido.getItem())
                .valor(pedido.getValor())
                .mesa(pedido.getMesa())
                .build();

        Pedido pedidoPersistido = pedidoRepository.save(novoPedido);

        return PedidoDTO.builder()
                .id(pedidoPersistido.getId())
                .item(pedidoPersistido.getItem())
                .valor(pedidoPersistido.getValor())
                .situacao(pedidoPersistido.getSituacao())
                .mesa(pedidoPersistido.getMesa())
                .build();
    }

    public PedidoDTO alterarPedido(PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoDTO.getId());
        if (pedidoOptional.isEmpty()) {
            System.out.println("Erro! Objeto não encontrado");
        }
        Pedido alteraPedido = pedidoOptional.get();
        alteraPedido.setItem(pedidoDTO.getItem());
        alteraPedido.setValor(pedidoDTO.getValor());
        alteraPedido.setMesa(pedidoDTO.getMesa());

        Pedido pedidoAlterado = pedidoRepository.save(alteraPedido);

        return PedidoDTO.builder()
                .id(pedidoAlterado.getId())
                .item(pedidoAlterado.getItem())
                .valor(pedidoAlterado.getValor())
                .situacao(pedidoAlterado.getSituacao())
                .mesa(pedidoAlterado.getMesa())
                .build();
    }

    public void cancelarPedido(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            System.out.println("Erro! Objeto não encontrado");
        }
        Pedido pedido = pedidoOptional.get();
        pedido.setSituacao(Situacao.builder().id(2L).build());

        pedidoRepository.save(pedido);
    }

    public void concluirPedido(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            System.out.println("Erro! Objeto não encontrado");
        }
        Pedido pedido = pedidoOptional.get();
        pedido.setSituacao(Situacao.builder().id(3L).build());

        pedidoRepository.save(pedido);
    }

    public ContaDTO fecharConta(Long mesa) {
        List<Pedido> pedidos = pedidoRepository.buscarContaPorMesa(mesa);
        List<PedidoDTO> pedidosConta = new ArrayList<>();
        Double valorTotal = 0.0;

        for (Pedido pedido : pedidos) {
            pedido.setSituacao(Situacao.builder().id(4L).descricao("Fechado").build());

            PedidoDTO pedidoDTO = PedidoDTO.builder()
                    .id(pedido.getId())
                    .item(pedido.getItem())
                    .valor(pedido.getValor())
                    .situacao(pedido.getSituacao())
                    .mesa(pedido.getMesa())
                    .build();

            valorTotal += pedido.getValor();
            pedidosConta.add(pedidoDTO);
        }

        pedidoRepository.saveAll(pedidos);

        return ContaDTO.builder()
                .pedidos(pedidosConta)
                .valorTotal(valorTotal)
                .build();
    }

    public List<PedidoDTO> buscarPedidosPendentes() {
        List<Pedido> pedidos = pedidoRepository.buscarPedidosPendemtes();
        List<PedidoDTO> pedidosPendentes = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = PedidoDTO.builder()
                    .id(pedido.getId())
                    .item(pedido.getItem())
                    .valor(pedido.getValor())
                    .situacao(pedido.getSituacao())
                    .mesa(pedido.getMesa())
                    .build();

            pedidosPendentes.add(pedidoDTO);
        }
        return pedidosPendentes;
    }

    public List<PedidoDTO> listAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> todosPedidos = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = PedidoDTO.builder()
                    .id(pedido.getId())
                    .item(pedido.getItem())
                    .valor(pedido.getValor())
                    .situacao(pedido.getSituacao())
                    .mesa(pedido.getMesa())
                    .build();

            todosPedidos.add(pedidoDTO);
        }
        return todosPedidos;
    }

    public List<Long> listarMesas(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<Long> mesas = new ArrayList<>();

        pedidos.forEach(pedido -> {
            mesas.add(pedido.getMesa());
        });

        List<Long> mesasSemDuplicatas = new ArrayList<>(
                new HashSet<>(mesas));

        return mesasSemDuplicatas;
    }

}
