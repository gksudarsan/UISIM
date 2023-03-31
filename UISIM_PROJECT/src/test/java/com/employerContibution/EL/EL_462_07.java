package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_462_07 extends TestBase{

	@Test(priority=1, description = "EL.462.07 - Verify CSR can update PEO Group members and clients associated to a PEO member.",groups = {"Regression"})
	public void EL_462_07() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest("EL.462.07 - Verify CSR can update PEO Group members and clients associated to a PEO member.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(2000);

		//Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='PNDN' AND TYPE_OF_REQUEST='PEOGR' AND EMPLOYER_REGISTRATION_NUMBER IS NOT NULL ORDER BY UPDATED_TS DESC", "PEO_NAME");
		//String PEOName =databaseResults.get("PEO_NAME");
		//commonFuntions.enterTextboxContains("PEO Name",PEOName);
		
		commonFuntions.enterTextboxContains("PEO Name", "Groupsoleprop13");
		commonFuntions.screenShot("enter peo name", "Pass", "Peo Name");
		//System.out.println("The PeoName is: "+PEOName);
		commonFuntions.clickButtonContains("Search ");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");sleep();
		commonFuntions.screenShot("Search PEO", "Pass", "PEO Search");
		commonFuntions.clickButtonContains("Continue");sleep();
		//commonFuntions.selectDropdown("PEO Status ", " Issued ");
		commonFuntions.selectDropdown("PEO Conversion", "PEO Group to PEO Individual");
		commonFuntions.selectRadio("Select");sleep();
		commonFuntions.screenShot("ManageGroupPEO", "Pass", "Manage PEO Group");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","peoMemberAddressDetails"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.selectDropdown("PEO Member Status", " Issued ");
		commonFuntions.screenShot("PEO Member Deatails", "Pass", "Manage PEO Details ");sleep();
		commonFuntions.clickButtonContains("UPDATE ");
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();	    
		}
		catch(Exception e) {
			System.out.println("Address pop up not showing");
		}
		
		Thread.sleep(2000);
		commonFuntions.screenShot("Confirmation", "Pass", "Confirmation Message");
		sleep();
		
		// test case ends here
		
		}
	}