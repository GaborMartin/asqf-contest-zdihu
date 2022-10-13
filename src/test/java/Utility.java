import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Timestamp;
import java.util.Properties;
import java.util.UUID;

import com.testfabrik.webmate.javasdk.ProjectId;
import com.testfabrik.webmate.javasdk.WebmateAPISession;
import com.testfabrik.webmate.javasdk.WebmateAuthInfo;
import com.testfabrik.webmate.javasdk.WebmateCapabilityType;
import com.testfabrik.webmate.javasdk.WebmateEnvironment;
import com.testfabrik.webmate.javasdk.selenium.WebmateSeleniumSession;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Utility {

    private static WebmateAPISession webmateSession;
    private static Properties webmateProps;
    private static Properties driverProps;
    private static long timestamp = System.currentTimeMillis();

    public static Properties readProps(String propsPath) {
        Properties prop = new Properties();

        try (InputStream input = Utility.class.getClassLoader().getResourceAsStream(propsPath)) {

            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static DesiredCapabilities configCapabilities() {
        webmateProps = Utility.readProps("webmate.properties");
        driverProps = Utility.readProps("driver.properties");

        String username = webmateProps.getProperty("user.name");
        String apiKey = webmateProps.getProperty("api.key");
        String project = webmateProps.getProperty("project");
        String doScreenshot = webmateProps.getProperty("automation.screenshots");
        String name = webmateProps.getProperty("name");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(WebmateCapabilityType.API_KEY, apiKey);
        caps.setCapability(WebmateCapabilityType.USERNAME, username);
        caps.setCapability(WebmateCapabilityType.PROJECT, project);
        caps.setCapability(WebmateCapabilityType.AUTOMATION_SCREENSHOTS, doScreenshot);
        caps.setCapability(WebmateCapabilityType.NAME, name+timestamp);
        System.setProperty("testRunName", name+timestamp );

        caps.setCapability("browserName", driverProps.getProperty("browser.name"));
        caps.setCapability("version", driverProps.getProperty("browser.version"));
        caps.setCapability("platform", driverProps.getProperty("platform"));

        return caps;
    }

    public static RemoteWebDriver setupDriver(DesiredCapabilities caps) {
        try {
            return new RemoteWebDriver(new URL(driverProps.getProperty("remote.url")), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WebmateAPISession setWebmateSession(RemoteWebDriver driver) {
        String apiKey = webmateProps.getProperty("api.key");

        WebmateAuthInfo authInfo = new WebmateAuthInfo(webmateProps.getProperty("user.name"), apiKey);
        try {
            webmateSession = new WebmateAPISession(
                    authInfo,
                    WebmateEnvironment.create(new URI(webmateProps.getProperty("webmate.api.url"))),
                    new ProjectId(UUID.fromString(apiKey)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

       // webmateSession.browserSession.getBrowserSessionForSeleniumSession(driver.getSessionId().toString());

        return webmateSession;
    }

    public static WebmateSeleniumSession addSeleniumSession(RemoteWebDriver driver) {
        return webmateSession.addSeleniumSession(driver.getSessionId().toString());
    }
}
