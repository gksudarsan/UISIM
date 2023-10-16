package com.ui.pages;


import java.time.Duration;

import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	

	public void selectCheckboxfis009(String xpathParameter) {
		By element = By.xpath("//mat-checkbox//label[contains(.,'" + xpathParameter + "')]//preceding::*[@class='mdc-checkbox__native-control'][1]");
		//final WebDriverWait wait = new WebDriverWait(driver, 10);
		/*WebDriverWait not supported in latest version of selenium updated as below*/

		final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			//stepDef.safeJavaScriptClick(ele);
			ele.click();
		} catch (final Exception e) {
		}
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am a Professional Employer Organization that needs to create an online account for maintaining my client’s associations and Professional Employer Organization registration status.']")
	public WebElement peoRegisteredRadio;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' Same as Contact Number ')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement cellSameasContactNumber;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' TPR - All UI Matters')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement  tprAllUIMatters;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,' Employer - Contributions')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement  select_Employer_Contributions;
	
	@FindBy(how = How.XPATH, using = "//*[@class='mdc-radio__native-control']//following::*[contains(@id, '_1-input')][1]")
	public WebElement tprRadio;
	
	@FindBy(how = How.XPATH, using = "//*[@class='mdc-radio__native-control']//preceding::*[contains(@id, '_1-input')][1]")
	public WebElement employerRadio;

	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Quarter ')]//following::mat-select[1]")
	public WebElement quarterDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 3 ']")
	public WebElement quarterValue_3;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Year ')]//following::mat-select[1]")
	public WebElement yearDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement yearValue_2023;
}
