//------------------------Palak---------------------------------------

package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_08_001_Verify_CSR_can_search_ERN_details_and_add_new_prosecution_for_Prosecution_Type_is_Criminal_and_status_is_Active
		extends TestBase {
	@Test
	public void BCL_855_08_001() throws Exception {

		test = report.createTest(
				"BCL.855.08.001 - Verify CSR can search ERN details and add new prosecution for Prosecution Type is  'Criminal' and status is 'Active' ");
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// --------Login-------
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT tea.EAN,* FROM T_TX_PROSECUTION ttp INNER JOIN T_REGULAR_EMPLOYER tre ON ttp.EMPLOYER_ID = tre.EMPLOYER_ID INNER JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID","EAN");
		
		String eanNumber=databaseResults.get("EAN");//0509531
		
		// --------Menu---------
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(1000);
		commonFuntions.screenShot("menu", "Pass", "selected option");
		commonFuntions.clickMenu("Prosecution");
		sleep(1000);

		// ----COL-593
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution", "Pass", "Successfully launched to COL-593 page");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Prosecution", "Pass", "Entered nothing on ERN:  COL-593 page");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number", "6778563");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Prosecution", "Pass", "Entered  ERN with no record on  COL-593 page");
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111111");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Prosecution", "Pass", "Entered invalid ERN on  COL-593 page");
		sleep(2000);
		commonFuntions.screenShot("Prosecution", "Pass", "Entered  ERN with no record on  COL-593 page");
		//commonFuntions.enterTextboxContains("Employer Registration Number", "4730215");
		commonFuntions.enterTextboxContains("Employer Registration Number",eanNumber);
		sleep(2000);
		commonFuntions.screenShot("Prosecution", "Pass", "Entered valid ERN on  COL-593 page");
		commonFuntions.clickButtonContains("Continue ");

		// --- COL-594 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Prosecutions", "Pass",
				"Successfully launched to List of Prosecutions(COL-594) screen");
		sleep(2000);
		commonFuntions.clickButtonContains(" Add Prosecution ");
		commonFuntions.waitForLoadingIconToDisappear();

		// -------COL-595 -----
		commonFuntions.screenShot("Enter Prosecution Details", "Pass",
				"Successfully landed on Prosecution Details COL-595 screen");
		sleep(2000);

		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.screenShot("Enter Prosecution Details", "Pass",
				"Details not filled, please Enter Prosecution Details on COL-595 screen");

		commonFuntions.enterTextboxContains("a. First Name", "Ken");
		commonFuntions.enterTextboxContains("c. Last Name", "Disuza");
		commonFuntions.enterTextboxContains("3. Prosecution Case Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999)));
		commonFuntions.selectDropdown("4. Prosecution Type", " Criminal ");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "25/07/2023");
		commonFuntions.selectDropdown("7. Status", " Active ");
		commonFuntions.enterTextboxContains("6. Assigned To", "Anie");
		commonFuntions.enterTextboxContains("8. Comments", "Anie");
		home.commentBox.sendKeys("Testing automation ");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "Please enter valid date on COL-595 screen");

		commonFuntions.enterTextboxContains("a. First Name", "Ken");
		commonFuntions.enterTextboxContains("c. Last Name", "Disuza");
		commonFuntions.enterTextboxContains("3. Prosecution Case Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999)));
		commonFuntions.selectDropdown("4. Prosecution Type", " Criminal ");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "05/07/2023");
		commonFuntions.selectDropdown("7. Status", " Active ");
		commonFuntions.enterTextboxContains("6. Assigned To", "Anie");
		home.commentBox.sendKeys("Testing text box");
		sleep(2000);
		commonFuntions.screenShot("Enter Prosecution Details", "Pass",
				" Valid Prosecution Details Entered on COL-595 screen");
		commonFuntions.clickButtonContains("Submit ");

		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution Confirmation  ", "Pass", "Successfully landed on  ACOR-001 screen");
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag(" Generate Correspondence ");
		// commonFuntions.clickButtonContains("Home ");

		// ----------ACOR-001
		commonFuntions.screenShot("Select Corresspondance ", "Pass", "Successfully landed on  ACOR-001 screen");
		commonFuntions.clickButtonContains("Home ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL_855_08_001", "Pass", "Successfully passed TC BCL_855_08_001");

		System.out.println("Pass :)");

		//Completed Palak
	}

}
