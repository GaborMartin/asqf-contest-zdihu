import com.testfabrik.webmate.javasdk.*;
import com.testfabrik.webmate.javasdk.browsersession.BrowserSessionId;
import com.testfabrik.webmate.javasdk.browsersession.BrowserSessionRef;
import com.testfabrik.webmate.javasdk.selenium.WebmateSeleniumSession;
import com.testfabrik.webmate.javasdk.testmgmt.TestRunEvaluationStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

public class BaseTest {

    RemoteWebDriver driver;
    private WebmateAPISession webmateSession;
    private WebmateSeleniumSession seleniumSession;
    private BrowserSessionRef browserSession;

//    @BeforeAll
//    static void setupAll() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeEach
    void setup() throws MalformedURLException, URISyntaxException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(WebmateCapabilityType.API_KEY, "49addaed-d902-4dc7-a233-d22d19d2bc6e");
        caps.setCapability(WebmateCapabilityType.USERNAME, "zoltan.jakab@zeiss.com");
        caps.setCapability(WebmateCapabilityType.PROJECT, "a7918535-d828-42e4-b2fc-327ff6f82eb6");
        caps.setCapability(WebmateCapabilityType.AUTOMATION_SCREENSHOTS, true);
        caps.setCapability(WebmateCapabilityType.NAME, "Late Night Session");


        caps.setCapability("browserName", "CHROME");
        caps.setCapability("version", "106");
        caps.setCapability("platform", "WINDOWS_7_64");

        driver = new RemoteWebDriver(new URL("https://selenium-demo.webmate.io/wd/hub"), caps);

        WebmateAuthInfo authInfo = new WebmateAuthInfo("zoltan.jakab@zeiss.com", "49addaed-d902-4dc7-a233-d22d19d2bc6e");
        webmateSession = new WebmateAPISession(
          authInfo,
          WebmateEnvironment.create(new URI("https://demo.webmate.io/api/v1/")),
          new ProjectId(UUID.fromString("a7918535-d828-42e4-b2fc-327ff6f82eb6")));


        seleniumSession = webmateSession.addSeleniumSession(driver.getSessionId().toString());
        browserSession = webmateSession.browserSession
          .getBrowserSessionForSeleniumSession(driver.getSessionId().toString());
//        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zeiss.com/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void validateOpeningPageTitle() {
        try {
            Assertions.assertEquals(driver.getTitle(), "ZEISS Group");
            seleniumSession.finishTestRun(TestRunEvaluationStatus.PASSED, "TestRun completed successfully");
        }
        catch(Throwable e) {
            seleniumSession.finishTestRun(TestRunEvaluationStatus.FAILED, "TestRun has failed");
            e.printStackTrace();
        }
    }

//    @Test
//    void validateOpeningPageLogo() {
//        Assertions.assertEquals(driver.findElement(By.xpath("//div[@class='logo']//a[@title='ZEISS International']")).isDisplayed(), true);
//    }

}
