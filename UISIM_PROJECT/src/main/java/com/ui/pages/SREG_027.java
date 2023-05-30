package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_027 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_027(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how = How.XPATH, using = "//h2[@id='titleId']")
	public WebElement pageTitle;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-form-field-infix ng-tns-c138-104']/input")
	public WebElement ernInput;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']")
	public WebElement continueButton;
	
	
	public void validateTitle() {
		Assert.assertEquals("Employer Account Maintenance – Enter ERN", pageTitle.getText());
	}
	
	public void enterERNNumber(String ern) throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("ERNField", "pass", "ERN field not displayed");
		stepDef.enterTextboxContains("Employer Registration Number", ern);
		sleep();
		continueButton.click();
	}
	
	
}
