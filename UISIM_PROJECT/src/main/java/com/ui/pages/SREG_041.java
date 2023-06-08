package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_041 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_041(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'Search')]")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'POA/TPR Legal Name')]//following::input[1]")
	public WebElement legalnameField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'TPR ID')]//following::input[1]")
	public WebElement tprID;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Please select a record to proceed further.']")
	public WebElement legalnameError;
	
	
	
	
	

}
