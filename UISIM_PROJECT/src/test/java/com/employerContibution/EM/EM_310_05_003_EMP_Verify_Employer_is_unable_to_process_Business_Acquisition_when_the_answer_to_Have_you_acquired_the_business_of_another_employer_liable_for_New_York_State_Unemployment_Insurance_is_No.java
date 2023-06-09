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

public class EM_310_05_003_EMP_Verify_Employer_is_unable_to_process_Business_Acquisition_when_the_answer_to_Have_you_acquired_the_business_of_another_employer_liable_for_New_York_State_Unemployment_Insurance_is_No extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is unable to process Business Acquisition when the answer to 'Have you acquired the business of another employer liable for New York State Unemployment Insurance? is 'No'", groups = { "Regression" })
	public void 
	EM_310_05_003()throws Exception {
		test=report.createTest("Verify Employer is unable to process Business Acquisition when the answer to 'Have you acquired the business of another employer liable for New York State Unemployment Insurance? is 'No'");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		//---------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_1,COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD );
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//----Menu
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu page");
		CommFun.clickMenu("Menu");
		sleep(2000);
		CommFun.ScrollMenu("Account Maintenance");
		home.accountMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.BusinessAcquisition.click();
		sleep(2000);
		
		//-----SREG-011
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on SREG-011");
		sleep(2000);
		CommFun.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		sleep(2000);
		CommFun.clickButtonContains("Continue ");
		
		//----Home
		sleep(2000);
		CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		
	
		//------completed by palak	
		
		
					}
			
		}
