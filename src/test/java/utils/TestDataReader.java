package utils;

import com.google.gson.Gson;
import model.api.AdminRequestDTO;
import model.api.UserRequestDTO;
import model.ui.AdminDTO;
import model.ui.BirthApplicationDTO;
import model.ui.DeathApplicationDTO;
import model.ui.MarriageApplicationDTO;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestDataReader {

    private static final Gson gson = new Gson();

    public static MarriageApplicationDTO readMarriageData() {
        return read("testdata/marriageData.json", MarriageApplicationDTO.class);
    }

    public static BirthApplicationDTO readBirthData() {
        return read("testdata/birthData.json", BirthApplicationDTO.class);
    }

    public static DeathApplicationDTO readDeathData() {
        return read("testdata/deathData.json", DeathApplicationDTO.class);
    }

    public static AdminDTO readAdminData() {
        return read("testdata/adminData.json", AdminDTO.class);
    }
    public static UserRequestDTO readWeddingRequest() {
        return read("testdata/user-wedding-request.json", UserRequestDTO.class);
    }

    public static AdminRequestDTO readAdminRequest() {
        return read("testdata/admin-request.json", AdminRequestDTO.class);
    }

    private static <T> T read(String path, Class<T> clazz) {
        try (InputStream is = TestDataReader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Не найден файл тестовых данных в classpath: " + path);
            }
            return gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения тестовых данных из " + path + ": " + e.getMessage(), e);
        }
    }
}