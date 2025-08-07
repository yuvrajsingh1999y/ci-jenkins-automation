package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void validLoginTest() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement successMsg = driver.findElement(By.cssSelector(".flash.success"));
        Assert.assertTrue(successMsg.isDisplayed());
    }

    @Test
    public void invalidLoginTest() {
        driver.findElement(By.id("username")).sendKeys("invalid");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector(".flash.error"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @Test
    public void emptyLoginTest() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        WebElement errorMsg = driver.findElement(By.cssSelector(".flash.error"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
