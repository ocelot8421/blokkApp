package hu.progmasters.repository;

import hu.progmasters.domain.Block;
import hu.progmasters.domain.Product;
import hu.progmasters.domain.ProductList;
import hu.progmasters.domain.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static hu.progmasters.repository.DataBaseConfig.*;

public class ProductListRepository {
    Connection connection;

    public ProductListRepository() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProductListTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS productList (" +
                "id INT NOT NULL PRIMARY KEY," +
                "product_id INT NOT NULL," +
                "block_id INT NOT NULL, " +
//                "name VARCHAR(50) NOT NULL," +
                "price DOUBLE NOT NULL," +
                "amount DOUBLE NOT NULL" +
                "FOREIGN KEY (product_id) REFERENCES product(id), " +
                "FOREIGN KEY (block_id) REFERENCES block(id) " +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTable () {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)){
            String update = "ALTER TABLE productList MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createNewProductList(ProductList productList) {
        String infoBack = "ProductList can not be created";
        String insertProductListStatement = "INSERT INTO productList VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductListStatement)) {
            preparedStatement.setInt(1, productList.getIdList());
            preparedStatement.setInt(2,productList.getIdProduct());
            preparedStatement.setInt(3, productList.getIdBlokk());

            preparedStatement.executeUpdate();
            infoBack = "Productlist is created";
        } catch (SQLException e) {
            System.out.println(" -- Exception: " + e.getMessage());
            e.printStackTrace();

        }
        return infoBack;
    }

//    public List<Product> addProduct (Product product) {
//        Scanner scanner = new Scanner(System.in);
//        int productId = scanner.nextInt();
//
//    }
}
