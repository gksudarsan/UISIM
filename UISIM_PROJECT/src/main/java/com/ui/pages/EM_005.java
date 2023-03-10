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
	public WebElement registerEAN;
	
	@FindBy(how = How.XPATH, using = "//button[@type='submit']") 
	public WebElement continueButon;
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id=' EM-005']") 
	public WebElement screenIDText;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']") 
	public WebElement requiredText;
	
	@FindBy(how = How.XPATH, using = "//li[@id='businessError0']")
	public WebElement eanProvidedNotExitText;
	
	public Boolean checkKeepBlankErrorMessage() throws InterruptedException {
		stepDef.clickElement(continueButon);
		Thread.sleep(2000);
		Boolean flag = requiredText.isDisplayed();
		return flag;
	}
	
	public Boolean checkEanNotExit(String EAN) throws InterruptedException {
		registerEAN.sendKeys("00-00001");
		Thread.sleep(2000);
		Boolean flag = eanProvidedNotExitText.isDisplayed();
		return flag;
	}
	public void enterValidEANNumber(String EAN) throws InterruptedException {
		registerEAN.sendKeys(EAN);
		Thread.sleep(2000);
		stepDef.clickElement(continueButon);
	}

}
