package com.ui.pages;

import org.openqa.selenium.ElementClickInterceptedException;
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
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SREG-503']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//label[@for='soldBusinessId_Yes-input']//span[@class='mat-radio-inner-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorEanId']")
	public WebElement successorEan;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Length of this response must be at least 7 characters.']")
	public WebElement invalidSuccessorEan;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Employer Registration Number is invalid.']")
	public WebElement eanNotValid;
	
	
	public Boolean validateScreenIdText() {
		selectRadioButton.click();
		Boolean flag = screenIdText.isDisplayed();
		return flag;
	}
	
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
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source']/../../div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceValue;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source Type']/../../div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceTypeValue;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']")
	public WebElement continueButton;
	
	
	public void fillFormDetails() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Form", "Pass", "Form in blank state");
		haveYouSoldYourRadio.click();
		successorInput.sendKeys(prop.getProperty("successor"));
		saerchButton.click();
		Thread.sleep(3000);
		partRadioButton.click();
		effectiveDateTransfer.sendKeys(prop.getProperty("Date_EM_310_02"));
		sourceDropDown.click();
		stepDef.screenShot("Form1", "Pass", "Entering data");
		Thread.sleep(3000);
		stepDef.ScrollMenu(" Correspondence/Email ");
		try {
			stepDef.waitForElementClicable(sourceValue);
		}catch(ElementClickInterceptedException e) {
			stepDef.safeJavaScriptClick(sourceValue);
		}
		System.out.println("Selected drop down 1");
		Thread.sleep(2000);
		sourceTypeDropDown.click();
		stepDef.ScrollMenu(" Correspondence/Email ");
		stepDef.screenShot("Form2", "Pass", "Entering data");
		Thread.sleep(2000);
		try {
			stepDef.waitForElementClicable(sourceTypeValue);
		}catch(ElementClickInterceptedException e) {
			stepDef.safeJavaScriptClick(sourceTypeValue);
		}
		
		System.out.println("Selected drop down 2");
		stepDef.screenShot("Form3", "Pass", "Entered data");
		continueButton.click();
		Thread.sleep(5000);
	}
	
	
	public Boolean validateEanMessageText() throws InterruptedException {
		successorEan.sendKeys("6789");
		Thread.sleep(2000);
		Boolean flag = invalidSuccessorEan.isDisplayed();
		return flag;
	}
	
	public Boolean validateEanMessageText2() throws InterruptedException {
		successorEan.sendKeys("12345678");
		Thread.sleep(2000);
		Boolean flag = eanNotValid.isDisplayed();
		return flag;
	}
}
