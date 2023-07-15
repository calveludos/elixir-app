package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO extends CrudDAO<User> {

    @Override
    public int create(User user) throws SQLException {
        String query = "INSERT INTO User (email, user_name, name, password, verification_code, registration_date, is_verified) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getVerificationCode());
            stmt.setTimestamp(6, user.getRegistrationDate());
            stmt.setBoolean(7, user.isVerified());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                user.setId(generatedId);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
        return user.getId();
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE User SET email = ?, user_name = ?, name = ?, password = ?, verification_code = ?, " +
                "registration_date = ?, is_verified = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getVerificationCode());
            stmt.setTimestamp(6, user.getRegistrationDate());
            stmt.setBoolean(7, user.isVerified());
            stmt.setInt(8, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, User> read() throws SQLException {
        String query = "SELECT * FROM User";
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
                user.setUserName(resultSet.getString("user_name"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setVerificationCode(resultSet.getString("verification_code"));
                user.setRegistrationDate(resultSet.getTimestamp("registration_date"));
                user.setVerified(resultSet.getBoolean("is_verified"));

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
        String query = "DELETE FROM User WHERE id = ?";

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
}
