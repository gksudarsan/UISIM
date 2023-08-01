package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;
import com.ui.utilities.ConstantData;

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
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of Business']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement legalNameOfBusinessTextBox;
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Legal Name of business']")
	public WebElement legalNameOfBusinessTextBoxEEWI_005;
	
	
	
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
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div")
	public WebElement countyDropDown_Form1;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']")
	public WebElement countyValue_Form1;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[3]/div/div/mat-label[text()='Address Line 1 ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement addressLine1_Form2;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[4]/div/div/mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement city_Form2;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[4]/div/div/mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement zipCode_Form2;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-select/div/mat-label/mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id]")
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
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add Another Acquisition')]")
	public WebElement addAnotherAcquisitionLink;
	
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
	
	//-ankan
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][2]")
	public WebElement uspsBusinessAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-inner-circle'][2]")
	public WebElement uspsBusinessAddressInnerCircle;
	
	// SREG-043
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'mat-form-field')]//textarea[contains(@id,'comment')]")
    public WebElement EEWI002CommentsField;
	
	// SREG -084
	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Verify Transfer Failed Rules')])[1]")
    public WebElement verifyTransferlink;
	
	//--- SREG-004 ---
	//---Contact Person for (Location of Books and Records) & (Notice of Potential Charges (LO400)) Address ---
	
	public void enterContactPersonFirstName(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys(Keys.SPACE, Keys.BACK_SPACE);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[1]")).sendKeys(value);
	}
	
	public void enterContactPersonMiddleName(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[2]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[2]")).sendKeys(Keys.SPACE, Keys.BACK_SPACE);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[2]")).sendKeys(value);
	}
	
	public void enterContactPersonLastName(String xpathParameter, String value) {
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[3]")).clear();
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[3]")).sendKeys(Keys.SPACE, Keys.BACK_SPACE);
		driver.findElement(By.xpath("//mat-label[contains(.,'" + xpathParameter + "')]//following::input[3]")).sendKeys(value);
	}
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsBmadAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsLbraAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsNpcaAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bmadmailingAddressId_address1']")
	public WebElement uspsBmadAddressText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bmadmailingAddressId_city']")
	public WebElement uspsBmadCityText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bmadmailingAddressId_zip']")
	public WebElement uspsBmadZipText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bmadcountyId']")
	public WebElement uspsBmadCounty;
	
	@FindBy(how = How.XPATH, using = "//*[@id='lbramailingAddressId_address1']")
	public WebElement uspsLbraAddressText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='lbramailingAddressId_city']")
	public WebElement uspsLbraCityText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='lbramailingAddressId_zip']")
	public WebElement uspsLbraZipText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='lbracountyId']")
	public WebElement uspsLbraCounty;
	
	@FindBy(how = How.XPATH, using =  "//*[@id='firstNameId']")
	public WebElement uspsLbraFirstNameText;
	
	@FindBy(how = How.XPATH, using =  "//*[@id='lastNameId']")
	public WebElement uspsLbraLastNameText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcaAddressId_address1']")
	public WebElement uspsNpcaAddressText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcaAddressId_city']")
	public WebElement uspsNpcaCityText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcaAddressId_zip']")
	public WebElement uspsNpcaZipText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcacountyId']")
	public WebElement uspsNpcaCounty;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcafirstNameId']")
	public WebElement uspsNpcaFirstNameText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='npcalastNameId']")
	public WebElement uspsNpcaLastNameText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'bmad Address_0')]//following::*[@class='mat-radio-container'][1]")
	public WebElement uspsBmadAddressRadio;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'lbra Address_0')]//following::*[@class='mat-radio-container'][1]")
	public WebElement uspsLbraAddressRadio;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'npca Address_0')]//following::*[@class='mat-radio-container'][1]")
	public WebElement uspsNpcaAddressRadio;

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
	
	@FindBy(how = How.XPATH, using = "(//mat-label[contains(text(),'123')]/../../span/span[@class='mat-radio-outer-circle'])[1]")
	public WebElement uspsAddressRadio_123_state;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Indicate your principal')]/../../div/mat-form-field/div/div/div[3]/mat-select")
	public WebElement individualPrinciple;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Other ']")
	public WebElement individualPrinciple_value_other;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Yes ']")
	public WebElement liability_error_Yes;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Are you a subdivision, subsidiary')]/../../div/mat-radio-group/mat-radio-button[1]/label/span[2]/../span/span")
	public WebElement are_You_Subsidiary_Yes;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2022 ']")
	public WebElement firstCalender_Year_Value_2022;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'What is the first calendar quarter and year you employed 4 (four)')]//following::mat-select[1]")
	public WebElement firstCalender_Quater_employed_4;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
	public WebElement firstCalender_Quater_employed_4_value;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'What is the first calendar quarter and year you employed 4 (four)')]//following::mat-select[2]")
	public WebElement firstCalender_Year_employed_4;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' 2023 ']")
	public WebElement firstCalender_Year_employed_4_value_2023;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Choose the option you wish')]/../../div/mat-radio-group/mat-radio-button[1]/label/span[2]/../span/span[@class='mat-radio-outer-circle']")
	public WebElement Choose_Option_Contri_Radio;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Notice of Potential Charges (LO400) Address']/../../app-address/div/div[3]/div/div/mat-label[text()='Address Line 1 ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement notice_potential_AddressLine_1;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Notice of Potential Charges (LO400) Address']/../../app-address/div/div[4]/div/div/mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement notice_potential_City;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Notice of Potential Charges (LO400) Address']/../../app-address/div/div[4]/div/div/mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement notice_potential_Zipcode;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Notice of Potential Charges (LO400) Address']/../../app-select/div/mat-label/mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id]")
	public WebElement notice_potential_county;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Contact Person for Notice of Potential Charges (LO')]/../../../../following-sibling::app-textbox/div/div/mat-label//mat-label[text()='First Name']/../following-sibling::div/mat-form-field/div/div/div[3]/input")
	public WebElement notice_potential_firstName;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Contact Person for Notice of Potential Charges (LO')]/../../../../following-sibling::app-textbox/div/div/mat-label//mat-label[text()='Last Name']/../following-sibling::div/mat-form-field/div/div/div[3]/input")
	public WebElement notice_potential_LastName;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of Business']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement legal_Name_Business_Input;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()=' Date Payroll Withheld is invalid']")
	public WebElement date_Payroll_Error_Message;
	
	@FindBy(how = How.XPATH, using = "//u[text()='Review Employer Type']")
	public WebElement review_employer_My_Q;
	
	@FindBy(how = How.XPATH, using = "//u[text()='Obtain Bond Task']")
	public WebElement obtain_bond_task_My_Q;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[3]/div/div/mat-label[text()='Address Line 1 ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement location_Of_Book_AddresLine1;

	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[4]/div/div/mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement location_Of_Book_City;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-address/div/div[4]/div/div/mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement location_Of_Book_ZipCode;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Location of Books and Records']/../../app-select/div/mat-label/mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id]")
	public WebElement location_Of_Book_County;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']")
	public WebElement albany_County_Value;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Agent (C/O) address']/../following-sibling::div/app-address/div/div[3]/div/div/mat-label[text()='Address Line 1 ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement agent_CO_AddresLine1;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Agent (C/O) address']/../following-sibling::div/app-address/div/div[4]/div/div/mat-label[text()='City ']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement agent_CO_City;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Agent (C/O) address']/../following-sibling::div/app-address/div/div[4]/div/div/mat-label[text()='Zip Code']/following-sibling::mat-form-field/div/div/div[3]/input")
	public WebElement agent_CO_ZipCode;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Agent (C/O) address']/../following-sibling::div/app-select/div/mat-label/mat-label[text()='County']/../following-sibling::div/mat-form-field/div/div/div/following-sibling::div[2]/mat-select/div/div[@id]")
	public WebElement agent_CO_County;
	
	//Palak	

@FindBy(how = How.XPATH, using = "//*[@id='commentid']")
public WebElement commentid;

@FindBy(how = How.XPATH, using = "//*[@id='comment']")
public WebElement comment;

@FindBy(how = How.XPATH, using = "//*[@id='Comment']")
public WebElement Comment;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bmadmailingAddressId_attention']")
	public WebElement uspsBmadAddressAttention;
	@FindBy(how = How.XPATH, using = "//*[@id='AccountMaintenance']")
	public WebElement Account_Maintenance;

@FindBy(how = How.XPATH, using = "//*[@id='MaintainRateUpdateContributionRate']")
public WebElement Employer_Rate;

@FindBy(how = How.XPATH, using = "//*[@id='AccountMaintenanceMaintainRate']")
public WebElement Maintain_Rate;

@FindBy(how = How.XPATH, using = "/*[@id=\"eanBeanId\"]")
public WebElement Emp_RegiNo;

@FindBy(how = How.XPATH, using = "//*[text()='Account Maintenance'][1]")
public WebElement Account_Maintenance1;


@FindBy(how = How.XPATH, using = "//*[@id='main-container']")
public WebElement UpdateContributionRate;

// --- ankan

@FindBy(how = How.XPATH, using = "//*[@id='principalProducts']")
public WebElement principalProducts_SREG008;

@FindBy(how = How.XPATH, using = "//*[@id='principalRawMaterial']")
public WebElement principalRawMaterial_SREG008;

@FindBy(how = How.XPATH, using = "//*[@id='agadAddressId_careOf']")
public WebElement uspsAgadCareOf;

@FindBy(how = How.XPATH, using = "//*[@id='agadAddressId_address1']")
public WebElement uspsAgadAddressText;

@FindBy(how = How.XPATH, using = "//*[@id='agadAddressId_city']")
public WebElement uspsAgadCityText;

@FindBy(how = How.XPATH, using = "//*[@id='agadAddressId_zip']")
public WebElement uspsAgadZipText;

@FindBy(how = How.XPATH, using = "//*[@id='agadcountyId']")
public WebElement uspsAgadCounty;

@FindBy(how = How.XPATH, using =  "//*[@id='agadfirstNameId']")
public WebElement uspsAgadFirstNameText;

@FindBy(how = How.XPATH, using =  "//*[@id='agadlastNameId']")
public WebElement uspsAgadLastNameText;

@FindBy(how = How.XPATH, using =  "//*[@id='tradeNameId']")
public WebElement tradeNameId_SREG011;

@FindBy(how = How.XPATH, using =  "//*[@id='preAddressId_address1']")
public WebElement address1_SREG011;

@FindBy(how = How.XPATH, using =  "//*[@id='preAddressId_city']")
public WebElement city_SREG011;

@FindBy(how = How.XPATH, using =  "//*[@id='preAddressId_zip']")
public WebElement zip_SREG011;

@FindBy(how = How.XPATH, using =  "//*[@id='productSoldOrRendered']")
public WebElement productSoldOrRendered_SREG008;

@FindBy(how = How.XPATH, using = "//*[@id='typeOfEstablishment']")
public WebElement typeOfEstablishment_SREG008;

@FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
public WebElement firstCalender_Quater_Value_1;

@FindBy(how = How.XPATH, using = "//input[@id='qtrYearTotalCashWgsIdyear']")
public WebElement firstCalender_YearField;

@FindBy(how = How.XPATH, using = "//input[@id='qtrYearEmployedIdyear']")
public WebElement whatFirstCalender_YearField;

@FindBy(how = How.XPATH, using = "//*[@id='commentId']")
public WebElement commentId_EEWI002;


@FindBy(how = How.XPATH, using = "//*[@id='legalEntityNameId']")
public WebElement legalEntityNameId;

@FindBy(how = How.XPATH, using = "//*[@id='commentId']")
public WebElement commentId;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Registration']/../following-sibling::div/div/button")
public WebElement employer_Register_Edit_Button;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Uploaded Documents']/../following-sibling::div/div/button")
public WebElement upload_Document_Edit_Button;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Contact Details']/../following-sibling::div/div/button")
public WebElement Contact_Detail_Edit_Button;

@FindBy(how = How.XPATH, using = "//mat-label[text()='General Information']/../following-sibling::div/div/button")
public WebElement general_Info_Edit_Button;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Employer Entity Information']/../following-sibling::div/div/button")
public WebElement employer_entity_Edit_Button;

@FindBy(how = How.XPATH, using = "//u[text()='Short Form Registration Review']")
public WebElement shor_Form_Registration_Review_Link;

@FindBy(how = How.XPATH, using = "//a[text()='Short Form Registration']")
public WebElement shor_Form_Registration_Link_SREG_818;

@FindBy(how = How.XPATH, using = "//mat-label[text()='FEIN']/following-sibling::mat-label")
public WebElement FEIN_Value_Text_SREG_051;

@FindBy(how = How.XPATH, using = "//span[text()='Inquiry']")
public WebElement inquiry_dropDown_Menu;

@FindBy(how = How.XPATH, using = "//span[text()='Contribution Inquiry']")
public WebElement Contribution_dropDown_Menu;

@FindBy(how = How.XPATH, using = "//u[text()='Add Member/Managing Member Details']")
public WebElement add_Member_Managing_Member_Detail_Link;

@FindBy(how = How.XPATH, using = "//*[@id='typeOfLegalEntityId']")
public WebElement typeOfLegalEntityId;

@FindBy(how = How.XPATH, using = "//span[text()=' Individual Ownership ']")
public WebElement individualOwnership;



@FindBy(how = How.XPATH, using =  "//*[@id='eanBeanId']")
public WebElement eanBeanId;

@FindBy(how = How.XPATH, using =  "//*[@id='employerFeinId']")
public WebElement employerFeinId;

@FindBy(how = How.XPATH, using =  "//*[@id='totalRevenue']")
public WebElement totalRevenue;
//shubhanshi
@FindBy(how = How.XPATH, using = "//*[.='Association End Date']//following::*[@id='dataTableId'][1]//following::input[1]")
public WebElement Select_date_from_calender;

@FindBy(how = How.XPATH, using = "//*[@id='eanBeanId']")
public WebElement EAN;

@FindBy(how = How.XPATH, using = "//mat-label[text()='usps address']/ancestor::app-row-labels/following-sibling::app-radio//mat-radio-button[2]//span/span[@class='mat-radio-outer-circle']")
public WebElement uspsCommonButton;

@FindBy(how = How.XPATH, using = "//mat-label[text()='usps address']/ancestor::app-row-labels/following-sibling::app-radio[2]//mat-radio-button[2]//span/span[@class='mat-radio-outer-circle']")
public WebElement uspsCommonButton2;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Business Physical Address Details']/../following-sibling::div/div/button")
public WebElement business_physical_Edit_Button;

@FindBy(how = How.XPATH, using = "//mat-label[text()='DOL Failed Reasons']/following-sibling::mat-label[text()='"+ConstantData.EE_02_004_DOL_FAILED_REASON+"']")
public WebElement dolFailedReasonText;

@FindBy(how = How.XPATH, using = "//mat-label[text()='DOL Failed Reasons']/following-sibling::mat-label[text()='"+ConstantData.EE_02_004_DTF_FAILED_REASON+"']")
public WebElement dtfFailedReasonText;

@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Contact Person for Agent (C/O) Address')]/../../../../following-sibling::app-textbox/div/div/mat-label//mat-label[text()='First Name']/../following-sibling::div/mat-form-field/div/div/div[3]/input")
public WebElement agent_Co_First_Name;

@FindBy(how = How.XPATH, using = "//mat-label[contains(text(),'Contact Person for Agent (C/O) Address')]/../../../../following-sibling::app-textbox/div/div/mat-label//mat-label[text()='Last Name']/../following-sibling::div/mat-form-field/div/div/div[3]/input")
public WebElement agent_Co_Last_Name;

@FindBy(how = How.XPATH, using = "//button/span/span[text()='Menu']")
public WebElement menuButtonHomepage;

@FindBy(how = How.XPATH, using = "//mat-label[text()='usps address']/ancestor::app-row-labels/following-sibling::app-radio[3]//mat-radio-button[2]//span/span[@class='mat-radio-outer-circle']")
public WebElement uspsCommonButton3;

@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
public WebElement menu;

@FindBy(how = How.XPATH, using = "//*[contains(@id, 'business Address_1')]//following::*[@class='mat-radio-inner-circle'][1]")
public WebElement uspsBusiAddress1InnerRadio;

@FindBy(how = How.XPATH, using = "//*[contains(@id, 'business')]//following::*[@class='mat-radio-outer-circle'][1]")
public WebElement uspsBusiAddress1OuterRadio;

@FindBy(how = How.XPATH, using = "//*[contains(@id, 'Address_1')]//following::*[@class='mat-radio-inner-circle'][1]")
public WebElement uspsAddress1InnerRadio;

@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-container'][2]")
public WebElement verifyAddressUspsAddress;

@FindBy(how = How.XPATH, using = "//*[@id='AccountMaintenanceEmployerAccountMaintenance']")
public WebElement employerAccountMaintanceMenu;

//--Workitem

@FindBy(how = How.XPATH, using =  "//*[@id='predecessorErn']")
public WebElement predecessorErn;

@FindBy(how = How.XPATH, using = "//*[@id='predecessorFein']")
public WebElement predecessorFein;

@FindBy(how = How.XPATH, using = "//*[.='bmad Address']//following::*[@class='mat-radio-outer-circle'][1]")
public WebElement bmad_Address;

@FindBy(how = How.XPATH, using = "//*[.='bmad Address']//following::*[@class='mat-radio-inner-circle'][1]")
public WebElement bmad_AddressInnerCircle;

@FindBy(how = How.XPATH, using = "//*[.='lbra Address']//following::*[@class='mat-radio-outer-circle'][1]")
public WebElement lbra_Address;

@FindBy(how = How.XPATH, using = "//*[.='lbra Address']//following::*[@class='mat-radio-inner-circle'][1]")
public WebElement lbra_AddressInnerCircle;

@FindBy(how = How.XPATH, using = "//*[.='npca Address']//following::*[@class='mat-radio-outer-circle'][1]")
public WebElement npca_Address;

@FindBy(how = How.XPATH, using = "//*[.='npca Address']//following::*[@class='mat-radio-inner-circle'][1]")
public WebElement npca_AddressInnerCircle;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Type of Establishment']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Type_of_Establishment;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Product Sold or Service Rendered']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Principal_Product_Sold_or_Service_Rendered;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Percent of Total Revenue']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Percent_of_Total_Revenue;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Products Produced']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Principal_Products_Produced;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Raw Materials Used']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Principal_Raw_Materials_Used;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of Business']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Legal_Name_of_Business;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Reason for Update CSR Remarks']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Reason_for_Update_CSR_Remarks;

