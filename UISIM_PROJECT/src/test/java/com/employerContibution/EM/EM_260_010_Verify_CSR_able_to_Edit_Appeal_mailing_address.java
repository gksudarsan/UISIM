package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_010_Verify_CSR_able_to_Edit_Appeal_mailing_address extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.010.Verify CSR is able to view and Edit Appeal mailing address information of an employer account", groups = {
			"Regression" })
	public void EM_260_010() throws Exception {
		test = report.createTest(
				"EM.260.010.Verify CSR is able to view and Edit Appeal mailing address information of an employer account");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				// "SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS
				// NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
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
		cf.selectTableWithoutId("Appeal Mailing Address", 6, 1, "Maintain Address Details");

		// Add - SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business
		// Physical Address");
		sleep();
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
//		cf.enterTextboxContains("First Name", "AutoTestFirstName");
//		cf.enterTextboxContains("Last Name", "AutoTestLastName");
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		cf.selectDropdown("Address Status", "Active");
		sleep();
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");
		
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		// Edit - SREG-700
		cf.selectTableWithoutId("Appeal Mailing Address", 6, 1, "Maintain Address Details");
		// cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business
		// Physical Address");
		sleep();
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
		cf.enterTextboxContains("Effective End Date", "3/4/2023");
		cf.selectDropdown("Address Status", "Active");
		sleep();
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.logoutAndLogin("ndfjp3", "Admin@123456789");
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
		addPage.verifyInquiryAddressEmployerHistory("Appeal Mailing Address");
		System.out.println("Worked");

	}
}
