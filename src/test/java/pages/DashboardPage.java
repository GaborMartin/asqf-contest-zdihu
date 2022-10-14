package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends CommonPage {

    @FindBy(xpath = "//a[@data-testing-id='project_dasboard-testlab-link']")
    private WebElement icon;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public TestlabPage navigateToTestLabPage() {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(icon))
                .click();

        return new TestlabPage(driver);
    }
}
