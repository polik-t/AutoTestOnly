package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidationNameFieldTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSendingAnEmptyFields() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Обязательное поле']")));
        Assert.assertEquals("Обязательное поле", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormInvalidCharacters() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("!@#$%^&*");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWhiteSpace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys(" ");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormMixedFormat() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("АПРФЫЫА21324!@#$%^");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormNumbers() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("12132434565");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormLongName() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("Этооченьдлинноеназвание,котороенедолжнопомещатьсявполеимени");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Имя слишком длинное']")));
        Assert.assertEquals("Имя слишком длинное", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormShortName() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*']")));
        nameField.sendKeys("А");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Имя*'][error='Имя слишком короткое']")));
        Assert.assertEquals("Имя слишком короткое", errorMessage.getAttribute("error"));
    }
}