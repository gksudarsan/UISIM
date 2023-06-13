package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_030 extends TestBase{

public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_030(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[@id='titleId']")
	public WebElement pageTitle;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Employer Registration Number']")
	public WebElement ernText;
	
	@FindBy(how = How.XPATH, using = "//*[text()='00-00123']")
	public WebElement ernValue;
	
	@FindBy(how = How.XPATH, using = "//*[text()='FEIN']")
	public WebElement feinText;
	
	@FindBy(how = How.XPATH, using = "//*[text()='84-3532423']")
	public WebElement feinValue;
	
	@FindBy(how = How.XPATH, using = "//div[@id='mat-select-value-9']/span")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' IA602 ']")
	public WebElement sourceDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//div[@id='mat-select-value-11']/span")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' UIES ']")
	public WebElement sourceTypeDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Submit ']")
	public WebElement submitButton;
	
	
	
	public void fillFormAndValidate() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		Assert.assertTrue(ernText.isDisplayed());
		stepDef.screenShot("FormSS", "Pass", "failed");
		Assert.assertTrue(feinText.isDisplayed());
//		Assert.assertEquals("00-00123", ernValue.getText());
//		Assert.assertEquals("84-3532423", feinValue.getText());
//		stepDef.waitForElementClicable(sourceDropDown);
		stepDef.screenShot("FormSS2", "Pass", "Filling the form");
//		Thread.sleep(3000);
//		sourceDropDownValue.click();
//		stepDef.waitForElementClicable(sourceTypeDropDown);
//		sourceTypeDropDownValue.click();
		
		stepDef.selectDropdown("Source", " IA602 ");
		stepDef.selectDropdown("Source Type", " UIES ");
		
	}
	
	public void validatePageTitle() throws InterruptedException {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		String title = pageTitle.getText();
		Assert.assertEquals(title, "Employer Account Maintenance – Enter ERN");
	}
	
	public void clickSubmit() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Submit", "Pass", "submit button not clickable");
		submitButton.click();
	}
	
}
