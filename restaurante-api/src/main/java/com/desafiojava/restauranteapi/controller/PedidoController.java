package com.desafiojava.restauranteapi.controller;

import com.desafiojava.restauranteapi.model.dto.ContaDTO;
import com.desafiojava.restauranteapi.model.dto.PedidoDTO;
import com.desafiojava.restauranteapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listAll());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> salvar(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(pedidoDTO));
    }

    @PutMapping("/alterarPedido")
    public ResponseEntity<PedidoDTO> alterarPedido(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.alterarPedido(pedidoDTO));
    }

    @PutMapping("/cancelarPedido/{id}")
    public ResponseEntity<?> cancelarPedido(@PathVariable("id") Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/concluirPedido/{id}")
    public ResponseEntity<?> concluirPedido(@PathVariable("id") Long id) {
        pedidoService.concluirPedido(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/fecharConta/{mesa}")
    public ResponseEntity<ContaDTO> fecharConta(@PathVariable("mesa") Long mesa) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.fecharConta(mesa));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<PedidoDTO>> buscarPendentes() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosPendentes());
    }

    @GetMapping("/listTables")
    public ResponseEntity<List<Long>> listarMesas() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarMesas());
    }

}
