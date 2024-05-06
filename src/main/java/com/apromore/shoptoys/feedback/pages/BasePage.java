package com.apromore.shoptoys.feedback.pages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }
    public void performElementClick(WebElement webElement) {
        webElement.click();
    }

    public void sendKeys(WebElement webElement, String value) {
        webElement.sendKeys(value);
    }
    public void waitForElementVisibility(WebElement successFeedbackResponse) throws ElementNotVisibleException {
        wait.until(ExpectedConditions.visibilityOf(successFeedbackResponse));
    }
}
