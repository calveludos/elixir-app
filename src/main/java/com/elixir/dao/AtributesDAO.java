package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.elixir.model.Atributes;


public class AtributesDAO {
    
    public void save(Atributes atributes){
        String query = "INSERT INTO atributes(id_character, str, wis, dex, inte, con, cha) VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        

        try{
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, atributes.getId_character());
            pstm.setInt(2, atributes.getStr());
            pstm.setInt(3, atributes.getWis());
            pstm.setInt(4, atributes.getDex());
            pstm.setInt(5, atributes.getInte());
            pstm.setInt(6, atributes.getCon());
            pstm.setInt(7, atributes.getCha());

            pstm.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
