package com.apromore.shoptoys;

import com.apromore.shoptoys.functionLibrary.ReportLog;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.apromore.shoptoys.functionLibrary.ExtentTestManager.getTest;

public class TestListener extends BasePageTest implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
       ReportLog.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //reportLog.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //ExtentReports log operation for passed tests.
        String base64Screenshot = getBase64Screenshot(iTestResult);
        getTest().log(Status.PASS, "Test Passed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    private static String getBase64Screenshot(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BasePageTest) testClass).driver;
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //Log.info(getTestMethodName(iTestResult) + " test is failed.");
        String base64Screenshot = getBase64Screenshot(iTestResult);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
       // Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
       // Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}