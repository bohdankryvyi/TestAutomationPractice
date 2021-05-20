package pages;

import driver.DriverSingleton;
import model.User;
import model.UserBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BaseAbstractPage{
    private  static final String SIGNIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    /**
     * constructor for  fields initialization
     */
    public SignInPage() {
        super(DriverSingleton.getDriver());
        PageFactory.initElements(driver, this);
    }

    public SignInPage openSignIn(){
        driver.get(SIGNIN_URL);
        return this;
    }

    @FindBy(xpath = "//*[@id=\"email_create\"]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"SubmitCreate\"]/span")
    private WebElement createButton;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement registeredEmailField;

    @FindBy(xpath = "//*[@id=\"passwd\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"SubmitLogin\"]/span")
    private WebElement signInButton;

    @FindBy(css = "div[class='alert alert-danger'] p")
    private WebElement alertAboutInvalidLogin;

    public String allElementsAreAvailable() {
        if (emailField.isDisplayed() && createButton.isDisplayed() && registeredEmailField.isDisplayed() && passwordField.isDisplayed()) {
            signInButton.isDisplayed();
        }
        String elementsExisted = "true";

        return elementsExisted;
    }

    public void login(User user){
        registeredEmailField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        signInButton.click();

    }

    public String invalidLogin(User user){
        registeredEmailField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        signInButton.click();
        return alertAboutInvalidLogin.getText();

    }

}
