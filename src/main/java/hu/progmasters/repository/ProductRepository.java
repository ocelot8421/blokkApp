package hu.progmasters.repository;

import hu.progmasters.domain.Product;

import java.sql.*;
import java.time.format.DateTimeFormatter; //TODO
import java.util.ArrayList;
import java.util.List;

import static hu.progmasters.repository.DataBaseConfig.*;

public class ProductRepository {
    Connection connection;

    public ProductRepository() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createProductTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS product (" +
                "id INT NOT NULL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "price DOUBLE NOT NULL" +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public String createNewProduct(Product product) {
        String infoBack = "Product can not be created";
        String insertCityStatement = "INSERT INTO product VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCityStatement)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.executeUpdate();
            infoBack = "Product created";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return infoBack;
    }

    public List<Product> searchAllProductOrderByPrice() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY price";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public Product searchProductById(int id) {
        Product product = null;
        String sql = "SELECT id, name, price FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
}


