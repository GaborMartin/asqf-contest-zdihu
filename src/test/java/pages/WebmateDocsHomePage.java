package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class WebmateDocsHomePage {

    public static final int TIME_OUT_IN_SECONDS = 30;
    public static final String URL = "https://docs.webmate.io/home/";
    public static final String EXPECTED_TITLE = "Everything you need to know about webmate :: Docs";

    WebDriver driver;

    public WebmateDocsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,TIME_OUT_IN_SECONDS), this);

    }


    public String  getTitle() {
        return driver.getTitle();
    }
}
