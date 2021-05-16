package util;

import org.testng.annotations.DataProvider;

/**
 * The DataProvider class provides a functionality to set different data in the tests
 */
public class DataProviderClass {
    /**
     * DataProvider method for providing data for Search test
     * @return keywords which are used for products searching
     */
    @DataProvider(name = "search data")
    public static Object[][] dpMethod() {
        return new Object[][]{{"printed"}, {"blouse"}};
    }
    /**
     * DataProvider method for providing data for the test about login with valid credentials
     * @return valid login, password, username
     */
    @DataProvider(name ="valid login test data")
    public static Object[][] validLoginMethod(){
        return new Object[][]{{"Bohdan_Kryvyi@epam.com", "TestTestTest", "Test Testik"}};
    }
    /**
     * DataProvider method for providing data for the test about login with invalid credentials
     * @return invalid (or empty) login and password
     */
    @DataProvider(name ="invalid login test data")
    public static Object[][] invalidLoginMethod(){
        return new Object[][]{{"",""}, {"bohdankryvyi@gmail.com", "test"}, {"test", "Q!42DFz2hB@KCz@"}};
    }
}

