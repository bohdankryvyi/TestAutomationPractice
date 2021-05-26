package util;

import com.google.common.base.Throwables;
import driver.DriverSingleton;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.MyStoreTests;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TestListener class implements ITestListener and it is important for listening the tests
 * There is possibilities to add different actions on test failure/ test success /test start/etc
 */
public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();
    Throwable exception = null;
    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    /**
     * Test Failure method with actions
     * Screenshot creation and showing info in the console about test name that is failed
     * @param iTestResult will return screenshot of the failed test
     */
    public void onTestFailure(ITestResult iTestResult) {
        log.error("The name of the testcase failed is :"+ iTestResult.getName()+ "\n"+
                "Exception is: "+ iTestResult.getThrowable());
        //log.error(Thread.getAllStackTraces());
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
    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveScreenshot(String methodName) {
        byte[] screen =  ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            FileUtils.writeByteArrayToFile(new File(
                    ".//target/screenshots/" +methodName+ "/"
                            + getCurrentTimeAsString() +
                            ".png"), screen);
        } catch (IOException e) {
           log.error ("Failed to save screenshot" + e.getLocalizedMessage());
        }
        return screen;
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

 /*
    old version that created File and not byte[] at the beginning
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
    }*/
