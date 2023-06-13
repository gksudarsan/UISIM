package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

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
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(2000);

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD'", "PEO_NAME");
		String PEOName =databaseResults.get("PEO_NAME");
		commonFuntions.enterTextboxContains("PEO Name",PEOName);
		System.out.println(PEOName);
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		commonFuntions.screenShot("enter peo name", "Pass", "Peo Name");
		commonFuntions.clickButtonContains("Search ");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("Search PEO", "Pass", "PEO Search");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectDropdown("PEO Conversion", "PEO Group to PEO Individual");
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("ManageGroupPEO", "Pass", "Manage PEO Group");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","peoMemberAddressDetails"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code ", "23867");
		commonFuntions.enterTextboxContains(" Phone Number ", "8698347683");
		commonFuntions.enterTextboxContains("Business Email Address", "Test123@gmail.com");
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
		
		}
	}