package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_019_01_003_Verify_CSR_is_able_to_search_ERN_and_add_Related_Business_Details_of_a_Management_Agreement
		extends TestBase {

	@Test
	public void Tc_EM_019_01_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);
		HomePage homePage = new HomePage(driver);

		test = report.createTest(
				"Verify_CSR_is_able_to_search_ERN_and_add_Related_Business_Details_of_a_Management_Agreement");

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		test.info(
				"Step: 3 -- Navigate the Path Home page > Account Maintenance > Employer Account Maintenance > Joint Employment/Management Agreement Creation – Enter ERN");
		Thread.sleep(5000);
		// homePage.menu.click();
		// commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		Thread.sleep(1000);
		commonFuntions.ScrollMenu("Joint Employment/Management Agreement Creation");
		commonFuntions.clickMenu("Joint Employment/Management Agreement Creation");
		Thread.sleep(2000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Creation", "Pass",
				"SREG-520 screen is visible");

		// Map<String, String> databaseResults = PEOPage.database_SelectQuery(
		// "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE CREATED_BY !='LEGACY' AND EAN IS
		// NOT NULL");

		Map<String, String> databaseResults = PEOPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' AND EAN IS NOT NULL");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalNameValue = databaseResults.get("legalName");

		/*
		 * // GET method // valid EAN for 9 series Map<String, String> databaseEanResult
		 * = commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' AND EAN IS NOT NULL AND EAN LIKE '9%' AND LENGTH(EAN)=7 AND FEIN IS NOT NULL"
		 * , "EAN"); String eanValue = databaseEanResult.get("EAN");
		 * System.out.println("EAN value is " + eanValue);
		 * 
		 * // FEIN to the above EAN sleep(); Map<String, String> databaseFeinResult =
		 * commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 AND EAN = ',"
		 * + eanValue +"'","FEIN"); String feinValue = databaseFeinResult.get("FEIN");
		 * System.out.println("FEIN value is " + feinValue);
		 * 
		 * // legalname to the above EAN sleep(); Map<String, String>
		 * databaselegalNameResult = commonFuntions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI' AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 AND EAN = ',"
		 * + eanValue +"'", "ENTITY_NAME"); String legalNameValue =
		 * databaselegalNameResult.get("ENTITY_NAME");
		 * System.out.println("legal value is " + legalNameValue);
		 */

		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		Assert.assertTrue(sreg084.continueButton.isDisplayed());

		test.info("Step: 3 -- Click Continue Button.");
		sreg084.continueButton.click();
		Thread.sleep(2000);

		test.info("Step: 4 -- help/search/previous & continue button is visible");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg041.searchButton.isDisplayed());
		Assert.assertTrue(sreg084.previousButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());
		commonFuntions.screenShot("Joint Employment/Management Agreements", "Pass",
				" help/search/previous & continue button is visible");

		test.info("Step: 5 -- Click on search");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanValue);
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.screenShot("Joint Employment/Management Agreements", "Pass",
				"Successfully clicked on search button");

		test.info("Step: 6 -- Select the record an click on continue button");
		sreg084.selectradioBtn1.click();
		Thread.sleep(1000);
		sreg084.continueButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Related Business Details", "Pass", "SREG-007 page is displayed");
		Assert.assertTrue(sreg084.helpButton.isDisplayed());
		Assert.assertTrue(sreg084.previousButton.isDisplayed());
		Assert.assertTrue(sreg084.continueButton.isDisplayed());

		test.info(
				"Step: 7 -- Enter/Select the information in Related Business Details (SREG-007) screen with the below fields");
		commonFuntions.selectRadio("Management Agreement");
		Thread.sleep(2000);
		commonFuntions.selectRadioQuestions(
				"Are businesses financially related, with the same principal(s) owning 50% or more of each business?",
				"Yes");
		commonFuntions.selectRadioQuestions(
				"Is there a management agreement in place allowing one entity to hire, fire, supervise, direct and control the employees of another entity?",
				"Yes");
		commonFuntions.populateListbox("Remarks", "Test");
		commonFuntions.screenShot("Related Business Details", "Pass", "SREG-007 -- all details filled.");
		sreg084.continueButton.click();

		test.info("Step: 8 ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass",
				"SREG-114 screen is displayed");
		sreg084.addAnoterlink.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass",
				"SREG-110 screen is displayed");

		test.info("Step: 9 ");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanValue);
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.screenShot("Joint Employment/Management Agreements", "Pass",
				"Successfully clicked on search button");

		test.info("Step: 10 -- ");
		sreg084.selectradioBtn1.click();
		Thread.sleep(1000);
		sreg084.continueButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Related Business Details", "Pass", "SREG-007 page is displayed");

		test.info("Step: 11 -- ");
		sreg084.continueButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass",
				"SREG-114 screen is displayed");

		test.info("Step: 12 -- ");
		sreg084.continueButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass",
				"SREG-524 screen is displayed");

		test.info("Step: 13 -- ");
		sreg084.deleteLinkFirst.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass", "popUp is displayed");

		test.info("Step: 14 -- ");
		Thread.sleep(2000);
		commonFuntions.clickButton(" Yes ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Verify Joint Employment or Management Agreement", "Pass",
				"1 item is delete from list");

		test.info("Step: 15 -- ");
		commonFuntions.selectDateInTable(legalNameValue, 5, 1, "Joint Employment/Management Agreement Arrangement ",
				"03/10/2023");
		commonFuntions.clickButton("Submit ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"SUC-002 page is displayed");

		test.info("Step: 16 -- ");
		commonFuntions.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");

	}

}
