package pages;

import com.testfabrik.webmate.javasdk.WebmateAPISession;
import com.testfabrik.webmate.javasdk.browsersession.BrowserSessionId;
import com.testfabrik.webmate.javasdk.selenium.WebmateSeleniumSession;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ActionData;
import utils.DataProvider;

import java.util.List;

public class TestRunPage extends CommonPage {

    public static final String EXPECTED_TITLE = "webmate by Testfabrik";

    public TestRunPage(WebDriver driver) {
       super(driver);
    }


    public void checkActions(WebmateSeleniumSession webmateSeleniumSession, WebmateAPISession webmateAPISession){
        DataProvider dataProvider = new DataProvider();
        BrowserSessionId browserSessionId = webmateSeleniumSession.getBrowserSessionId();
        List<ActionData> expectedActions = dataProvider.getActionsFromTestRun(browserSessionId, webmateAPISession);

        List<WebElement> actualActions = new WebDriverWait(driver, TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.headsection .name"), expectedActions.size()));

        for (int i = 0; i < actualActions.size(); i++) {
            Assertions.assertEquals(expectedActions.get(i).getName(), actualActions.get(i).getText() );

        }


    }


}
