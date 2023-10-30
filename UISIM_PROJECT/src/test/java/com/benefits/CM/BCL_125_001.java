package com.benefits.CM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.B_BCL_Page;
import com.ui.pages.B_CA_Page;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_125_001
		extends TestBase {

	@Test(priority = 1, description = "BCL.125.001 - BCL - Create a Payment Agreement Debtor - Verify system allow CSR to complete the application of payment agreement and establish the payment plan", groups = {"Regression" })
	public void BCL_125_001() throws Exception {

		test = report.createTest("BCL - Create a Payment Agreement Debtor - Verify system allow CSR to complete the application of payment agreement and establish the payment plan");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		B_BCL_Page bcllocators = new B_BCL_Page(driver);
		//String CLAIMANT_IDENTIFIER_value = "2043477230";

		//---Getting CLAIMANT_IDENTIFIER---///
		 Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn("SELECT * FROM T_CLAIMANT WHERE SSN IN (SELECT SSN FROM T_FTI_OVERPAYMENT WHERE OVERPAYMENT_BALANCE > 0 AND STATUS = 'ACTV') AND CLAIMANT_IDENTIFIER IS NOT NULL", "CLAIMANT_IDENTIFIER"); 
		 String CLAIMANT_IDENTIFIER_value =databaseEanResult.get("CLAIMANT_IDENTIFIER");
		 
		 System.out.println("CLAIMANT_IDENTIFIER is: " + CLAIMANT_IDENTIFIER_value + ".");
		 
		 if ((CLAIMANT_IDENTIFIER_value == null) || (CLAIMANT_IDENTIFIER_value.isEmpty())) 
		 {
		 System.out.println("CLAIMANT_IDENTIFIER Value is null"); }
		 else {
		 test.log(Status.PASS, "DB connected successfully and fetched CLAIMANT_IDENTIFIER : " + CLAIMANT_IDENTIFIER_value + "."); }
		 //---Ends---//
		 
		 //---Getting SSN value---//
		 Map<String, String> databaseSsnResult = commonFunctions.database_SelectQuerySingleColumn("SELECT * FROM T_CLAIMANT WHERE CLAIMANT_IDENTIFIER=" + CLAIMANT_IDENTIFIER_value + "", "SSN"); 
		 String SSN_value =databaseSsnResult.get("SSN");
		 
		 System.out.println("SSN : " + SSN_value + ".");
		 
		 String input = SSN_value;
		 String lastFourdigitSSN = "";  

		 if (input.length() > 4) {
			 lastFourdigitSSN = input.substring(input.length() - 4);
		 } else {
			 lastFourdigitSSN = input;
		 }
		 
		 System.out.println("SSN Last 4 digit : " + lastFourdigitSSN + ".");
		 
		//---Ends---//
		 
		
		// ---Login---
		commonFunctions.benefitsLogin(COMMON_CONSTANT.COLLECTIONS, COMMON_CONSTANT.COLLECTIONS_PASSWORD);
		test.log(Status.PASS, "Login with Collections is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");sleep(3000);
		commonFunctions.clickMenu("Payment Plan");sleep(3000);
		commonFunctions.screenShot("Menu", "Pass", "Setup Payment Plan Menu");sleep(3000);
		commonFunctions.clickMenu("Setup Payment Plan");sleep(3000);

		// ---SetupPaymentPlan_EnterSSN BPC-171----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("SetupPaymentPlan_EnterSSN", "Pass", "SetupPaymentPlan_EnterSSN page BPC-171");
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.screenShot("SetupPaymentPlan_EnterSSN", "Pass", "SetupPaymentPlan_EnterSSN_ClaimantID_Given");
		commonFunctions.clickButtonContains("Continue ");sleep(2000);

		// ---PaymentPlan_TermsandConditions BPC-177----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("PaymentPlan_TermsandConditions", "Pass", "PaymentPlan_TermsandConditions apeared BPC-177");
		bcllocators.checkBoxContactInfoCorrect.click();
		commonFunctions.screenShot("PaymentPlan_TermsandConditions", "Pass", "PaymentPlan_TermsandConditions Details_selected");
		commonFunctions.clickButtonContains("Continue ");sleep(2000);

		// ---Overpayment_Setup_PaymentPlan BPC-172----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Overpayment_Setup_PaymentPlan", "Pass", "Overpayment_Setup_PaymentPlan launched BPC-172");
		
		commonFunctions.enterFutureDate("Payment Due date", 10);
		commonFunctions.enterTextboxContains("Enter the last four (4) digits of your SSN", lastFourdigitSSN);
		commonFunctions.enterCurrentDate("Date");
		commonFunctions.screenShot("Overpayment_Setup_PaymentPlan", "Pass", "Overpayment_Setup_PaymentPlan_detailsFilled BPC-172");
		commonFunctions.clickButtonContains("Submit ");sleep(5000);
		
		
		// ---Setup_PaymentPlanConfirmation SUC-002----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Setup_PaymentPlanConfirmation", "Pass", "Setup_PaymentPlanConfirmation launched SUC-002");
		//commonFunctions.clickOnLinkAnchorTag(" make your first payment online ");
		commonFunctions.clickOnLinkAnchorTag(" print out a payment voucher.");
		
		// ---Generate Payment Voucher BPC-777----
		commonFunctions.screenShot("GeneratePaymentVoucher", "Pass", "GeneratePaymentVoucher launched BPC-777");
		commonFunctions.clickButtonContains("Home ");sleep(15000);
		
		// ---Home Page----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Arrived Home Page", "Pass", "Back to Home Page"); 

		// ---Login---
		commonFunctions.benefitsLogoutAndLogin(COMMON_CONSTANT.BASIC_BENEFITS_INQUERY,COMMON_CONSTANT.BASIC_BENEFITS_INQUERY_PASSWORD);
		//commonFunctions.benefitsLogin(COMMON_CONSTANT.BASIC_BENEFITS_INQUIRY_User,COMMON_CONSTANT.BASIC_BENEFITS_INQUIRY_pwd);
		test.log(Status.PASS, "Login with BasicBenefitsInquiry is successful");
		//String CLAIMANT_IDENTIFIER_value = "2043477230";
		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");sleep(3000);
		commonFunctions.clickMenu("Claim Information");sleep(3000);
		commonFunctions.clickMenu("Benefits");sleep(3000);
		commonFunctions.screenShot("Menu", "Pass", "View Claim Information option");sleep(3000);
		commonFunctions.clickMenu("View Claim Information");sleep(3000);

		// ---Claimant Search INQ-001----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Search INQ-001 Open");
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.screenShot("Claimant_Search INQ-001", "Pass", "Claimant Search_ClaimantID_Given");
		commonFunctions.clickButtonContains(" Search ");

		commonFunctions.waitForLoadingIconToDisappear();
		sleep(30000);
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Details apeared");
		commonFunctions.selectRadioInTable(CLAIMANT_IDENTIFIER_value, 1, 1, "");
		commonFunctions.screenShot("Claimant_Search", "Pass", "Claimant Details selected");
		commonFunctions.clickButtonContains("Continue ");

		// ---Claim Information INQ-002----
		commonFunctions.waitForLoadingIconToDisappear();
		sleep(5000);
		commonFunctions.screenShot("Claim_Information", "Pass", "Claim_Information page INQ-002 launched");
		commonFunctions.ScrollMenu("Comment Date");
		commonFunctions.screenShot("Claim_Information", "Pass", "Claim_Information page remaining sceenshot1");
		commonFunctions.ScrollMenu("Previous ");
		commonFunctions.screenShot("Claim_Information", "Pass", "Claim_Information page remaining sceenshot2");
		commonFunctions.clickOnLinkAnchorTag("Overpayments");

		// ---OverpaymentInquiry_ListofOverpayments BPC-056----
		commonFunctions.waitForLoadingIconToDisappear();sleep(2000);
		commonFunctions.screenShot("OverpaymentInquiry_ListofOverpayments", "Pass", "OverpaymentInquiry_ListofOverpaymentspage BPC-056 launched");
		commonFunctions.ScrollMenu("Repayment Details");
		commonFunctions.screenShot("OverpaymentInquiry_ListofOverpayments", "Pass", "remaining sceenshot1");
		commonFunctions.clickOnLinkAnchorTag("Payment Plans");

		// ---PaymentPlans INQ-410----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("PaymentPlans", "Pass", "PaymentPlans INQ-410");
		commonFunctions.clickOnLinkAnchorTag("Update Payment Plan");

		// ---Update_Payment_Plan BPC-175----
		commonFunctions.waitForLoadingIconToDisappear();sleep(2000);
		commonFunctions.screenShot("Update_Payment_Plan", "Pass", "Update_Payment_Plan BPC-175");
		
		//DOne till here

		// ---PaymentPlans INQ-410----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("PaymentPlans", "Pass", "BackPaymentPlans INQ-410");
		commonFunctions.clickButtonContains("Previous ");

		// ---Over_paymentInquiry_Repayment_Details BPC-056----
		commonFunctions.waitForLoadingIconToDisappear();sleep(2000);
		commonFunctions.screenShot("Over_paymentInquiry_Repayment_Details", "Pass", "Back_OverpaymentInquiry_ListofOverpayments BPC-056");
		commonFunctions.clickButtonContains("Previous "); //this previous not working

		// ---Claim Information INQ-002----
		commonFunctions.waitForLoadingIconToDisappear();sleep(2000);
		commonFunctions.screenShot("Claim_Information", "Pass", "Back_Claim_Informationpage INQ-002");
		commonFunctions.clickButtonContains("Previous ");

		// ---Claimant Search INQ-001----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Claimant_Search", "Pass", "BackClaimant_Search INQ-001");
		commonFunctions.clickButtonContains("Previous ");

		// ---Home----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Home", "Pass", "Test BCL_125_001 got Pass ");

		// ---Test Case Completed---//

	}

}
