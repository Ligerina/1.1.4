package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
//        userDaoJDBC.saveUser("Anton1","Sukhankin", (byte) 22);
//        userDaoJDBC.saveUser("Anton2","Sukhankin", (byte) 22);
//        userDaoJDBC.saveUser("Anton3","Sukhankin", (byte) 22);
//        userDaoJDBC.saveUser("Anton4","Sukhankin", (byte) 22);
//        userDaoJDBC.dropUsersTable();

        List<User> list = userDaoJDBC.getAllUsers();
        System.out.println(list);
        System.out.println("");
        System.out.println("amount: " + list.size());

//        userDaoJDBC.removeUserById(3);

    }

}