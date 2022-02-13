package hu.progmasters;

import java.sql.*;
import java.util.Scanner;

import static hu.progmasters.config.DatabaseConfig.*;

public class BlockGenerator {
    public static void main(String[] args) {

        createShopTable();
//        createShop();
        updateShop();

////        try (Scanner scanner = new Scanner(System.in)) {
////            giveShopInfo();
////
////            System.out.println("Which shop do you want to modify? (Enter its ID");
////            System.out.println("Shop's ID:");
////            int shopID = scanner.nextInt();
////            scanner.nextLine();
////            System.out.println("Which field would you like to modify?");
////            String shopField = scanner.nextLine();
////            System.out.println("What is the new value?");
////            String shopValue = scanner.nextLine();
////
////            updateShop(shopValue, shopID);
////        }

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
////            String updateShop =
////                    "UPDATE shops SET ? = ? " +
////                            "WHERE id = ?";
////            PreparedStatement preparedStatement = connection.prepareStatement(updateShop);
////            preparedStatement.setString(1, field);
////            preparedStatement.setString(2, value);
////            preparedStatement.setInt(3, id);
            statement.execute("SET sql_safe_updates = 0");
            int affectedRows = statement.executeUpdate(updateShop);
//            int affectedRows = preparedStatement.executeUpdate(updateShop);

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
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("A shop has been already existed with this ID");
            e.printStackTrace();
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
