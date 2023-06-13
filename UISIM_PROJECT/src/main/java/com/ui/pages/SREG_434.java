package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_434 extends TestBase{
	
	public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_434(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[@id=' SREG-434access.continue']")
	public WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']")
	public WebElement employerRegistrationNumberText;
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanId']")
	public WebElement registerERN;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()=' SREG-434']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']")
	public WebElement requiredText;
	
	public Boolean checkRequiredText() throws Exception {
		stepDef.clickButtonContains("Continue");
		Thread.sleep(2000);
		stepDef.screenShot("Required_Text", "Pass", "Required text");
		Boolean flag = requiredText.isDisplayed();
		return flag;
		
	}
		public void enterEANNumber(String EAN) throws Exception {
			stepDef.enterTextbox("Employer Registration Number",EAN);
			//registerERN.sendKeys(EAN);
			Thread.sleep(2000);
			stepDef.screenShot("EnterErn", "Pass", "ERN text");
			stepDef.clickButtonContains("Continue");	
	
	}


}
