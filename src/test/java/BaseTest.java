import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ZeissHomePage;

public class BaseTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zeiss.com/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void validateOpeningPageTitle() {
        Assertions.assertEquals(driver.getTitle(), "ZEISS Group");
    }

    @Test
    void validateOpeningPageLogo() {
        Assertions.assertEquals(driver.findElement(By.xpath("//div[@class='logo']//a[@title='ZEISS International']")).isDisplayed(), true);
    }

    @Test
    void PageObjectTest() {
        ZeissHomePage zeissHomePage = new ZeissHomePage(driver);
        Assertions.assertTrue(zeissHomePage.DataPrivacySettingsIsDisplayed());
    }

}
