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

import static org.junit.Assert.assertNotNull;

public class FieldsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void testExistenceOfRequiredFields() {
        // Открытие веб-страницы с формой анкеты
        driver.get("https://only.digital/projects#brief");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        // Поиск всех полей
        WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Имя*']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='E-mail*']"));
        WebElement phoneField = driver.findElement(By.xpath("//input[@placeholder='Телефон']"));
        WebElement companyField = driver.findElement(By.xpath("//input[@placeholder='Компания']"));
        WebElement projectField = driver.findElement(By.xpath("//textarea[@placeholder='Расскажите о вашем проекте']"));

        // Проверка, что все поля существуют на странице
        assertNotNull("Поле 'Имя' существует", nameField);
        assertNotNull("Поле 'Почта' существует", emailField);
        assertNotNull("Поле 'Телефон' существует", phoneField);
        assertNotNull("Поле 'Компания' существует", companyField);
        assertNotNull("Поле 'Расскажите о вашем проекте' существует", projectField);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}