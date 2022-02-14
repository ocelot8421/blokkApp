package hu.progmasters.repository;

import hu.progmasters.domain.Block;
import hu.progmasters.domain.Shop;

import java.sql.*;
import java.time.format.DateTimeFormatter;

import static hu.progmasters.config.DatabaseConfig.*;

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
                "FOREIGN KEY (product_id) REFERENCES product(id), " +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String createNewBlock(Block block) {
        String infoBack = "Block can not be created";
        String insertFlightStatement = "INSERT INTO flight VALUES (?,?,?,?)";
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

    public Block searchBlockByShop(Shop shop) {
        Block block = null;
        String sql = "SELECT * FROM block";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, shop.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }
}
