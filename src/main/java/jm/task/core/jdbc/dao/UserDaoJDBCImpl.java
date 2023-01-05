package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `users` (
                      `idUsers` int NOT NULL AUTO_INCREMENT,
                      `name` varchar(45) NOT NULL,
                      `lastName` varchar(45) NOT NULL,
                      `age` int NOT NULL,
                      PRIMARY KEY (`idUsers`),
                      UNIQUE KEY `idUsers_UNIQUE` (`idUsers`)
                    )""");
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void dropUsersTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void removeUserById(long id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE idUsers=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
