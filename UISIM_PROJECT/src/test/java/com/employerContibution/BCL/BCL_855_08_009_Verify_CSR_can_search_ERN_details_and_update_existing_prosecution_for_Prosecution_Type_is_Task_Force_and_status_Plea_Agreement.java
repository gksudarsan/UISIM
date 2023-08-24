//------------------------Palak---------------------------------------

package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_08_009_Verify_CSR_can_search_ERN_details_and_update_existing_prosecution_for_Prosecution_Type_is_Task_Force_and_status_Plea_Agreement
		extends TestBase {
	@Test
	public void BCL_855_08_009() throws Exception {

		test = report.createTest(
				"BCL.855.08.009 - Verify_CSR_can_search_ERN_details_and_update_existing_prosecution_for_Prosecution_Type_is_Task_Force_and_status_Plea_Agreement ");
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// --------Login-------
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();
        //-------DB---
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT tea.EAN,* FROM T_TX_PROSECUTION ttp INNER JOIN T_REGULAR_EMPLOYER tre ON ttp.EMPLOYER_ID = tre.EMPLOYER_ID INNER JOIN T_EMPLOYER_ACCOUNT tea ON tea.EMPLOYER_ACCOUNT_ID = tre.EMPLOYER_ACCOUNT_ID","EAN");
		
		String eanNumber=databaseResults.get("EAN");
		
		 if ((eanNumber == null) || eanNumber.isEmpty()) {
	            System.out.println("EAN value is null");
	    } else {
	            test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanNumber + ".");
	    }
		
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
		BclPage.ListofProsecutions_Radio.click();
		//commonFuntions.selectRadio("Select");
		//commonFuntions.clickButtonContains(" Add Prosecution ");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// -------COL-596 -----
		commonFuntions.screenShot("Enter Prosecution Details", "Pass",
				"Successfully landed on Prosecution Details COL-596 screen");

		commonFuntions.enterTextboxContains("a. First Name", "Ken");
		commonFuntions.enterTextboxContains("c. Last Name", "Disuza");
		commonFuntions.enterTextboxContains("3. Prosecution Case Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999)));
		commonFuntions.selectDropdown("4. Prosecution Type", " Task Force ");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "05/07/2023");
		commonFuntions.selectDropdown("7. Status", " Plea Agreement ");
		commonFuntions.enterTextboxContains("6. Assigned To", "Anie");
		commonFuntions.enterTextboxContains("8. Comments", "Anie");
		home.commentBox.sendKeys("Updating prosecution");
		sleep(2000);
		commonFuntions.screenShot("Update Prosecution Details", "Pass",
				" Valid Prosecution Details Entered on COL-596 screen");
		commonFuntions.clickButtonContains("Submit ");

		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Prosecution Confirmation  ", "Pass", "Successfully landed on  ACOR-001 screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		/*
		commonFuntions.clickOnLinkAnchorTag(" Generate Correspondence ");
		

		// ----------ACOR-001
		commonFuntions.screenShot("Select Corresspondance ", "Pass", "Successfully landed on  ACOR-001 screen");
		commonFuntions.clickButtonContains("Home ");
		*/
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL_855_08_009", "Pass", "Successfully passed TC BCL_855_08_009");

		System.out.println("Pass :)");
		
		//Completed Palak
	}

}
