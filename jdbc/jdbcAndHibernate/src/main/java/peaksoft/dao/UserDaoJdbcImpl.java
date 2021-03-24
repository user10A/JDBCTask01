package peaksoft.dao;

import Config.Config;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection = Util.getConnection();


    public void createUsersTable() {
        Statement statement = null;
        try {
            statement= connection.createStatement();
            statement.executeUpdate("""
        create table if not exists \"User\"(
        id serial primary key ,
        name varchar(50),
        lastName varchar ,
        age int) 
        """);
            System.out.println("Table successfully created!");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement=connection.createStatement();
            statement.executeUpdate("""
DROP TABLE IF EXISTS "User";
""");
            System.out.println("table user successfully dropping ");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement= connection.prepareStatement("""
    insert into "User" (name,lastName,age)
    values (?,?,?)
    """);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User successfully saved!");
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement= connection.prepareStatement("""
    delete from "User" where id=?
    """);
            preparedStatement.setLong(1,id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted user with id = " + id);
            } else {
                System.out.println("No student found with id = " + id);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User>users = new ArrayList<>();
        Statement statement = null;
        try {
            statement= connection.createStatement();
            ResultSet resultSet = statement.executeQuery("""
select * from "User"
""");
            while(resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")
                ));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            statement= connection.createStatement();
            statement.executeUpdate("""
DELETE FROM "User"
""");
            System.out.println("User table successfully cleaning");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}