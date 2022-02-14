package hu.progmasters.repository;

import hu.progmasters.Address;
import hu.progmasters.domain.Shop;

import java.sql.*;

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
            preparedStatement.setString(3, shop.getAddress());

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
                Address address = new Address()
                shop = new Shop(
                        resultSet.getInt("id"),
                        resultSet.getString("franchise"),
                        resultSet.getString("address")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }
}
