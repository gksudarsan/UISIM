package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddressPage {

	
	public WebDriver driver;

	public AddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[@id='EmployerRegistration']") 
    public WebElement employerRegisterMenu; 

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea") 
	public WebElement legalNameTextBox; 
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Is your entity a legally')]/../following-sibling::div/mat-radio-group/mat-radio-button/label/span[2][text()='Yes ']/../span/span") 
	public WebElement iSyourEntityQuestion_Yes; 

	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3][@class='mat-form-field-infix ng-tns-c138-164']/input") 
	public WebElement addressLine1_Form1; 

	@FindBy(how = How.XPATH, using = "//mat-label[text()='City ']/span/../following-sibling::mat-form-field/div/div/div[3]/input") 
	public WebElement city_Form1; 

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Zip Code']/span/../following-sibling::mat-form-field/div/div/div[3]/input") 
	public WebElement zipCode_Form1; 

	@FindBy(how = How.XPATH, using = "//mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id='mat-select-value-25']") 
	public WebElement countyDropDown_Form1; 

	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']") 
	public WebElement countyValue_Form1; 

	@FindBy(how = How.XPATH, using = "//input[@id='lbramailingAddressId_address1']") 
	public WebElement addressLine1_Form2; 

	@FindBy(how = How.XPATH, using = "//input[@id='lbramailingAddressId_city']") 
	public WebElement city_Form2; 
	
	@FindBy(how = How.XPATH, using = "//input[@id='lbramailingAddressId_zip']") 
	public WebElement zipCode_Form2; 

	@FindBy(how = How.XPATH, using = "//mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id='mat-select-value-31']") 
	public WebElement countyDropDown_Form2; 

	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']") 
	public WebElement countyValue2; 

	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'40 PARK AVE')]/../../span/span[@class='mat-radio-outer-circle']") 
	public WebElement uspsAddressRadio; 

	@FindBy(how = How.XPATH, using = "//button[@id='access.continue']") 
	public WebElement continueButton_popUp; 

	@FindBy(how = How.XPATH, using = "//strong[text()='Browse']") 
	public WebElement browserLink; 
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Business Mailing Address']/../following-sibling::app-radio/div/div/mat-radio-group/mat-radio-button/label/span[2]/mat-label[text()='Same as Primary Business Physical Address']/../../span/span[@class='mat-radio-outer-circle']") 
	public WebElement radioButton1; 
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../following-sibling::app-radio/div/div/mat-radio-group/mat-radio-button/label/span[2]/mat-label[text()='Other']/../../span/span[@class='mat-radio-outer-circle']") 
	public WebElement otherRadioButton;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Notice of Potential Charges (LO400) Address']/../following-sibling::app-radio/div/div/mat-radio-group/mat-radio-button/label/span[2]/mat-label[text()='Same as Mailing']/../../span/span[@class='mat-radio-outer-circle']") 
	public WebElement sameMailing;
	
	
	@FindBy(how = How.XPATH, using = "//button[@id='access.continue']") 
	public WebElement verifyContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[.='bmad Address']//following::*[@class='mat-radio-outer-circle'][1]") 
	public WebElement adderessRadioButton1;
	
	@FindBy(how = How.XPATH, using = "//*[.='lbra Address']//following::*[@class='mat-radio-outer-circle'][1]") 
	public WebElement adderessRadioButton2;
	
	@FindBy(how = How.XPATH, using = "//*[.='npca Address']//following::*[@class='mat-radio-outer-circle'][1]") 
	public WebElement adderessRadioButton3;

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"County\")]//following::mat-select[1])[last()-1]") 
	public WebElement countyDropdown2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Products Produced']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea") 
	public WebElement productsName;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Raw Materials Used']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea") 
	public WebElement rawMaterialName;
	
	
	        public void requiredError_genInfo(String xpathParameter) {
		        driver.findElement(By.xpath("(//mat-error[.=\" Required \"][1])[last()]")).isDisplayed();
         	}
	
			public void requiredError_empReg(String xpathParameter) {
				driver.findElement(By.xpath("(//mat-error[.=\" Required \"][1])[last()]")).isDisplayed();
			}		
			
	@FindBy(how = How.XPATH, using = "//input[@id='firstPayrollDtId']") 
	public WebElement clearDateField;	
	
	@FindBy(how = How.XPATH, using = "//input[@id='covEmploymentBeginDtId']") 
	public WebElement clearDateField1;	

	
}
