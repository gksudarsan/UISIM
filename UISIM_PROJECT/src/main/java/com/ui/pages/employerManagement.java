package com.ui.pages;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.testng.Assert;

import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.locators.employerManagementLocators;

import stepDefinitions.commonStepDefinitions;


public class employerManagement extends TestBase
{

	commonStepDefinitions commonFunctions = new commonStepDefinitions();
	employerManagementLocators em = new employerManagementLocators();
	commonStepDefinitions cf= new commonStepDefinitions();
	public void updateAddress(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

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

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
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
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");





	
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
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");

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
	
	
	
	
	
	
}