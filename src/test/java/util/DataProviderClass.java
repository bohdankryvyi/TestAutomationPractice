package util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The DataProvider class provides a functionality to set different data in the tests
 */
public class DataProviderClass {
    /**
     * DataProvider method for providing data for Search test
     * Works with .csv file
     * @return keywords which are used for products searching
     */
    @DataProvider(name = "searching")
    public static Object[][] readCsvSearching() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "/build/resources/test/searchData.csv"));
        List<String[]> csvData = csvReader.readAll();
        Object[][] csvDataObject = new Object[csvData.size()][2];
        for (int i = 0; i < csvData.size(); i++) {
            csvDataObject[i] = csvData.get(i);
        }
        return csvDataObject;
    }

    /**
     * DataProvider method for providing data for the test about login with valid credentials
     * Works with Excel file
     * @return valid login, password, username
     */
    @DataProvider(name = "valid login test data")
    public static Object[][] validLoginMethod() throws Exception {
        return GetExcelData.getTableArray(
                "D:\\Java_projects\\TestAutomationPractice\\build\\resources\\test\\testData.xlsx", "Users");
    }

    /**
     * DataProvider method for providing data for the test about login with invalid credentials
     * @return invalid (or empty) login and password
     */
    @DataProvider(name = "invalid login test data")
    public static Object[][] invalidLoginMethod() {
        return new Object[][]{{"", ""}, {"bohdankryvyi@gmail.com", "test"}, {"test", "Q!42DFz2hB@KCz@"}};
    }
}

