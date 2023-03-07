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
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='Account flag(s) successfully added.']")
	public WebElement sucessMessageText;
	
	public String validateSucessMessage() {
		String sucessMsg = sucessMessageText.getText();
		return sucessMsg;
	}
	
	
	
}
