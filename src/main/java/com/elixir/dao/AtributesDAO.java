package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.elixir.model.Atributes;


public class AtributesDAO {
    
    public void save(Atributes atributes){
        String sqlAtributes = "INSERT INTO atributes(str, wis, dex, inte, con, cha) VALUES (?, ?, ?, ?, ?, ?)"
        
        Connection conn = null;
        PreparedStatement pstm = null;
        

        try{
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sqlAtributes);
            pstm.setInt(1, atributes.getStr());
            pstm.setInt(2, atributes.getWis());
            pstm.setInt(3, atributes.getDex());
            pstm.setInt(4, atributes.getInte());
            pstm.setInt(5, atributes.getCon());
            pstm.setInt(6, atributes.getCha());

            pstm.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
