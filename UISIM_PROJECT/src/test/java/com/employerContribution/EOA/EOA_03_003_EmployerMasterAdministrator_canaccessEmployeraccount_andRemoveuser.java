package com.employerContribution.EOA;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_03_003_EmployerMasterAdministrator_canaccessEmployeraccount_andRemoveuser extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify Employer Master Administrator can access Employer account and Remove user(s)", groups = "Regression")
	public void EOA_03_003() throws Exception {

		test = report.createTest(
				"EOA.03.003 Verify Employer Master Administrator can access Employer account and Remove user(s)");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		//EOAPage eoalocators = new EOAPage(driver);

		// ---Login---
		//commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		test.log(Status.PASS, "Login with Employer User is successful");

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on EMployer User");
		commonFuntions.clickMenu("Employer Users");

		// ---Employer Users-SREG-061---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer Users", "Pass","Employer Users page is displayed-SREG-061");
		
		commonFuntions.clickHyperlink("Manage User");
		
		// ---Update Employer User-SREG-062---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update Employer Users", "Pass","Update Employer Users page is displayed-SREG-062");
		
		commonFuntions.selectDropdown("User Types", "Employer Sub-User");
		sleep(3000);
		commonFuntions.selectCheckbox(" Employer-Benefits");
		
		commonFuntions.clickButton("Remove ");
		
	}
}