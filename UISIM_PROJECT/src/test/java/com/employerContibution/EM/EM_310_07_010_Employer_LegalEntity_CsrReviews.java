package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_07_010_Employer_LegalEntity_CsrReviews extends TestBase {

	@Test(priority = 1, description = "Verify Employer is able to change in Legal entity when predecessor provided information is erroneous and system create task for CSR reviews.", groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EM_310_07_010() throws Exception {

		test = report.createTest("EM.310.07.010 - Verify Employer is able to change in Legal entity when a gap status exists and system create task for CSR reviews.");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		// EAN <-> FEIN in DOL
		Map<String, String> databaseFeinResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT ACCOUNT_STATUS status, * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS IS NOT NULL AND EAN IS NOT NULL AND FEIN IS NOT NULL",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FEIN is " + feinValue);
		
		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT ACCOUNT_STATUS status, * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS IS NOT NULL AND EAN IS NOT NULL AND FEIN IS NOT NULL",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_1.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFuntions.waitForLoadingIconToDisappear();
		empManage.menu.click();
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Change in Legal Entity");
		sleep(1500);
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Account Maintenance -> Change in Legal Entity");
		commonFuntions.clickMenu("Change in Legal Entity");

		// --- SREG-012 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successfully launched to Change in Legal Entity(SREG-012) page");
		String retrievedFein = commonFuntions.retrieveValue("FEIN");
		System.out.println("The linked FEIN is " + retrievedFein);// linked FEIN - 24-1524929, EAN - 89-54756
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFuntions.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", feinValue); // 241524929
		commonFuntions.enterTextboxContains("Prior Employer Registration Number", eanValue); // 8954756
		commonFuntions.enterCurrentDate("Date of Legal Entity change"); //06/23/2023
		sleep(2000);
		commonFuntions.screenShot("EM31007010", "Pass", "Entered details in SREG-012 page");
		commonFuntions.clickButtonContains("Continue ");

		// --- EM-007 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successfully launched to Change in Legal Entity Details(EM-007) page");
		commonFuntions.clickButtonContains("Submit ");

		// --- SUC-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successful launch to Change in Legal Entity Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");

		// Employer logout -> CSR login
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);

	    commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1 + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='" + retrievedFein + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);

		peoPage.queue.click();
		Thread.sleep(15000);
	  	commonFuntions.enterTextboxContains("FEIN", retrievedFein);
		commonFuntions.screenShot("FeinSearch", "Pass", "FEIN Search");
		commonFuntions.clickButtonContains("Search");

		sleep(3000);
		commonFuntions.ScrollMenu("Verify Transfer Failed Rules");
		sleep();
		commonFuntions.screenShot("WIClick", "Pass", "Clicked on Work Item - 'Verify Transfer Failed Rules'");
		sleep();
		commonFuntions.clickOnLink("Verify Transfer Failed Rules");

		// --- WF-091 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successful launch to Work Item Details(WF-091) page");
		commonFuntions.clickButtonContains("Open Work Item ");

		// --- EEWI-014 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successful launch to Validate Total/Partial Transfer Failed Rules Task(EEWI-014) page");
		commonFuntions.enterTextboxContains("Comment", "Testing");
		commonFuntions.screenShot("EM31007010", "Pass", "Entered details in EEWI-014 page");
		commonFuntions.clickButtonContains("Submit ");

		// --- SUC-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "Successful launch to Task Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM31007010", "Pass", "TC EM.310.07.010 passed successfully.");

	}

}
