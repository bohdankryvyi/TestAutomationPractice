package pages;

import org.openqa.selenium.WebDriver;

public abstract class BaseAbstractPage {
    protected WebDriver driver;
    public BaseAbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
