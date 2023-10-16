package com.employerContribution.EOA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.FIS_009;
import com.ui.pages.AddressPage;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_002_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_TPR_FEIN_Legal_name
		extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify the UI Online Services Account Creation process (TPR) - (FEIN, Legal name)", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_01_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);
		FIS_009 fis009 = new FIS_009(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"EOA_01_002_Online_Authentication_Verify_the_UI_Online_Services_Account_Creation_process_TPR_FEIN_Legal_name");

		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		test.info("Step: 1 -- ");
		commonFuntions.screenShot("UI Services Account Creation", "Pass",
				"UI Services Account Creation Popup is displayed");
		sleep(1000);

		test.info("Step: 2 -- ");
		eoaPage.tprRepresentativeRadio.click();
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("UI Services Third Party Representative Account Creation", "Pass",
				"SREG-612 screen is displayed");

		test.info("Step: 3 -- ");
		// FEIN
		/*Map<String, String> feinValue = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN IS NOT NULL AND EAN IS NOT NULL AND EFIN IS NOT NULL;",
				"FEIN");
		String fein = feinValue.get("FEIN");
		System.out.println(fein);
		test.log(Status.INFO, "FEIN used on : " + fein);
		*/

		// LEGAL NAME
		Map<String, String> COMPANY_NAME_Value = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT tfti.EFIN,ttpa.COMPANY_NAME,ttpa.TPR_ID FROM T_THIRD_PARTY_AGENT ttpa JOIN T_FTI_TPR_INFO tfti ON ttpa.THIRD_PARTY_AGENT_ID = tfti.THIRD_PARTY_AGENT_ID;",
				"COMPANY_NAME");
		String legalName = COMPANY_NAME_Value.get("COMPANY_NAME");
		System.out.println(legalName);
		test.log(Status.INFO, "LEGAL used on : " + legalName);

		// EAN
		/*Map<String, String> EAN_Value = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT COMPANY_NAME, FEIN, EAN, EFIN, TPR_ID FROM T_THIRD_PARTY_AGENT WHERE FEIN IS NOT NULL AND EAN IS NOT NULL AND EFIN IS NOT NULL;",
				"EAN");
		String ean = EAN_Value.get("EAN");
		System.out.println(ean);
		test.log(Status.INFO, "EAN used : " + ean);
*/
		// EFIN
		Map<String, String> EFIN_Value = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT tfti.EFIN,ttpa.COMPANY_NAME,ttpa.TPR_ID FROM T_THIRD_PARTY_AGENT ttpa JOIN T_FTI_TPR_INFO tfti ON ttpa.THIRD_PARTY_AGENT_ID = tfti.THIRD_PARTY_AGENT_ID;",
				"EFIN");
		String efin = EFIN_Value.get("EFIN");
		System.out.println(efin);
		test.log(Status.INFO, "EFIN used: " + efin);

		// TPR ID
		Map<String, String> TPR_ID_Value = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT tfti.EFIN,ttpa.COMPANY_NAME,ttpa.TPR_ID FROM T_THIRD_PARTY_AGENT ttpa JOIN T_FTI_TPR_INFO tfti ON ttpa.THIRD_PARTY_AGENT_ID = tfti.THIRD_PARTY_AGENT_ID;",
				"TPR_ID");
		String tprID = TPR_ID_Value.get("TPR_ID");
		System.out.println(tprID);
		test.log(Status.INFO, "EFIN used: " + tprID);

		// commonFuntions.enterTextboxContains("Federal Employer Identification Number
		// (FEIN)", fein);
		// commonFuntions.enterTextboxContains("Employer Registration Number (ERN)",
		// ean);
		commonFuntions.enterTextboxContains("Electronic Filer Identification Number (EFIN)", efin);
		sleep(2000);
		commonFuntions.enterTextboxContains(" TPR ID", tprID);
		commonFuntions.enterTextboxContains("Legal Name of Business", legalName);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		try {
			test.info("Step: 4 -- ");
			fis009.selectCheckboxfis009(" Opt-in of SIDES E-Response auto-enrollment. ");
			commonFuntions.screenShot("SIDES E-Response Auto-enrollment", "Pass", "EOA-003 screen is displayed");
			commonFuntions.clickButtonContains("Submit ");
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);
			commonFuntions.screenShot("SIDES E-Response Auto-enrollment Confirmation", "Pass",
					"SUC-002 screen is displayed");

			test.info("Step: 5 -- ");
			commonFuntions.clickButtonContains("Continue ");
			commonFuntions.waitForLoadingIconToDisappear();
			sleep(2000);

		} catch (Exception e) {
			System.out.println("EOA-003 & SUC-002 screen got skipped");
		}
		sleep();

		test.info("Step: 6 -- ");
		commonFuntions.screenShot("Contact Information", "Pass", "EOA-002 screen is displayed");
		commonFuntions.enterTextboxContains("First Name", "Dev");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.selectRadioQuestions("Do you want to sign up for Electronic Correspondence?", "Yes");
		commonFuntions.enterTextboxContains("E-mail Address", "test2@gmail.com");
		commonFuntions.enterTextboxContains(" Contact Number ", "1234567890");
		commonFuntions.enterTextboxContains(" Cell Number ", "1234567098");
		commonFuntions.selectCheckbox("Same as Contact Number");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("UI Services Account Creation Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 7 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("HomePage", "Pass", "Home screen is displayed");

		test.info("Step: 8 -- ");
		// Unable to automate this step

	}

}
