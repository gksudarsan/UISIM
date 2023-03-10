package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_503 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_503(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SREG-503']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//label[@for='soldBusinessId_Yes-input']//span[@class='mat-radio-inner-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorEanId']")
	public WebElement successorEan;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Length of this response must be at least 7 characters.']")
	public WebElement invalidSuccessorEan;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Employer Registration Number is invalid.']")
	public WebElement eanNotValid;
	
	
	public Boolean validateScreenIdText() {
		selectRadioButton.click();
		Boolean flag = screenIdText.isDisplayed();
		return flag;
	}
	
	public Boolean validateEanMessageText() throws InterruptedException {
		successorEan.sendKeys("6789");
		Thread.sleep(2000);
		Boolean flag = invalidSuccessorEan.isDisplayed();
		return flag;
	}
	
	public Boolean validateEanMessageText2() throws InterruptedException {
		successorEan.sendKeys("12345678");
		Thread.sleep(2000);
		Boolean flag = eanNotValid.isDisplayed();
		return flag;
	}
}
