package hu.progmasters.repository;

import hu.progmasters.domain.*;

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
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS productList " +
                "(id INT AUTO_INCREMENT NOT NULL, " +
                "product_id INT NOT NULL, " +
                "block_id INT NOT NULL, " +
//                "name VARCHAR(50) NOT NULL," +
                "price DOUBLE NOT NULL, " +
                "amount DOUBLE NOT NULL, " +
                "PRIMARY KEY (id), " +
                "FOREIGN KEY (product_id) REFERENCES product(id), " +
                "FOREIGN KEY (block_id) REFERENCES block(id) " +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String update = "ALTER TABLE productList MODIFY COLUMN id INT PRIMARY KEY AUTO_INCREMENT";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createNewProductList(ProductList productList) {
        String infoBack = "ProductList can not be created";
        String insertProductListStatement = "INSERT INTO productList (product_id, block_id, price, amount) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertProductListStatement)) {
//            preparedStatement.setInt(1, productList.getIdList());
            preparedStatement.setInt(1, productList.getIdProduct());
            preparedStatement.setInt(2, productList.getIdBlokk());
            preparedStatement.setDouble(3, productList.getPrice());
            preparedStatement.setDouble(4, productList.getAmount());

            preparedStatement.executeUpdate();
            infoBack = "Productlist is created";
        } catch (SQLException e) {
            System.out.println(" -- Exception: " + e.getMessage());
            e.printStackTrace();

        }
        return infoBack;
    }

    public ProductList searchProductListById(int id) {
        ProductList productList = null;
        String sql = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productList = new ProductList(
                        resultSet.getInt("id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("block_id"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("amount"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    public List<ProductList> printOutAllProductListDetails() {
        List<ProductList> productLists = new ArrayList<>();
        String sql = "SELECT *, " +
                "p.name AS product_name, " +
                "s.name AS shop_name " +
                "FROM block b " +
                "JOIN productList pl ON pl.block_id = b.id " +
                "JOIN product p ON pl.product_id = p.id " +
                "JOIN shop s ON b.shop_id = s.id ;";

//        SELECT *,
//                p.name AS product_name,
//                s.name AS shop_name
//                FROM productList pl
//                JOIN block b ON pl.block_id = b.id
//                JOIN product p ON pl.product_id = p.id
//                JOIN shop s ON b.shop_id = s.id ;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            Product product;
            Block blokk;
            Shop shop;
            Address address;
            while (resultSet.next()) {
                product = new Product(resultSet.getString("product_name"));
                address = new Address(
                        resultSet.getString("city"),
                        resultSet.getString("street")
                );
                shop = new Shop(
                        resultSet.getString("shop_name"),
                        address
                );
                blokk = new Block(
                        resultSet.getInt("shop_id"),
                        shop,
                        resultSet.getString("date")
                );
                productLists.add(new ProductList(
                        product,
                        blokk,
                        resultSet.getInt("product_id"),
                        resultSet.getInt("block_id"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("amount")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productLists;
    }

//    public List<Product> addProduct (Product product) {
//        Scanner scanner = new Scanner(System.in);
//        int productId = scanner.nextInt();
//
//    }
}
