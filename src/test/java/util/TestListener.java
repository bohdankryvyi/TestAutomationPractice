package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TestListener class implements ITestListener and it is important for listening the tests
 * There is possibilities to add different actions on test failure/ test success /test start/etc
 */
public class TestListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    /**
     * Test Failure method with actions
     * Screenshot creation and showing info in the console about test name that is failed
     * @param iTestResult
     */
    public void onTestFailure(ITestResult iTestResult) {

        System.out.println("The name of the testcase failed is :"+ iTestResult.getName());
        saveScreenshot(iTestResult.getName());
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {

    }

    /**
     * Method that provides screenshot creation using method name and current time
     * @param methodName to use it in the screenshot name
     */
    private void saveScreenshot(String methodName) {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/" +methodName+ "/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot");
        }
    }

    /**
     * Method that provides current date and time
     * @return current time
     */
    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
