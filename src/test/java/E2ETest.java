import com.testfabrik.webmate.javasdk.WebmateCapabilityType;
import com.testfabrik.webmate.javasdk.browsersession.BrowserSessionId;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TestRunPage;
import pages.TestlabPage;

import java.util.List;

public class E2ETest extends BaseTest{

    WebDriver driver;

    @BeforeEach
    void setupLocalDriver(){
        if (!continueExecution) throw new RuntimeException("Something went wrong");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void validateTestRunSteps() {
        // Login to the system
        LoginPage loginPage = LoginPage.navigateTo(driver);
        DashboardPage dashboardPage = loginPage.login("adam.andrasko@zeiss.com", "ZdiHungaryTeam");
        Assertions.assertEquals(dashboardPage.getTitle(), TestRunPage.EXPECTED_TITLE);

        // Navigate to the Testlab page via menu link
        TestlabPage testlabPage = dashboardPage.navigateToTestLabPage(driver);

        // Navigate to the run result page
        String testRunName = System.getProperty("testRunName");
        TestRunPage testRunPage = testlabPage.selectTestRun(testRunName);
        testRunPage.checkActions(webmateSeleniumSession, webmateAPISession);
    }

    @AfterEach
    void teardownLocalDriver() {
        driver.quit();
    }
}
