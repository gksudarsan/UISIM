package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_02_003_Verify_Employer_is_able_to_Edit_Sole_Proprietorship_Details extends TestBase{

	
	@Test(priority = 1, description = "Verify Employer is able to Edit Sole Proprietorship Details", groups = { "Regression" })
	public void EM_321_02_003()throws Exception {
		test=report.createTest("Verify Employer is able to Edit Sole Proprietorship Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		//------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_3, COMMON_CONSTANT.EMPLOYER_USER_3_PASSWORD);
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	/*
		Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '8%' ORDER BY UPDATED_TS","EAN");
		 
		String ErnNum=databaseResults.get("EAN");
		//CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		System.out.println(ErnNum);
		sleep(2000);
	*/
		
		//---Menu Click---
		
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu page");
		CommFun.clickMenu("Menu");
		sleep(2000);
		CommFun.ScrollMenu("Account Maintenance");
		home.accountMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.MaintainBusinessOwnership.click();
		sleep(2000);
		
		//-----SREG-706
		CommFun.screenShot("Sole Proprietor Details", "Pass", "Successfully landed on SREG-706");
		sleep(2000);
		sreg.actionEdit.click();
		sleep(2000);
		
		
		//------SREG-705
		CommFun.screenShot("Edit Sole Proprietor Details", "Pass", "Successfully landed on SREG-705");
		sleep(2000);
		//Term End Date
		CommFun.enterTextboxContains("Term End Date", "05/13/2023");
		sleep(2000);
		CommFun.enterTextboxContains("Address Line 1 ", "Near DB City");
		CommFun.enterTextboxContains("City ", "New Jersey");
		CommFun.enterTextboxContains("Zip Code", "25678");
		CommFun.enterTextboxContains(" Contact Number ", "6263179965");
		CommFun.screenShot("Edit Sole Proprietor Details", "Pass", "entered information on SREG-705");
		sleep(2000);
		CommFun.clickButton("Submit ");
		

	    //----SUC 002
	    sleep(2000);
	    CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
	    sleep(2000);
	    CommFun.clickButtonContains("Home ");
	    sleep(2000);
	    CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		 
		}
}
