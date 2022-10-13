package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestlabPage extends CommonPage {

    private static final By PROJECT_ROW = By.xpath("//td[contains(@class, 'report-table-name') and text()='having fun2']");

    public TestlabPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    public static TestRunPage navigateToTestRunPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECT_ROW));
        driver.findElement(PROJECT_ROW).click();

        return new TestRunPage(driver);
    }
}
