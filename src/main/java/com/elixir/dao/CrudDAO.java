package com.elixir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public abstract class CrudDAO<T> {
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public abstract int create(T model) throws SQLException;

    public abstract void update(T model) throws SQLException;

    public abstract Map<Integer, T> read() throws SQLException;

    public abstract Map<Integer, T> read(T model) throws SQLException;

    public abstract void delete(T model) throws SQLException;

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
