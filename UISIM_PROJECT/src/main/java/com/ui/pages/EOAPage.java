package com.ui.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EOAPage extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public EOAPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am a Third Party Representative who needs to create an online account to represent my clients in Unemployment Insurance matters.']")
	public WebElement tprRepresentativeRadio;
	
	@FindBy(how = How.XPATH, using = "(//a[contains(.,' ADD USER')])[1]")
	public WebElement addUserLink;
	
	public void userIdVerificationSREG061(String xpathParameter) {
		driver.findElement(By.xpath("//mat-label[text()='"+xpathParameter+"']")).isDisplayed();
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'User Types')]//following::mat-select[1]")
	public WebElement userTypeField;
	
	public void manageUserClickSREG061(String xpathParameter) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//mat-cell[@aria-label='"+xpathParameter+"']//following::mat-cell//u[text()='Manage User'])[1]")).click();
	}
	
	public void manageClientClickSREG061(String xpathParameter) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//mat-cell[@aria-label='"+xpathParameter+"']//following::mat-cell//u[text()='Manage Client'])[1]")).click();
	}
	
	@FindBy(how = How.XPATH, using = "//mat-option//span[text()=' Employer Sub-User ']")
	public WebElement employerSubUserOption;
	
	@FindBy(how = How.XPATH, using = "//mat-option//span[text()=' Master Administrator ']")
	public WebElement masterAdministratorOption;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='Employer Users'])[1]")
	public WebElement employerUserOption;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='Employer Users'])[2]")
	public WebElement employerUserOption2;
	
	
	@FindBy(how = How.XPATH, using = "//ul//li[text()='The Employer Registration Number (ERN) provided does not exist in the system.']")
	public WebElement ernErrorlabel;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' User Administrator')]")
	public WebElement userAdminCheckbox;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' TPR - All UI Matters')]")
	public WebElement tprAllCheckbox;
	
	@FindBy(how = How.XPATH, using = "//mat-card[text()='The user account(s) have been updated successfully.']")
	public WebElement updatedSuccesMsg;
	
	@FindBy(how = How.XPATH, using = "(//mat-cell//following::mat-cell//u[text()='Manage Client'])[1]")
	public WebElement manageClientButton;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='mdc-checkbox']//input)[1]")
	public WebElement firstCheckboxAll;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='mdc-checkbox']//input)[2]")
	public WebElement firstCheckbox;
	
	@FindBy(how = How.XPATH, using = "(//input[@aria-label='Enter Date'])[1]")
	public WebElement firstEnterDateField;
	
	
	
	

}
