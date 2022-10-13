package selenium;

import com.testfabrik.webmate.javasdk.testmgmt.TestRunEvaluationStatus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TestRunPage;
import pages.TestlabPage;
import pages.WebmateDocsHomePage;

public class WebMateTest {

    WebDriver driver;

    @BeforeEach
    void setup(){
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
        TestRunPage testRunPage = testlabPage.navigateToTestRunPage(driver);
        Assertions.assertEquals(testRunPage.getTitle(), TestRunPage.EXPECTED_TITLE);

        System.out.println(testRunPage.getTestrunName());
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
