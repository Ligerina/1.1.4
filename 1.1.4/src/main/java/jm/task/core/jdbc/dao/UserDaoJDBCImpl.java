package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String query;
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
//        query = "insert into mydbtest.table_name (name,lastname,age) values ("+ name + "," + lastName + "," + age + ")";
        query = "insert into mydbtest.table_name (name,lastname,age) values (name,lastName,age)";
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить нового человека ");
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
