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

public class ValidationPhoneFieldTest {
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

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон']")));
        nameField.sendKeys("");
        nameField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Обязательное поле']")));
        Assert.assertEquals("Обязательное поле", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWhitespace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys(" ");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormLongPhone() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("8913735683587235341234567654321234567654");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormShortPhone() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("89");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormOnlyChars() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("asadsfdgdfhgsddgdg");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormOnlySymbols() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("!@#$%^&*()");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormMixedFormat() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("ASDFGH!@#$%23456");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormErrorSymbol() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("8961+=1124567");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormErrorFormat() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("(123) 456-7890-123");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormPhoneWhitespace() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("89 612 17 298 20");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormWithoutCode() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("1234567890");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }

    @Test
    public void testSendingFormPhoneWithChar() {
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement phoneCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iti__selected-flag']")));
        phoneCountry.click();
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.iti__country[data-country-code='ru']")));
        country.click();

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Телефон' and @name='phone']")));
        phoneField.sendKeys("8961Hello1247632");
        phoneField.sendKeys(Keys.TAB);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Телефон'][error='Некорректный формат']")));
        Assert.assertEquals("Некорректный формат", errorMessage.getAttribute("error"));
    }
}
