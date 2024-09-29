package com.unisabana.monolito.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class UserRepository {

    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<User> getAll(){
        return jdbcClient.sql("SELECT * FROM app_user")
                .query(User.class)
                .list();
    }

    public Optional<User> findByEmail(String email){
        return jdbcClient.sql("SELECT * FROM app_user WHERE email = :email")
                .param("email", email)
                .query(User.class)
                .optional();
    }

    public Optional<User> findByUsername(String username){
        return jdbcClient.sql("SELECT * FROM app_user WHERE username = :username")
                .param("username", username)
                .query(User.class)
                .optional();
    }

    public void create(User user){

        var updated = jdbcClient.sql("INSERT INTO app_user(email, username, password, role) values (?,?,?,?)")
                .params(List.of(user.getEmail(), user.getUsername(), user.getPassword(), user.getRole().toString()))
                .update();

        Assert.state(updated == 1, "Fallo al crear el usuario" + user.getUsername());
    }

    public void update(User user, String email){
        var updated = jdbcClient.sql("UPDATE app_user SET username = ?, password = ?, role = ? where email = ?")
                .params(List.of(user.getUsername(), user.getPassword(), user.getRole(), email))
                .update();

        Assert.state(updated == 1, "Fallo al actualizar el usuario" + user.getUsername());
    }

    public void delete(String email){
        var updated = jdbcClient.sql("DELETE FROM app_user WHERE email = :email")
                .param("email", email)
                .update();

        Assert.state(updated == 1, "Fallo al borrar el usuario " + email);
    }

    public int count(){
        return jdbcClient.sql("SELECT * FROM app_user").query().listOfRows().size();
    }
    
}

