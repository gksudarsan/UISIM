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
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='Account flag(s) successfully added.']")
	public WebElement accountFlagSuccessText;
	
	@FindBy(how = How.XPATH, using = "//mat-card[@id='successMatCardId']")
	public WebElement saleOfBusinessSuccessText;
	
	@FindBy(how = How.XPATH, using = "//button[@id='SUC-002access.home']//span[@class='mat-button-wrapper'][normalize-space()='Home']")
	public WebElement homeButton;
	
	
	public String validateSucessMessage() {
		String sucessMsg = accountFlagSuccessText.getText();
		return sucessMsg;
	}
	
	public String validateSaleOfBusinessText() {
		String sucessMsg = saleOfBusinessSuccessText.getText();
		return sucessMsg;
	}
	
	public void validateHomeButton() {
		Assert.assertTrue(homeButton.isDisplayed());
	}
}
