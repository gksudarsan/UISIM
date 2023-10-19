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

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_260_002_Verify_CSR_update_employer_address_business_mailing_address extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.260.002.Verify CSR is able to update employer address for address type 'business mailing address'", groups = {
			"Regression" })
	public void EM_260_002() throws Exception {
		test = report.createTest(
				"EM.260.002.Verify CSR is able to update employer address for address type 'business mailing address'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_REGULAR_EMPLOYER tre JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID WHERE EAN IS NOT NULL AND LENGTH(EAN)=7 AND BUSINESS_TYPE = 'BUSI'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		// Login
		cf.login(COMMON_CONSTANT.LND_SPECIALIST.toUpperCase(), COMMON_CONSTANT.LND_SPECIALIST_PASSWORD);
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
		cf.waitForLoadingIconToDisappear();

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		cf.selectTableWithoutId("Business Mailing Address", 6, 1, "Maintain Address Details");

		// SREG-700
		cf.screenShot("Maintain Address/Contact Details", "Pass", "Launched to SREG-486");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		cf.selectDropdown("County", " Albany ");
		cf.selectDropdown("Source", "Correspondence/Email");
		sleep(2000);
		cf.selectDropdown("Source Type", "Correspondence/Email");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();

		// SREG-486
		cf.screenShot("Maintain Address Details", "Pass", "Launched to SREG-486");
		sleep();
		//cf.logoutAndLogin("ndfjp3", "Admin@123456789");
		//cf.clickButtonContains("Home ");
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
		addPage.verifyInquiryAddressEmployerHistory("Business Mailing Address");
		System.out.println("Worked");

	}
}
