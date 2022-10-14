package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import entities.WebUser;

public class LoginPage extends CommonPage {

    public static final String URL = "https://demo.webmate.io/#/login?returnUrl=L3Byb2plY3RzL2E3OTE4NTM1LWQ4MjgtNDJlNC1iMmZjLTMyN2ZmNmY4MmViNi9kYXNoYm9hcmQ%3D";

    @FindBy(xpath = "//input[@data-testing-id='login-userfield']") private WebElement userNameInput;
    @FindBy(xpath = "//input[@data-testing-id='login-passwordfield']") private WebElement userPasswordInput;
    @FindBy(xpath = "//button[@data-testing-id='login-submit-button']") private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage navigateTo(WebDriver driver) {
        driver.get(URL);

        return new LoginPage(driver);
    }

    public DashboardPage login(WebUser webUser) {
        userNameInput.sendKeys(webUser.getEmail());
        userPasswordInput.sendKeys(webUser.getPassword());
        loginButton.click();

        return new DashboardPage(driver);
    }
}
