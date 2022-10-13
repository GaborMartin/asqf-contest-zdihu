package pages;

import org.openqa.selenium.WebDriver;

public class CommonPage {

    WebDriver driver;

    public static final int TIME_OUT_IN_SECONDS = 30;

    public String getTitle() {
        return driver.getTitle();
    }

}
