package com.elixir.dao;

import java.sql.SQLException;
import java.util.Map;

public interface DAO<Model> {
    public void create(Model model) throws SQLException;
    public void update(Model model) throws SQLException;
    public Map read() throws SQLException;
    public void delete(Model model) throws SQLException;
}
