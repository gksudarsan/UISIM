package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SREG_492 {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_492(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='ernId']")
	public WebElement ernInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='janId']")
	public WebElement jointAccountInput;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Continue ']/..")
	public WebElement continueButton;
	
	
	public void enterERNAndJointAccountNumber() throws InterruptedException {
		stepDef.doSendKeysWithWait(ernInput , "00-00173");
		stepDef.doSendKeysWithWait(jointAccountInput , "9500022");
		continueButton.click();
	}
}
