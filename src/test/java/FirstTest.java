import factory.BrowserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import config.ConfigReader;
import config.Credentials;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = BrowserFactory.createDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void checkTitle() throws InterruptedException {
        Credentials creds = ConfigReader.readCredentials();

        String urlWithAuth = "https://" + creds.getUsername() + ":" + creds.getPassword()
                + "@" + creds.getBaseUrl().replace("https://", "");

        driver.get(urlWithAuth);
        Thread.sleep(8000);

        WebElement button = driver.findElement(By.xpath("//button[contains(text(), 'Войти как пользователь')]"));
        button.click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='TextInputField-1']")).sendKeys("Иванов");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='TextInputField-2']")).sendKeys("Иван");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='TextInputField-3']")).sendKeys("Иванович");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='TextInputField-4']")).sendKeys("89999999999");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='TextInputField-5']")).sendKeys("00000000");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='TextInputField-6']")).sendKeys("ул дом");
        Thread.sleep(1000);


        driver.findElement(By.cssSelector("button:nth-child(2)")).click();
        Thread.sleep(2000);

        WebElement button2 = driver.findElement(By.xpath("//button[contains(text(), 'Регистрация брака')]"));
        button2.click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[contains(text(), 'Фамилия')]/following::input[1]")).sendKeys("asd");

        driver.findElement(By.xpath("//label[contains(text(), 'Имя')]/following::input[1]")).sendKeys("rtg");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Отчество')]/following::input[1]")).sendKeys("efrfer");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Дата рождения')]/following::input[1]")).sendKeys("11.11.1111");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Номер паспорта')]/following::input[1]")).sendKeys("rdfr");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Пол')]/following::input[1]")).sendKeys("erfe");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Адрес прописки')]/following::input[1]")).sendKeys("erfe");
        Thread.sleep(1000);

        WebElement button3 = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]"));
        button3.click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[contains(text(), 'Дата регистрации')]/following::input[1]")).sendKeys("11.11.1111");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Новая фамилия')]/following::input[1]")).sendKeys("sdf");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Фамилия супруга/и')]/following::input[1]")).sendKeys("sdfsdf");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Имя супруга/и')]/following::input[1]")).sendKeys("sdf");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Отчество супруга/и')]/following::input[1]")).sendKeys("dfhfhsuidfh");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Дата рождения супруга/и')]/following::input[1]")).sendKeys("11.11.1111");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[contains(text(), 'Номер паспорта супруга/и')]/following::input[1]")).sendKeys("1111101");
        Thread.sleep(1000);

        WebElement button4 = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[3]"));
        button4.click();
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}