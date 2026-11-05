package tests.api;

import org.junit.jupiter.api.Test;
import utils.DbConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbConnectionTest {

    @Test
    public void connectionWorks() throws Exception {
        Connection conn = DbConnection.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("DB: " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());

        // Посмотреть все таблицы
        ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println("TABLE: " + tables.getString("TABLE_NAME"));
        }
        assertTrue(true);
    }
}