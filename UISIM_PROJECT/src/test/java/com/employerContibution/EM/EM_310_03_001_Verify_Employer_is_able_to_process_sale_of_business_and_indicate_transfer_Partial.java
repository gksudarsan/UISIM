package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.SREG_503;
import com.ui.pages.SUC_002;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_03_001_Verify_Employer_is_able_to_process_sale_of_business_and_indicate_transfer_Partial
		extends TestBase {

	@Test
	public void Tc_310_03_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);
		SREG_503 sreg503 = new SREG_503(driver);
		SUC_002 suc002 = new SUC_002(driver);
		
		

		test = report.createTest(
				"EM_310_03_001_Verify_Employer_is_able_to_process_sale_of_business_and_indicate_transfer_Partial");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info("Step: 3 -- Navigate to Main Menu -> Account Maintenance -> Maintain Address – Enter ERN");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Sale of Business");
		commonFuntions.clickMenu("Sale of Business");
		Thread.sleep(2000);
		commonFuntions.screenShot("Sale of Business", "Pass", "SREG-503 screen is visible");

		test.info("Step: 4");
		Map<String, String> databaseResults = sreg503.database_SelectQuery_Successor(
				"SELECT * FROM T_EMPLOYER_TRANSFER_SUCCESSOR WHERE NOT_100_PERCENT_TRANSFER_EXPLAIN = 'Partial Transfer'");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		System.out.println("legalName " + legalName);

		commonFuntions.selectRadioQuestions("Have you sold your business?", "Yes");
		Thread.sleep(2000);
		commonFuntions.selectRadioQuestions("Did you sell all or part of your business?", "Part");
		Thread.sleep(2000);
		commonFuntions.enterCurrentDate("Effective Date of Transfer");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Successor`s Name", legalName.trim());
		commonFuntions.enterTextboxContains("Successor`s FEIN", feinValue);
		commonFuntions.enterTextboxContains("Successor`s ERN", eanValue);
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Attention/Care Of", "Einser Com");
		commonFuntions.enterTextboxContains("Address Line 1 ", "testadd");
		commonFuntions.enterTextboxContains("Address Line 2 ", "james");
		commonFuntions.enterTextboxContains("City ", "Boston");
		Thread.sleep(1000);
		//commonFuntions.selectDropdown("Country", " United States ");
		Thread.sleep(1000);
		commonFuntions.selectDropdown("State", " New York ");
		Thread.sleep(1000);
		commonFuntions.enterTextboxContains("Zip Code", "02110");
		sreg503.successorTelePhoneInput.sendKeys("6174577513");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Sale of Business", "Pass", "SREG-504 screen is visible");
		
		test.info("Step: 5&6");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Employer Address and Contact Person Details Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 7");
		//suc002.validateSaleOfBusinessText();
		commonFuntions.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");
	}
}
