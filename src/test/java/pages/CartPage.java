package pages;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaiterWrapperClass;

public class CartPage extends BaseAbstractPage {
    private static final String CART_URL = "http://automationpractice.com/index.php?controller=order";

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

    public CartPage openCart() {
        driver.get(CART_URL);
        WaiterWrapperClass.waitForElement(driver, productCount);
        WaiterWrapperClass.waitForElement(driver, itemBasketName);
        return this;
    }

    public String returnCount() {
        WaiterWrapperClass.waitForElement(driver, productCount);
        return productCount.getText();
    }

    public String returnBasketName() {
        WaiterWrapperClass.waitForElement(driver, itemBasketName);
        return itemBasketName.getText();
    }

    public String deleteItem() {
        WaiterWrapperClass.waitForElement(driver, deleteFirstItem);
        deleteFirstItem.click();
        WaiterWrapperClass.waitForElement(driver, messageAfterDeletion);
        return messageAfterDeletion.getText();
    }
}
