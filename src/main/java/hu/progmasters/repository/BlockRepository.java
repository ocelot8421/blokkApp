package hu.progmasters.repository;

import hu.progmasters.domain.Block;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
//                "name VARCHAR(50) NOT NULL," + //TODO
//                "price DOUBLE NOT NULL" +
                ");"
                ;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreateTable);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
