package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SUC-002']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='Employer Registration Number 00-00022 has been closed successfully effective 1/2021']")
	public WebElement successMessageText;
	
	@FindBy(how = How.XPATH, using = "//button[@id='SUC-002access.home']")
	public WebElement homeButton;
	
	public String validateSucessMessage() {
		String sucessMsg = successMessageText.getText();
		return sucessMsg;
	}
	
	
}
