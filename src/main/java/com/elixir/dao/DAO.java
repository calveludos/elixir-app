package com.elixir.dao;

import com.elixir.model.Model;

import java.sql.SQLException;

public interface DAO {
    public void create(Model model) throws SQLException;
    public void update(Model model) throws SQLException;
    public void read(Model model) throws SQLException;
    public void delete(Model model) throws SQLException;
}
