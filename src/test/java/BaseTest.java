import com.testfabrik.webmate.javasdk.WebmateAPISession;
import com.testfabrik.webmate.javasdk.selenium.WebmateSeleniumSession;
import com.testfabrik.webmate.javasdk.testmgmt.TestRunEvaluationStatus;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.WebmateDocsHomePage;

public class BaseTest {

    RemoteWebDriver webMateDriver;
    WebmateAPISession webmateAPISession;
    WebmateSeleniumSession webmateSeleniumSession;
    boolean continueExecution = false;


    @BeforeEach
    void setup(){
        DesiredCapabilities caps = Utility.configCapabilities();
        webMateDriver = Utility.setupDriver(caps);

        webmateAPISession = Utility.setWebmateSession(webMateDriver);
        webmateSeleniumSession = Utility.addSeleniumSession(webMateDriver);

        webMateDriver.manage().window().maximize();
        webMateDriver.get(WebmateDocsHomePage.URL);

        try {
            WebmateDocsHomePage homePage = new WebmateDocsHomePage(webMateDriver);
            Assertions.assertEquals(homePage.getTitle(), WebmateDocsHomePage.EXPECTED_TITLE);
            webmateSeleniumSession.finishTestRun(TestRunEvaluationStatus.PASSED, "TestRun completed successfully");
            continueExecution = true;
        }
        catch(Throwable e) {
            webmateSeleniumSession.finishTestRun(TestRunEvaluationStatus.FAILED, "TestRun has failed");
            e.printStackTrace();
        }
    }

    @AfterEach
    void teardown() {
        webMateDriver.quit();
    }


//    @Test
//    void validateOpeningPageLogo() {
//        Assertions.assertEquals(driver.findElement(By.xpath("//div[@class='logo']//a[@title='ZEISS International']")).isDisplayed(), true);
//    }

}
