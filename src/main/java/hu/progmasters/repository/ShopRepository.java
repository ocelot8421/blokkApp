package hu.progmasters.repository;

import hu.progmasters.domain.Address;
import hu.progmasters.domain.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static hu.progmasters.repository.DataBaseConfig.*;

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

        String createTable = "CREATE TABLE IF NOT EXISTS shop" +
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
    public void updateTable () {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)){
            String update = "ALTER TABLE shop MODIFY COLUMN ID INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createNewShop(Shop shop) {
        String infoBack = "Shop can not be created";
        String insertShopStatement = "INSERT INTO shops VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertShopStatement)) {
            preparedStatement.setInt(1, shop.getId());
            preparedStatement.setString(2, shop.getFranchise());
            preparedStatement.setString(3, shop.getAddress().getCity());
            preparedStatement.setString(4, shop.getAddress().getStreet());
            preparedStatement.executeUpdate();
//            System.out.println("Shop created");
            infoBack = "Shop created";
        } catch (SQLException throwables) {
            System.out.println("This id has been used for a shop. PLease, chose another.");
            //TODO van egy shop meg egy shops táblázat is - Hajni
            //TODO utolsó id-t kiiratni - Hajni
            throwables.printStackTrace();
        }
        return infoBack;
    }

    public Shop searchShopByName (String franchise) {
        Shop shop = null;
        String sql = "SELECT id, name, address FROM shop WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, franchise);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address(resultSet.getString("shop"), resultSet.getString("street"));
                shop = new Shop(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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
                    "UPDATE shop SET " + shopFiled + " = '" + shopValue + "' " +
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
                System.out.println(" út/utca");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Shop searchShopById(int id) {
        Shop shop = null;
        String sql = "SELECT id, name, city, street  FROM shops WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Address address;
            if (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street")
                );
                shop = new Shop(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        address
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }

    public List<Shop> printOutAllShopDetails() {
        List<Shop> shops = new ArrayList<>();
        String sql = "SELECT * FROM shops";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            Address address;
            while (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street")
                );
                shops.add(new Shop(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        address
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shops;
    }
}
