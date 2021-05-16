package tests;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners({TestListener.class})
public class BaseAbstractTest {
    public WebDriver driver;

    @BeforeClass
    public void generalSetUp(){

    }

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        // can be any essence that needed for different tests or any common action that needed for all tests

    }

    @AfterMethod(alwaysRun = true)
    public void clearCacheCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void stopBrowser(){
        DriverSingleton.closeDriver();
    }
}
