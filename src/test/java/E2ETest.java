import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TestRunPage;
import pages.TestlabPage;


public class E2ETest extends BaseTest{

    WebDriver driver;

    @BeforeEach
    void setupLocalDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void validateTestRunSteps() {
        // Login to the system
        LoginPage loginPage = LoginPage.navigateTo(driver);
        DashboardPage dashboardPage = loginPage.login(Utility.getConfiguredWebUser());
        Assertions.assertEquals(dashboardPage.getTitle(), TestRunPage.EXPECTED_TITLE);

        // Navigate to the Testlab page via menu link
        TestlabPage testlabPage = dashboardPage.navigateToTestLabPage();

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
