package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_008 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_008(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'finishlater')]")
	public WebElement finishLaterButton;
	
	@FindBy(how = How.XPATH, using = "//button//span[text()=' Yes ']")
	public WebElement YesButton;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-group//mat-radio-button)[1]")
	public WebElement firstradiobuttonVerifyAddPopup;
	
	

}
