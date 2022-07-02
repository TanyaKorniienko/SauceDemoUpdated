package org.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//form//input[@id='user-name']")
    private static WebElement nameField;

    @FindBy(xpath = "//form//input[@id='password']")
    private static WebElement passwordField;

    @FindBy(xpath = "//form//input[@id='login-button']")
    private WebElement loginButton;

    static class User {
        private String user_name;
        private String user_password;

        public User(String user_name, String user_password) {
            this.user_name = user_name;
            this.user_password = user_password;
        }

        public String getPassword() {
            return user_password;
        }

        public String getName() {
            return user_name;
        }

        public void inputLoginInfo() {
            nameField.sendKeys(user_name);
            passwordField.sendKeys(user_password);
        }
    }

    public CatalogPage clickLoginButton() {
        loginButton.click();
        return new CatalogPage((ChromeDriver) driver);
    }

}
