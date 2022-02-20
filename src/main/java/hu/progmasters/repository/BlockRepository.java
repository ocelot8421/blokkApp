package hu.progmasters.repository;

import hu.progmasters.domain.*;

import java.sql.*;
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
                "id INT AUTO_INCREMENT NOT NULL, " +
                "shop_id INT NOT NULL, " +
                "date DATE NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (shop_id) REFERENCES shop(id) " +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String update = "ALTER TABLE block MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String createNewBlock(Block block) {
        String infoBack = "Block can not be created";
        String sql = "INSERT INTO block VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, block.getId());
            preparedStatement.setInt(2, block.getShop().getId());
//          preparedStatement.setString(3, DateTimeFormatter.ofPattern("yyyy-MM-dd").format((TemporalAccessor) block.getDate()));
            preparedStatement.setString(3, block.getDate());
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
                                resultSet.getString("name"),
                                (Address) resultSet.getObject("address")),
                        resultSet.getString("date"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public Block searchBlockById(int id) {

        Block block = null;
        String sql = "SELECT * FROM blokk_app.block b " +
                "JOIN shop s ON s.id = b.shop_id " +
                "WHERE b.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Address address;
            while (resultSet.next()) {
                address = new Address(
                        resultSet.getInt("id"),
                        resultSet.getString("city"),
                        resultSet.getString("street")
                );
                block = new Block(
                        resultSet.getInt("id"),
                        new Shop(resultSet.getInt("shop_id"),
                                resultSet.getString("name"),
                                address),
                        resultSet.getString("date"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return block;
    }

    public List<Block> printOutAllBlockDetails() {
        List<Block> blokks = new ArrayList<>();
        String sql = "SELECT * FROM blokk_app.block b " +
                "JOIN blokk_app.shop s ON s.id = b.shop_id; ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            Shop shop;
            while (resultSet.next()) {
                shop = new Shop(
                        resultSet.getInt("shop_id"),
                        resultSet.getString("name"),
                        new Address(
                                resultSet.getString("city"),
                                resultSet.getString("street")
                        )
                );
                blokks.add(new Block(
                        resultSet.getInt("id"),
                        shop,
                        resultSet.getString("date")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return blokks;
    }
}

