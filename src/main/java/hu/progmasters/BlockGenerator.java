package hu.progmasters;

import java.sql.*;
import java.util.Scanner;

public class BlockGenerator {

    public static final String DB_URL =
            "jdbc:mysql://blokkapp.cwb4knygtzsz.us-east-1.rds.amazonaws.com:3306/blokk_app?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "Test123!";

    public static void main(String[] args) {

        createShopTable();
        createShop();

    }

    private static void createShop() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); Scanner scanner = new Scanner(System.in)) {
            Statement statement = connection.createStatement();

            System.out.println("Shop's ID:");
            int shopID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Shop's name:");
            String shopName = scanner.nextLine();
            System.out.println("City:");
            String shopCity = scanner.nextLine();
            System.out.println("Street:");
            String shopStreet = scanner.nextLine();

            String createShop = "INSERT INTO shops (ID, name, city, street) " +
                    "VALUES (" + shopID + ", '" + shopName + "', '" + shopCity + "', '" + shopStreet + "')";

            statement.execute(createShop);

            System.out.println("New shop has been created");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createShopTable() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
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
