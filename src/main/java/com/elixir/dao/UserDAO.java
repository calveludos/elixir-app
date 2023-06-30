package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.elixir.model.User;

public class UserDAO {
    public void save(User user){
        String scriptSQL = "INSERT INTO user(email, user_name, name, password, code_verify, data_register, is_verify) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(scriptSQL);

            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getUser_name());
            pstm.setString(3, user.getName());
            pstm.setString(4, user.getPassword());
            pstm.setString(5, user.getCode_verify());
            pstm.setDate(6, new Date(user.getDate_register()).getTime());  // Deixar a data para padrão para não precisar preencher
            pstm.setBoolean(7, user.getIs_verify());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
