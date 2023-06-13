package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_011 extends TestBase{
	
	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_011(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanBeanId']")
	public WebElement eanField;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='tradeNameId']")
	public WebElement legalNameField;
	
	@FindBy (how = How.XPATH, using = "//textarea")
	public WebElement LegalNameOfBusiness;

	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Acquisition Date')]//following::input[1]")
	public WebElement AcquisitionDate;

}
