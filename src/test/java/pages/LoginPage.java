package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends CommonPage {

    public static final String URL = "https://demo.webmate.io/#/login?returnUrl=L3Byb2plY3RzL2E3OTE4NTM1LWQ4MjgtNDJlNC1iMmZjLTMyN2ZmNmY4MmViNi9kYXNoYm9hcmQ%3D";

    private static final By USERNAME_FIELD = By.xpath("//input[@data-testing-id='login-userfield']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@data-testing-id='login-passwordfield']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@data-testing-id='login-submit-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    public static LoginPage navigateTo(WebDriver driver) {
        driver.get(URL);

        return new LoginPage(driver);
    }

    public DashboardPage login(String userName, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(userName);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();

        return new DashboardPage(driver);
    }
}
