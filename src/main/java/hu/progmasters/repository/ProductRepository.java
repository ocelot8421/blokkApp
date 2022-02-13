package hu.progmasters.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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


}
