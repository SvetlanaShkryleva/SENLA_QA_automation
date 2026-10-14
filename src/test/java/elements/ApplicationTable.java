package elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class ApplicationTable {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationTable.class);

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By table = By.cssSelector("table.MuiTable-root");
    private final By dataRows =
            By.cssSelector("table.MuiTable-root tr.MuiTableRow-root:not(.MuiTableRow-head)");
    private final By cells = By.cssSelector("td.MuiTableCell-root");
    private final By statusCell = By.cssSelector("td.MuiTableCell-alignCenter");

    public ApplicationTable(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Ждём загрузки таблицы заявок")
    public ApplicationTable waitForTable() {
        logger.info("Ждём загрузки таблицы заявок");
        wait.until(ExpectedConditions.visibilityOfElementLocated(table));
        return this;
    }

    @Step("Проверяем, есть ли заявка № {applicationNumber} в таблице")
    public boolean hasRowWithId(String applicationNumber) {
        logger.info("Ищем заявку № {} в таблице", applicationNumber);
        for (WebElement row : driver.findElements(dataRows)) {
            List<WebElement> rowCells = row.findElements(cells);
            if (!rowCells.isEmpty()
                    && rowCells.get(0).getText().trim().equals(applicationNumber.trim())) {
                logger.info("Заявка № {} найдена", applicationNumber);
                return true;
            }
        }
        logger.warn("Заявка № {} не найдена в таблице", applicationNumber);
        return false;
    }

    @Step("Получаем статус заявки № {applicationNumber}")
    public String getStatusTextById(String applicationNumber) {
        logger.info("Получаем статус заявки № {}", applicationNumber);
        WebElement row = findRowById(applicationNumber);
        String status = row.findElement(statusCell).getText().trim();
        logger.info("Статус заявки № {}: '{}'", applicationNumber, status);
        return status;
    }

    /**
     * Ищет строку по номеру заявки. Используется внутри класса.
     */
    private WebElement findRowById(String applicationNumber) {
        for (WebElement row : driver.findElements(dataRows)) {
            List<WebElement> rowCells = row.findElements(cells);
            if (!rowCells.isEmpty()
                    && rowCells.get(0).getText().trim().equals(applicationNumber.trim())) {
                return row;
            }
        }
        throw new RuntimeException("Заявка с номером " + applicationNumber + " не найдена в таблице");
    }
}