package hu.progmasters.repository;

import hu.progmasters.domain.Shop;

import java.sql.*;

import static hu.progmasters.repository.DataBaseConfig.*;

public class ShopRepository {

    Connection connection;


    public void createShopTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            String createTable = "CREATE TABLE IF NOT EXISTS shops" +
                    "(ID INT PRIMARY KEY, " +
                    "name VARCHAR(50), " +
                    "city VARCHAR(50), " +
                    "street VARCHAR(50) " +
                    "); ";
            statement.execute(createTable);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewShop(Shop shop) {

        String insertShopStatement = "INSERT INTO shop VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertShopStatement)) {
            preparedStatement.setInt(1, shop.getId());
            preparedStatement.setString(2, shop.getFranchise());
            preparedStatement.setString(3, shop.getAddress());

            preparedStatement.executeUpdate();
            System.out.println("Shop created");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
