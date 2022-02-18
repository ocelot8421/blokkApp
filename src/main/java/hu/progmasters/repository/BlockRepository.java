package hu.progmasters.repository;

import hu.progmasters.domain.*;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
                "date DATE NOT NULL, " +
                "FOREIGN KEY (shop_id) REFERENCES shop(id), " +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTable () {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)){
            String update = "ALTER TABLE block MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String createNewBlock(Block block) {
        String infoBack = "Block can not be created";
        String sql = "INSERT INTO block VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, block.getId());
            preparedStatement.setInt(2, block.getShop().getId());
       //     preparedStatement.setInt(3, block.getProductList().getIdList());
            preparedStatement.setString(5, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(block.getDate()));
            preparedStatement.executeUpdate();
            infoBack = "Block created";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ne) {
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
                ProductList productList = new ProductList();
                List<Product> products = new ArrayList<>();
//                productList.setProductList(products);
//                products.add(new Product(resultSet.getInt("product_id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("price"),
//                        resultSet.getDouble("amount")));
                block = new Block(
                        resultSet.getInt("id"),
                        new Shop(resultSet.getInt("shop_id"),
                                resultSet.getString("franchise"),
                                (Address) resultSet.getObject("address")),
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
                ProductList productList = new ProductList();
//                productList.add(new Product(resultSet.getInt("product_id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("price"),
//                        resultSet.getDouble("amount")));
                block = new Block(
                        resultSet.getInt("id"),
                        new Shop(resultSet.getInt("shop_id"),
                                resultSet.getString("franchise"),
                                (Address) resultSet.getObject("address")),
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

