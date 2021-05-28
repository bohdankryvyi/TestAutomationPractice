package tests;

import driver.DriverSingleton;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import model.User;
import model.UserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SignInPage;
import pages.StorePage;
import util.DataProviderClass;
import util.EventHandler;

public class MyStoreTests extends BaseAbstractTest {
    private static final Logger logger = LogManager.getLogger(MyStoreTests.class);

    public static StorePage storePage = new StorePage();
    public static CartPage cartPage;
    public static SignInPage signInPage;
    public static ProductPage productPage;

    /**
     * test to check whether web page can be opened
     */
    @Test
    public void pageIsOpened() {
        storePage.openPage();
        logger.info("Store page is opened");
        Assert.assertEquals("My Store", driver.getTitle());
    }

    @Test(testName = "", description = "Test to check whether item from main page can be opened")
    public void productIsOpened() {
        //check whether element can be opened via checking "Add to cart" button in the opened window
        productPage = storePage.openPage().openFirstItem();
        String buttonName = productPage.getButtonName();
        logger.info("First element is opened");
        Assert.assertEquals(buttonName, "Add to cart");
    }

    @Test
    public void addAnyItem() {
        cartPage = new CartPage();
        productPage = new ProductPage();
        String itemName = storePage.openPage().openFirstItem().getProductName();
        //add to the basket
        productPage.clickAddToCartButton();
        productPage.clickBannerAfterAddingToCart();

        //open basket and get added item name
        String itemBasketName = cartPage.openCart().returnBasketName();
        //get number of added elements
        String productCount = cartPage.openCart().returnCount();

        logger.info("Item is added to the cart. Itemname is: " + itemBasketName +
                ", product count in the cart is: " + productCount);
        //check products count
        Assert.assertEquals(productCount, "1 Product");
        //check name of added item
        Assert.assertEquals(itemName, itemBasketName);
    }

    @Test
    public void deleteItemFromCart() {
        cartPage = new CartPage();
        productPage = new ProductPage();

        //add to the basket
        storePage.openPage().openFirstItem().clickAddToCartButton();
        //Banner will be opened, we should close it
        productPage.clickBannerAfterAddingToCart();

        String emptyFolder = cartPage.openCart().deleteItem();

        logger.info("Item is deleted from the cart");
        //check message after deleting all
        Assert.assertEquals(emptyFolder, "Your shopping cart is empty.");
    }

    /**
     * Test for checking searching
     */
    @Test(dataProvider = "searching", dataProviderClass = DataProviderClass.class)
    public void searchItem(String data) {
        logger.info("Search is performed via provided data");
        //check whether all founded elements titles contains the word that we are searched by
        Assert.assertEquals(storePage.openPage().searchItem(data), true);
    }

    @Story("Story about subscription")
    @Test(priority = 1)
    public void subscribeOnNewsletters() {
        storePage.openPage();
        String successfullMessage = storePage.subscribeSucessfully();

        logger.info("Subscribe is performed via provided mail");
        Assert.assertEquals(successfullMessage, "Newsletter : You have successfully subscribed to this newsletter.");
    }

    @Story("Story about subscription")
    @Test(priority = 1)
    public void invalidSubscribeOnNewsletters() {
        storePage.openPage();
        String errorMessage = storePage.subscribeWithInvalidData();
        logger.info("Subscribe is not performed because invalid data was provided. Error message is: " + errorMessage);
        Assert.assertEquals(errorMessage, "Newsletter : Invalid email address.");
    }

    @Test
    public void checkSignInForm() {
        String elementsExisted = storePage.openPage().openSignInPage().allElementsAreAvailable();

        logger.info("Sign elements are existed");
        Assert.assertEquals(elementsExisted, "true");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "valid login test data", dataProviderClass = DataProviderClass.class)
    public void successfulLogin(String login, String password, String username) {
        User testUser = new UserBuilder()
                .withLogin(login)
                .withPassword(password)
                .build();
        storePage.openPage().openSignInPage().login(testUser);
        String loggedInUserName = storePage.openPage().getAccountName();
        logger.info("logged in user is:" + storePage.getAccountName());
        Assert.assertEquals(loggedInUserName, username);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(dataProvider = "invalid login test data", dataProviderClass = DataProviderClass.class)
    public void unsuccessfulLogin(String login, String password) {
        User testUser = new UserBuilder()
                .withLogin(login)
                .withPassword(password)
                .build();

        String message = storePage.openPage().openSignInPage().invalidLogin(testUser);
        logger.info("User is not logged with invalid credentials. Error message is " + message);
        Assert.assertEquals(message, "There is 1 error");
    }
}