@FindBy(how = How.XPATH, using = "//*[@id='Contribution/WageMaintenanceModifyPaymentDate']")
public WebElement Menu_Contribution_WageMaintenanceModifyPaymentDate;


@FindBy(how = How.XPATH, using = "//*[@id='modifyPaymentDateId']")
public WebElement modifyPaymentDateId;


@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-1']")
public WebElement Interest;

@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-2']")
public WebElement Rating_Timeliness;

@FindBy(how = How.XPATH, using = "//*[@id='mat-checkbox-3']")
public WebElement Slop_Over;

@FindBy(how = How.XPATH, using = "//*[@id='mat-input-4']")
public WebElement Comments_Box;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Submitter Comments may be entered below.']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Submitter_Comments_may_be_entered_below;

@FindBy(how = How.XPATH, using = "//mat-label[text()='Legal Name of business']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
public WebElement Legal_Name_of_business;

@FindBy(how = How.XPATH, using = "//*[@id='comments']//following::span[@class='mat-form-field-label-wrapper ng-tns-c126-198']")
public WebElement Comments;

@FindBy(how = How.XPATH, using = "//div[@class='mat-form-field-infix ng-tns-c126-251']//preceding::*[contains(@id, 'comments')]")
public WebElement transferDetailsTable_checkbox;

@FindBy(how = How.XPATH, using = "//u[contains(.,'')][1]")
public WebElement transferDetailsTable_Dateoftransfer_hyperlink;

@FindBy(how = How.XPATH, using = "//*[.='Beginning Liability Date']//following::mat-label[text()='Quarter ']/../mat-form-field/div/div/div[3]/mat-select[1]")
public WebElement beginningLiableDateQuarter;

@FindBy(how = How.XPATH, using = "(//*[.='Suspension Date']//following::mat-label[text()='Quarter ']/../mat-form-field/div/div/div[3]/mat-select)[2]")
public WebElement suspensionDateQuarter;

@FindBy(how = How.XPATH, using = "(//mat-label[text()='Year ']/../mat-form-field/div/div/div[3]/mat-select)[2]")
public WebElement yearDropDown2;

}

