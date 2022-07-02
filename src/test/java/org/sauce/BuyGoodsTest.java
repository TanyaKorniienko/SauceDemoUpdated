package org.sauce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BuyGoodsTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private LoginPage.User user;
    private CatalogPage catalogPage;
    private YourCartPage cart;
    private CheckOutInfo checkoutInfo;
    private ThankYouPage thankYouPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage((ChromeDriver) driver);
        user = new LoginPage.User("standard_user", "secret_sauce");
        catalogPage = new CatalogPage((ChromeDriver) driver);
        cart = new YourCartPage((ChromeDriver) driver);
        checkoutInfo = new CheckOutInfo((ChromeDriver) driver);
        thankYouPage = new ThankYouPage((ChromeDriver) driver);
    }

    @Test
    public void buyGoods() {
        user.inputLoginInfo();
        loginPage.clickLoginButton();
        catalogPage.addItemToCart();
        catalogPage.openCart();
        cart.submitOrder();
        checkoutInfo.input_UserInfo("Elena", "Rob", "32444");
        checkoutInfo.finishOrder();
        thankYouPage.returnToCatalogPage();

        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://www.saucedemo.com/inventory.html");
        assertThat(user.getName()).isEqualTo("standard_user").hasSize(13);
        assertThat(user.getPassword()).isEqualTo("secret_sauce").hasSize(12);
    }

    @After
    public void close() {
        driver.close();
    }
}
