package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SREG_493 {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_493(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c156-6 ng-star-inserted']")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c156-7 ng-star-inserted']")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceTypeDropDownValue;
	
	@FindBy(how = How.XPATH, using = "//label[@for='mat-radio-2-input']//span[@class='mat-radio-outer-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-form-field-infix ng-tns-c138-10']//input[@id='terminationDateId']")
	public WebElement jointAccountDissolutionDate;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='commentId']")
	public WebElement comments;
	
	public void fillForm() throws InterruptedException {
		stepDef.waitForElementClicable(sourceDropDown);
		sourceDropDownValue.click();
		stepDef.waitForElementClicable(sourceTypeDropDown);
		sourceTypeDropDownValue.click();
		selectRadioButton.click();
		stepDef.doSendKeysWithWait(jointAccountDissolutionDate, "03132023");
		comments.sendKeys("Test Data");
	}
}
