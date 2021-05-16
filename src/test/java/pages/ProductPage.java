package pages;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.WaiterWrapperClass;

public class ProductPage extends BaseAbstractPage{
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


    public String getButtonName(){
        WaiterWrapperClass.waitForElement(driver, addToCardButton);
        return addToCardButton.getText();
    }

    public void clickAddToCartButton(){
        addToCardButton.click();
    }

    public void clickBannerAfterAddingToCart(){
        WaiterWrapperClass.waitForElement(driver, bannerButtonAfterAddingToCart);
        bannerButtonAfterAddingToCart.click();
    }

    public String getProductName(){
        WaiterWrapperClass.waitForElement(driver, productName);
        return productName.getText();
    }
}
