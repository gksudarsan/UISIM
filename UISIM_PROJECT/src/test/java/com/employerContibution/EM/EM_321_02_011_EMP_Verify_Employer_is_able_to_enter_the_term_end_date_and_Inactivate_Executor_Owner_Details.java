		package com.employerContibution.EM;
		
		import org.testng.annotations.Test;
		
		import com.ui.base.TestBase;
		import com.ui.pages.EmployerRegisterPage;
		import com.ui.pages.HomePage;
		import com.ui.pages.SREG_074;
		import com.ui.pages.SREG_EM_mod;
		import com.ui.utilities.COMMON_CONSTANT;
		
		import stepDefinitions.commonStepDefinitions;
		
		public class EM_321_02_011_EMP_Verify_Employer_is_able_to_enter_the_term_end_date_and_Inactivate_Executor_Owner_Details extends TestBase {
		
			
			@Test(priority = 1, description = "Verify Employer is able to enter the term end date and Inactivate Details", groups = { "Regression" })
		public void EM_321_02_011()throws Exception {
			test=report.createTest("Verify Employer is able to enter the term end date and Inactivate Details");
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
		
		sreg.actionInactiveExecutor.click();
		sleep(2000);
		CommFun.clickButtonContains(" Yes ");
		sleep(2000);
		
		
		//------SREG-711
		CommFun.screenShot("Edit Executor/Owner Details", "Pass", "Successfully landed on SREG-711");
		sleep(2000);
		
		CommFun.enterTextboxContains("Term End Date", "05/31/2023");
		sleep(6000);
		
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
		
		//---Executed & completed by Palak										 
					}
			
		}
