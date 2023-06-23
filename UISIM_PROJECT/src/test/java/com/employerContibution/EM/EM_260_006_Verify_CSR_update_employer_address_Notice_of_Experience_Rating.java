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

public class EM_260_006_Verify_CSR_update_employer_address_Notice_of_Experience_Rating extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.006.Verify CSR is able to update employer address for address type 'Notice of Experience Rating Charges Address'", groups = {
			"Regression" })
	public void EM_260_006() throws Exception {
		test = report.createTest(
				"EM.260.006.Verify CSR is able to update employer address for address type 'Notice of Experience Rating Charges Address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				// "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '93%' AND EMPLOYER_TYPE
				// = 'BUSI' IS NULL ORDER BY UPDATED_TS",
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Address");
		sleep(4000);

		// SREG-070
		cf.screenShot("Maintain Address – Enter ERN", "Pass", "Launched to SREG-070");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButton("Continue ");
		sleep(4000);
		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes
		// ");
		cf.selectTableWithoutId("Notice of Experience Rating Charges (IA96) Address", 6, 1, "Maintain Address Details");
		sleep(4000);
		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		//cf.selectRadioQuestions("Notice of Experience Rating Charges (IA96) Address", "Other");
		sleep(4000);
		cf.enterTextboxContains("Address Line 1", "PrioraddressLine1" + cf.createRandomInteger(1000, 9999));
		sleep();
		cf.enterTextboxContains("Address Line 2", "PrioraddressLine2" + cf.createRandomInteger(1000, 9999));
		sleep();
		cf.selectDropdown("State", "New York");
		sleep();
		cf.enterTextboxContains("City", "NewYork");
		sleep();
		cf.enterTextboxContains("Zip Code", "13429");
		sleep();
		cf.enterTextboxContains("First Name", "AutoTestFirstName");
		sleep();
		cf.enterTextboxContains("Last Name", "AutoTestLastName");
		sleep();
//		cf.enterTextboxContains(" Contact Telephone Number ", Long.toString(cf.createRandomInteger(10000000, 99999999))
//				+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		sleep();
		cf.selectDropdown("Status", "Active");
		sleep();
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		sleep(2000);
		cf.clickButtonContains("Submit ");
		sleep();
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		// cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes
		// ");
		sleep(4000);
		cf.selectTableWithoutId("Main Notice of Experience Rating Charges (IA96) Address", 6, 1,
				"Maintain Address Details");
		sleep();
		// SREG-700
		cf.screenShot("Edit-Employer Contact Details", "Pass", "Launched to SREG-700");
		//cf.selectRadioQuestions("Notice of Experience Rating Charges (IA96) Address", "Other");
		sleep();
		cf.clearTextboxContains("Address Line 1");
		sleep();
		cf.enterTextboxContains("Address Line 1", "EditedAddress");
		sleep();
		cf.clearTextboxContains("Address Line 2");
		sleep();
		cf.enterTextboxContains("Address Line 2", "AddressLine2" + cf.createRandomInteger(1000, 9999));
		// cf.enterTextboxContains("City", "NewYork");
		// cf.enterTextboxContains("Zip Code", "13429");
		cf.enterTextboxContains("First Name", "AutoTestFirstName");
		sleep();
		cf.enterTextboxContains("Last Name", "AutoTestLastName");
		sleep();
		//cf.enterTextboxContains(" Contact Telephone Number ", Long.toString(cf.createRandomInteger(10000000, 99999999))
		//		+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Effective End Date", "10/6/2022");
		sleep();
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		sleep();
		cf.clickButtonContains("Submit ");
		sleep(4000);

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		cf.logoutAndLogin("ndfjp3", "Admin@123456789");
		// cf.clickButtonContains("home");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		sleep();
		cf.clickMenu("Menu");
		sleep();
		cf.clickMenu("Inquiry");
		sleep();
		cf.clickMenu("Contribution Inquiry");
		sleep();
		cf.clickMenu("Inquiry Employer Address History");
		sleep(4000);
		cf.screenShot("Inquiry Employer Address History - Enter ERN", "Pass", "Launched to EM-051");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep();
		cf.clickButtonContains("Continue ");
		sleep(4000);

		// SREG-057
		addPage.verifyInquiryAddressEmployerHistory("Notice of Experience Rating Charges (IA96) Address");
		cf.screenShot("Inquiry Employer Address History", "Pass", "Launched to SREG-057");
		System.out.println("Worked");

	}
}
