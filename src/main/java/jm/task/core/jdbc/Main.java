package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
            final UserService userService = new UserServiceImpl();
            userService.createUsersTable();
            userService.saveUser("Roman", "Komissarov", (byte) 20);
            System.out.printf("User с именем – %s добавлен в базу данных\n", "Roman");
            userService.saveUser("Maxim", "Komissarov", (byte) 16);
            System.out.printf("User с именем – %s добавлен в базу данных\n", "Maxim");
            userService.saveUser("Anastasiya", "Komissarova", (byte) 7);
            System.out.printf("User с именем – %s добавлен в базу данных\n", "Anastasiya");
            userService.saveUser("Natalya", "Komissarova", (byte) 45);
            System.out.printf("User с именем – %s добавлен в базу данных\n", "Natalya");
            List<User> userList = userService.getAllUsers();
            for (User user : userList) {
                System.out.println(user);
            }
            userService.cleanUsersTable();
            userService.dropUsersTable();
    }
}
