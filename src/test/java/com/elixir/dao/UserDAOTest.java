package com.elixir.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import com.elixir.model.User;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    @Test
    void update() throws SQLException {
        UserDAO userDAO = new UserDAO();
        var users = userDAO.read().values();
        for (User u :
                users) {
            User user = new User(
                    u.getEmail(),
                    u.getUserName(),
                    u.getPassword(),
                    u.getVerificationCode(),
                    u.getRegistrationDate(),
                    u.isVerified()
            );
            user.setId(u.getId());
            user.setHashPassword(user.getUserName().split("_")[0] + "123");
            System.out.println(user.getUserName().split("_")[0] + "123");
            userDAO.update(user);
            System.out.println(user);
        }
    }
}