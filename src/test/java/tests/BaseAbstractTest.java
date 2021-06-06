package tests;

import com.google.common.collect.ImmutableMap;
import driver.DriverSingleton;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import util.EventHandler;
import util.RetryAnnotationTransformer;
import util.TestListener;

// to connect listeners via base test class:
@Listeners({TestListener.class, RetryAnnotationTransformer.class})
@Epic("Test Automation Practice Epic")
public class BaseAbstractTest {
    public WebDriver driver;

/* it doesnt work for me. Should be a method to write environment properties in Allure report.
Appropriate environment.properties file was added manually to the Allure test results folder
    @BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", "Chrome")
                        .put("URL", "http://automationpractice.com/index.php")
                        .build());
    }*/

    @BeforeClass
    public void generalSetUp() {
        driver = DriverSingleton.getDriver();
    }

    @BeforeMethod()
    public void setUp() {
        // can be any essence that needed for different tests or any common action that needed for all tests
    }

    @AfterMethod(alwaysRun = true)
    public void clearCacheCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
