package com.employerContibution.EM;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_02_010_EMP_Verify_Employer_is_able_to_Edit_Executor_Owner_Details2 extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is able to Edit Executor/Owner  Details", groups = { "Regression" })
	public void EM_321_02_010()throws Exception {
		test=report.createTest("Verify Employer is able to Edit Executor/Owner  Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		//---------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_6,COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD );
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
		
												//-----SREG-712
		CommFun.screenShot("Executor/Owner Details", "Pass", "Successfully landed on SREG-712");
		sleep(2000);
		CommFun.clickOnLink("Add Executor/Owner Details");
		sleep(2000);
		
		CommFun.enterTextboxContains("SSN",Long.toString(CommFun.createRandomInteger(1000000,9999999))+Long.toString(CommFun.createRandomInteger(10,99)));
		CommFun.enterTextboxContains("First Name ", "Prena");
		CommFun.enterTextboxContains("Last Name ", "Gupta");
		CommFun.selectDropdown("Title ", " Executor");
		//CommFun.enterTextboxContains("", "");
		
		CommFun.enterTextboxContains("Term End Date", "05/13/2023");
		sleep(2000);
		CommFun.enterTextboxContains("Address Line 1 ",Long.toString(CommFun.createRandomInteger(1000,9999)));
		CommFun.enterTextboxContains("City ", "New Jersey");
		CommFun.enterTextboxContains("Zip Code", "25678");
		CommFun.enterTextboxContains(" Contact Number ",Long.toString(CommFun.createRandomInteger(1000000,9999999))+Long.toString(CommFun.createRandomInteger(100,999)));
		CommFun.screenShot("Edit Executor/Owner Details", "Pass", "entered information on SREG-711");
		CommFun.clickButtonContains("Submit ");
		
		//----SUC 002
		sleep(2000);
		CommFun.screenShot("Executor/Owner Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		CommFun.clickOnLinkAnchorTag("Executor/Owner Details");
		sleep(2000);
		
		
		//-------SREG-712
		CommFun.screenShot("Executor/Owner Details", "Pass", "Successfully landed on SREG-712");
		sleep(2000);
		
		sreg.actionEditExecutor.click();
		sleep(2000);
		
		
		//------SREG-711
		CommFun.screenShot("Edit Executor/Owner Details", "Pass", "Successfully landed on SREG-711");
		sleep(2000);
		
		CommFun.enterTextboxContains("SSN",Long.toString(CommFun.createRandomInteger(1000000,9999999))+Long.toString(CommFun.createRandomInteger(10,99)));
		sleep(2000);
		//CommFun.enterTextboxContains("First Name ", "Rupal");
		
		//CommFun.enterTextboxContains("Last Name ", "Gupta");
		//CommFun.clickButtonContains(" Yes ");
		sleep(2000);
		//Term End Date
		CommFun.enterTextboxContains("Term End Date", "05/31/2023");
		sleep(2000);
		CommFun.enterTextboxContains("Address Line 1 ", "Near DB City");
		CommFun.enterTextboxContains("City ", "New Jersey");
		CommFun.enterTextboxContains("Zip Code", "25678");
		CommFun.enterTextboxContains(" Contact Number ", "6267877865");
		sleep(2000);
		CommFun.screenShot("Edit Executor/Owner Details", "Pass", "entered information on SREG-711");
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
