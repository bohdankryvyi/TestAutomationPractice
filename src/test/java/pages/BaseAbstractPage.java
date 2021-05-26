package pages;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.EventHandler;

public abstract class BaseAbstractPage {
    protected WebDriver driver;
    EventFiringWebDriver eventDriver = new EventFiringWebDriver(DriverSingleton.getDriver());
    EventHandler handler = new EventHandler();

    public BaseAbstractPage(WebDriver driver)
    {
        this.driver = driver;
        eventDriver.register(handler);
    }

}
