package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WaiterWrapperClass provides a functionality to wait any element on the web page
 * Use it instead of Thread.sleep
 */
public class WaiterWrapperClass {
    /**
     * Method that waits for an element founded by locator till it will be available
     * @param driver
     * @param by (locator)
     * @return new element till it will be available
     */
    public static WebElement waitForElementLocatedBy (WebDriver driver, By by){
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Method that waits for an web element till it will be available
     * @param driver -
     * @param webElement -
     * @return new web element till it will be available
     */
    public static WebElement waitForElement (WebDriver driver, WebElement webElement){
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
