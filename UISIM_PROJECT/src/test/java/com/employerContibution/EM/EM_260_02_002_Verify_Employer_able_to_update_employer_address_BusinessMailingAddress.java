package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_02_002_Verify_Employer_able_to_update_employer_address_BusinessMailingAddress extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.02.002.Verify Employer is able to update employer address for address type 'business mailing address'", groups = {
			"Regression" })
	public void EM_260_02_002() throws Exception {
		test = report.createTest(
				"EM.260.02.002.Verify Employer is able to update employer address for address type 'business mailing address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Address");
		sleep();

		cf.clickButton("Continue ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Business Mailing Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business
		// Physical Address");
		sleep();
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.logoutAndLogin("ndfjp3", "Admin@12345678");
		// cf.clickButtonContains("home");
		cf.clickMenu("Menu");
		cf.clickMenu("Inquiry");
		cf.clickMenu("Contribution Inquiry");
		cf.clickMenu("Inquiry Employer Address History");
		sleep(2000);
		cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Launched to EM-051");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButtonContains("Continue ");
		sleep();

		// SREG-057
		cf.screenShot("Inquiry Employer Address History", "Pass", "Launched to SREG-057");
		System.out.println("Worked");

	}
}
