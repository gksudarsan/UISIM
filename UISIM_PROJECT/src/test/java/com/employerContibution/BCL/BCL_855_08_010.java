package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.COL_474;
import com.ui.pages.BclPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_855_08_010 extends TestBase{
	
	@Test
	public void BCL855_08_010() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_474 col474 = new COL_474(driver); 
		BclPage bclPage = new BclPage(driver);

		test = report.createTest(
				"BCL.855.08.010 - Verify CSR can search ERN details and update existing prosecution for Prosecution Type is 'Other' with comments and status is 'Judgment'");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_CollectionsSpecialistThree.toUpperCase(), COMMON_CONSTANT.CSR_USER_CollectionsSpecialistThree_PASSWORD);
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		commonFuntions.ScrollMenu("Prosecution");
		commonFuntions.screenShot("Prosecution", "Pass", "Prosecution");
		commonFuntions.clickMenu("Prosecution");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution", "Pass", "COL-593 screen is displayed");
		//Add PROSECUTION
		
		/*Map<String, String> EANinput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT WHERE ACCOUNT_STATUS = 'LIAB' and REGISTRATION_STATUS = 'C';",
				"EAN");
		String eanadd= EANinput.get("EAN");
		System.out.println(eanadd);
		
		commonFuntions.enterTextboxContains("Employer Registration Number", eanadd);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Prosecutions", "Pass", "COL-594 screen is displayed");
		
		sleep(1000);
		commonFuntions.clickButtonContains(" Add Prosecution ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "COL-595 screen is displayed");
		commonFuntions.enterTextboxContains("a. First Name", "Den");
		commonFuntions.enterTextboxContains("c. Last Name", "Mark");
		commonFuntions.enterTextboxContains("3. Prosecution Case Number", "123456");
		commonFuntions.selectDropdown("4. Prosecution Type", " Criminal ");
		commonFuntions.enterCurrentDate("5. Date Prosecution Proceedings Commenced");
		commonFuntions.enterTextboxContains("6. Assigned To", "annie");
		commonFuntions.selectDropdown("7. Status", " Plea Agreement ");
		bclPage.comments.sendKeys("test comm");
		sleep(1000);
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "COL-595 screen is displayed");
		sleep(1000);
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution Confirmation", "Pass", "SUC-002 screen is displayed");
		
		commonFuntions.clickButtonContains("Home ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Home screen is displayed");
		*/
		
		//
		Map<String, String> EANOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PROSECUTION ttp JOIN T_EMPLOYER te ON ttp.EMPLOYER_ID = te.EMPLOYER_ID;",
				"EAN");
		String eanCorrect = EANOutput.get("EAN");
		System.out.println(eanCorrect);
		
		
		test.info("Step: 4 -- ");
		/*commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		commonFuntions.ScrollMenu("Prosecution");
		commonFuntions.screenShot("Prosecution", "Pass", "Prosecution");
		commonFuntions.clickMenu("Prosecution");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution", "Pass", "COL-593 screen is displayed");
		*/
		commonFuntions.enterTextboxContains("Employer Registration Number", eanCorrect);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Prosecutions", "Pass", "COL-594 screen is displayed");
		
		test.info("Step: 5 -- ");
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Update Prosecution Details", "Pass", "COL-596 screen is displayed");
		
		test.info("Step: 6-- ");
		/*Map<String, String> ProCaseNumber = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PROSECUTION ttp JOIN T_EMPLOYER te ON ttp.EMPLOYER_ID = te.EMPLOYER_ID;",
				"PROSECUTION_CASE_NUMBER");
		String caseNumber = ProCaseNumber.get("PROSECUTION_CASE_NUMBER");
		System.out.println(caseNumber);
		*/
		
		//
		commonFuntions.forceClearTextWithElement("3. Prosecution Case Number");
		commonFuntions.forceClearTextWithElement("6. Assigned To");
		//
		
		commonFuntions.enterTextboxContains("3. Prosecution Case Number", "98654");
		
		sleep(1000);
		commonFuntions.selectDropdown("4. Prosecution Type", " Other ");
		commonFuntions.selectDropdown("7. Status", " Judgement ");
		sleep(2000);
		commonFuntions.screenShot("Update Prosecution Details", "Pass", "COL-596 screen is displayed");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 7-- ");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Home screen is displayed");
		
	}

}
