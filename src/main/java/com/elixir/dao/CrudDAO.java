package com.elixir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public abstract class CrudDAO<Model> implements DAO<Model> {
    public Connection conn = null;
    public PreparedStatement stmt = null;

    @Override
    public int create(Model model) throws SQLException {
        return 0;
    }

    @Override
    public void update(Model model) throws SQLException {

    }

    @Override
    public Map read() throws SQLException {
        return null;
    }

    @Override
    public void delete(Model model) throws SQLException {

    }
    protected void closeResources() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
