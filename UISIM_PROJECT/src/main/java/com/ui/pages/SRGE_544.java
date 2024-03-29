package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SRGE_544 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SRGE_544(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']")
	public WebElement registrationText;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of Business']")
	public WebElement legalText;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Flag Type']/../../div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement flagTypeDropDown;

	@FindBy(how = How.XPATH, using = "//span[text()=' First Returned Mail ']")
	public WebElement firstReturnDropValue;

	@FindBy(how = How.XPATH, using = "//span[text()=' Add']/..")
	public WebElement addButton;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='MM/DD/YYYY']")
	public WebElement dateField;

	@FindBy(how = How.XPATH, using = "//textarea")
	public WebElement commentBox;

	@FindBy(how = How.XPATH, using = "//div[text()='Flag Type']")
	public WebElement flagTypeFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Date']")
	public WebElement DateFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Comment']")
	public WebElement commentFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Created By']")
	public WebElement createdByFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Created Date']")
	public WebElement createdDateFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Updated By']")
	public WebElement updatedByFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Date of Update']")
	public WebElement dateOfUpdateFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Removed By']")
	public WebElement removedByFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Removed Date']")
	public WebElement removeDateFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Removal Comments']")
	public WebElement removalCommentsFilterText;

	@FindBy(how = How.XPATH, using = "//div[text()='Action']")
	public WebElement actionFilterText;

	@FindBy(how = How.XPATH, using = "//mat-error[text()=' Required ']")
	public WebElement requiredText;

	@FindBy(how = How.XPATH, using = "//span[text()='Submit ']/..")
	public WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='First Returned Mail']")
	public WebElement flagTypeValue;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Sample Text']")
	public WebElement commentValue;
	
	
	public Boolean submitWithoutDetails() throws InterruptedException {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.clickElement(submitButton);
		Boolean flag = requiredText.isDisplayed();
		return flag;
	}
	
	public void enterDetails() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.clickElement(flagTypeDropDown);
		Thread.sleep(2000);
		stepDef.clickElement(firstReturnDropValue);
		
		stepDef.enterCurrentDate(dateField);
		commentBox.sendKeys("Sample Text");
		stepDef.screenShot("Form", "PASS", "Entered the details and click add button");
		stepDef.clickElement(addButton);
		Thread.sleep(2000);
		stepDef.screenShot("Form", "PASS", "Add button clicked");
	}
	public void checkFilter() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Filter", "PASS", "Filter Screenshot");
		String flagValueText = flagTypeValue.getText();
		Assert.assertEquals(flagValueText, "First Returned Mail");
		String commentValueText = commentValue.getText();
		Assert.assertEquals(commentValueText, "Sample Text");
	}
	public void clickSubmit() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		submitButton.click();
		Thread.sleep(3000);
		stepDef.screenShot("Submit", "PASS", "Clicked Submit");
	}
}
