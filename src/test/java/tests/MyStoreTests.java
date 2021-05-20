package tests;

import model.User;
import model.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SignInPage;
import pages.StorePage;
import util.DataProviderClass;

public class MyStoreTests extends BaseAbstractTest {
    //public static WebDriver driver;
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
        Assert.assertEquals("My Store", driver.getTitle());
    }

    @Test(testName = "", description = "Test to check whether item from main page can be opened")
    public void productIsOpened() {
        //check whether element can be opened via checking "Add to cart" button in the opened window
        productPage = storePage.openPage().openFirstItem();
        String buttonName = productPage.getButtonName();
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

        //check message after deleting all
        Assert.assertEquals(emptyFolder, "Your shopping cart is empty.");
    }

    /**
     * Test for checking searching
     */
    @Test(dataProvider = "search data", dataProviderClass = DataProviderClass.class)
    public void searchItem(String data) {
        //check whether all founded elements titles contains the word that we are searched by
        Assert.assertEquals(storePage.openPage().searchItem(data), true);
    }

    @Test (priority = 1)
    public void subscribeOnNewsletters() {
        storePage.openPage();
        String successfullMessage = storePage.subscribeSucessfully();

        Assert.assertEquals(successfullMessage, "Newsletter : You have successfully subscribed to this newsletter.");
    }

    @Test (priority = 1)
    public void invalidSubscribeOnNewsletters() {
        storePage.openPage();
        String errorMessage = storePage.subscribeWithInvalidData();
        Assert.assertEquals(errorMessage, "Newsletter : Invalid email address.");
    }

    @Test
    public void checkSignInForm() {
        String elementsExisted = storePage.openPage().openSignInPage().allElementsAreAvailable();

        Assert.assertEquals(elementsExisted, "true");
    }

    @Test(dataProvider = "valid login test data", dataProviderClass = DataProviderClass.class)
    public void successfulLogin(String login, String password, String username){
        User testUser = new UserBuilder()
                .withLogin(login)
                .withPassword(password)
                .build();
        storePage.openPage().openSignInPage().login(testUser);
        String loggedInUserName= storePage.openPage().getAccountName();
        Assert.assertEquals(loggedInUserName, username);
    }

    @Test(dataProvider = "invalid login test data", dataProviderClass = DataProviderClass.class)
    public void unsuccessfulLogin(String login, String password){
        User testUser = new UserBuilder()
                .withLogin(login)
                .withPassword(password)
                .build();

        String message = storePage.openPage().openSignInPage().invalidLogin(testUser);
        Assert.assertEquals(message, "There is 1 error");
    }
}