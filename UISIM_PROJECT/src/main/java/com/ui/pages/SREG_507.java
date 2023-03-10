package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.model.Test;
import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_507 extends TestBase{

	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_507(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h2[@id='titleId']")
	public WebElement pageTitleText;
	
	@FindBy(how = How.XPATH, using = "//label[@for='memberOfJointAccountId_No-input']//span[@class='mat-radio-inner-circle']")
	public WebElement isJointACMemberRadio_NO;
	
	@FindBy(how = How.XPATH, using = "//label[@for='poaId_Yes-input']//span[@class='mat-radio-outer-circle']")
	public WebElement havePOARadio_Yes;
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanId']")
	public WebElement jointAC_ERN_Input;
	
	@FindBy(how = How.XPATH, using = "//input[@id='legalNameId']")
	public WebElement legalNameBusinessInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='firstNameId']")
	public WebElement firstNameInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='lastNameId']")
	public WebElement lastNameInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_address1']")
	public WebElement addressLane1Input;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_city']")
	public WebElement cityInput;
	
	@FindBy(how = How.XPATH, using = "//div[@id='mat-select-value-3']")
	public WebElement stateDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='New York']")
	public WebElement stateDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mailingAddressId_zip']")
	public WebElement zipCode;
	
	@FindBy(how = How.XPATH, using = "//input[@id='phoneId']")
	public WebElement telephoneInput;

	@FindBy(how = How.XPATH, using = "//input[@id='emailId']")
	public WebElement emailInput;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']]")
	public WebElement continueButton;
	
	public void fillForm(String ERN , String businessName , String fname , String lname , String address, String city , String zipcode , String tele , String emailID) throws Exception {
		stepDef.screenShot("Establish_Joint_Account_Administrator","Pass","Redirected to SREG_507 Page");
		isJointACMemberRadio_NO.click();
		stepDef.waitForElementClicable(havePOARadio_Yes);
		jointAC_ERN_Input.sendKeys(ERN);
		legalNameBusinessInput.sendKeys(businessName);
		firstNameInput.sendKeys(fname);
		lastNameInput.sendKeys(lname);
		stepDef.screenShot("Establish_Joint_Account_Administrator2","Pass","Entered details till Last Name");
		addressLane1Input.sendKeys(address);
		cityInput.sendKeys(city);
		stateDropDown.click();
		zipCode.sendKeys(zipcode);
		telephoneInput.sendKeys(tele);
		emailInput.sendKeys(emailID);
		stepDef.screenShot("Establish_Joint_Account_Administrator3","Pass","Entered all the Details and click continue");
		stepDef.waitForElementClicable(continueButton);
	}
	
	public void verifyPageTitle() {
		String titleText = pageTitleText.getText();
		Assert.assertEquals("Establish Joint Account Administrator", titleText);
	}
}
