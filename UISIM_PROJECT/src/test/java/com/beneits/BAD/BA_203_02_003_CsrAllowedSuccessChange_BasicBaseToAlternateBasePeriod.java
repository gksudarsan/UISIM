package com.beneits.BAD;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.BenefitsAdjeustmentDebtLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BA_203_02_003_CsrAllowedSuccessChange_BasicBaseToAlternateBasePeriod extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is allowed to successfully change from Basic Base Period to Alternate Base Period", groups = COMMON_CONSTANT.REGRESSION)
	public void TC_BAD_203_02_003_() throws Exception {
	
	test = report.createTest("BA.203.02.003 - Verify CSR is allowed to successfully change from Basic Base Period to Alternate Base Period.");
	
	test.log(Status.INFO, "TC Script developed by Ankan Das");
	
	commonStepDefinitions commonFuntions = new commonStepDefinitions();
	BenefitsAdjeustmentDebtLocators badLocators = new BenefitsAdjeustmentDebtLocators(driver);
	
	//GET Query
	Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT CLAIMANT_IDENTIFIER, c2.* FROM T_claimant c1, T_claim c2\r\n"
			+ "WHERE c1.CLAIMANT_ID = c2.CLAIMANT_ID\r\n"
			+ "AND c2.STATUS = 'ACTV'\r\n"
			+ "AND c2.BYE_DATE > CURRENT_DATE\r\n"
			+ "AND c2.EFFECTIVE_DATE < CURRENT_DATE\r\n"
			+ "AND c2.WBA= '504'\r\n"
			+ "AND C2.BASE_PERIOD_TYPE= 'BASC'\r\n"
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
	commonFuntions.clickMenu("Monetary");
	commonFuntions.clickMenu("Monetary Reconsideration");
	sleep();
	commonFuntions.screenShot("MenuPage", "Pass", "Navigation : Menu -> Monetary -> Monetary Reconsideration -> Correct Wages or Employer Information");
	commonFuntions.clickMenu("Correct Wages or Employer Information");
	
	// --- MON-025 ---
	commonFuntions.waitForLoadingIconToDisappear();
	commonFuntions.screenShot("BAD20302003", "Pass", "Successfully launched to Monetary Reconsideration Request(MON-025) screen");
	commonFuntions.enterTextboxContains("Claimant ID", claimantIdentifier); // 23-48439001
	sleep();
	commonFuntions.screenShot("BAD20302003", "Pass", "Correct Claimant ID entered.");
	commonFuntions.clickButton("Continue ");
	
	// --- MON-027 ---
	commonFuntions.waitForLoadingIconToDisappear(); // Avg wait time 12s
	commonFuntions.screenShot("BAD20302003", "Pass", "Successfully launched to Report Incorrect Employer/Wages(MON-027) screen");
	commonFuntions.ScrollMenu("Reconsideration Request/Request Type");
	sleep();
	commonFuntions.screenShot("BAD20302003", "Pass", "Radio button unselected on MON-027 screen");
	commonFuntions.selectRadioQuestions("Reconsideration Request/Request Type", "Change in Base Period");
	sleep();
	commonFuntions.screenShot("BAD20302003", "Pass", "Selected 'Change in Base Period' - radio on MON-027 screen");
	commonFuntions.clickButton("Continue ");
	
	// --- MON-1421 ---
	commonFuntions.waitForLoadingIconToDisappear(); // Avg wait time 12s
	commonFuntions.screenShot("BAD20302003", "Pass", "Successfully launched to Change in Base Period(MON-1421) screen");
	commonFuntions.selectRadioInTable("Alternate Base Period", 1, 1, "");
	badLocators.remarksId.sendKeys("Change in base period");
	commonFuntions.selectRadioQuestions("Request Conclusion", "Approve Request");
	sleep(2000);
	commonFuntions.screenShot("BAD20302003", "Pass", "Entered required data in MON-1421 screen");
	commonFuntions.clickButtonContains("Submit ");
	
	// --- SUC-002 ---
	commonFuntions.waitForLoadingIconToDisappear();
	commonFuntions.Label("SUC-002");
	commonFuntions.screenShot("BAD20302003", "Pass", "Successfully launched to Cancel Claim Confirmation(SUC-002) screen");
	commonFuntions.clickButton("Home ");
	
	test.log(Status.PASS, "Successful passed TC BAD-203-02-003 till step 6. Step 7 has batch execution.");
	
	
	
	
	
	
	
	
	
	
				
			
				
	}

}
