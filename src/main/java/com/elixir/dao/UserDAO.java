package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.elixir.model.User;

public class UserDAO {
    public void save(User user){
        String query = "INSERT INTO user(email, user_name, name, password, code_verify, data_register, is_verify) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(query);

            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getUser_name());
            pstm.setString(3, user.getName());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, user.getPassword());
            pstm.setTimestamp(6, user.getDate_register()); //melhro colocar dentro da clase User
            pstm.setBoolean(7, user.getIs_verify());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
