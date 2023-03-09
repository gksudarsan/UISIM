package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_503 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_503(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']")
	public WebElement requiredText;
	
	
	@FindBy(how = How.XPATH, using = "//label[@for='soldBusinessId_Yes-input']//span[@class='mat-radio-inner-circle']")
	public WebElement haveYouSoldYourRadio;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorEanId']")
	public WebElement successorInput;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Search']")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Search']")
	public WebElement saerchButton;
	
	@FindBy(how = How.XPATH, using = "//label[@for='sellAllPartofBusinessId_P-input']//span[@class='mat-radio-outer-circle']")
	public WebElement partRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='effectiveDateOfTransferId']")
	public WebElement effectiveDateTransfer;
	
	@FindBy(how = How.XPATH, using = "//label[@for='suppressPtId_Yes-input']//span[@class='mat-radio-outer-circle']")
	public WebElement yesRadio;
	
	@FindBy(how = How.XPATH, using = "//label[@for='suppressPtPercentageId_No-input']//span[@class='mat-radio-outer-circle']")
	public WebElement eAANoRadio;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c156-120 ng-star-inserted']")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceValue;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c156-121 ng-star-inserted']")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceTypeValue;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']")
	public WebElement continueButton;
	
	
	public void fillFormDetails() throws InterruptedException {
		haveYouSoldYourRadio.click();
		successorInput.sendKeys(prop.getProperty("successor"));
		saerchButton.click();
		Thread.sleep(3000);
		partRadioButton.click();
		effectiveDateTransfer.sendKeys(prop.getProperty("Date_EM_310_02"));
		sourceDropDown.click();
		Thread.sleep(2000);
		sourceValue.click();
		Thread.sleep(2000);
		sourceTypeDropDown.click();
		Thread.sleep(2000);
		sourceTypeValue.click();
		continueButton.click();
		Thread.sleep(5000);
	}
	
	
	
}
