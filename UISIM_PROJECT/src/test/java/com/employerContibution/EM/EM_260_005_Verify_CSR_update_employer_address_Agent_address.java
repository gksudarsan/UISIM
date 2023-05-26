package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_005_Verify_CSR_update_employer_address_Agent_address extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.005.Verify CSR is able to update employer address for address type 'Agent(C/O) address'", groups = {
			"Regression" })
	public void EM_260_005() throws Exception {
		test = report.createTest(
				"EM.260.005.Verify CSR is able to update employer address for address type 'Agent(C/O) address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_employer_account WHERE EMPLOYER_TYPE = 'INDT' AND ORGANIZATION_TYPE_OTHER IS NULL AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"SELECT * FROM T_employer_account WHERE EMPLOYER_TYPE = 'INDT' AND ORGANIZATION_TYPE_OTHER IS NULL AND EAN LIKE '07%' ",
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
		cf.clickMenu("Maintain Address");
		sleep();

		// SREG-070
		cf.screenShot("Maintain Address – Enter ERN", "Pass", "Launched to SREG-070");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButton("Continue ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
//		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Agent Address", 6, 1, "Maintain Address Details");
		// SREG-004
		cf.screenShot("Add-Employer Contact Details", "Pass", "Launched to SREG-004");
		cf.selectRadioQuestions("Agent (C/O) address", "Other");
		sleep();
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.selectDropdown("County", " Albany ");
		cf.enterTextboxContains("First Name", "AutoTestFirstName");
		cf.enterTextboxContains("Last Name", "AutoTestLastName");
//		cf.enterTextboxContains(" Telephone Number ", Long.toString(cf.createRandomInteger(10000000, 99999999))
//				+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		cf.selectDropdown("Source", "Miscellaneous");
		sleep(2000);
		cf.selectDropdown("Source Type", "Other");
		cf.clickButtonContains("Submit ");
		sleep();

		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectTableWithoutId("Agent Address", 6, 1, "Maintain Address Details");
		sleep();
		// SREG-004
		cf.screenShot("Edit-Employer Contact Details", "Pass", "Launched to SREG-004");
		cf.selectRadioQuestions("Agent (C/O) address", "Other");
		sleep();
		cf.enterTextboxContains("Address Line 1", "AutoTestUpdate");
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.enterTextboxContains("First Name", "EditTestFirstName");
		cf.enterTextboxContains("Last Name", "EditTestLastName");
//		cf.enterTextboxContains(" Contact Telephone Number ", Long.toString(cf.createRandomInteger(10000000, 99999999))
//				+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		sleep();
		cf.selectDropdown("Source", "Miscellaneous");
		sleep(2000);
		cf.selectDropdown("Source Type", "Other");
		cf.clickButtonContains("Submit ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.logoutAndLogin("ndfjp3", "Admin@123456789");
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
		addPage.verifyInquiryAddressEmployerHistory("Agent Address");
		System.out.println("Worked");

	}
}
