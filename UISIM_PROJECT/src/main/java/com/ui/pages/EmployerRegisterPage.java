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
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input[@id='bmadmailingAddressId_address1']")
	public WebElement addressLine1_Form1;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input[@id='lbramailingAddressId_address1']")
	public WebElement addressLine1_Form1_SREG_004;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input[@id='physicalAddressId_address1']")
	public WebElement addressLine1SREG_008;
	
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
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3]/input[@id='lbramailingAddressId_city']")
	public WebElement city_Form2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3]/input[@id='lbramailingAddressId_zip']")
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
	
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'123 STATE ST')]/../../span/span[@class='mat-radio-outer-circle']")
	public WebElement uspsAddress;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Quarter ']/../mat-form-field/div/div/div[3]/mat-select")
	public WebElement quaterDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
	public WebElement quaterValue;
	 
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Year ']/../mat-form-field/div/div/div[3]/mat-select")
	public WebElement yearDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement yearValue;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2022 ']")
	public WebElement yearValue_2022;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' Add Another Business Location ']")
	public WebElement addAnotherBusinessLink;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'First calendar quarter and year')]//following::mat-select[1]")
	public WebElement firstCalender_Quater;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 4 ']")
	public WebElement firstCalender_Quater_Value;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
	public WebElement firstCalender_Quater_Value_2;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'First calendar quarter and year')]//following::mat-select[2]")
	public WebElement firstCalender_Year;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2021 ']")
	public WebElement firstCalender_Year_Value;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2024 ']")
	public WebElement firstCalender_Year_Value_2024;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'What is the first calendar quarter')]//following::mat-select[1]")
	public WebElement What_firstCalender_Quater;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'What is the first calendar quarter')]//following::mat-select[2]")
	public WebElement What_firstCalender_Year;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Do persons work for you whom')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2][text()='No ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement DO_Person_Work_radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Do persons work for you whom')]/../../div/mat-radio-group/mat-radio-button[1]/label/span[2][text()='Yes ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement DO_Person_Work_Yes_radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'If you are not liable under')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2][text()='No ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement If_Not_Lible_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'If you are not liable under')]/../../div/mat-radio-group/mat-radio-button[1]/label/span[2][text()='Yes ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement If_Not_Lible_Yes_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Does this organization have')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2][text()='No ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement DOes_Org_Have_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Does this organization have')]/../../div/mat-radio-group/mat-radio-button[1]/label/span[2][text()='Yes ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement DOes_Org_Have_Yes_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Choose the option you wish to')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2][text()='No ']/../span/span[@class='mat-radio-outer-circle']")
	public WebElement Choose_Option_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Choose the option you wish')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2]/../span/span[@class='mat-radio-outer-circle']")
	public WebElement Choose_Option_Reim_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'If Reimbursable, has a copy')]/../../div/mat-radio-group/mat-radio-button[2]/label/span[2]/../span/span[@class='mat-radio-outer-circle']")
	public WebElement Is_Reimbursable_Radio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement legalTextInput;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement firstCalender_Year_Value_2023;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id='mat-select-value-35']")
	public WebElement countyDropDown_Form1_SREG_004;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input[@id='npcaAddressId_address1']]")
	public WebElement addresLine1_Form2_SREG_004;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Address Line 1']/following-sibling::mat-form-field/div/div/div[3]/input[@id='npcaAddressId_address1']")
	public WebElement addresLine1_Form3_SREG_004;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'20 COOPER SQUARE')]/../../span/span[@class='mat-radio-outer-circle']")
	public WebElement uspsAddressRadio_20_Cooper;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'20 COOPER SQ')]/../../span/span[@class='mat-radio-outer-circle']")
	public WebElement uspsAddressRadio_20_square;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Indicate your principal')]/../../div/mat-form-field/div/div/div[3]/mat-select")
	public WebElement individualPrinciple;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Other ']")
	public WebElement individualPrinciple_value_other;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Yes ']")
	public WebElement liability_error_Yes;
	
}

