package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_027;
import com.ui.pages.SREG_030;
import com.ui.pages.SREG_503;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions; 
public class EM_412_02_001_Verify_Employer_is_able_to_update_legal_name_of_business_information_and_the_system_create_task_for_CSR_review_and_Approve extends TestBase
 {
	 @Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
	
	test = report.createTest("EM_412_02_001_ Verify Employer is able to update employer legal name of business and the system create task for CSR reviews and Approve.'");
	commonStepDefinitions commonFunction = new commonStepDefinitions();
			HomePage home = new HomePage(driver);
			SREG_030 sreg030 = new SREG_030(driver);
			SREG_027 sreg27 = new SREG_027(driver);
			SUC_002 suc002 = new SUC_002(driver);
			
			commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_1.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
			
			//---Menu Click---
			commonFunction.waitForLoadingIconToDisappear();
			commonFunction.clickMenu("menu");
			commonFunction.ScrollMenu("Account Maintenance");
			commonFunction.clickMenu("Account Maintenance");
			commonFunction.ScrollMenu("Employer Account Maintenance");
			//commonFunction.clickMenu("AccountMaintenanceEmployerAccountMaintenance");
			commonFunction.screenShot("AccountMaintenanceEmployerAccountMaintenance", "Pass", "AccountMaintenanceEmployerAccountMaintenance");
			commonFunction.waitForLoadingIconToDisappear();
			commonFunction.screenShot("SREG-030", "Pass", "Modify Employer Account Details page is displayed");
			//commonFunction.enterTextboxContains("Legal Name of Business", "ok man");
			//commonFunction.enterTextboxContains("Comment", "update");
			//commonFunction.selectLink("Supporting documents like", "Browse");
			//sleep(3000);
			//D:\AutomationFiles\Sample.docx
			//commonFunction.uploadDoc("Sample.docx");
			//commonFunction.enterTextboxContains(" Business Phone Number", "(788)-876-7566");
			commonFunction.screenShot("SREG-030", "Pass", "Modify Employer Account Details emtered");
			commonFunction.clickButtonContains("Submit ");
			commonFunction.waitForLoadingIconToDisappear();
			commonFunction.screenShot("SUC-002", "Pass", "Employer Account Maintenance Confirmation page is displayed");
			commonFunction.clickButtonContains("Home ");
			commonFunction.waitForLoadingIconToDisappear();
			commonFunction.screenShot("Home", "Pass", "Home page is displayed");
			
			 commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			 commonFunction.waitForLoadingIconToDisappear();
			    PEOPage.queue.click();
			  //commonFunction.enterTextboxContains("FEIN","99-9950123");
				commonFunction.screenShot("FeinSearch","Pass","feinSearch");
				//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
				commonFunction.screenShot("Update Legal Name Task ","Pass","Update Legal Name Task Page s displayed");
				commonFunction.clickButtonContains("Open Work Item");
				commonFunction.screenShot("EMWI-004","Pass","Update Legal Name Task page is displayed");
			    commonFunction.selectFromDropdown(" Approve ");
			    //commonFunction.enterTextboxContains("Comment", "Approve");
			   
				commonFunction.screenShot("GeneralInfo","Pass","General Information");
				 commonFunction.clickButtonContains("Submit ");
				commonFunction.screenShot("SUC-002","Pass","Work Item Completed.");
				commonFunction.clickButtonContains("Home");
				commonFunction.waitForLoadingIconToDisappear();
				commonFunction.screenShot("Home", "Pass", "Home page is displayed");
	 }}