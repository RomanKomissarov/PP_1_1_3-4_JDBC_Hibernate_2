package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root","root")) {
            final UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Roman", "Komissarov", (byte) 20);
            System.out.printf("User с именем – %s добавлен в базу данных", "Roman");
            userService.saveUser("Maxim", "Komissarov", (byte) 16);
            System.out.printf("User с именем – %s добавлен в базу данных", "Maxim");
            userService.saveUser("Anastasiya", "Komissarova", (byte) 7);
            System.out.printf("User с именем – %s добавлен в базу данных", "Anastasiya");
            userService.saveUser("Natalya", "Komissarova", (byte) 45);
            System.out.printf("User с именем – %s добавлен в базу данных", "Natalya");
            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user);
            }
            userService.cleanUsersTable();
            userService.dropUsersTable();
        }
    }
}
