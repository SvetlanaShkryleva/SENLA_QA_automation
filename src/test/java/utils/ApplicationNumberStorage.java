package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Хранит номер последней созданной заявки в файле target/application-number.txt.
 * Нужен, чтобы передавать данные между тестами, запускаемыми отдельно.
 */
public class ApplicationNumberStorage {

    private static final Path FILE = Paths.get("target", "application-number.txt");

    public static void save(String applicationNumber) {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, applicationNumber, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить номер заявки в " + FILE, e);
        }
    }

    public static String read() {
        if (!Files.exists(FILE)) {
            throw new RuntimeException(
                    "Файл " + FILE + " не найден. Сначала запустите тест на создание заявки.");
        }
        try {
            String value = Files.readString(FILE, StandardCharsets.UTF_8).trim();
            if (value.isEmpty()) {
                throw new RuntimeException("Файл " + FILE + " пуст.");
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать номер заявки из " + FILE, e);
        }
    }
}