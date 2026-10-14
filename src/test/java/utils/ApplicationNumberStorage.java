package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Хранит номера созданных заявок в одном properties-файле.
 * Ключи: marriage, birth, death.
 */
public class ApplicationNumberStorage {

    public static final String MARRIAGE = "marriage";
    public static final String BIRTH    = "birth";
    public static final String DEATH    = "death";

    private static final Path FILE =
            Paths.get("target", "application-numbers.properties");

    /**
     * Сохраняет номер заявки под указанным ключом.
     * Остальные ключи в файле сохраняются.
     */
    public static void save(String service, String applicationNumber) {
        Properties props = loadProperties();
        props.setProperty(service, applicationNumber);

        try {
            Files.createDirectories(FILE.getParent());
            try (OutputStream out = Files.newOutputStream(FILE)) {
                props.store(out, "Номера последних созданных заявок");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить номер заявки в " + FILE, e);
        }
    }

    /**
     * Читает номер заявки по ключу.
     */
    public static String read(String service) {
        Properties props = loadProperties();
        String value = props.getProperty(service);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException(
                    "В файле " + FILE + " нет номера для '" + service + "'. "
                            + "Сначала запустите тест на создание заявки для этой услуги.");
        }
        return value.trim();
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        if (Files.exists(FILE)) {
            try (InputStream in = Files.newInputStream(FILE)) {
                props.load(in);
            } catch (IOException e) {
                throw new RuntimeException("Не удалось прочитать " + FILE, e);
            }
        }
        return props;
    }
}