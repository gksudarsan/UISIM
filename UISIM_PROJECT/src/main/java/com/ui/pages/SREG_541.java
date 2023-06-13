package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_541 extends TestBase{
	
	public WebDriver driver;
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_541(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//keepblank- required text
	@FindBy(how = How.XPATH, using = "//button[@id='SearchId']")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration Number']")
	public WebElement employerRegistrationNoText; 
    
	//ean not exit
	@FindBy(how = How.XPATH, using = "//input[@id='eanBeanId']")
	public WebElement registerEAN; 
	
	@FindBy(how = How.XPATH, using = "//input[@id='eanBeanId']")// xpath
	public WebElement eanNotExitText;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']")
	public WebElement requiredText;   
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SREG-541']")
	public WebElement screenIdText;   
	
	@FindBy(how = How.XPATH, using = "//button[@id='SREG-541access.tax.reversetransfer.reversetransfer']")
	public WebElement voidTransferButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='SUMA_BOYILLLA']")
	public WebElement successorName;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='2023-03-06']")
	public WebElement transferEffectiveDate;
	
	@FindBy(how = How.XPATH, using = "//li[@id='businessError0']")
	public WebElement errorMessagesPopUp;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-radio-inner-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()=' Required']")
	public WebElement commentRequiredText;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='explainReasonId']")
	public WebElement enterValidComment;
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-arrow ng-tns-c155-115']")
	public WebElement sourceDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Correspondence/Email']")
	public WebElement sourceDropdownValue; 
	
	@FindBy(how = How.XPATH, using = "//div[@class='mat-select-arrow ng-tns-c155-116']")
	public WebElement sourceTypeDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[@class='mat-option-text'][normalize-space()='Correspondence/Email']")
	public WebElement sourceTypeDropdownValue;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id=\"explainReasonId\"]")
    public WebElement commentBox_voidTransfer;
	
	public Boolean validateRequiredText() throws InterruptedException {
		stepDef.clickElement(searchButton);
		Thread.sleep(2000);
		Boolean flag = requiredText.isDisplayed();
		return flag;
	}	
	
	public Boolean validateEANNotExits(String EAN) throws InterruptedException {
		registerEAN.sendKeys("4585695");
		Thread.sleep(2000);
		stepDef.clickElement(searchButton);
		Boolean flag = eanNotExitText.isDisplayed();
		return flag;
		
	}
	
	public void registerEANNumber(String EAN) throws InterruptedException {
		registerEAN.sendKeys(EAN);
		Thread.sleep(2000);
		stepDef.clickElement(searchButton);
		
	}
	
	public void checkEANData() {
		String screenIDName = screenIdText.getText();
		Assert.assertEquals(screenIDName, "SREG-541");
		String successorNameText = successorName.getText();
		Assert.assertEquals(successorNameText, "SUMA_BOYILLLA");
		String transferEffectiveDateText = transferEffectiveDate.getText();
		Assert.assertEquals(transferEffectiveDateText, "2023-03-06");
		
	}
		public Boolean validateErrorMessagesPopUp() throws InterruptedException {
			stepDef.clickElement(voidTransferButton);
			Thread.sleep(2000);
			//stepDef.clickElement(errorMessagesPopUp);
			//Thread.sleep(2000);
			Boolean flag = errorMessagesPopUp.isDisplayed();
			return flag;
	}	
		public Boolean validateCommentErrorMessagePop() throws InterruptedException {
			stepDef.clickElement(selectRadioButton);
			Thread.sleep(2000);
			stepDef.clickElement(voidTransferButton);
			Boolean flag = commentRequiredText.isDisplayed();
			return flag;
			
	}
		public void enterRequiredFields() throws InterruptedException {
			stepDef.clickElement(searchButton);
			Thread.sleep(2000);
			stepDef.clickElement(selectRadioButton);
			enterValidComment.sendKeys("test test");
			stepDef.clickElement(sourceDropdown);
			Thread.sleep(2000);
			stepDef.clickElement(sourceDropdownValue);
			stepDef.clickElement(sourceTypeDropdown);
			Thread.sleep(2000);
			stepDef.clickElement(sourceTypeDropdownValue);
    }
		public void clickVoidTransfer() {
			voidTransferButton.click();
	}
}

