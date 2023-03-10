package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stepDefinitions.commonStepDefinitions;

public class SUC_002 {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SUC_002(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(how = How.XPATH, using = "//mat-card[text()='Account flag(s) successfully added.']")
	//public WebElement sucessMessageText;
	
	
	
	@FindBy(how = How.XPATH, using = "//*[text()='The Account Information has been succesfully saved']")
	public WebElement accountInfoSuccessText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SUC-002']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='Employer Registration Number 00-00022 has been closed successfully effective 1/2021']")
	public WebElement successMessageText;
	
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='Account flag(s) successfully added.']")
	public WebElement accountFlagSuccessText;
	
	@FindBy(how = How.XPATH, using = "//mat-card[@id='successMatCardId']")
	public WebElement saleOfBusinessSuccessText;
	
	@FindBy(how = How.XPATH, using = "//button[@id='SUC-002access.home']//span[@class='mat-button-wrapper'][normalize-space()='Home']")
	public WebElement homeButton;
	
	
	public String validateSucessMessage() throws Exception {
		stepDef.screenShot("Success_Message", "Pass", "Success message Screenshot");
		String sucessMsg = accountFlagSuccessText.getText();
		stepDef.screenShot("SuccessMessage", "Pass", "validation failed");
		return sucessMsg;
	}
	
	public String validateSaleOfBusinessText() throws Exception {
		stepDef.screenShot("Success_Message", "Pass", "Success message Screenshot");
		String sucessMsg = saleOfBusinessSuccessText.getText();
		stepDef.screenShot("SuccessMessage", "Pass", "validation failed");
		return sucessMsg;
	}
	

	public void validateHomeButton() throws Exception {
		stepDef.screenShot("Home", "Pass", "Home Screenshot");
		Assert.assertTrue(homeButton.isDisplayed());
		stepDef.screenShot("SuccessMessage", "Pass", "validation failed");
	}
	
	public void validateEmployerAccountMSG() throws Exception {
		Assert.assertEquals(accountInfoSuccessText.getText(), "The Account Information has been succesfully saved");
		stepDef.screenShot("SuccessMessage", "Pass", "validation failed");
	}
}
