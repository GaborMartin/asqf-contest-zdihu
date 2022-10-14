package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestlabPage extends CommonPage {
    public TestlabPage(WebDriver driver) {
        super(driver);
    }

    public TestRunPage selectTestRun(String testRunName) {

        WebDriverWait wait = new WebDriverWait(this.driver, 30);
        wait.until(ExpectedConditions.
          visibilityOfElementLocated(By.xpath("//td[contains(@class, 'report-table-name') and text()='" + testRunName + "']" )))
          .click();

        return new TestRunPage(driver);
    }
}
