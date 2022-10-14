package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class CommonPage {

    WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    public static final int TIME_OUT_IN_SECONDS = 30;

    public String getTitle() {
        return driver.getTitle();
    }

}
