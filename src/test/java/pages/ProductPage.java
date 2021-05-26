package pages;

import driver.DriverSingleton;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaiterWrapperClass;

public class ProductPage extends BaseAbstractPage{
    private Logger log = LogManager.getRootLogger();
    /**
     * constructor for  fields initialization
     */
    public ProductPage(){
        super(DriverSingleton.getDriver());
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    private WebElement addToCardButton;

    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement productName;

    @FindBy(xpath = "//span[@title='Continue shopping']//span[1]")
    private WebElement bannerButtonAfterAddingToCart;

    @Step("Get add to card button name ")
    public String getButtonName(){
        WaiterWrapperClass.waitForElement(driver, addToCardButton);
        log.info("Added to card  button name:" + addToCardButton.getText());
        return addToCardButton.getText();
    }

    @Step("Click add to card button ")
    public void clickAddToCartButton(){
        addToCardButton.click();
        log.info("Add to card button is clicked");
    }

    @Step("Click the button on the opened banner after adding product to the cart")
    public void clickBannerAfterAddingToCart(){
        WaiterWrapperClass.waitForElement(driver, bannerButtonAfterAddingToCart);
        bannerButtonAfterAddingToCart.click();
        log.info("Button on the opened banner is clicked after adding product to the cart");
    }
    @Step("Get the product name that was opened")
    public String getProductName(){
        WaiterWrapperClass.waitForElement(driver, productName);
        log.info("Opened product name is:" + productName.getText());
        return productName.getText();
    }
}
