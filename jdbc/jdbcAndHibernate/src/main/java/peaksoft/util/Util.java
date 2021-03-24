package peaksoft.util;

import peaksoft.dao.UserDao;
import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Connection getConnection(){
        Connection connection;
        try {
            connection= DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres1.1",
                    "postgres",
                    "erkin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}


