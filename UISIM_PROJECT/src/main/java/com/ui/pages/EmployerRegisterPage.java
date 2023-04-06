package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EmployerRegisterPage extends TestBase {
	
	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public EmployerRegisterPage(WebDriver driver) {
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
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement addressLine1_Form2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3][@class='mat-form-field-infix ng-tns-c138-170']/input")
	public WebElement city_Form2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3][@class='mat-form-field-infix ng-tns-c138-171']/input")
	public WebElement zipCode_Form2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id='mat-select-value-31']")
	public WebElement countyDropDown_Form2;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']")
	public WebElement countyValue_Form2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'40 PARK AVE')]/../../span/span[@class='mat-radio-outer-circle']")
	public WebElement uspsAddressRadio;
	
	@FindBy(how = How.XPATH, using = "//button[@id='access.continue']")
	public WebElement continueButton_popUp;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Comment']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement commentBox_MyQ;

	@FindBy(how = How.XPATH, using = "//strong[text()='Browse']")
	public WebElement browserLink;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Is your entity a legally')]/../following-sibling::div/mat-radio-group/mat-radio-button/label/span[2][text()='No ']/../span/span")
	public WebElement iSyourEntityQuestion_No;
	
	
	
}

