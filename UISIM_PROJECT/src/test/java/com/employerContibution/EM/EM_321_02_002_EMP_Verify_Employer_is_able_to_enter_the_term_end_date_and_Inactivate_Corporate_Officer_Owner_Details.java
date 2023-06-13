package com.employerContibution.EM;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_02_002_EMP_Verify_Employer_is_able_to_enter_the_term_end_date_and_Inactivate_Corporate_Officer_Owner_Details extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is able to enter the term end date and Inactivate Corporate_Officer Details", groups = { "Regression" })
	public void EM_321_02_002()throws Exception {
		test=report.createTest("Verify Employer is able to enter the term end date and Inactivate Corporate_Officer Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		//---------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_4,COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD );
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		
		//----menu
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
		
		
		
		sreg.actionEditOfficer.click();
		sleep(2000);
		CommFun.enterTextboxContains("SSN",Long.toString(CommFun.createRandomInteger(1000000,9999999))+Long.toString(CommFun.createRandomInteger(10,99)));
		CommFun.enterTextboxContains("First Name ", "Prena");
		CommFun.enterTextboxContains("Last Name ", "Gupta");
		CommFun.clickButtonContains("Submit ");
		sleep(2000);
		CommFun.clickOnLinkAnchorTag(" Corporate/Owner Details");
		sleep(2000);
		
		
		//-----SREG-702
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on SREG-702");
		sleep(2000);
		
		sreg.actionInactiveOfficer.click();
		sleep(2000);
		
		CommFun.clickButtonContains(" Yes ");
		sleep(2000);
		
		//------SREG-701
		CommFun.screenShot("Edit Corporate Officer/Owner", "Pass", "Successfully landed on SREG-701");
		sleep(2000);
		
		
		CommFun.enterTextboxContains("Term End Date", "05/29/2023");
		sleep(6000);
		
		CommFun.screenShot("Edit Corporate Officer/Owner", "Pass", "entered information on SREG-701");
		sleep(2000);
		CommFun.clickButton("Submit ");
		
		
		//----SUC 002
		sleep(2000);
		CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		CommFun.clickButtonContains("Home ");
		sleep(2000);
		CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
	
		//---completed by Palak
		
		
					}
			
		}
