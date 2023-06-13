
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

public class EM_260_008_Verify_CSR_able_to_Edit_Warrant_Address extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.008.Verify CSR is able to view and Edit Warrant Address information of an employer account", groups = {
			"Regression" })
	public void EM_260_008() throws Exception {
		test = report.createTest(
				"EM.260.008.Verify CSR is able to view and Edit Warrant Address information of an employer account");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '93%' AND EMPLOYER_TYPE = 'BUSI' IS NULL ORDER BY UPDATED_TS",
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI'",
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
		// cf.enterTextboxContains("Employer Registration Number", "9300004");

		cf.clickButton("Continue ");

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes
		// ");
		cf.selectTableWithoutId("Warrant Address", 6, 1, "Maintain Address Details");
		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		//cf.selectRadioQuestions("Notice of Experience Rating Charges (IA96) Address", "Other");
		sleep();
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("City", "NewYork");
		cf.enterTextboxContains("Zip Code", "13429");
//		cf.enterTextboxContains("First Name", "AutoTestFirstName");
//		cf.enterTextboxContains("Last Name", "AutoTestLastName");
//		cf.enterTextboxContains(" Contact Telephone Number ", Long.toString(cf.createRandomInteger(10000000, 99999999))
//				+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		sleep();
		cf.selectDropdown("Address Status", "Active");
		sleep();
		cf.selectDropdown("Source", "NYS-100 (paper)");
		sleep(2000);
		cf.selectDropdown("Source Type", " NYS-100 ");
		cf.clickButtonContains("Submit ");

		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.logoutAndLogin("ndfjp3", "Admin@12345678");
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
		addPage.verifyInquiryAddressEmployerHistory("Warrant Address");
		System.out.println("Worked");

	}
}
