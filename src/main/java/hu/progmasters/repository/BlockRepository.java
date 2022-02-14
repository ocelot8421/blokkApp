package hu.progmasters.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
}
