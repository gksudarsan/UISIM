package com.beneits.BAD;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BenefitsAdjeustmentDebtLocators;
import com.ui.pages.CaPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BA_504_02_001_CsrAllowedInvalidateRegClaim_NoPaidWeekExiting extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify the CSR is allowed to successfully invalidate a Regular claim with no paid weeks existing.", groups = COMMON_CONSTANT.REGRESSION)
	public void TC_BA_504_02_001() throws Exception {
		
		test = report.createTest("BA.504.02.001 : Verify the CSR is allowed to successfully invalidate a Regular claim with no paid weeks existing.");
		
		test.log(Status.INFO, "TC Script developed by Ankan Das");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		BenefitsAdjeustmentDebtLocators badLocators = new BenefitsAdjeustmentDebtLocators(driver);
		
		//GET Query
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT CLAIMANT_IDENTIFIER, c2.* FROM T_claimant c1, T_claim c2\r\n"
				+ "WHERE c1.CLAIMANT_ID = c2.CLAIMANT_ID\r\n"
				+ "AND c2.STATUS = 'ACTV'\r\n"
				+ "AND c2.BYE_DATE > CURRENT_DATE\r\n"
				+ "AND c2.EFFECTIVE_DATE < CURRENT_DATE\r\n"
				+ "AND CLAIMANT_IDENTIFIER IS NOT NULL \r\n"
				+ "ORDER BY CLAIMANT_ID DESC LIMIT 200;" ,"CLAIMANT_IDENTIFIER");
		String claimantIdentifier = databaseResults.get("CLAIMANT_IDENTIFIER");
		System.out.println("The Claimant Identifier fetched is " + claimantIdentifier);
		
		if ((claimantIdentifier == null) || claimantIdentifier.isEmpty())
		{
			System.out.println("Claimant Identifier is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched Claimant Identifier is " + claimantIdentifier + ".");
		}
		
		// --- Login ---
		commonFuntions.benefitsLogin(COMMON_CONSTANT.BASIC_BENEFITS_ACCESS.toUpperCase(), COMMON_CONSTANT.BASIC_BENEFITS_ACCESS_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Access role is successful.");
		
		// ---Menu Click---
		commonFuntions.waitForLoadingIconToDisappear();
		badLocators.menu.click();
		commonFuntions.clickMenu("Benefit Maintenance");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigation : Menu -> Benefit Maintenance -> Invalidate a Claim");
		commonFuntions.clickMenu("Invalidate a Claim");
		
		/// --- MON-071 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BA50402001", "Pass", "Successfully launched to Invalidate Claim - Enter SSN(MON-071) screen");
		
			//claimant with no record
		commonFuntions.enterTextboxContains("Claimant ID", "2136946982");
		sleep();
		commonFuntions.screenShot("BA50402001", "Pass", "Claimant ID with no record entered.");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("BA50402001", "Pass", "Error: No record found for the entered Claimant ID.");
		
			//invalid claimant
		commonFuntions.enterTextboxContains("Claimant ID", "");
		sleep();
		commonFuntions.enterTextboxContains("Claimant ID", "9999999999");
		commonFuntions.screenShot("BA50402001", "Pass", "Invalid Claimant ID entered.");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("BA50402001", "Pass", "Error: No record found for the entered Claimant ID.");		
		
			// valid claimant
		commonFuntions.enterTextboxContains("Claimant ID", "");
		sleep();
		commonFuntions.enterTextboxContains("Claimant ID", claimantIdentifier); //23-48450980
		sleep();
		commonFuntions.screenShot("BA50402001", "Pass", "Correct Claimant ID entered.");
		commonFuntions.clickButton("Continue ");
		
		// --- MON-072 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BA50402001", "Pass", "Successfully launched to Invalidate Claim(MON-072) screen");
		commonFuntions.clickButton("Submit ");
		sleep(2000);
		commonFuntions.screenShot("BA50402001", "Pass", "Error: Select a claim to be invalidated or Reinstated.");	
		
		//#step over
		commonFuntions.selectRadioInTable("", 1, 1, "Active");
		sleep();
		commonFuntions.screenShot("BA50402001", "Pass", "Radio selected with Active status.");
		commonFuntions.clickButton("Submit ");
		
		// --- SUC-002 ---
		commonFuntions.waitForLoadingIconToDisappear(); //average waiting 15s
		commonFuntions.screenShot("BA50402001", "Pass", "Successfully launched to Cancel Claim Confirmation(SUC-002) screen");
		commonFuntions.clickButton("Home ");
		
		// ********** NAVIGATION 2 **********
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.BASIC_BENEFITS_INQUERY, COMMON_CONSTANT.BASIC_BENEFITS_INQUERY_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry role is successful.");
		
		// ---Menu Click---
		commonFuntions.waitForLoadingIconToDisappear();
		badLocators.menu.click();
		commonFuntions.clickMenu("Claim Information");
		commonFuntions.clickMenu("Benefits");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigation : Menu -> Claim Information -> Benefits -> View Claim Information");
		commonFuntions.clickMenu("View Claim Information");
		
		// --- INQ-001 ---
		commonFuntions.waitForLoadingIconToDisappear(); //average waiting 15s
		commonFuntions.screenShot("BA50402001", "Pass", "Successfully launched to Claimant Search(INQ-001) screen");
		commonFuntions.enterTextboxContains("Claimant ID", claimantIdentifier);
		sleep();
		commonFuntions.screenShot("BA50402001", "Pass", "Claimant ID entered as Search Criteria");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("BA50402001", "Pass", "Error on clicking Continue: A selection is required.");
		
		commonFuntions.clickButtonContains(" Search ");
		sleep(5000); // average load time 7s
		commonFuntions.screenShot("BA50402001", "Pass", "Data pouplated on Search button click");
		
		commonFuntions.selectRadioInTable(claimantIdentifier, 1, 1, "");
		sleep(); 
		commonFuntions.screenShot("BA50402001", "Pass", "Selected required radio");
		commonFuntions.clickButton("Continue ");
		
		// --- INQ-002 ---
		commonFuntions.waitForLoadingIconToDisappear(); //average waiting 15s
		commonFuntions.screenShot("BA50402001", "Pass", "Successfully launched to Claimant Information(INQ-002) screen");
		
		test.log(Status.PASS, "Successful passed TC BA-504-02-001");
		System.out.println("Pass :)");
		
		
	}
	

}
