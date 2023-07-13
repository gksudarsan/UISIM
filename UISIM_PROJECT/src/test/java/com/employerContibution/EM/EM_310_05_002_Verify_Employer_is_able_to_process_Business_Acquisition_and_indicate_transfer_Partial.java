package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SREG_503;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_011;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_05_002_Verify_Employer_is_able_to_process_Business_Acquisition_and_indicate_transfer_Partial
		extends TestBase {

	@Test
	public void Tc_EM_310_05_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_503 sreg503 = new SREG_503(driver);
		SREG_011 sreg011 = new SREG_011(driver);

		test = report.createTest(
				"EM_310_05_002_Verify_Employer_is_able_to_process_Business_Acquisition_and_indicate_transfer_Partial");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 3 -- Navigate to Main Menu -> Account Maintenance -> Business Acquisition");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Business Acquisition");
		commonFuntions.clickMenu("Business Acquisition");
		Thread.sleep(2000);
		commonFuntions.screenShot("Maintain Address", "Pass", "SREG-011 screen is visible");

		test.info("Step: 4 -- ");
		Map<String, String> databaseResults = sreg503.database_SelectQuery_Successor(
				"SELECT * FROM T_EMPLOYER_TRANSFER_SUCCESSOR WHERE NOT_100_PERCENT_TRANSFER_EXPLAIN = 'Partial Transfer'");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		System.out.println("legalName " + legalName);
		
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		Thread.sleep(2000);
		//commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		sreg011.eanField.sendKeys(eanValue);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		Thread.sleep(1000);
		sreg011.legalNameField.sendKeys("Home Inc");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 Madson Road");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("State", " New York ");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		Thread.sleep(1000);
		commonFuntions.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		Thread.sleep(2000);
		commonFuntions.enterCurrentDate("Acquisition Date");
		Thread.sleep(1000);
		commonFuntions.enterCurrentDate("Notification date of Transfer");
		Thread.sleep(1000);
		commonFuntions.clickButton("Continue ");
		
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		
		Thread.sleep(2000);
		commonFuntions.screenShot("Business Acquisition Details", "Pass", "EM-006 screen is visible");
		
		test.info("Step: 6 -- ");
		Thread.sleep(2000);
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Business Acquisition Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 7 -- ");
		commonFuntions.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");
	}

}
