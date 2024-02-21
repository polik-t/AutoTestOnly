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

public class ValidationEmailFieldTest {
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

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Обязательное поле']")));
        Assert.assertEquals("Обязательное поле", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWhitespace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys(" ");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormErrorFormat() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("testgmail.com");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingErrorFormatWithoutPoint() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("testgmail@com");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingErrorFormatWhitespace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("test @gmail.com");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWithoutDomain() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("test");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWithoutName() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("@gmail.com");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormLongCode() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("test@gmail.commmmmmmmmmmmmm");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWithoutCode() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("test@gmail");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormShortCode() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("test@gmail.c");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormOnlyNumbers() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("112324234354");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormOnlyChars() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("!@#$%^&");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormMixedFormat() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("112324234354!@#$%^&*");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormLongEmail() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("averyveryverylonginvalidemailaddress@example.com");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='E-mail слишком длинный']")));
        Assert.assertEquals("E-mail слишком длинный", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormShortEmail() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*']")));
        nameField.sendKeys("a@b.c");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='E-mail*'][error='E-mail слишком короткий']")));
        Assert.assertEquals("E-mail слишком короткий", errorMessage.getAttribute("error"));
    }
}
