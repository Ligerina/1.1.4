package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private String query;
    private String sqlRequest;
    private final String TABLE_NAME = "mydbtest.table_name";
    Connection connection = Util.connect();
    Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        query = "create table mydbtest.table_name\n" +
                "(\n" +
                "    id       int auto_increment invisible,\n" +
                "    name     varchar(40) null,\n" +
                "    lastName varchar(40) null,\n" +
                "    age      int         null,\n" +
                "    constraint table_name_pk\n" +
                "        primary key (id)\n" +
                ");\n";
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось создать таблицу или создать statement");
        }

    }

    public void dropUsersTable() {
        query = "drop table if exists table_name;";
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось удалить таблицу ");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        query = "insert into mydbtest.table_name (name,lastname,age) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить нового человека ");
        }
    }

    public void removeUserById(long id) {
        query = "delete from mydbtest.table_name where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        query = "SELECT * FROM mydbtest.table_name";
        List<User> listOfUsers = new ArrayList<>();
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1,TABLE_NAME);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                String name = res.getString("name");
                String lastName = res.getString("lastName");
                int age = res.getInt("age");
                User user = new User(name, lastName, (byte) age);
                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        query = "DELETE FROM table_name";
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}