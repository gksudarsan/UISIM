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

	public EM_005(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanId']")
	public WebElement registerERNInput;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']")
	public WebElement continueButton;
	

	@FindBy(how = How.XPATH, using = "//mat-label[@id=' EM-005']")
	public WebElement pageNameText;
	
	
	public void enterDetailInERNField(String ERN) throws Exception {
		Thread.sleep(2000);
		System.out.println("Inside the ERN page");
		stepDef.screenShot("ERN", "Pass", "ERN field input field");
		String pageNametext = pageNameText.getText();
		System.out.println(pageNametext);
		registerERNInput.sendKeys(ERN);
		Thread.sleep(2000);
		stepDef.clickElement(continueButton);
		Thread.sleep(2000);
//		return pageNametext;
	}	
}
