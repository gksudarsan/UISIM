package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsBusinessAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-inner-circle'][1]")
	public WebElement uspsBusinessAddressInnerCircle;
	
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
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'npca Address_0')]//following::*[@class='mat-radio-container'][1]")
	public WebElement uspsNpcaAddressRadio;

}

