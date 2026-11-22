package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Хранит staffid последнего созданного администратора.
 * Нужен для передачи ID между AdminRegistrationApiTest и AdminDbVerifyTest.
 */
public class AdminStaffIdStorage {

    private static final Path FILE = Paths.get("target", "admin-staff-id.txt");

    public static void save(int staffId) {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, String.valueOf(staffId), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить staffId в " + FILE, e);
        }
    }

    public static int read() {
        if (!Files.exists(FILE)) {
            throw new RuntimeException(
                    "Файл " + FILE + " не найден. Сначала запустите AdminRegistrationApiTest.");
        }
        try {
            String value = Files.readString(FILE, StandardCharsets.UTF_8).trim();
            if (value.isEmpty()) {
                throw new RuntimeException("Файл " + FILE + " пуст.");
            }
            return Integer.parseInt(value);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать staffId из " + FILE, e);
        }
    }
}