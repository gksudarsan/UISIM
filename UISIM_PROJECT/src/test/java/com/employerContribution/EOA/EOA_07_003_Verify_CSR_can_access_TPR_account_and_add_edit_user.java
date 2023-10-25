package com.employerContribution.EOA;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_07_003_Verify_CSR_can_access_TPR_account_and_add_edit_user extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EOA_07_003_Verify_CSR_can_access_TPR_account_and_add_edit_user", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_07_003() throws Exception {

		test = report.createTest("EOA_07_003_Verify_CSR_can_access_TPR_account_and_add_edit_user");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		EOAPage eoaPage = new EOAPage(driver);

		Map<String, String> databaseResult1 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");

		String eanNumber = databaseResult1.get("EAN");
		System.out.println("The eanNumber is:" + eanNumber);

		commonFuntions.login(COMMON_CONSTANT.CSR_UI_Services_Specialist.toUpperCase(), COMMON_CONSTANT.CSR_UI_Services_Specialist_Pass);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Users");
		commonFuntions.clickMenu("Employer Users");
		test.info("Step: 2 -- ");
		// ----SREG 533
		commonFuntions.screenShot("User Search", "Pass", "User Search on SREG 533");
		sleep(1000);
		commonFuntions.selectRadioQuestionsContains("Account Type: ", "Third Party Representative");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFuntions.screenShot("User Search", "Pass", "Entered details on User Search on SREG 533");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		test.info("Step: 3 -- ");
		// -------SREG-061
		commonFuntions.screenShot("Third Party Representative User", "Pass",
				"Third Party Representative User on SREG 533");
		sleep(1000);
		commonFuntions.clickOnLinkAnchorTag(" ADD USER");
		commonFuntions.waitForLoadingIconToDisappear();
		test.info("Step: 4 -- ");
		// --------SREG-531
		commonFuntions.screenShot("Add TPR User", "Pass", "Add TPR User on SREG-531");
		sleep(1000);
		commonFuntions.enterTextboxContains("First Name", "AutoSuper");
		commonFuntions.enterTextboxContains("Last Name", "AutoLast");
		commonFuntions.enterTextboxContains("Email Address", "autoEmail"
				+ StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4) + "@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));
		commonFuntions.enterTextboxContains(" Cell Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));

		commonFuntions.enterTextboxContains("User ID",
				"user" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4));
		commonFuntions.enterTextbox("Temporary Password", "Password@12345");
		commonFuntions.enterTextbox("Confirm Temporary Password", "Password@12345");
		sleep(2000);
		commonFuntions.selectDropdown("User Types", " TPR Sub-User ");
		eoaPage.tpralluimatters.click();
		sleep(2000);
		commonFuntions.screenShot("Add TPR User", "Pass", "Entered info on Add TPR User on SREG-531");
		commonFuntions.clickButton("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----SUC-002
		test.info("Step: 5 -- SUC-002 ");
		commonFuntions.screenShot("Added User Confirmation", "Pass", "Added User Confirmation : SUC - 002");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----Home
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page ");

		test.info("Step: 6 -- ");
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Users");
		commonFuntions.clickMenu("Employer Users");

		// ----SREG 533
		commonFuntions.screenShot("User Search", "Pass", "User Search on SREG 533");
		sleep(1000);
		commonFuntions.selectRadioQuestionsContains("Account Type: ", "Employer or Professional Employer Organization");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFuntions.screenShot("User Search", "Pass", "Entered details on User Search on SREG 533");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// -------SREG-061
		commonFuntions.screenShot("Employer User", "Pass", "Employer User on SREG 533");
		sleep(1000);
		commonFuntions.clickOnLinkAnchorTag(" ADD USER");
		commonFuntions.waitForLoadingIconToDisappear();

		// --------SREG-531
		commonFuntions.screenShot("Add Employer User", "Pass", "Add Employer User on SREG-531");
		sleep(1000);
		commonFuntions.enterTextboxContains("First Name", "TestSuper");
		commonFuntions.enterTextboxContains("Last Name", "TestLast");
		commonFuntions.enterTextboxContains("Email Address", "autoEmail"
				+ StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4) + "@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));
		commonFuntions.enterTextboxContains(" Cell Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));

		commonFuntions.enterTextboxContains("User ID",
				"usremp" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4));
		commonFuntions.enterTextbox("Temporary Password", "Password@12345");
		commonFuntions.enterTextbox("Confirm Temporary Password", "Password@12345");
		sleep(2000);
		commonFuntions.selectDropdown("User Types", " Employer Sub-User ");
		eoaPage.employercontributionsCheckbox.click();
		sleep(2000);
		commonFuntions.screenShot("Add employer User", "Pass", "Entered info on Add employer User on SREG-531");
		commonFuntions.clickButton("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----SUC-002

		commonFuntions.screenShot("Added User Confirmation", "Pass", "Added User Confirmation : SUC - 002");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----Home
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page ");

		test.info("Step: 7 -- ");
		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.clickMenu("Users");
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Click on Users");
		commonFuntions.clickMenu("Employer Users");

		// ----SREG 533
		commonFuntions.screenShot("User Search", "Pass", "User Search on SREG 533");
		sleep(1000);
		commonFuntions.selectRadioQuestionsContains("Account Type: ", "Employer or Professional Employer Organization");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		sleep(2000);
		commonFuntions.screenShot("User Search", "Pass", "Entered details on User Search on SREG 533");
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// -------SREG-061
		commonFuntions.screenShot("Employer User", "Pass", "Employer User on SREG 533");
		sleep(1000);
		eoaPage.userListingManageUser.click();
		commonFuntions.waitForLoadingIconToDisappear();

		// --------SREG-062
		commonFuntions.screenShot("Update Employer User", "Pass", "Update Employer User on SREG-062");
		sleep(1000);

		commonFuntions.enterTextboxContains("Last Name", "Soni");
		commonFuntions.enterTextboxContains("Email Address",
				"Soni" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4) + "@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));
		commonFuntions.enterTextboxContains(" Cell Number ",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));

		commonFuntions.enterTextboxContains("User ID",
				"Soniemp" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4));
		commonFuntions.enterTextbox("Temporary Password", "Password@123456");
		commonFuntions.enterTextbox("Confirm Temporary Password", "Password@123456");
		sleep(2000);
		commonFuntions.selectDropdown("User Types", " Employer Sub-User ");

		eoaPage.employercontributionsCheckbox.click();
		eoaPage.employerbenefitsCheckbox.click();
		eoaPage.useradminCheckbox.click();

		sleep(2000);
		commonFuntions.screenShot("Update employer User", "Pass", "Entered info on Update employer User on SREG-531");
		commonFuntions.clickButton("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----SUC-002

		commonFuntions.screenShot("Update User Confirmation", "Pass", "Added User Confirmation : SUC - 002");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----Home
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page ");
		commonFuntions.screenShot("TC:  EOA_07_003", "Pass", "Test completed  ");
		test.info("TC Competed -- ");
		// completed by Palak

	}

}
