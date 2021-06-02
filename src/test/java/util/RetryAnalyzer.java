package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    int retryLimit = 4; //failed test will run "count" times

    @Override
    public boolean retry(ITestResult result) {
        if(counter < retryLimit)
        {
            counter++;
            return true;
        }
        return false;
    }
}
