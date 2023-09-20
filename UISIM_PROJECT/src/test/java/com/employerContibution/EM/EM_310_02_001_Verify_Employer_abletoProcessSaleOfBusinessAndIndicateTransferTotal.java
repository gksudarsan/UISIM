//--------Smoke-----
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

public class EM_310_02_001_Verify_Employer_abletoProcessSaleOfBusinessAndIndicateTransferTotal extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.310.02.001  - Verify Employer is able to process sale of business and indicate transfer 'Total'.", groups = {
			"Regression" })
	public void EM_310_02_001() throws Exception {
		
		test = report.createTest(
				"EM.310.02.001  - Verify Employer is able to process sale of business and indicate transfer 'Total'.");
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage addPage = new AddressPage(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_504 sreg504 = new SREG_504(driver);

		// DB Query
		// Valid ERN
		Map<String, String> databaseResults = sreg504.database_SelectQuery("SELECT * FROM T_EMPLOYER_TRANSFER_SUCCESSOR");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String successorName = databaseResults.get("Name");
		System.out.println("FEIN is: " + feinValue);
		System.out.println("Entity Name is: " + successorName);
		System.out.println("EAN is: " + eanValue);

		// Login
		cf.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
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
		cf.enterTextboxContains("If Yes, enter Legal Name of Entity", "abc");
		cf.enterTextboxContains("Address Line 1 ", "7th Street 40");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "44673");
		cf.selectDropdown("County", " New York ");
		cf.enterTextboxContains(" Successor's Telephone Number ", "2345678654");
		cf.clickButton("Continue ");

		// SREG-504
		cf.screenShot("Sale of Business Details", "Pass", "Launched to SREG-504");
		cf.clickButton("Submit ");

		// SUC-002
		cf.screenShot("Sale of Business Complete", "Pass", "Launched to SUC-002");
		suc002.validateSaleOfBusinessText();
		cf.clickButton("Home ");
		cf.screenShot("Home", "Pass", "Launched to Home Screen");
		System.out.println("Worked");

	}

}
