package com.apromore.shoptoys;

import com.apromore.shoptoys.feedback.pages.FeedbackPage;
import com.apromore.shoptoys.feedback.pages.ShopToysPage;
import com.apromore.shoptoys.functionLibrary.ReportLog;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static com.apromore.shoptoys.constants.WebPageConstants.StepDescription.*;
import static com.apromore.shoptoys.constants.WebPageConstants.StepName.*;
import static com.apromore.shoptoys.functionLibrary.ExtentTestManager.getTest;
import static com.apromore.shoptoys.functionLibrary.ExtentTestManager.startTest;

public class FeedBackTest extends BasePageTest {

    FeedbackPage feedbackPage;
    ShopToysPage shoptoysPage;
    ReportLog logger;

    @BeforeClass
    public void startTestSuite() {
        logger = new ReportLog(driver);
        feedbackPage = new FeedbackPage(driver);
        shoptoysPage = new ShopToysPage(driver);
    }

    @BeforeMethod
    public void beforeMethodExecution(Method m) {
        driver.get("http://jupiter.cloud.planittesting.com");

    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"Lion", "Lion@gmail.com", "Thank you for the wonderful toys", "Submit_Feedback_withMandatoryFields" }, {"Tiger", "Tiger@gmail.com", "the product looks impressive", "Submit_Feedback_withMandatoryFields"}};
    }

    @Test
    public void Submit_Feedback_withoutMandatoryFields(Method method) throws IOException {
        startTest(method.getName(), "Submit Feedback without filling required fields");

        feedbackPage.waitForElementVisibility(feedbackPage.launchPage);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.validateLoginPage();
        logStepInfoExtentReport(method.getName(), VALIDATE_LOGIN_STEP_NAME, VALIDATE_LOGIN_STEP_DESC);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.click_contact();
        logStepInfoExtentReport(method.getName(), CLICK_CONTACT_STEP_NAME, CLICK_CONTACT_STEP_DESC);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.submit_feedback();
        logStepInfoExtentReportFailure(method.getName(), SUBMIT_FEEDBACK_FAIL_STEP_NAME, SUBMIT_FEEDBACK_FAIL_STEP_DESC);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.validate_errors();
        logStepInfoExtentReport(method.getName(), VALIDATE_ERRORS_STEP_NAME, VALIDATE_ERRORS_STEP_DESC);
        }

    @Test(dataProvider = "data-provider")
    public void Submit_Feedback_withMandatoryFields(String userForeName, String userEmail, String userFeedbackMessage, Method method) throws IOException {
        startTest(method.getName(), "Submit Feedback by filling required fields");

        feedbackPage.waitForElementVisibility(feedbackPage.launchPage);
        feedbackPage.validateLoginPage();
        logStepInfoExtentReport(method.getName(), VALIDATE_LOGIN_STEP_NAME, VALIDATE_LOGIN_STEP_DESC);

        feedbackPage.click_contact();
        logStepInfoExtentReport(method.getName(), CLICK_CONTACT_STEP_NAME, CLICK_CONTACT_STEP_DESC);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.enter_details(userForeName, userEmail, userFeedbackMessage);
        logStepInfoExtentReport(method.getName(), ENTER_DETAILS_STEP_NAME, ENTER_DETAILS_STEP_DESC);

        feedbackPage.submit_feedback();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackPage.validate_successResponse(userForeName);
        logStepInfoExtentReport(method.getName(), VALIDATE_SUCCESS_RESPONSE_STEP_NAME, VALIDATE_SUCCESS_RESPONSE_STEP_DESC);
    }

    @Test
    public void shopToys(Method method) throws IOException, InterruptedException {
        startTest(method.getName(), "Execution of shop toys test.");

        shoptoysPage.click_shop();
        logStepInfoExtentReport(method.getName(), CLICK_TOYS_STEP_NAME, CLICK_TOYS_STEP_DESC);

        shoptoysPage.add_itemBear(2);
        logStepInfoExtentReport(method.getName(), ADD_ITEM_STEP_NAME, ADD_ITEMS_STEP_DESC);

        shoptoysPage.click_cart();
        logStepInfoExtentReport(method.getName(), CLICK_CART_STEP_NAME, CLICK_CART_STEP_DESC);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        shoptoysPage.verify_quantity();
        logStepInfoExtentReport(method.getName(), VERIFY_QUANTITY_STEP_NAME, VERIFY_QUANTITY_STEP_DESC);

    }

    private void logStepInfoExtentReport(String methodName, String stepName,
                                         String stepDescription) throws IOException {
        takeScreenShot(driver, methodName, stepName);
        getTest().log(Status.PASS, stepDescription,
                MediaEntityBuilder
                        .createScreenCaptureFromPath(
                                compareImageAndReturnBase64EncodedImageString(methodName, stepName)).build());
    }

    private void logStepInfoExtentReportFailure(String methodName, String stepName,
                                         String stepDescription) throws IOException {
        takeScreenShot(driver, methodName, stepName);
        getTest().log(Status.FAIL, stepDescription,
                MediaEntityBuilder
                        .createScreenCaptureFromPath(
                                compareImageAndReturnBase64EncodedImageString(methodName, stepName)).build());
    }

}