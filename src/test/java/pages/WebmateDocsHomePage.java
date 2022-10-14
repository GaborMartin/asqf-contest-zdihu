package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebmateDocsHomePage extends  CommonPage {

    public static final int TIME_OUT_IN_SECONDS = 30;
    public static final String URL = "https://docs.webmate.io/home/";
    public static final String EXPECTED_TITLE = "Everything you need to know about webmate :: Docs";

    @FindBy(xpath = "//h1") private WebElement h1;

    public WebmateDocsHomePage(WebDriver driver) {
        super(driver);
    }


    public String  getTitle() {
       new WebDriverWait(driver, TIME_OUT_IN_SECONDS)
               .until(ExpectedConditions.visibilityOf(h1))
               .getText();
       return driver.getTitle();
    }
}
