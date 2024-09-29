package com.unisabana.monolito.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public List<Product> getAll(){
        return jdbcClient.sql("SELECT * FROM product ORDER BY id ASC")
                .query(Product.class)
                .list();
    }

    public Optional<Product> findById(int id){
        return jdbcClient.sql("SELECT * FROM product WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    public void create(Product product){
        var updated = jdbcClient.sql("INSERT INTO product(title, price) values (?,?)")
                .params(List.of(product.getTitle(), product.getPrice()))
                .update();

        Assert.state(updated == 1, "Fallo al crear el producto" + product.getTitle());
    }

    public void update(Product product, int id){
        var updated = jdbcClient.sql("UPDATE product SET title = ?, price = ? where id = ?")
                .params(List.of(product.getTitle(), product.getPrice(), id))
                .update();

        Assert.state(updated == 1, "Fallo al actualizar el producto" + product.getTitle());
    }

    public void delete(int id){
        var updated = jdbcClient.sql("DELETE FROM product WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Fallo al borrar el producto " + id);
    }

    public int count(){
        return jdbcClient.sql("SELECT * FROM product").query().listOfRows().size();
    }
    
}
