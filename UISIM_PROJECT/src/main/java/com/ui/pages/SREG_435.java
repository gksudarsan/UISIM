package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_435 extends TestBase{
	
	public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_435(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']")
	public WebElement employerRegistrationNumberText;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of Business']")
	public WebElement legalNameBussinessText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='FEIN']")
	public WebElement feinNumber;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Current Status']")
	public WebElement currentStatusText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Liability Date']")
	public WebElement liabilityDateText;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-arrow ng-tns-c156-119']")
	public WebElement statusOfEmployerAccountDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Liable ']")
	public WebElement statusOfEmployerAccountDropdownValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-arrow ng-tns-c128-225']")
	public WebElement quarterDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()='3']")
	public WebElement quarterDropdownValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-arrow ng-tns-c128-227']")
	public WebElement yearDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()='2023']")
	public WebElement yearDropdownValue;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='remarksId']")
	public WebElement enterComments;
	
	@FindBy(how = How.XPATH, using = "//label[@for='closureLetterId_No-input']//span[@class='mat-radio-container']")
	public WebElement radioButton;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Source')]//following::mat-select[1])[last()-1]")
	public WebElement sourceDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceDropdownValue;
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Source')]//following::mat-select[1])[last()]")
	public WebElement selectSourceTypeDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-option-text'][normalize-space()='Correspondence/Email']")
	public WebElement selectSourceTypeDropdownValue;
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id=' SREG-435']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//mat-error[@id='mat-error-4']")
	public WebElement requiredText;
	
	@FindBy(how = How.XPATH, using = "//button[@id=' SREG-435access.submit']")
	public WebElement submitButton;
	
	
	public Boolean submitWithoutFillingDetails() throws InterruptedException {
		stepDef.clickElement(submitButton);
		Boolean flag = requiredText.isDisplayed();
		return flag;
	}
	
	public void enterRequiredFields() throws Exception {
		stepDef.screenShot("RequiredField1", "Pass", "Required Field");
		stepDef.clickElement(statusOfEmployerAccountDropdown);
		Thread.sleep(2000);
		stepDef.clickElement(statusOfEmployerAccountDropdownValue);
		stepDef.clickElement(quarterDropdown);
		Thread.sleep(2000);
		stepDef.screenShot("RequiredField2", "Pass", "Required Field");
		stepDef.clickElement(quarterDropdownValue);
		stepDef.clickElement(yearDropdown);
		Thread.sleep(2000);
		stepDef.clickElement(yearDropdownValue);
		enterComments.sendKeys("Testing Sample");
		stepDef.screenShot("RequiredField3", "Pass", "Required Field");
		Thread.sleep(2000);
		System.out.println("Trying to click radio button");
		//radioButton.click();
		Thread.sleep(2000);
		stepDef.clickElement(sourceDropdown);
		Thread.sleep(2000);
		stepDef.clickElement(sourceDropdownValue);
		Thread.sleep(2000);
		stepDef.clickElement(selectSourceTypeDropdown);
		stepDef.screenShot("RequiredField4", "Pass", "Required Field");
		stepDef.clickElement(selectSourceTypeDropdownValue);
		Thread.sleep(2000);
		stepDef.screenShot("RequiredField5", "Pass", "Required Field");
	}
	
		//public void checkFilter() {
			//String flagValueText = flagTypeValue.getText();
			//Assert.assertEquals(flagValueText, "First Returned Mail");
			//String commentValueText = commentValue.getText();
			//Assert.assertEquals(commentValueText, "Sample Text");
		//}
		public void clickSubmit() throws Exception {
			submitButton.click();
			stepDef.screenShot("submitButton", "Pass", "submit button click");
			Thread.sleep(2000);
		}
		
	

}
