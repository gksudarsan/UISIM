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
public class EOA_03_001_EmployerMasterAdministrator_canaccessEmployeraccount_andaddedit_users_AddUserType_EmployerSub_User_UserRole_EmployerContributions
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EOA.03.001	Verify Employer Master Administrator can access Employer account and add, edit user(s) (Add User Type - Employer Sub-User and User Role - Employer Contributions)", groups = "Regression")
	public void EOA_03_003() throws Exception {

		test = report.createTest(
				"EOA.03.001	Verify Employer Master Administrator can access Employer account and add, edit user(s) (Add User Type - Employer Sub-User and User Role - Employer Contributions)");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoalocators = new EOAPage(driver);

		// ---Login--- //
		//commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(),COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		test.log(Status.PASS, "Login with Employer User is successful");

		// ---Menu---- //
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on EMployer User");
		commonFuntions.clickMenu("Employer Users");sleep();

		// ---Employer Users-SREG-061---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer Users", "Pass", "Employer Users page is displayed-SREG-061");

		commonFuntions.clickOnLinkAnchorTag(" ADD USER");

		// ---Add Employer User-SREG-531---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Add Employer User page launched-SREG-531");
		
		//Negative Test 1
		commonFuntions.clickButton("Submit ");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Add Employer got Required error message");
		//Negative Test 2
		commonFuntions.enterTextbox("First Name", "Raj11");
		commonFuntions.enterTextbox("Middle Initial", "K");
		commonFuntions.enterTextbox("Last Name", "Khanna");
		commonFuntions.enterTextbox("Email Address", "Rajesh2.Khanna@gmail.com");
		commonFuntions.enterTextboxContains("Contact Number", "9766655555");
		commonFuntions.enterTextboxContains("Cell Number", "9766678460");
		eoalocators.cellSameasContactNumber.click();
		commonFuntions.enterTextboxContains("User ID", "Wsdl0004");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766688");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766688");
		commonFuntions.selectDropdown("User Types", "Employer Sub-User");
		eoalocators.select_Employer_Contributions.click();
		commonFuntions.clickButton("Submit ");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Name Validation");
		//Negative Test 3
		commonFuntions.enterTextbox("First Name", "Rajesh A");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Password Validation");
		//Negative Test 4
		commonFuntions.enterTextboxContains("User ID", "Wsdl");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766688");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766688");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "UserId Validation");
		
		//Success Flow
		commonFuntions.enterTextbox("First Name", "Rajesh A");
		commonFuntions.enterTextbox("Middle Initial", "K");
		commonFuntions.enterTextbox("Last Name", "Khanna");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Add Employer User Details Filled-1");

		commonFuntions.enterTextbox("Email Address", "Rajesh2.Khanna@gmail.com");
		commonFuntions.enterTextboxContains("Contact Number", "9766655555");
		commonFuntions.enterTextboxContains("Cell Number", "9766678460");
		
		commonFuntions.enterTextboxContains("User ID", "Wsdl0004");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766688");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766688");
		commonFuntions.selectDropdown("User Types", "Employer Sub-User");
		commonFuntions.screenShot("Add Employer User-SREG-531", "Pass", "Add Employer User Details Filled-2");
		commonFuntions.clickButton("Submit ");

		// ---Add User Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add User Confirmation-SUC-002", "Pass", "Add User Confirmation launched-SUC-002");
		commonFuntions.clickButton("Home ");

		// ---HomePage Launched again---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on EMployer User");
		commonFuntions.clickMenu("Employer Users");

		// ---Employer Users-SREG-061---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer Users", "Pass", "Employer Users page is displayed-SREG-061");
		commonFuntions.clickHyperlink("Manage User");

		// ---Update Employer User-SREG-062---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update Employer User-SREG-062", "Pass", "Update Employer User launched-SREG-062");
		commonFuntions.enterTextboxContains("Temporary Password", "Rajesh@9766677");
		commonFuntions.enterTextboxContains("Confirm Temporary Password", "Rajesh@9766677");
		commonFuntions.screenShot("Update Employer User-SREG-062", "Pass", "Password Updating-SREG-062");
		commonFuntions.clickButton("Submit ");

		// ---Updated User Confirmation-SUC-002---//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Updated User Confirmation-SUC-002", "Pass", "Updated User Confirmation-SUC-002");
		commonFuntions.clickButton("Home ");

		commonFuntions.screenShot("EOA_03_001", "Pass", "Successfully passed TC EOA_03_001");

	}
}