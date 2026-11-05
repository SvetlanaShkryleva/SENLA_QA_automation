package utils;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbHelper {

    private static final Logger logger = LoggerFactory.getLogger(DbHelper.class);

    @Step("Запрос в БД: {sql} с параметрами {params}")
    public static Map<String, Object> querySingleRow(String sql, Object... params) {
        logger.info("Выполняем SQL: {} | params: {}", sql, params);
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    logger.info("Найдена запись: {}", row);
                    return row;
                }
                logger.warn("Запись не найдена по запросу: {}", sql);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка выполнения SQL: " + sql, e);
        }
    }

    @Step("Проверка существования записи: {sql}")
    public static boolean exists(String sql, Object... params) {
        return querySingleRow(sql, params) != null;
    }
}