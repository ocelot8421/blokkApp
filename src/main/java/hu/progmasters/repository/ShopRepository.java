package hu.progmasters.repository;

import hu.progmasters.domain.Address;
import hu.progmasters.domain.Shop;

import java.sql.*;
import java.util.Scanner;

import static hu.progmasters.config.DatabaseConfig.*;

public class ShopRepository {

    Connection connection;

    public ShopRepository() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void createShopTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS shops" +
                    "(ID INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "city VARCHAR(50), " +
                    "street VARCHAR(50) " +
                    "); ";
           try (Statement statement = connection.createStatement()) {
               statement.execute(createTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewShop(Shop shop) {

        String insertShopStatement = "INSERT INTO shop VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertShopStatement)) {
            preparedStatement.setInt(1, shop.getId());
            preparedStatement.setString(2, shop.getFranchise());
            preparedStatement.setObject(3, shop.getAddress());

            preparedStatement.executeUpdate();
            System.out.println("Shop created");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Shop searchShopByName (String franchise) {
        Shop shop = null;
        String sql = "SELECT id, franchise, address FROM shops WHERE franchise = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, franchise);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address(resultSet.getString("city"), resultSet.getString("street"));
                shop = new Shop(
                        resultSet.getInt("id"),
                        resultSet.getString("franchise"),
                        address
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }

    private static void updateShop() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); Scanner scanner = new Scanner(System.in)) {
            Statement statement = connection.createStatement();

            giveShopInfo();
            System.out.println("Which shop do you want to modify? (Enter its ID");


            System.out.println("Shop's ID:");
            int shopID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Which field would you like to modify?");
            String shopFiled = scanner.nextLine();
            System.out.println("What is the new value?");
            String shopValue = scanner.nextLine();

            String updateShop =
                    "UPDATE shops SET " + shopFiled + " = '" + shopValue + "' " +
                            "WHERE id = " + shopID + ";";

            statement.execute("SET sql_safe_updates = 0");
            int affectedRows = statement.executeUpdate(updateShop);


            System.out.println("Number of modified shops: " + affectedRows);
            giveShopInfo();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void giveShopInfo() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            String shopInfo = "SELECT * from shops";
            ResultSet resultSet = statement.executeQuery(shopInfo);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnsNumber = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                System.out.print(" -- " + resultSetMetaData.getColumnName(i));
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(" -- " + resultSet.getObject(i));
                }
                System.out.println(" utca");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
