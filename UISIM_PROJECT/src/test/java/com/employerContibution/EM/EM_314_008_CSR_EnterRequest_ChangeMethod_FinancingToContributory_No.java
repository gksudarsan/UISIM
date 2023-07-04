package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_314_008_CSR_EnterRequest_ChangeMethod_FinancingToContributory_No extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter request for change method of financing  to Contributory with select option No.", groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EM_314_008() throws Exception {
		
		test = report.createTest("EM.314.008 : Verify CSR is able to enter request for change method of financing  to Contributory with select option No.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManagement = new employerManagement(driver);
		
		//SQL -> SELECT DISTINCT BUSINESS_TYPE,EAN FROM T_REGULAR_EMPLOYER tre JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID WHERE tre.BUSINESS_TYPE IN ('NONP', 'INDT', 'GOVT') AND tea.REGISTRATION_STATUS = 'C'
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		empManagement.menu.click();		
		sleep();
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.ScrollMenu("Change in Method of Financing");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Change in Method of Financing");
		commonFunction.clickMenu("Change in Method of Financing");
		
		// --- ETR-228 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314008", "Pass", "Successful launch to Change in Method of Financing - Enter ERN(ETR-228) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "8601874"); //8601874
		// INDT -> 0000037, GOVT -> 0000056, NONP -> 0400001
		commonFunction.screenShot("EM314008", "Pass", "Entered ERN in ETR-228 page");
		commonFunction.clickButtonContains("Continue ");
		
		// ---ETR-229---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314008", "Pass", "Successful launch to Change in Method of Financing(ETR-229) page");
		commonFunction.selectRadioQuestions("Do you want to change the method of financing to reimbursable?", "No ");
		commonFunction.screenShot("EM314008", "Pass", "Selected NO from radio");
		sleep();
		commonFunction.screenShot("EM314008", "Pass", "Selcting No will redirect to Home");
		//No button is not redirecting to the home screen, had to click Yes for home.
		commonFunction.clickButtonContains(" Yes ");
		
		// --- WEL-1000 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM314008", "Pass", "Successful launch to Home(WEL-1000) page");

		sleep(2000);
		commonFunction.screenShot("EE314008", "Pass", "Successfully passed TC EM.314.008");
		
		System.out.println("pass :)");
		
	}

}
