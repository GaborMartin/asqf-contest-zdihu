package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ZeissHomePage {
    public static final int TIME_OUT_IN_SECONDS = 30;
    WebDriver driver;

    @FindBy(id ="onetrust-banner-sdk")
    private WebElement DataPrivacySettings;
    public ZeissHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,TIME_OUT_IN_SECONDS), this);
    }

    public boolean DataPrivacySettingsIsDisplayed(){
        return DataPrivacySettings.isDisplayed();
    }
}
