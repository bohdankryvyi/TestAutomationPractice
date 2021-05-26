package pages;

import driver.DriverSingleton;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import util.EventHandler;
import util.WaiterWrapperClass;

public class CartPage extends BaseAbstractPage {
    private static final String CART_URL = "http://automationpractice.com/index.php?controller=order";
    private Logger log = LogManager.getRootLogger();
    /**
     * constructor for  fields initialization
     */
    public CartPage() {
        super(DriverSingleton.getDriver());
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"summary_products_quantity\"]")
    private WebElement productCount;

    @FindBy(xpath = "//*[@id=\"product_1_1_0_0\"]/td[2]/p/a")
    private WebElement itemBasketName;

    @FindBy(xpath = "//*[@id=\"cart_quantity_down_1_1_0_0\"]/span")
    private WebElement deleteFirstItem;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    private WebElement messageAfterDeletion;

    @Step("Open cart; Then observe added product and count of all added products")
    public CartPage openCart() {
        driver.get(CART_URL);
        WaiterWrapperClass.waitForElement(driver, productCount);
        WaiterWrapperClass.waitForElement(driver, itemBasketName);
        log.info("Cart is opened");
        return this;
    }

    @Step("Get the count of added products")
    public String returnCount() {
        WaiterWrapperClass.waitForElement(driver, productCount);
        log.info("Returned count of product:" + productCount.getText());
        return productCount.getText();
    }

    @Step("Get the product name of the product that was added to the cart")
    public String returnBasketName() {
        WaiterWrapperClass.waitForElement(driver, itemBasketName);
        log.info("Returned count of product:" + itemBasketName.getText());
        return itemBasketName.getText();
    }

    @Step("Delete added product from the cart and check message after deletion")
    public String deleteItem() {
        WaiterWrapperClass.waitForElement(driver, deleteFirstItem);
        deleteFirstItem.click();
        WaiterWrapperClass.waitForElement(driver, messageAfterDeletion);
        log.info("Item is deleted");
        return messageAfterDeletion.getText();
    }
}
