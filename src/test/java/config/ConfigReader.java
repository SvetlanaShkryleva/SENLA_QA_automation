package config;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_PATH = "/browser-config.json";
    private static final String CREDENTIALS_PATH = "/credentials.properties";

    public static BrowserConfig read() {
        try (InputStream is = ConfigReader.class.getResourceAsStream(CONFIG_PATH)) {
            if (is == null) {
                throw new RuntimeException("Не найден файл " + CONFIG_PATH + " в classpath");
            }
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, BrowserConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения конфига: " + e.getMessage(), e);
        }
    }

    public static Credentials readCredentials() {
        Properties props = new Properties();
        try (InputStream is = ConfigReader.class.getResourceAsStream(CREDENTIALS_PATH)) {
            if (is == null) {
                throw new RuntimeException("Не найден файл " + CREDENTIALS_PATH + " в classpath. " +
                        "Скопируйте credentials.example.properties в credentials.properties и заполните.");
            }
            props.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения credentials: " + e.getMessage(), e);
        }

        Credentials creds = new Credentials();
        creds.setUsername(props.getProperty("username"));
        creds.setPassword(props.getProperty("password"));
        creds.setBaseUrl(props.getProperty("baseUrl"));
        return creds;
    }
}