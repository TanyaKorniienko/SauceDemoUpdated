package org.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage {
    WebDriver driver;

    public CatalogPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//div[@class= 'inventory_list']/div[2]//div[2]/button")
    WebElement addToCartButton;
    @FindBy(xpath = "//div//a[@class='shopping_cart_link']")
    WebElement goodsCart;


    public void addItemToCart() {
        addToCartButton.click();
    }

    public YourCartPage openCart() {
        goodsCart.click();
        return new YourCartPage((ChromeDriver) driver);
    }
}
