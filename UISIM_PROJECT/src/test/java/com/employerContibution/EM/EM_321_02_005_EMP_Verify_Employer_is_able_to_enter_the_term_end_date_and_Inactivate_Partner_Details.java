package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_02_005_EMP_Verify_Employer_is_able_to_enter_the_term_end_date_and_Inactivate_Partner_Details extends TestBase{

	
	@Test(priority = 1, description = "Verify Employer is able to enter the term end date and Inactivate Partner Details", groups = { "Regression" })
	public void EM_321_02_005()throws Exception {
		test=report.createTest("Verify Employer is able to enter the term end date and Inactivate Partner Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_074 sreg = new SREG_074(driver);
		
		//------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_5, COMMON_CONSTANT.EMPLOYER_USER_5_PASSWORD);
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
		
		//-----SREG 074
		CommFun.screenShot("Partners Details", "Pass", "Successfully landed on SREG 074");
		sleep(2000);
		//CommFun.selectDateInTable("action", 10, 1, "Individual as Partner ", "inactive");
		//CommFun.selectTableParameterizedId("inactive", 10, 1, "Individual as Partner ", "dataTableId");
		sreg.actionInactive.click();
		sleep(2000);
		CommFun.screenShot("Pop up", "Pass", "Successfully landed on popup");
		sleep(2000);
		CommFun.clickButton(" Yes ");
		sleep(2000);
		
		
		//------SREG 073
		CommFun.screenShot("Edit Partnership Details", "Pass", "Successfully landed on SREG 073");
		sleep(2000);
		//Term End Date
		CommFun.enterTextboxContains("Term End Date", "09/13/2023");
		sleep(2000);
		CommFun.screenShot("Edit Partnership Details", "Pass", "entered information on SREG 073");
		sleep(2000);
		CommFun.clickButton("Submit ");
		

	    //----SUC 002
	    sleep(2000);
	    CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
	    sleep(2000);
	    CommFun.Label("The Partner Details have been added/updated successfully");
	    sleep(2000);
	    CommFun.clickButtonContains("Home ");
	    sleep(2000);
	    CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		 //Executed and completed by PAlak
		}
}
