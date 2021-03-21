package com.desafiojava.restauranteapi.controller;

import com.desafiojava.restauranteapi.model.dto.ContaDTO;
import com.desafiojava.restauranteapi.model.dto.PedidoDTO;
import com.desafiojava.restauranteapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> salvar(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(pedidoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> alterarPedido(@RequestParam Long id, @RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.alterarPedido(id, pedidoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> detalharPedido(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.detalharPedido(id));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarPedido(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<?> concluirPedido(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/fechar-conta/{mesa}")
    public ResponseEntity<ContaDTO> fecharConta(@RequestParam Long mesa) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.fecharConta(mesa));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<PedidoDTO>> buscarPendentes() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosPendentes());
    }

}
