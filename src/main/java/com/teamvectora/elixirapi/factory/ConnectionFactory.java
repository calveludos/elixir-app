package com.teamvectora.elixirapi.factory;

import com.teamvectora.elixirapi.manager.XMLManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        XMLManager xmlManager = new XMLManager();
        String url = xmlManager.getElement("url").getTextContent();
        String user = xmlManager.getElement("user").getTextContent();
        String password = xmlManager.getElement("password").getTextContent();

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLSyntaxErrorException e){
            if (e.getMessage().equals("Unknown database 'elixiroffline'")){
                try {
                    url = url.replace("/elixiroffline", "");
                    xmlManager.editElement("url", url);
                    createDatabase();
                    url = url + "/elixiroffline";
                    xmlManager.editElement("url", url);
                    return createConnection();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                throw new RuntimeException(e);
            }
        }


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

            XMLManager xmlManager = new XMLManager();
            xmlManager.editElement("url", "jdbc:mysql://localhost:3306/elixiroffline");

            System.out.println("Arquivo SQL executado com sucesso!");
        }
    }
}
