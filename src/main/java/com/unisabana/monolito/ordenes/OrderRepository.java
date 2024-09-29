package com.unisabana.monolito.ordenes;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OrderRepository {

    private final JdbcClient jdbcClient;

    public List<Order> getAll(){
        return jdbcClient.sql("SELECT * FROM ordenes ORDER BY id ASC")
                .query(Order.class)
                .list();
    }

    public Optional<Order> findById(int id){
        return jdbcClient.sql("SELECT * FROM ordenes WHERE id = :id")
                .param("id", id)
                .query(Order.class)
                .optional();
    }

    public void create(Order order){
        var updated = jdbcClient.sql("INSERT INTO ordenes(user_id, product_id) values (?,?)")
                .params(List.of(order.getUser_id(), order.getProduct_id()))
                .update();

        Assert.state(updated == 1, "Fallo al crear la orden");
    }

    public void delete(int id){
        var updated = jdbcClient.sql("DELETE FROM ordenes WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Fallo al borrar la orden" + id);
    }

    public int count(){
        return jdbcClient.sql("SELECT * FROM ordenes").query().listOfRows().size();
    }
    
}
