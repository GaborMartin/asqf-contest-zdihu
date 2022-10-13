import com.testfabrik.webmate.javasdk.WebmateAPISession;
import com.testfabrik.webmate.javasdk.selenium.WebmateSeleniumSession;
import com.testfabrik.webmate.javasdk.testmgmt.TestRunEvaluationStatus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import pages.WebmateDocsHomePage;

public class BaseTest {

    RemoteWebDriver driver;
    WebmateAPISession webmateAPISession;
    WebmateSeleniumSession webmateSeleniumSession;

//    @BeforeAll
//    static void setupAll() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    void setup(){

        DesiredCapabilities caps = Utility.configCapabilities();
        driver = Utility.setupDriver(caps);

        webmateAPISession = Utility.setWebmateSession(driver);
        webmateSeleniumSession = Utility.addSeleniumSession(driver);

        driver.manage().window().maximize();
        driver.get(WebmateDocsHomePage.URL);
    }

    @AfterEach
    void teardown() {
        //driver.quit();
    }

    @Test
    void validateOpeningPageTitle() {
        try {
            WebmateDocsHomePage homePage = new WebmateDocsHomePage(driver);
            Assertions.assertEquals(homePage.getTitle(), WebmateDocsHomePage.EXPECTED_TITLE);
            webmateSeleniumSession.finishTestRun(TestRunEvaluationStatus.PASSED, "TestRun completed successfully");
        }
        catch(Throwable e) {
            webmateSeleniumSession.finishTestRun(TestRunEvaluationStatus.FAILED, "TestRun has failed");
            e.printStackTrace();
        }
    }

//    @Test
//    void validateOpeningPageLogo() {
//        Assertions.assertEquals(driver.findElement(By.xpath("//div[@class='logo']//a[@title='ZEISS International']")).isDisplayed(), true);
//    }

}
