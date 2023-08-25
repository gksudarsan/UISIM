package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase; 
import com.ui.pages.COL_592; 
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_08_002 extends TestBase{
	
	@Test
	public void BCL855_08_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_592 col592 = new COL_592(driver); 
		SUC_002 suc002 = new SUC_002(driver);

		test = report.createTest(
				"BCL.855.08.002 -  Verify CSR can search ERN details and add new prosecution for Prosecution Type is 'Civil' and status is 'Closed'");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		commonFuntions.ScrollMenu("Prosecution");
		commonFuntions.screenShot("Prosecution", "Pass", "Prosecution");
		commonFuntions.clickMenu("Prosecution");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution", "Pass", "COL-593 screen is displayed");
		
		Map<String, String> EANOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PROSECUTION ttp JOIN T_EMPLOYER te ON ttp.EMPLOYER_ID = te.EMPLOYER_ID;",
				"EAN");
		String eanCorrect = EANOutput.get("EAN");
		System.out.println(eanCorrect);
		
		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanCorrect);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Prosecutions", "Pass", "COL-594 screen is displayed");
		
		test.info("Step: 5 -- ");
		commonFuntions.clickButtonContains(" Add Prosecution ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "COL-595 screen is displayed");
		
		test.info("Step: 6-- ");
		commonFuntions.enterTextboxContains("a. First Name", "Dev");
		commonFuntions.enterTextboxContains("c. Last Name", "tes");
		
		Map<String, String> ProCaseNumber = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PROSECUTION ttp JOIN T_EMPLOYER te ON ttp.EMPLOYER_ID = te.EMPLOYER_ID;",
				"PROSECUTION_CASE_NUMBER");
		String caseNumber = ProCaseNumber.get("PROSECUTION_CASE_NUMBER");
		System.out.println(caseNumber);
		
		commonFuntions.enterTextboxContains("3. Prosecution Case Number", caseNumber);
		commonFuntions.selectDropdown("4. Prosecution Type", " Civil ");
		commonFuntions.enterCurrentDate("5. Date Prosecution Proceedings Commenced");
		commonFuntions.enterTextboxContains("6. Assigned To", "anna");
		commonFuntions.selectDropdown("7. Status", " Closed ");
		col592.col595commentfield.sendKeys("test comment");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 7-- ");
		suc002.generateCorrespondenceLink.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Correspondence", "Pass", "ACOR-001 screen is displayed");
		
		test.info("Step: 8-- ");
		commonFuntions.clickButtonContains(" Home ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Home screen is displayed");
		
	}

}
