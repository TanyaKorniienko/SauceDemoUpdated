package org.sauce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutInfo {
    WebDriver driver;
    private String firstName;
    private String lastName;
    private String zipCode;

    public CheckOutInfo(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//form//div[@class='checkout_info']//input[@id='first-name']")
    WebElement firstNameField;

    @FindBy(xpath = "//form//div[@class='checkout_info']//input[@id='last-name']")
    WebElement lastNameField;

    @FindBy(xpath = "//form//div[@class='checkout_info']//input[@id='postal-code']")
    WebElement zipCodeField;

    @FindBy(xpath = "//div[@class='checkout_buttons']/input[@id='continue']")
    WebElement continueButton;

    @FindBy(xpath = "//div[@class='cart_footer']//button[@id='finish']")
    WebElement finishButton;

    public void input_UserInfo(String firstName, String lastName, String  zipCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
        continueButton.click();
    }
    public ThankYouPage finishOrder() {
        finishButton.click();
        return new ThankYouPage((ChromeDriver) driver);
    }
}
