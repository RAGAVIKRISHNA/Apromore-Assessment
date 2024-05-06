package com.apromore.shoptoys.feedback.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class ShopToysPage extends BasePage {
  private StringBuffer verificationErrors = new StringBuffer();

  @FindBy(xpath="//a[contains(text(),'Shop')]")
  public WebElement shop;

  @FindBy(xpath="//li[@id='product-7']//a")
  public WebElement valentineBear;
  
  @FindBy(xpath="//a[contains(text(),'Cart')]")
  public WebElement cart;
  
  @FindBy(xpath="(//td[3])[1]//input")
  public WebElement quantity_product;


  public ShopToysPage(WebDriver driver)  {
		 super(driver);
		 PageFactory.initElements(driver, this);
	  }

  public void click_shop() {
      performElementClick(shop);
  }
  public void click_cart() {
	  performElementClick(cart);
  }

  public void add_itemBear(int noOfTimes) {
	  for (int i = 0; i < noOfTimes; i++) {
          performElementClick(valentineBear);
		}
	  }
  public void verify_quantity() {
      assertEquals(quantity_product.getAttribute("value"), "2", "Items are not added to the Cart");
  }
}
