package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UserDAO extends CrudDAO<User> {

    @Override
    public int create(User user) throws SQLException {
        String query = "INSERT INTO `User` (email, username, password, code_verify, data_register, is_verify) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getVerificationCode());
            stmt.setTimestamp(5, user.getRegistrationDate());
            stmt.setBoolean(6, user.isVerified());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
                generatedKeys.close();
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
        return generatedId;
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE `User` SET email = ?, username = ?, password = ?, code_verify = ?, data_register = ?, is_verify = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getVerificationCode());
            stmt.setTimestamp(5, user.getRegistrationDate());
            stmt.setBoolean(6, user.isVerified());
            stmt.setInt(7, user.getId());

            System.out.println(stmt.toString());
            int i = stmt.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, User> read() throws SQLException {
        String query = "SELECT * FROM `User`";
        ResultSet resultSet = null;
        Map<Integer, User> userMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setVerificationCode(resultSet.getString("code_verify"));
                user.setRegistrationDate(resultSet.getTimestamp("data_register"));
                user.setVerified(resultSet.getBoolean("is_verify"));

                userMap.put(user.getId(), user);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return userMap;
    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "DELETE FROM `User` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    public Map<Integer, User> read(User filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `User` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            query.append(" AND email = '").append(filter.getEmail()).append("'");
        }
        if (filter.getUserName() != null && !filter.getUserName().isEmpty()) {
            query.append(" AND username = '").append(filter.getUserName()).append("'");
        }
        if (filter.getVerificationCode() != null && !filter.getVerificationCode().isEmpty()) {
            query.append(" AND code_verify = '").append(filter.getVerificationCode()).append("'");
        }
        if (filter.getRegistrationDate() != null) {
            query.append(" AND data_register = '").append(filter.getRegistrationDate()).append("'");
        }
        query.append(";");
        System.out.println(query);

        String queryString = query.toString();

        ResultSet resultSet = null;
        Map<Integer, User> userMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(queryString);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setVerificationCode(resultSet.getString("code_verify"));
                user.setRegistrationDate(resultSet.getTimestamp("data_register"));
                user.setVerified(resultSet.getBoolean("is_verify"));

                userMap.put(user.getId(), user);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return userMap;
    }
}
