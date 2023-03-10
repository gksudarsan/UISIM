package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EM_005 extends TestBase {
	
	public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public EM_005 (WebDriver drievr) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanId']")
	public WebElement registerERN;
	

	@FindBy(how = How.XPATH, using = "//button[@type='submit']") 
	public WebElement continueButton;
	

	@FindBy(how = How.XPATH, using = "//mat-label[@id=' EM-005']")
	public WebElement pageNameText;
	
	
	public String enterDetailInERNField(String ERN) throws InterruptedException {
		String pageNametext = pageNameText.getText();
//		ERNInputField.sendKeys(ERN);
		Thread.sleep(2000);
	    stepDef.clickElement(continueButton);
		return pageNametext;
	}	
}
