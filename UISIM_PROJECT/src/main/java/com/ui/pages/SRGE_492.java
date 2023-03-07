package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SRGE_492 {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SRGE_492(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(how = How.XPATH, using = "//input[@id='ernId']")
//	public WebElement ERNField;
//	
//	@FindBy(how = How.XPATH, using = "//input[@id='janId']")
//	public WebElement joinAccountNumber;
//	
//	@FindBy(how = How.XPATH, using = "//input[@id='ernId']")
//	public WebElement ERNField;
//	
//	@FindBy(how = How.XPATH, using = "//input[@id='ernId']")
//	public WebElement ERNField;
	
}
