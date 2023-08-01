package com.employerContibution.EM;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_328_003_Verify_CSR_ableToSearchPOA_TPRLegalNameNoticeOfExperienceRatings extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.328.003 - Verify CSR is able to search POA/TPR Legal name of business and update POA/TPR details with additional address Form Type 'Notice of experience Ratings (IA96)'", groups = {
			"Regression" })
	public void EM_328_003() throws Exception {
		test = report.createTest(
				"EM.328.003 - Verify CSR is able to search POA/TPR Legal name of business and update POA/TPR details with additional address Form Type 'Notice of experience Ratings (IA96)'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_504 sreg504 = new SREG_504(driver);
		PEOPage peoPage = new PEOPage(driver);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Reporting Service Details");
		sleep();
		cf.screenShot("Search POA/Third Party Representative", "Pass", "Launched to SREG-041");

		// SREG-041
		cf.enterTextboxContains("POA/TPR Legal Name", "Test");
		cf.clickButton(" Search ");
		cf.selectRadio("Select");
		cf.clickButton("Continue ");

		// SREG-600
		cf.screenShot("POA/Third Party Representative Details", "Pass", "Launched to SREG-600");
		cf.clickOnLinkAnchorTag(" Add Additional Addresses ");

		// SREG-085
		cf.screenShot("Add Additional Addreses", "Pass", "Launched to SREG-085");
		cf.selectRadioQuestions("Form Type", "Notice of experience Ratings (IA96)");
		cf.enterTextboxContains("First Name", "Karen");
		cf.enterTextboxContains("Last Name", "Jose");
		cf.enterTextboxContains(" Contact Number ", "1667385239");

		cf.enterTextboxContains("Address Line 1 ", "7th Street 40");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("State", "New York");
		cf.enterTextboxContains("Zip Code", "44673");
		cf.enterTextboxContains("Country", "United States");
		sleep();
		cf.clickButton("Continue ");

		// SREG-600
		cf.screenShot("POA/Third Party Representative Details", "Pass", "Launched to SREG-600");
		sleep();
		cf.selectRadioQuestions("Source", "Correspondence/Email");
		cf.selectRadioQuestions("Source Type", "Correspondence/Email");

		sleep();
		cf.clickButton("Submit");

		// SUC-002
		cf.screenShot("POA/Third Party Representative Details", "Pass", "Launched to SUC-002");
		cf.validateTextIsDisplayed("The POA/Third Party Reperesentative Details have been updated successfully.");

		sleep();

		cf.clickButton("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home");

		System.out.println("Worked");
	}
}
