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
public class BCL_148_005_BCLRequestJudgement_systemallow_CSR_torequestajudgement_basedonNYclaimantcounty
		extends TestBase {

	@Test(priority = 1, description = "BCL - Request Judgement - Verify system allow CSR to request a judgement based on NY claimant county", groups = {"Regression" })
	public void BCL_148_005() throws Exception {

		test = report.createTest("BCL - Request Judgement - Verify system allow CSR to request a judgement based on NY claimant county");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		B_BCL_Page bcllocators = new B_BCL_Page(driver);
		//String CLAIMANT_IDENTIFIER_value = "1942250176";
		
		 Map<String, String> databaseEanResult = commonFunctions.
		 database_SelectQuerySingleColumn("select * from T_FTI_OVERPAYMENT tfo\r\n" +
		 "LEFT JOIN T_CLAIM tc ON tfo.CLAIM_ID = tc.CLAIM_ID\r\n" +
		 "LEFT JOIN T_CLAIMANT tc2 ON tc.CLAIMANT_ID = tc2.CLAIMANT_ID\r\n" +
		 "WHERE tfo.STATUS = 'ACTV'\r\n" + "AND tfo.CLASS = 'WLFL'",
		 "CLAIMANT_IDENTIFIER"); 
		 
		 String CLAIMANT_IDENTIFIER_value =databaseEanResult.get("CLAIMANT_IDENTIFIER");
		 System.out.println("CLAIMANT_IDENTIFIER is: " + CLAIMANT_IDENTIFIER_value + ".");
		 
		 if ((CLAIMANT_IDENTIFIER_value == null) || (CLAIMANT_IDENTIFIER_value.isEmpty())) 
		 {
		 System.out.println("CLAIMANT_IDENTIFIER Value is null"); }
		 else {
		 test.log(Status.PASS, "DB connected successfully and fetched CLAIMANT_IDENTIFIER is: " + CLAIMANT_IDENTIFIER_value + "."); }
		
		// ---Login---
		commonFunctions.benefitsLogin(COMMON_CONSTANT.COLLECTIONS, COMMON_CONSTANT.COLLECTIONS_PASSWORD);
		test.log(Status.PASS, "Login with Collections is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");sleep(3000);
		commonFunctions.clickMenu("Overpayment Maintenance");sleep(3000);
		commonFunctions.clickMenu("Maintain Enrollment Details");sleep(3000);
		commonFunctions.screenShot("Menu", "Pass", "Print Judgment option");sleep(3000);
		commonFunctions.clickMenu("Print Judgment");sleep(3000);

		// ---Request Judgment - Enter SSN BPC-207----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("RequestJudgmentEnterSSN", "Pass", "RequestJudgmentEnterSSN BPC-207");
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER_value);
		commonFunctions.screenShot("RequestJudgmentEnterSSN", "Pass", "RequestJudgmentEnterSSN_ClaimantID_Given");
		commonFunctions.clickButtonContains("Continue ");sleep(10000);

		// ---Print Judgment BPC-208----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("PrintJudgment", "Pass", "PrintJudgment apeared BPC-208");

		bcllocators.selectCheckBoxPrintJudgment.click();sleep(5000);
		bcllocators.clickDropdown1PrintJudgment.click();sleep(5000);
		bcllocators.selectDropdown1PrintJudgment.click();sleep(5000);
		bcllocators.clickDropdown2PrintJudgment.click();sleep(5000);
		bcllocators.selectDropdown2PrintJudgment.click();sleep(5000);
		bcllocators.AddCommentPrintJudgment.sendKeys("PrintJudgment report");

		commonFunctions.screenShot("PrintJudgment", "Pass", "PrintJudgment page Details selected");
		commonFunctions.clickButtonContains("Submit ");sleep(5000);

		// ---Print Judgment Confirmation SUC-002----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("PrintJudgmentConfirmation", "Pass", "PrintJudgmentConfirmation launched SUC-002");
		commonFunctions.clickButtonContains("Home ");

		// ---Home Page----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Arrived Home Page", "Pass", "Back to Home Page"); 

		/*
		 * Map<String, String> databaseEanResult =
		 * commonFunctions.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_CERTIFICATE OVERPAYMENT_ID='198883';", "EAN"); String
		 * ernValue = databaseEanResult.get("EAN");
		 */

		// ---Login---
		commonFunctions.benefitsLogoutAndLogin(COMMON_CONSTANT.BASIC_BENEFITS_INQUERY,COMMON_CONSTANT.BASIC_BENEFITS_INQUERY_PASSWORD);
		test.log(Status.PASS, "Login with BasicBenefitsInquiry is successful");

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
		sleep(20000);
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
		bcllocators.clickRepaymentIdLink.click();

		// ---Over_paymentInquiry_Repayment_Details BPC-051----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Over_paymentInquiry_Repayment_Details", "Pass", "Over_paymentInquiry_Repayment_Details BPC-051");
		commonFunctions.clickButtonContains("Previous ");

		// ---OverpaymentInquiry_ListofOverpayments BPC-056----
		commonFunctions.waitForLoadingIconToDisappear();sleep(2000);
		commonFunctions.screenShot("OverpaymentInquiry_ListofOverpayments", "Pass", "Back_OverpaymentInquiry_ListofOverpayments BPC-056");
		commonFunctions.clickOnLinkAnchorTag("Certificate/Garnishment/Prosecution");

		// ---OverpaymentInquiryJudgementProsecution BPC-151----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("OverpaymentInquiryJudgementProsecution", "Pass", "OverpaymentInquiryJudgementProsecutionpage BPC-151 launched");
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
		commonFunctions.screenShot("Home", "Pass", "Test BCL_148_005 got Pass ");

		// ---Test Case Completed---//

	}

}
