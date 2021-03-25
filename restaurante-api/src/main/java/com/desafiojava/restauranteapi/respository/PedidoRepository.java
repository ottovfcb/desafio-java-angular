package com.desafiojava.restauranteapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafiojava.restauranteapi.model.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT pedido " +
            "FROM Pedido pedido " +
            "WHERE pedido.mesa = ?1 " +
            "AND pedido.situacao = 3" +
            "ORDER BY pedido.id")
    List<Pedido> buscarContaPorMesa(Long mesa);

    @Query(value = "SELECT pedido " +
            "FROM Pedido pedido " +
            "WHERE pedido.situacao NOT IN (2, 4)" +
            "ORDER BY pedido.id")
    List<Pedido> buscarPedidosPendemtes();
}
