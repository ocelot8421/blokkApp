package hu.progmasters.repository;

import hu.progmasters.domain.ProductList;

import java.sql.*;
import java.util.List;

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
//                "price DOUBLE NOT NULL," +
//                "amount DOUBLE NOT NULL" +
                "FOREIGN KEY (product_id) REFERENCES product(id), " +
                "FOREIGN KEY (block_id) REFERENCES block(id) " +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String createNewProductList(ProductList productList){
        String infoBack =  "ProductList can not be created";
        String insertProductListStatement = "INSERT INTO product VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductListStatement)) {
            preparedStatement.setInt(1, productList.getId());
            preparedStatement.setInt(2, productList.getId());
            preparedStatement.setInt(3, productList.getId());
        } catch (SQLException e) {
            System.out.println(" -- Exception: " + e.getMessage());

        }
    }


}
