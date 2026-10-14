package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationSuccessPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSuccessPage.class);

    /**
     * Регулярка ищет цифровой номер заявки. Строку "Ваша заявка № null"
     * не матчит — там вместо цифр буквы. Ждём именно цифры.
     */
    private static final Pattern APPLICATION_NUMBER_PATTERN =
            Pattern.compile("Ваша заявка\\s*№\\s*(\\d+)");

    // Модальное окно MUI
    private final By successModal =
            By.cssSelector("div.MuiModal-root.MuiDialog-root");

    public ApplicationSuccessPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Ждёт, пока в модальном окне появится цифровой номер заявки,
     * и возвращает его.
     */
    @Step("Считываем номер заявки со страницы подтверждения")
    public String getApplicationNumber() {
        logger.info("Ждём появления модального окна с номером заявки");
        WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 1. Ждём, пока модальное окно появится
        WebElement modal = extendedWait.until(
                ExpectedConditions.visibilityOfElementLocated(successModal));

        // 2. Ждём, пока в тексте модального окна появится цифровой номер
        extendedWait.until(d -> {
            String text = modal.getText();
            return APPLICATION_NUMBER_PATTERN.matcher(text).find();
        });

        // 3. Извлекаем номер из текста всего модального окна
        String fullText = modal.getText();
        Matcher matcher = APPLICATION_NUMBER_PATTERN.matcher(fullText);
        if (matcher.find()) {
            String number = matcher.group(1);
            logger.info("Номер заявки: {}", number);
            return number;
        }

        throw new RuntimeException(
                "Не удалось извлечь номер заявки из текста модального окна:\n" + fullText);
    }
}