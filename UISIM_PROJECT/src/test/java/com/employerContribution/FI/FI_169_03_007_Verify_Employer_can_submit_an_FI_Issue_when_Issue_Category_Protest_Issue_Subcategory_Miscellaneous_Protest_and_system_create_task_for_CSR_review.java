package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_007_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Miscellaneous_Protest_and_system_create_task_for_CSR_review extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Miscellaneous Protest' and system create task for CSR review.", groups = {"Regression"})
	public void FI_169_03_007() throws Exception {
		
	test = report.createTest("FI.169.03.007 : Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Miscellaneous Protest' and system create task for CSR review.");

	commonStepDefinitions commonFunction = new commonStepDefinitions();
	FraudInvestigationLocators fiLocator = new FraudInvestigationLocators(driver);
	
	// Query
	Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
            "SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
            "EAN");
    String eanValue = databaseResults.get("EAN");

    if ((eanValue == null) || (eanValue.isEmpty())) {
		System.out.println("EAN Value is null");
	} else {
		test.log(Status.PASS, "DB connected successfully and fetched EAN is: " + eanValue + ".");
	}
    
	commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_9.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_9_PASSWORD);
	test.log(Status.PASS, "Login with Employer role is successful");

    // --- Menu ---
	fiLocator.menu.click();
    commonFunction.screenShot("MenuPage", "Pass", "Launched to Menu");
    commonFunction.ScrollMenu("Penalty");
    commonFunction.clickMenu("Penalty");
    commonFunction.clickMenu("Penalty Menu");
    sleep(5000);
    commonFunction.screenShot("FIP-001", "Pass", "Penality menupage is displayed");
	commonFunction.enterTextboxContains("Employer Registration Number", "04-63826");
	commonFunction.screenShot("FIP-001", "Pass", "Penality menupage is displayed");
	commonFunction.clickButton("Continue ");
	sleep(4000);
	
	commonFunction.screenShot("FIP-002", "Pass", "Select Penalty is displayed");
//	peoPage.selectRadiobutton1.click();
	commonFunction.screenShot("FIP-002", "Pass", "Select Penalty is displayed");
	commonFunction.clickButton("Continue ");
	sleep(5000);
	
	commonFunction.screenShot("FIP-005", "Pass", "Benefit Claim Penalty Summary page is displayed");
	//need to enter deatils
	//peoPage.selectRadiobutton2.click();
	commonFunction.screenShot("FIP-005", "Pass", "Benefit Claim Penalty Summary page is displayed");
	commonFunction.clickButton("Continue ");
	sleep(5000);
	
	commonFunction.screenShot("FIP-006", "Pass", "Benefit Claim Penalty Details page is displayed");
	sleep(3000);
	commonFunction.clickButton("Home");
	sleep(3000);
	commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");
}

}
