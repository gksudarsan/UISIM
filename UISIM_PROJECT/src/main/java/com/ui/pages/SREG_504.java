package com.ui.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_504 extends TestBase {

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
	
	public void verifyPageName(String actualPageName) throws InterruptedException {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		String name = pageNameText.getText();
		Assert.assertEquals(name, actualPageName);
	}
	
	public void verifyFilterValues() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Filters", "Pass", "Filter validation");
		Assert.assertTrue(successorERNText.isDisplayed());
		Assert.assertTrue(successorERNValue.isDisplayed());
		Assert.assertTrue(predecessorERNText.isDisplayed());
		Assert.assertTrue(predecessorERNValue.isDisplayed());
		Assert.assertTrue(totalOrPartialText.isDisplayed());
		Assert.assertTrue(totalOrPartialValue.isDisplayed());
		stepDef.screenShot("Filters", "PASS", "Filter validation");
	}
	
	public void clickSubmitButton() throws Exception{
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		submitButton.click();
		Thread.sleep(2000);
		stepDef.screenShot("Submit", "PASS", "Submit validation");
	}
	
	
}
