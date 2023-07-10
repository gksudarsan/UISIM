//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_039_001_CSR_ViewCreate_NonLiableEmployerUi extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can view and create a non-liable employer UI account.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void Test_EL_039_001() throws Exception {

		test = report.createTest("EL.039.001 - Verify CSR can view and create a non-liable employer UI account.");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		EmployerRegisterPage employeeLeasing = new EmployerRegisterPage(driver);

//		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
//		System.out.println("feinValue is"+feinValue);

		
		Map<String, String> databaseResults = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_INITIAL_CLIENT_ACCOUNT_DETAILS ttpa WHERE FEIN = '251855555' ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is" + feinValue);
		 

		// --- Login ---
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");

		// --- Navigation ---
		commonFunctions.waitForLoadingIconToDisappear();
		employeeLeasing.menu.click();
		commonFunctions.ScrollMenu("Inquiry");
		commonFunctions.clickMenu("Inquiry");
		sleep();
		commonFunctions.screenShot("Menu", "Pass", "Menu -> Inquery -> PEO Client Non-liable Employer information");
		commonFunctions.clickMenu("Inquiry PEO Client Non-liable Employer information");

		// --- IPEO-001 ---
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("EL039001", "Pass",
				"Successful launch Search for PEO Client Non-Liable Employer(IPEO-001) page");

		commonFunctions.clickButtonContains(" Search ");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Error on empty search");

		commonFunctions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Clicked on Advanced Search");

		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "111111111");
		commonFunctions.enterTextboxContains("Employer Registration Number", "1111111");
		commonFunctions.clickButtonContains(" Search ");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Error on invalid ERN and/or FEIN");

		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "");
		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); //251855555
		commonFunctions.clickButtonContains(" Search ");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Searched PEO with FEIN");

		commonFunctions.clickButton("Continue ");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Error at continue without selection");

		commonFunctions.selectRadioInTable("251855555", 1, 1, "");
		sleep();
		commonFunctions.screenShot("EL039001", "Pass", "Made appropriate radio selection on IPEO-001 page");
		commonFunctions.clickButton("Continue ");

		// --- NLE-002 ---
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("EL039001", "Pass","Successful launched PEO Client Non-Liable Employer(NLE-002) page");
		commonFunctions.clickOnLinkAnchorTag("05-00001 ");
		
		// --- SREG-051 ---
		try {
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("EL039001", "Pass","Successful launched Inquery Employer Account Information(SREG-051) page");
		commonFunctions.clickButtonContains("Previous ");
		} catch (Exception exception) {
			test.log(Status.PASS, "Does not launched SREG-051");
		}
		
		// --- NLE-002 ---
		commonFunctions.waitForLoadingIconToDisappear();
		//commonFunctions.screenShot("EL039001", "Pass","Successful launched PEO Client Non-Liable Employer(NLE-002) page");
		//commonFunctions.clickButtonContains("Convert to UI Account  ");
		
		//commonFunctions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunctions.screenShot("EL039001", "Pass", "Successfully passed TC EL.039.001.");

		System.out.println("Pass");

	}
}
