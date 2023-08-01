package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_443_007_Verify_CSR_enter_ERN_and_Add_POA_TPR extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.443.007. Verify CSR is able to enter ERN and Add POA/TPR association for designation type Payroll Agent Agreement", groups = {
			"Regression" })
	public void EM_443_007() throws Exception {
		test = report.createTest(
				"EM.443.007. Verify CSR is able to enter ERN and Add POA/TPR association for designation type Payroll Agent Agreement");
		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc = new SUC_002(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_EMPLOYER_ACCOUNT tea2 WHERE ACCOUNT_STATUS = 'ACTV' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS",
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Employer Account Maintenance");
		sleep();
		cf.clickMenu("Add or Remove POA/TPR Association");
		sleep();

		// SREG-430
		cf.screenShot("Add or Remove POA/TPR Association – Enter ERN", "Pass", "Launched to SREG-430");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep();
		cf.clickButton("Continue ");

		// SREG-537
		cf.screenShot("Add or Remove Third Party Association to Employer", "Pass", "Launched to SREG-537");
		sleep();
		cf.selectDropdownThirdParty("Payroll Agent Agreement - Solely for the purpose of filing returns and payments");
		cf.clickButtonContains(" Search POA/TPR ");

		// SREG-040
		cf.screenShot("Search POA/Third Party Representative", "Pass", "Launched to SREG-040");
		cf.enterTextboxContains("POA/TPR Legal Name", "Test");
		sleep();
		cf.clickButtonContains(" Search ");
		sleep();
		cf.selectRadio("Select");
		cf.clickButtonContains("Continue ");

		// SREG-537
		cf.screenShot("Add or Remove Third Party Association to Employer", "Pass", "Launched to SREG-537");
		sleep();
		cf.selectRadio("Select");
		sleep();
		cf.enterCommentBoxContains("Add the Name");
		sleep();
		cf.selectCheckbox("Additional authorization:");
		cf.clickButtonContains("Submit ");
		// SUC-002
		cf.screenShot("Add or Remove POA/Third Party Representative Association to Employer Confirmation", "Pass",
				" Landed to SUC-002");
		suc.validatePOASucessText();
		
		//Passed
	}
}
