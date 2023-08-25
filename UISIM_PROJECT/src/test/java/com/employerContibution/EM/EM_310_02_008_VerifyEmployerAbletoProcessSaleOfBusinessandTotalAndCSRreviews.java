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

public class EM_310_02_008_VerifyEmployerAbletoProcessSaleOfBusinessandTotalAndCSRreviews extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.310.02.008 - Verify Employer is able to process sale of business and indicate transfer 'Total' when the same transfer was processed previously and system create task for CSR reviews.", groups = {
			"Regression" })
	public void EM_310_02_008() throws Exception {
		test = report.createTest(
				"EM.310.02.008 - Verify Employer is able to process sale of business and indicate transfer 'Total' when the same transfer was processed previously and system create task for CSR reviews.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_504 sreg504 = new SREG_504(driver);
		PEOPage peoPage = new PEOPage(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseResults = sreg504
				.database_SelectQuery("SELECT * FROM T_EMPLOYER_TRANSFER_SUCCESSOR");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String successorName = databaseResults.get("Name");
		System.out.println("FEIN is: " + feinValue);
		System.out.println("Entity Name is: " + successorName);
		System.out.println("EAN is: " + eanValue);

		// Login
		cf.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		sleep(2000);
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Sale of Business");
		sleep();
		cf.screenShot("Sale of Business", "Pass", "Launched to SREG-503");

		// SREG-503
		cf.selectRadioQuestions("Have you sold your business?", "Yes ");
		cf.selectRadioQuestions("Did you sell all or part of your business?", "Full");
		cf.enterTextboxContains("Effective Date of Transfer", "6/12/2023");
		cf.enterTextboxContains("Successor`s Name", successorName);
		cf.enterTextboxContains("Successor`s FEIN", feinValue);
		cf.enterTextboxContains("Successor`s ERN", eanValue);
		sleep();
		cf.enterTextboxContains("First Name", "Karen");
		cf.enterTextboxContains("Last Name", "Tales");
		cf.enterTextboxContains("Attention/Care Of", "ABCD");
		cf.enterTextboxContains("Address Line 1 ", "7th Street 40");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "44673");
		cf.enterTextboxContains("State", "New York");
		cf.enterTextboxContains(" Successor's Telephone Number ", "2345678654");
		cf.clickButton("Continue ");

		// SREG-504
		cf.screenShot("Sale of Business Details", "Pass", "Launched to SREG-504");
		cf.clickButton("Submit ");

		// SUC-002
		cf.screenShot("Sale of Business Complete", "Pass", "Launched to SUC-002");
		suc002.validateSaleOfBusinessText();
		sleep();
		cf.clickButton("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home Screen");
		sleep();
		
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='" + feinValue
				+ "' ORDER BY UPDATED_TS desc)");
		//cf.logoutAndLogin("ndfjp3", "Admin@1234567891");
		peoPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN", feinValue);
		cf.screenShot("SerachWithFEIN", "Pass", "Searching with FEIN");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("Verify Transfer Failed Rules", "Pass", "Verify Transfer Failed Rules Task");
		cf.clickOnLinkAnchorTag("Verify Transfer Failed Rules");
		sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("OpenWorkItem", "Pass", "Launched to WF-091");
		cf.selectDropdown("Select the action", "Approve");
		cf.enterCommentBoxContains("Approved");
		sleep();
		cf.screenShot("ApprovePOA/ThirdPartyAssociationtoEmployer", "Pass",
				"Launched to SREG-499");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("Association Confirmation", "Pass", "Launched to SUC-002");
		cf.validateTextIsDisplayed("The Task details have been successfully saved.");
		System.out.println("Worked");
	}
}
