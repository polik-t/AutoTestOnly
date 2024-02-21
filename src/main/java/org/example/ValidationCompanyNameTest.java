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

public class ValidationCompanyNameTest {
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

        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Компания']")));
        companyField.sendKeys("");
        companyField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Компания'][error='Обязательное поле']")));
        Assert.assertEquals("Обязательное поле", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWhitespace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Компания']")));
        companyField.sendKeys(" ");
        companyField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Компания'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormLongName() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Компания']")));
        companyField.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fermentum turpis vel mi vulputate.");
        companyField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Компания'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingErrorName() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement companyField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Компания']")));
        companyField.sendKeys("!@#$%^&*(&^%$#@#$%^&*&^%$#%^");
        companyField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Компания'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }
}
