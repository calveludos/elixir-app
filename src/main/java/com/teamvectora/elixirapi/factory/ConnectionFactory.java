package com.teamvectora.elixirapi.factory;

import com.teamvectora.elixirapi.Secrets;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    public static String MYSQL_ADDON_USER = Secrets.MYSQL_ADDON_USER;
    public static String MYSQL_ADDON_PASSWORD = Secrets.MYSQL_ADDON_PASSWORD;
    public static String MYSQL_ADDON_URL = Secrets.MYSQL_ADDON_URL;
    public static Connection createConnection() throws SQLException {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        boolean offline = (boolean) saveManager.getObject("offline");

            Connection con = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(MYSQL_ADDON_URL, MYSQL_ADDON_USER, MYSQL_ADDON_PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }

        String url = "jdbc:mysql://localhost/estudante1?user=estudante1&password=estudante1";

        return con;
    }

    public static void createDatabase() throws SQLException, IOException {
        try (
                Connection conn = createConnection();
                Statement stmt = conn.createStatement();

                BufferedReader br = new BufferedReader(new FileReader("scripts.sql"));
        ) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            String[] querys = sb.toString().split(";");
            for (String query : querys) {
                stmt.execute(query);
            }

            System.out.println("Arquivo SQL executado com sucesso!");
        } catch (Exception e) {
            throw e;
        }
    }
}
