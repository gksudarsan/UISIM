package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BillingCollectionLiensLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_802_03_001_CSR_GenerateProofClaimReport_UsedCompleteClaimForm extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can Generate Proof of Claim Report which is used to complete the Proof of Claim form.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BCL_802_03_001() throws Exception {
		
		test = report.createTest("BCL.802.03.001 : Verify CSR can Generate Proof of Claim Report which is used to complete the Proof of Claim form.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		BillingCollectionLiensLocators bclLocators = new BillingCollectionLiensLocators(driver);
		
		//GET method
		// valid ERN where employer has existing Bankruptcy record
		/*Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");*/
		
		String eanValue = "0464364";
		
		if ((eanValue == null) || eanValue.isEmpty())
		{
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "The EAN value used is " + eanValue + ".");
		}
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.COLLECTIONS_SPECIALIST_THREE.toUpperCase(), COMMON_CONSTANT.COLLECTIONS_SPECIALIST_THREE_PASSWORD);
		test.log(Status.PASS, "Login with Collections Specialist 3 role is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		bclLocators.menu.click();
		commonFunction.ScrollMenu("Contribution Collection");
		commonFunction.clickMenu("Contribution Collection");
		bclLocators.bankruptcyMenuLocator.click();
		commonFunction.ScrollMenu("Generate Proof of Claim-Enter ERN");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution Collection -> Bankruptcy -> Generate Proof of Claim-Enter ERN");
		commonFunction.clickMenu("Generate Proof of Claim-Enter ERN");
		
		// --- COL-540 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80203001", "Pass", "Successfully launched to Generate Proof of Claim - Enter ERN(COL-540) screen");
		
		commonFunction.clickButtonContains(" Search ");
		sleep(3000);
		commonFunction.screenShot("BCL80203001", "Pass", "Error on empty Search");
		
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", "0463876");
		commonFunction.clickButtonContains(" Search ");
		sleep(3000);
		commonFunction.screenShot("BCL80203001", "Pass", "Error on ERN Search without Bankruptcy case");
		
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		sleep();
		commonFunction.enterTextboxContains("Owner SSN", "75653");
		commonFunction.clickButtonContains(" Search ");
		sleep(3000);
		commonFunction.screenShot("BCL80203001", "Pass", "Error on invalid SSN Search");
		
		sleep();
		commonFunction.enterTextboxContains("Owner SSN", "");
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //9073158
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.selectRadioQuestions("Do you want to generate the report?", "Yes ");
		sleep(2000);
		commonFunction.screenShot("BCL80203001", "Pass", "Entered proper ERN with Bankruptcy cases and click on Continue");
		commonFunction.clickButtonContains("Continue ");
		
		// --- COL-542 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("BCL80203001", "Pass", "Successfully launched to Generate Proof of Claim Verification(COL-542) screen");
		
		commonFunction.clickOnLinkAnchorTag("Open Report ");
		
		
		sleep(20000);
		commonFunction.verifyContentInPDf("PROOF OF CLAIM");
		test.log(Status.PASS, "Expected text 'PROOF OF CLAIM' found in PDF.");
		sleep(2000);
		commonFunction.screenShot("BCL80203001", "Pass", "TC BCL.802.03.001 passed successfully");
		
		
		System.out.println("Passed :)");
		
	}

}
