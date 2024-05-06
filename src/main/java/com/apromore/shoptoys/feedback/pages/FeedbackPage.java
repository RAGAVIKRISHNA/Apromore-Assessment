package com.apromore.shoptoys.feedback.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class FeedbackPage extends BasePage {

    private StringBuffer verificationErrors = new StringBuffer();

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    public WebElement contact;

    @FindBy(xpath = "//a[contains(text(),'Start Shopping')]")
    public WebElement launchPage;

    @FindBy(xpath = "//a[contains(text(),'Submit')][@class='btn-contact btn btn-primary']")
    public WebElement submitFeedback;

    @FindBy(xpath = "//input[@id='forename']")
    public WebElement enterForename;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement enterEmail;

    @FindBy(xpath = "//textarea[@id='message']")
    public WebElement enterFeedbackMessage;

    @FindBy(xpath = "//span[contains(text(),'is required')]")
    public List<WebElement> requiredFields;

    @FindBy(xpath = "/html/body/div[2]/div/div[@class='alert alert-success']")
    public WebElement successFeedbackResponse;

    public FeedbackPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void click_contact() {
        performElementClick(contact);
    }

    public void submit_feedback() {
        performElementClick(submitFeedback);
    }

    public void validate_errors() {
        assertTrue(requiredFields.get(0).getText().contains("is required"));
    }

    public void enter_details(String userForeName, String userEmail, String userFeedbackMessage) {
        sendKeys(enterForename, userForeName);
        sendKeys(enterEmail, userEmail);
        sendKeys(enterFeedbackMessage, userFeedbackMessage);
    }

    public void validateLoginPage() {
        waitForElementVisibility(launchPage);
        assertTrue(launchPage.getText().contains("Start Shopping"), "Error in launching the Jupiter Toys webpage");
    }


    public void validate_successResponse(String userForeName) {
        waitForElementVisibility(successFeedbackResponse);
        assertTrue(successFeedbackResponse.getText().matches("^Thanks " + userForeName + ", we appreciate your feedback.*$"));
    }
}
