package hu.progmasters.repository;

import hu.progmasters.domain.Product;

import java.sql.*;
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
                "id INT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL " +
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //TODO updateProductTable
    public void updateTable () {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)){
            String update = "ALTER TABLE productList MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String createNewProduct(Product product) {
        String infoBack = "Product can not be created";
        String insertProductStatement = "INSERT INTO product VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductStatement)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());

            preparedStatement.executeUpdate();
            infoBack = "Product is created";
        } catch (SQLException throwables) {
            System.out.println(" -- Exception: " + throwables.getMessage());
//            throwables.printStackTrace();
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
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public Product searchProductById(int id) {
        Product product = null;
        String sql = "SELECT id, name, price, amount FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public List<Product> printOutAllProductDetails() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return products;
    }


}


