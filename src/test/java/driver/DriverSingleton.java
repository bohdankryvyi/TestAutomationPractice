package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.ConfProperties;
import util.EventHandler;

public class DriverSingleton {
    public static WebDriver driver;


    private DriverSingleton() {
    }
    public static WebDriver getDriver() {
        if (null == driver) {
            // user ConfProperties util class to read browser property
            switch (ConfProperties.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        EventHandler handler = new EventHandler();
        return  eventDriver.register(handler);
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
