package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_02_007_EMP_Verify_Employer_is_able_to_enter_the_term_end_date_and_Inactivate_Member_Managing_Member_Details extends TestBase{

	
	@Test(priority = 1, description = "Verify Employer is able to enter the term end date and Inactivate Member/Managing Member Details", groups = { "Regression" })
	public void EM_321_02_007()throws Exception {
		test=report.createTest("Verify Employer is able to enter the term end date and Inactivate Member/Managing Member Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_074 sreg = new SREG_074(driver);
		
		//------Login
		//CommFun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_8,COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD );
		//CommFun.login("manju@2004", "Manhar2004@1234");
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		
		//---Menu Click---
		
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu page");
		CommFun.clickMenu("menu");
		sleep(2000);
		CommFun.ScrollMenu("Account Maintenance");
		home.accountMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.MaintainBusinessOwnership.click();
		sleep(2000);
		
		//-----SREG 706
		CommFun.screenShot("Member managing Details", "Pass", "Successfully landed on SREG 706");
		sleep(2000);
		//CommFun.selectDateInTable("action", 10, 1, "Individual as Partner ", "inactive");
		//CommFun.selectTableParameterizedId("inactive", 10, 1, "Individual as Partner ", "dataTableId");
		sreg.actionInactive.click();
		sleep(2000);
		CommFun.screenShot("Pop up", "Pass", "Successfully landed on popup");
		sleep(2000);
		CommFun.clickButton(" Yes ");
		sleep(2000);
		
		
		//------SREG 707
		CommFun.screenShot("Edit mamber managing Details", "Pass", "Successfully landed on SREG 707");
		sleep(2000);
		//Term End Date
		CommFun.enterTextboxContains("Term End Date", "05/13/2023");
		sleep(2000);
		CommFun.screenShot("Edit mamber managing Details", "Pass", "Successfully landed on SREG 707");
		sleep(2000);
		CommFun.clickButton("Submit ");
		

	    //----SUC 002
	    sleep(2000);
	    CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
	    sleep(2000);
	    CommFun.clickButtonContains("Home ");
	    sleep(2000);
	    CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		 
	  //---//--Facing issue in Mamber manging id Manju's one , User is inactive we are unable to click on menu
		}
}
