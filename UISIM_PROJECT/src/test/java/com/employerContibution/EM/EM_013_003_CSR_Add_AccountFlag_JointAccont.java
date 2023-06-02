package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_013_003_CSR_Add_AccountFlag_JointAccont extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to adding account flags information for flag type 'Joint Account (JA)'", groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EM_013_003() throws Exception {
		
		test = report.createTest("Verify CSR is able to adding account flags information for flag type 'Joint Account (JA)'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManagement = new employerManagement(driver);
		
		//GET method
		// valid EAN from T_EMPLOYER_ACCOUNT
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC LIMIT 10",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Maintain Account Flag Information");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Maintain Account Flag Information");
		commonFunction.clickMenu("Maintain Account Flag Information");
		
		// --- SREG-543 ---
		sleep(2000);
		commonFunction.screenShot("EM013003", "Pass", "Successful launch to Maintain Account Flag Information – Enter ERN(SREG-543) Page");
		commonFunction.enterTextbox("Employer Registration Number", "");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EM013003", "Pass", "Can not proceed with blank ERN");
		sleep();
		commonFunction.enterTextbox("Employer Registration Number", "9222222");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EM013003", "Pass", "Can not proceed with invalid ERN");
		sleep();
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //8071257
		commonFunction.screenShot("EM013003", "Pass", "Entered valid ERN and continue");
		commonFunction.clickButton("Continue ");
		sleep();
		
		// --- SREG-544 ---
		commonFunction.screenShot("EM013003", "Pass", "Successful launch to Account Flag Information Details(SREG-544) Page");
		commonFunction.selectDropdown("Flag Type", " Joint Account (JA) ");
		commonFunction.enterPastDate("Date", 20);
		//empManagement.detailsId_SREG544.sendKeys("Changing to JA");
		commonFunction.screenShot("EM013003", "Pass", "Cannot enter previous date");
		commonFunction.clickButtonContains(" Add");
		
		sleep();
		commonFunction.enterTextboxContains("Date", "");
		commonFunction.enterPastDate("Date", 0);
		commonFunction.screenShot("EM013003", "Pass", "Entered details in SREG-544 and clicked on ADD button");
		commonFunction.clickButtonContains(" Add");
		
		commonFunction.ScrollMenu("Submit ");
		sleep(2000);
		commonFunction.screenShot("EM013003", "Pass", "Account added successfully");
		commonFunction.clickButtonContains("Submit ");
		
		// --- SUC - 002 ---
		commonFunction.screenShot("EM013003", "Pass", "Successful launch to Account Flag Information Details Confirmation(SUC - 002) Page");
		
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_013_003 passed");
		
		
		

		
		
		
		
	}

}
