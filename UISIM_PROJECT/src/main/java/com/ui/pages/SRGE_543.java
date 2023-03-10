package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SRGE_543 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SRGE_543(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//button[@id='SREG-543access.continue']")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']")
	public WebElement registrationText;

	@FindBy(how = How.XPATH, using = "//input[@id='eanBeanId']")
	public WebElement registerInputField;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='SREG-543']")
	public WebElement pageNumberText;

	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']")
	public WebElement requiredText;

	public Boolean checkRequiredText() throws InterruptedException {
		stepDef.clickElement(continueButton);
		Thread.sleep(2000);
		Boolean flag = requiredText.isDisplayed();
		return flag;
	}

	public void enterEANNumber(String EAN) throws InterruptedException {
		registerInputField.sendKeys(EAN);
		Thread.sleep(2000);
		stepDef.clickElement(continueButton);
	}
}
