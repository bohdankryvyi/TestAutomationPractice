package pages;

import driver.DriverSingleton;
import io.qameta.allure.Step;
import model.User;
import model.UserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BaseAbstractPage{
    private  static final String SIGNIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private Logger log = LogManager.getRootLogger();
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

    @Step("Check that all elements are present (mail field, " +
            "create an account button, email address field, password field, Sign In button)")
    public String allElementsAreAvailable() {
        if (emailField.isDisplayed() && createButton.isDisplayed() && registeredEmailField.isDisplayed() && passwordField.isDisplayed()) {
            signInButton.isDisplayed();
        }
        String elementsExisted = "true";

        log.info("All elements are existed on the Sing in page");
        return elementsExisted;
    }
    @Step("Login with valid credentials")
    public void login(User user){
        registeredEmailField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        signInButton.click();
        log.info("User is logged in with valid credentials");
    }

    @Step(" Try to login with empty or invalid credentials ")
    public String invalidLogin(User user){
        registeredEmailField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        signInButton.click();
        log.info("User is not logged in with invalid credentials");
        return alertAboutInvalidLogin.getText();

    }

}
