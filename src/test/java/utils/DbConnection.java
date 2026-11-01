package utils;

import config.ConfigReader;
import config.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private static Connection connection;

    private DbConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            Credentials creds = ConfigReader.readCredentials();
            logger.info("Подключаемся к БД: {}", creds.getDbUrl());

            try {
                connection = DriverManager.getConnection(
                        creds.getDbUrl(),
                        creds.getDbUser(),
                        creds.getDbPassword());
                logger.info("Подключение к БД установлено");
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось подключиться к БД: " + e.getMessage(), e);
            }
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Подключение к БД закрыто");
            } catch (SQLException e) {
                logger.warn("Ошибка закрытия подключения: {}", e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}