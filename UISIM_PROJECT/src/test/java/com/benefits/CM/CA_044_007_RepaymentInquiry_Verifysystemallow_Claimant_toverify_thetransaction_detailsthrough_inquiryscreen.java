package com.benefits.CM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.B_CA_Page;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class CA_044_007_RepaymentInquiry_Verifysystemallow_Claimant_toverify_thetransaction_detailsthrough_inquiryscreen
		extends TestBase {

	@Test(priority = 1, description = "Verify system allow Claimant to verify the transaction details through inquiry screen", groups = {
			"Regression" })
	public void CA_044_007() throws Exception {

		test = report.createTest(
				"CA.044.007 - Repayment Inquiry  - Verify system allow Claimant to verify the transaction details through inquiry screen");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		B_CA_Page bcalocators = new B_CA_Page(driver);
		String CLAIMANT_IDENTIFIER ="2141331308";

		// ---Login---
		commonFunctions.benefitsLogin(COMMON_CONSTANT.BASIC_BENEFITS_INQUERY, COMMON_CONSTANT.BASIC_BENEFITS_INQUERY_PASSWORD);
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");sleep();
		commonFunctions.clickMenu("Claim Information");sleep();
		commonFunctions.clickMenu("Benefits");sleep();
		commonFunctions.screenShot("Menu", "Pass", "View Claim Information option");sleep();
		commonFunctions.clickMenu("View Claim Information");sleep();
		
		// ---Claimant Search INQ-001----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Search INQ-001 Open");
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER);
		commonFunctions.screenShot("Claimant_Search INQ-001", "Pass", "Claimant Search_ClaimantID_Given");
		commonFunctions.clickButtonContains(" Search ");
		
		commonFunctions.waitForLoadingIconToDisappear();sleep(10000);
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Details apeared");
		commonFunctions.selectRadioInTable(CLAIMANT_IDENTIFIER, 1, 1, "");
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Details selected");
		commonFunctions.clickButtonContains("Continue ");
		
		// ---Claim Information INQ-002----
		commonFunctions.waitForLoadingIconToDisappear();sleep(5000);
		commonFunctions.screenShot("Claim_Information", "Pass", "Claim_Information page INQ-002 launched");
		commonFunctions.clickOnLinkAnchorTag("View Correspondence");
		
		// ---View Correspondence DMS-007----
		commonFunctions.waitForLoadingIconToDisappear();sleep(5000);
		commonFunctions.screenShot("View_Correspondence", "Pass", "View_Correspondence page DMS-007 launched");
		commonFunctions.clickButtonContains(" Search ");sleep(10000);
		commonFunctions.ScrollMenu(" Search ");
		commonFunctions.screenShot("View_Correspondence", "Pass", "Correspondence details apeared");
		commonFunctions.clickButtonContains("Previous ");
		
		// ---Claim Information INQ-002----
		commonFunctions.waitForLoadingIconToDisappear();sleep(5000);
		commonFunctions.screenShot("Claim_Information", "Pass", "Claim_Information INQ-002 launched again");
		commonFunctions.clickOnLinkAnchorTag("Overpayments");
		
		// ---Over_payment_Inquiry_Listof_Over_payments BPC-056----
		commonFunctions.waitForLoadingIconToDisappear();sleep(5000);
		commonFunctions.screenShot("OverpaymentInquiry_Listof_Overpayments", "Pass", "OverpaymentInquiry_Listof_Overpayments page BPC-056 launched");
		commonFunctions.clickButtonContains("Previous ");
		
		// ---Claim Information INQ-002----
		commonFunctions.waitForLoadingIconToDisappear();sleep(5000);
		commonFunctions.screenShot("Claim_Information", "Pass", "Back to Claim_Information again");
		commonFunctions.clickButtonContains("Previous "); // Here cancel button expected as per TC to go to Home but discussed with manual tester it is change
		
		// ---Claimant Search INQ-001----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Claimant_Search", "Pass", "Back to Claimant_Search page");
		commonFunctions.clickButtonContains("Previous ");
		
		// ---Home----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Home", "Pass", "Test CA_044_007 got Pass ");
				
		//---Test Case Completed---//
	}

}
