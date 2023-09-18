package com.ui.pages;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.locators.employerManagementLocators;

import stepDefinitions.commonStepDefinitions;


public class employerManagement extends TestBase
{

	public employerManagement()
	{
		
	}
	
	public employerManagement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	commonStepDefinitions commonFunctions = new commonStepDefinitions();
	employerManagementLocators em = new employerManagementLocators();
	commonStepDefinitions cf= new commonStepDefinitions();
	public void updateAddress(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.editButtonPriBusinessPhyLoc().click();
		cf.screenShot("Menu","Pass","");
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("");
		em.select().click();
		em.selectCorres().click();
		em.select().click();
		em.selectCorres().click();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");



















	}
	
	
	public void updateAddress() throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();


		cf.screenShot("Menu","Pass","");
		em.editButtonBusinessPhyLoc().click();
		cf.screenShot("Menu","Pass","");
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("");
		em.select().click();
		em.selectCorres().click();
		em.select().click();
		em.selectCorres().click();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		String msg =   em.successMsgSuc002().getText();
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");


	}

	public void Inquery(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

//		 em.menuButton().click();
//		 em.AccountMaintenance().click();
//		 em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.businessActivity().click();
		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.businessActivity().click();sleep();
		cf.screenShot("Menu","Pass","");
	}
	public void addClientDatailsManually(String EAN) throws Exception
	{		
		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("updating address............");
		em.addClientDetails().click();
		cf.screenShot("Menu","Pass","");
		em.enterERN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.search().click();
		cf.screenShot("Menu","Pass","");



	}
	public void Inquery_fein(String FEIN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.FEIN().sendKeys(FEIN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		
		em.businessActivity().click();
		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.FEIN().sendKeys(FEIN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.businessActivity().click();sleep();
		cf.screenShot("Menu","Pass","");
	}

	public void updateAddressLocationOfBooks(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		try {
		em.editButtonlocationofbooksAdd().click();
		}
		catch (Exception e)
		{
			em.editButtonlocationofbooksEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");





	
	}

	
	
	
	
	public void updateAddressNoticeofPotentialCharge(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		try {
		em.editButtonlocationofbooksAdd().click();
		}
		catch (Exception e)
		{
			em.editButtonlocationofbooksEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");sleep();
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		//commonFunctions.selectDropdownEquals("Status", " Active ");sleep();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();sleep();
		try {
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		}
		catch (Exception e )
		{
			
		}
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");
	}
	
	
	public void empupdateAddressLocationOfBooks(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		//em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		try {
		em.editButtonlocationofbooksAdd().click();
		}
		catch (Exception e)
		{
			em.editButtonlocationofbooksEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		//commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		//commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
	//	commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		//commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		//commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		//em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are save successfully");





	
	}

	
	
	
	
	public void empupdateAddressNoticeofPotentialCharge(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		try {
		em.editButtonlocationofbooksAdd().click();
		}
		catch (Exception e)
		{
			em.editButtonlocationofbooksEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");sleep();
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		//commonFunctions.selectDropdownEquals("Status", " Active ");sleep();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();sleep();
		try {
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		}
		catch (Exception e )
		{
			
		}
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");
	}

	public void empupdateAddressAgent(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		cf.screenShot("Menu","Pass","");
		try {
		em.ButtonAgentAddressAdd().click();
		}
		catch (Exception e)
		{
			em.ButtonAgentAddressEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		//commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		//commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
	//	commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		//commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		//commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		//em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are save successfully");

	}

	public void updateAddressNoticeOfExpRating(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		try {
		em.NoticeOfExpRatingAdd().click();
		}
		catch (Exception e)
		{
			em.NoticeOfExpRatingEdit().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");

	}
	
	public void AditionalPhyLocationAddress(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
	
		em.AditionalBusinessPhyLocation().click();
		
	
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.selectOthers().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		//Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");

	}
	
	public void AppealMailAddress(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		System.out.println("updating address............");
		sleep(4000);
		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		sleep();
		cf.screenShot("Menu","Pass","");
		sleep();
		em.continueButton().click();
		sleep();
		cf.screenShot("Menu","Pass","");
		sleep();
		try {
		em.AppealMailAddress().click();
		}
		catch (Exception e)
		{
			em.AppealMailAddress().click();
		}
		cf.screenShot("Menu","Pass","");
		//em.continueButton().click();
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		commonFuntions.selectDropdown("County", " Albany ");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("new york");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		commonFunctions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "No");
		commonFuntions.selectDropdown("Source", "Correspondence/Email");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Source Type", "Correspondence/Email");
		
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");

	}	
	

	//-ankan
	@FindBy(how = How.XPATH, using = "//*[@id='eanBeanId']")
	public WebElement eanBeanId_SREG027;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Maintenance']//following::span[@id='AccountMaintenanceEmployerAccountMaintenance'][2]")
	public WebElement employerAccountMaintanceMenu;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsBusinessAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='usps address']//following::*[@class='mat-radio-inner-circle'][1]")
	public WebElement uspsBusinessAddressInnerCircle;
	
	@FindBy(how = How.XPATH, using = "//button[@id='access.continue']")
	public WebElement continueButton_popUp;
	
	@FindBy(how = How.XPATH, using = "//*[@id='sourceId']")
	public WebElement sourceId_SREG705;

	@FindBy(how = How.XPATH, using = "//span[text()=' NYS-100 (paper) ']")
	public WebElement nys100Paper_SREG705;
	
	@FindBy(how = How.XPATH, using = "//*[.='Source']//following::*[@id='sourceId']")
	public WebElement sourceId_SREG435;

	@FindBy(how = How.XPATH, using = "//span[text()=' IA602 ']")
	public WebElement IA602_SREG435;
	
	@FindBy(how = How.XPATH, using = "//*[.='Source Type']//following::*[@id='sourceTypeId']")
	public WebElement sourceTypeId_SREG435;

	@FindBy(how = How.XPATH, using = "//span[text()=' Coverage Exception ']")
	public WebElement CoverageException_SREG435;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Cash Wages ']")
	public WebElement CashWages_SREG435;
	
	@FindBy(how = How.XPATH, using = "//*[.='Suspension Date']//following::*[@id='mat-select-4']")
	public WebElement suspensionDateQtrDropdown_SREG435;
	
	@FindBy(how = How.XPATH, using = "//*[.='Suspension Date']//following::*[@id='mat-select-value-7']")
	public WebElement suspensionDateYearDropdown_SREG435;
	
	@FindBy(how = How.XPATH, using = "//*[@id='remarksId']")
	public WebElement remarksId_SREG435;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' L&D SUTA ']")
	public WebElement ldSuta_SREG435;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement correspondence_SREG435;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentId']")
	public WebElement commentId;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' NYS-100 ']")
	public WebElement nys100;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
    public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//*[@id='UpdateNAICS/CountyCodeUpdateNAICS/CountyCode']")
	public WebElement updateNAICS;
	
	@FindBy(how = How.XPATH, using = "//img[@alt='helpicon']/@src")
	public WebElement helpIcon;
	
	@FindBy(how = How.XPATH, using = "//*[@id='accountCountyCodeId']")
	public WebElement accountCountyCodeId;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ownerShipId']")
	public WebElement ownerShipIdDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(), ' State Gov')]")
	public WebElement stateGovtOwnCode;
	
	@FindBy(how = How.XPATH, using = "//*[.='Additional Address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement additionalAddressOuterRdio;
	
	@FindBy(how = How.XPATH, using = "//*[.='Additional Address']//following::*[@class='mat-radio-inner-circle'][1]")
	public WebElement additionalAddressInnerRdio;
	
	@FindBy(how = How.XPATH, using = "//button[@id='CIN-999access.continue']")
	public WebElement cin999continueButton;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Federal Employer Identification Number (FEIN)']")
	public WebElement feinEEWI005;
	
}