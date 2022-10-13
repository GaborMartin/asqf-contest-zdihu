package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestRunPage extends CommonPage {

    public static final String BASE_URL = "https://demo.webmate.io/#/projects/a7918535-d828-42e4-b2fc-327ff6f82eb6/testlab/testruns/";
    public static final String EXPECTED_TITLE = "webmate by Testfabrik";

    private static final By TESTRUN_NAME_SPAN = By.xpath("//*[@id='projects-id-testlab-testruns-id']/div[2]/projects/div/div[2]/div/wm-project-testlab/wm-project-testruns/div/div[1]/span");

    public TestRunPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIME_OUT_IN_SECONDS), this);
    }

    public static TestRunPage navigateTo(WebDriver driver, String runId) {
        driver.get(BASE_URL + runId);

        return new TestRunPage(driver);
    }

    public String getTestrunName() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TESTRUN_NAME_SPAN));
        return driver.findElement(TESTRUN_NAME_SPAN).getText();
    }

}
