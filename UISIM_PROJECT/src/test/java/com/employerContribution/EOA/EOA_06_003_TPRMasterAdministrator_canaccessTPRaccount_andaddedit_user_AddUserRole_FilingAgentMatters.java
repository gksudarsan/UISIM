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
public class EOA_06_003_TPRMasterAdministrator_canaccessTPRaccount_andaddedit_user_AddUserRole_FilingAgentMatters
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR Master Administrator can access TPR account and add, edit user(s) (Add User Role - Filing Agent Matters)", groups = "Regression")
	public void EOA_06_003() throws Exception {

		test = report.createTest(
				"EOA.06.003	Verify TPR Master Administrator can access TPR account and add, edit user(s) (Add User Role - Filing Agent Matters)");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoalocators = new EOAPage(driver);
		//---Login---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		test.log(Status.PASS, "Login with TPR User is successful");
		
		//---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on TPR Users");
		commonFuntions.clickMenu("TPR Users");

		//---Third Party Representative User-SREG-061---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("TPR Users", "Pass", "TPR Users page is displayed-SREG-061");
		commonFuntions.clickOnLinkAnchorTag(" ADD USER");

		//---Add TPR User-SREG-531---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add TPR User-SREG-531", "Pass", "Add TPR User page launched-SREG-531");
		commonFuntions.enterTextbox("First Name", "Rajeshji");
		commonFuntions.enterTextbox("Middle Initial", "K");
		commonFuntions.enterTextbox("Last Name", "Khanna");
		commonFuntions.screenShot("Add TPR User-SREG-531", "Pass", "Add TPR User Details Filled-1");

		commonFuntions.enterTextbox("Email Address", "Rajesh2.Khanna@gmail.com");
		commonFuntions.enterTextboxContains("Contact Number", "9766655555");
		commonFuntions.enterTextboxContains("Cell Number", "9766678460");
		eoalocators.cellSameasContactNumber.click();

		commonFuntions.enterTextboxContains("User ID", "Wsdl1232");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766688");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766688");
		commonFuntions.selectDropdown("User Types", "TPR Sub-User");
		eoalocators.tprAllUIMatters.click();
		commonFuntions.screenShot("Add TPR User-SREG-531", "Pass", "Add TPR User Details Filled-2");
		commonFuntions.clickButton("Submit ");

		//---Add User Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add User Confirmation-SUC-002", "Pass", "Add User Confirmation launched-SUC-002");
		commonFuntions.clickButton("Home ");

		//---HomePage Launched again---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on TPR Users");
		commonFuntions.clickMenu("TPR Users");

		//---Third Party Representative User-SREG-061---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("TPR Users", "Pass", "TPR Users page is displayed-SREG-061");
		commonFuntions.clickHyperlink("Manage User");

		//---Update TPR User-SREG-062---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update TPR User-SREG-062", "Pass", "Update TPR User launched-SREG-062");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766677");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766677");
		commonFuntions.screenShot("Update TPR User-SREG-062", "Pass", "Password Updating-SREG-062");
		commonFuntions.clickButton("Submit ");

		//---Updated User Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Updated User Confirmation-SUC-002", "Pass", "Updated User Confirmation-SUC-002");
		commonFuntions.clickButton("Home ");
		
		commonFuntions.screenShot("EOA_06_003", "Pass", "Successfully passed TC EOA_06_003");

	}
}