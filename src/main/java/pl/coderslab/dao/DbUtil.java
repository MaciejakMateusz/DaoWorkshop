package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";
    protected static final String DATABASE = "workshop2"; //

    public static Connection getConnection(String databaseName) throws SQLException {
        String url = DB_URL + databaseName +"?useSSL=false&characterEncoding=utf8";
        return DriverManager.getConnection(url,DB_USER,DB_PASS);
    }


}
