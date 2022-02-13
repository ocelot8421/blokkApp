package hu.progmasters;

import java.sql.*;
import java.util.Scanner;

public class BlockGenerator {

    public static final String DB_URL =
            "jdbc:mysql://blokkapp.cwb4knygtzsz.us-east-1.rds.amazonaws.com:3306/blokk_app?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "Test123!";

    public static void main(String[] args) {

        createTable();



//        System.out.println(Shop.shopGenerator());
    }

    private static void createTable() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)){
            Statement statement = connection.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS shops" +
                    "(ID INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "city VARCHAR(50), " +
                    "street VARCHAR(50) " +
                    "); ";
            statement.execute(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
