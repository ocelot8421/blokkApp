package hu.progmasters.repository;

import hu.progmasters.domain.Address;
import hu.progmasters.domain.Block;
import hu.progmasters.domain.Product;
import hu.progmasters.domain.Shop;

import java.sql.*;
import java.time.format.DateTimeFormatter;

import static hu.progmasters.repository.DataBaseConfig.*;

public class BlockRepository {
    Connection connection;

    public BlockRepository() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void createBlockTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS block (" +
                "id INT NOT NULL PRIMARY KEY," +
                "shop_id INT NOT NULL," +
                "product_id INT NOT NULL," +
                "amount DOUBLE NOT NULL," +
                "date DATE NOT NULL, " +
                "FOREIGN KEY (shop_id) REFERENCES shop(id), " +
                "FOREIGN KEY (product_id) REFERENCES product(id) " +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String createNewBlock(Block block) {
        String infoBack = "Block can not be created";
        String sql = "INSERT INTO block VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, block.getId());
            preparedStatement.setInt(2, block.getShop().getId());
            preparedStatement.setInt(3, block.getProduct().getId());
            preparedStatement.setDouble(4, block.getAmount());
            preparedStatement.setString(5, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(block.getDate()));
            preparedStatement.executeUpdate();
            infoBack = "Block created";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ne){
            System.out.println("Product doesn't exist with this ID.");
        }
        return infoBack;
    }

    public Block searchBlockByShop(Shop shop) {
        Block block = null;
        String sql = "SELECT * FROM blokk_app.block b \n" +
                "JOIN shop s ON s.id = b.shop_id\n" +
                "JOIN product p ON p.id = b.product_id" +
                "WHERE s.franchise = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, shop.getFranchise());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                block = new Block(
                        resultSet.getInt("id"),
                        new Shop(resultSet.getInt("shop_id"),
                                resultSet.getString("franchise"),
                                (Address) resultSet.getObject("address")),

                        new Product(resultSet.getInt("product_id"),
                                resultSet.getString("name"),
                                resultSet.getDouble("price"),
                                resultSet.getDouble("amount")),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("date").toLocalDate());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public Block searchBlockById(int id) {

        Block block = null;
        String sql = "SELECT * FROM blokk_app.block b \n" +
                "JOIN shop s ON s.id = b.shop_id\n" +
                "JOIN product p ON p.id = b.product_id" +
                "WHERE b.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                block = new Block(
                        resultSet.getInt("id"),
                        new Shop(resultSet.getInt("shop_id"),
                                resultSet.getString("franchise"),
                                (Address) resultSet.getObject("address")),

                        new Product(resultSet.getInt("product_id"),
                                resultSet.getString("name"),
                                resultSet.getDouble("price"),
                                resultSet.getDouble("amount")),
                        resultSet.getDouble("amount"),
                        resultSet.getDate("date").toLocalDate());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public String createNewBlockTestHajni(Block block) {
        String infoBack = "Block can not be created";
        String insertFlightStatement = "INSERT INTO blokk VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertFlightStatement)) {
            preparedStatement.setInt(1, block.getId());
            preparedStatement.setInt(2, block.getShop().getId());
            preparedStatement.setDouble(3, block.getAmount());
            preparedStatement.setString(4, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(block.getDate()));
            preparedStatement.executeUpdate();
            infoBack = "Block created";
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return infoBack;
    }

    public Block searchBlockByIdTestHajni(int askIntFromUser) {
        System.out.println("Fejlesztés alatt");
        return null;
    }
}

