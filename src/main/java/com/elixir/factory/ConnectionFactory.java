package com.elixir.factory;

import com.elixir.Secrets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection createConnection() throws SQLException {
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(Secrets.MYSQL_ADDON_URL, Secrets.MYSQL_ADDON_USER, Secrets.MYSQL_ADDON_PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }

        return con;
    }
}
