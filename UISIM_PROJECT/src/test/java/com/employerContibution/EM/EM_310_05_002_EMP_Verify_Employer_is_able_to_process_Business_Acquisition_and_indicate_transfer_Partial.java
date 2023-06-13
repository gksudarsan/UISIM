package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_011;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_05_002_EMP_Verify_Employer_is_able_to_process_Business_Acquisition_and_indicate_transfer_Partial extends TestBase {

	
	@Test(priority = 1, description = "Verify Employer is able to process Business Acquisition and indicate transfer Partial", groups = { "Regression" })
	public void 
	EM_310_05_002()throws Exception {
		test=report.createTest("Verify Employer is able to process Business Acquisition and indicate transfer Partial");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		SREG_011 sreg = new SREG_011(driver);
		
		
		//---------Login
		CommFun.login(COMMON_CONSTANT.EMPLOYER_USER_1,COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD );
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		
		//----Menu
		sleep(2000);
		CommFun.screenShot("menu", "Pass", "Menu page");
		CommFun.clickMenu("menu");
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
		CommFun.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		
		sreg.LegalNameOfBusiness.sendKeys("Township");
		
		CommFun.enterTextboxContains("Address Line 1 ", "Near LIG square");
		CommFun.enterTextboxContains("City ", "Albney");
		CommFun.selectDropdown("State", " New York ");
		CommFun.enterTextboxContains("Zip Code", "34565");
		CommFun.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		sreg.AcquisitionDate.sendKeys("10/10/2023");
		//CommFun.enterTextboxContainsThirdBox("Acquisition Date", "10/10/2023");
		CommFun.clickButtonContains("Continue ");
		
		//----- EM-006
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on EM-006");
		sleep(2000);
		CommFun.clickButtonContains("Submit ");
	
		//----SUC 002
		sleep(2000);
		CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		CommFun.clickButtonContains("Home ");
		sleep(2000);
		CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
	
		
		
					}
			
		}
