package com.ui.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stepDefinitions.commonStepDefinitions;

public class SREG_504 {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_504(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(how = How.XPATH, using = "//h2[@id='titleId']")
	public WebElement pageNameText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Successor ERN')]")
	public WebElement successorERNText;

	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='9300007']")
	public WebElement successorERNValue;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Predecessor ERN')]")
	public WebElement predecessorERNText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='9300010']")
	public WebElement predecessorERNValue;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Total or Partial')]")
	public WebElement totalOrPartialText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Part']")
	public WebElement totalOrPartialValue;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Submit']")
	public WebElement submitButton;
	
	public void verifyPageName(String actualPageName) {
		String name = pageNameText.getText();
		Assert.assertEquals(name, actualPageName);
	}
	
	public void verifyFilterValues() {
		Assert.assertTrue(successorERNText.isDisplayed());
		Assert.assertTrue(successorERNValue.isDisplayed());
		Assert.assertTrue(predecessorERNText.isDisplayed());
		Assert.assertTrue(predecessorERNValue.isDisplayed());
		Assert.assertTrue(totalOrPartialText.isDisplayed());
		Assert.assertTrue(totalOrPartialValue.isDisplayed());
	}
	
	public void clickSubmitButton(){
		submitButton.click();
	}
	
	
}
