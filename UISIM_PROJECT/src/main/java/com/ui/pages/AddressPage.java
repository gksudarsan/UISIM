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

	@FindBy(how = How.XPATH, using = "//input[@id='bmadmailingAddressId_address1']")
	public WebElement addressLine1_Form1;

	@FindBy(how = How.XPATH, using = "//input[@id='bmadmailingAddressId_city']")
	public WebElement city_Form1;

	@FindBy(how = How.XPATH, using = "//input[@id='bmadmailingAddressId_zip']")
	public WebElement zipCode_Form1;

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"County\")]//following::mat-select[1])[last()-2]")
	public WebElement countyDropDown_Form1;

	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']")
	public WebElement countyValue1;

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

	@FindBy(how = How.XPATH, using = "//input[@id='npcaAddressId_address1']")
	public WebElement addressLine1_Form3;

	@FindBy(how = How.XPATH, using = "//input[@id='npcaAddressId_city']")
	public WebElement city_Form3;

	@FindBy(how = How.XPATH, using = "//input[@id='npcaAddressId_zip']")
	public WebElement zipCode_Form3;

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"County\")]//following::mat-select[1])[last()]")
	public WebElement countyDropDown_Form3;

	@FindBy(how = How.XPATH, using = "//span[text()=' Albany ']")
	public WebElement countyValue3;

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

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"County\")]//following::mat-select[1])[last()-2]")
	public WebElement countyDropdown1;

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,\"County\")]//following::mat-select[1])[last()-1]")
	public WebElement countyDropdown2;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Products Produced']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement productsName;

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Principal Raw Materials Used']/../following-sibling::div/mat-form-field/div/div/div[3]/textarea")
	public WebElement rawMaterialName;

	public void requiredError_genInfo() {
		driver.findElement(By.xpath("(//mat-error[.=\" Required\"][1])[last()]")).isDisplayed();
	}

	public void requiredError_empReg() {
		driver.findElement(By.xpath("(//mat-error[.=\" Required \"][1])[last()]")).isDisplayed();
	}

	@FindBy(how = How.XPATH, using = "//input[@id='firstPayrollDtId']")
	public WebElement clearDateField;

	@FindBy(how = How.XPATH, using = "//input[@id='covEmploymentBeginDtId']")
	public WebElement clearDateField1;

	@FindBy(how = How.XPATH, using = "//input[@id='firstNameId']")
	public WebElement firstName_locationOfBooksAndrecords;

	@FindBy(how = How.XPATH, using = "//input[@id='lastNameId']")
	public WebElement lastName_locationOfBooksAndrecords;

	@FindBy(how = How.XPATH, using = "//input[@id='npcafirstNameId']")
	public WebElement firstName_noticeOfPotentialCharges;

	@FindBy(how = How.XPATH, using = "//input[@id='npcalastNameId']")
	public WebElement lastName_noticeOfPotentialCharges;

	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,\"Indicate your principal activity or farm production that produces the greatest gross sales.\")]//following::mat-select[1]")
	public WebElement selectDropdown;

	@FindBy(how = How.XPATH, using = "(//*[contains(.,\" Other \")][@class='mat-option-text'])[last()]")
	public WebElement selectOtherDropDown;

	public void clickOnLink(String xpathParameter) {
		driver.findElement(By.xpath("//a[contains(.,'" + xpathParameter + "')][1]")).click();

	}

	@FindBy(how = How.XPATH, using = "//textarea[@id='tradeNameId']")
	public WebElement legalNameOfBussiness;

	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][2]")
	public WebElement uspsAddress;

	@FindBy(how = How.XPATH, using = "(//u[contains(.,\"Edit\")][1])[last()]")
	public WebElement editLink;

	@FindBy(how = How.XPATH, using = "//span[text()=' Yes ']")
	public WebElement liability_error_Yes;

	@FindBy(how = How.XPATH, using = "//textarea[@id=\"commentId\"]")
	public WebElement commentField;

	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,\"Notification date of Transfer\")]//following::input[1]")
	public WebElement notificationDateTransfer;

	@FindBy(how = How.XPATH, using = "//input[@id=\"comment\"]")
	public WebElement commentBox;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id=\"comment\"]")
	public WebElement comment;

	@FindBy(how = How.XPATH, using = "(//mat-label[contains(.,'Employer Registration Number')]//following::input[1])[last()]")
	public WebElement EmployerRegNumber;

	@FindBy(how = How.XPATH, using = "//*[.='Employer Registration Number']//following::mat-label[1]")
	public WebElement getERN;

	@FindBy(how = How.XPATH, using = "//*[.='PEO ID']//following::mat-label[1]")
	public WebElement getPEOId;

	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Trade Name or Doing Business As (DBA)')]//following::input[1]")
	public WebElement DBA;

	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Previous FEIN')]//following::input[1]")
	public WebElement previousFein;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'mat-input')]")
	public WebElement searchByFilter;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'First Name')]//following::input[1]")
	public WebElement lastName;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
    public WebElement menu;
}
