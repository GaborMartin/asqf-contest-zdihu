package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends CommonPage {

    public static final String EXPECTED_TITLE = "webmate by Testfabrik";

    private static final By MENU_TESTLAB_ICON =  By.xpath("//a[@data-testing-id='project_dasboard-testlab-link']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    public static TestlabPage navigateToTestLabPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MENU_TESTLAB_ICON));
        driver.findElement(MENU_TESTLAB_ICON).click();

        return new TestlabPage(driver);
    }
}
