package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendFormValidTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testSendingFormWithRequiredFields() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("Валентина");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='E-mail*']")));
        emailField.sendKeys("test@gmail.com");

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();

        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("9612162524");

        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Компания']")));
        companyField.sendKeys("ТестКомпани");

        WebElement radiobuttonBudget = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Менее 2 млн')]")));
        radiobuttonBudget.click();

        WebElement radiobuttonFind = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Рейтинги')]")));
        radiobuttonFind.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
        driver.switchTo().frame(iframe);
        WebElement recaptchaCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'recaptcha-checkbox')]")));
        recaptchaCheckbox.click();
        driver.switchTo().defaultContent();

        WebElement buttonSend = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Отправить')]")));
        buttonSend.click();
    }

    @After
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
        }
    }
}
