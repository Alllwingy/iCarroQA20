package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigurationProperties;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    static EventFiringWebDriver driver;
    HelperUser userHelper;
    HelperCar carHelper;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;

    public ApplicationManager() {

        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init() {

        if (browser.equals(BrowserType.CHROME)) {

            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("CHROME driver");

        } else if (browser.equals(BrowserType.FIREFOX)) {

            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("FIREFOX driver");
        }

        driver.navigate().to(ConfigurationProperties.getProperty("url"));
        logger.info("open: " + ConfigurationProperties.getProperty("url") + " Start testing: " + LocalDateTime.now());

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.register(new WDListener());

        userHelper = new HelperUser(driver);
        carHelper = new HelperCar(driver);
        logger.info("navigated to the url: https://ilcarro.web.app/search");
    }

    public void navigateToMainPage() {
        driver.navigate().to("https://ilcarro.web.app/search");
    }

    public HelperUser getUserHelper() {

        return userHelper;
    }

    public HelperCar getCarHelper() {

        return carHelper;
    }

    public EventFiringWebDriver getDriver() {

        if (driver == null)
            init();

        return driver;
    }

    public void tearDown() {
        driver.quit();
    }

    public boolean isPageUrlHome() {

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl + "----------------- url");
        return currentUrl.equals(ConfigurationProperties.getProperty("url"));
    }
}
