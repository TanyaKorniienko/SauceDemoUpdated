package org.sauce;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class SignInUserTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LoginPage.User user;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage((ChromeDriver) driver);
        user = new LoginPage.User("standard_user", "secret_sauce");
    }

    @Test
    public void signInUser() {
        user.inputLoginInfo();
        loginPage.clickLoginButton();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.saucedemo.com/inventory.html");
        assertThat(user.getName()).startsWith("stan").endsWith("r").isEqualTo("standard_user");
        assertThat(user.getPassword()).isEqualTo("secret_sauce").hasSize(12);
    }

    @After
    public void close() {
        driver.close();
    }
}
