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
public class CA_044_010_GeneratePaymentVoucher_CSRabletogenerate_and_printPaymentVoucherusing_Cash
		extends TestBase {

	@Test(priority = 1, description = "Generate Payment Voucher - Verify CSR is able to generate and print Payment Voucher using 'Cash'", groups = {
			"Regression" })
	public void CA_044_010() throws Exception {

		test = report.createTest(
				"CA.044.010 - Generate Payment Voucher - Verify CSR is able to generate and print Payment Voucher using 'Cash'");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		B_CA_Page bcalocators = new B_CA_Page(driver);
		String CLAIMANT_IDENTIFIER ="2141331279";
		
		/*Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn("", "");
		String ssnValue = databaseEanResult.get("");
		
		System.out.println("SSN is: " + ssnValue + ".");

		if ((ssnValue == null) || (ssnValue.isEmpty())) {
			System.out.println("SSN Value is null");
		} else {
			test.log(Status.PASS, "DB connected successfully and fetched SSN is: " + ssnValue + ".");
		}*/

		// ---Login---
		commonFunctions.benefitsLogin(COMMON_CONSTANT.CASHIERING_OVERPAYMENT, COMMON_CONSTANT.CASHIERING_OVERPAYMENT_PASSWORD);
		test.log(Status.PASS, "Login with Cashiering Overpayment is successful");

		// ---Menu----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickMenu("Menu");
		//commonFunctions.clickMenu("Make Payment");
		bcalocators.clickMakePaymentMenu.click();
		commonFunctions.screenShot("Menu", "Pass", "Make Payment by Mail option");
		commonFunctions.clickMenu("Make Payment by Mail");
		
		// ---Generate Payment Voucher BPC-777----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("GeneratePaymentVoucher", "Pass", "GeneratePaymentVoucher_launched BPC-777");
		commonFunctions.enterTextbox("Claimant ID", CLAIMANT_IDENTIFIER);
		commonFunctions.screenShot("GeneratePaymentVoucher", "Pass", "GeneratePaymentVoucher_ClaimantID_Given");
		commonFunctions.clickButtonContains(" Search ");
		
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("GeneratePaymentVoucher", "Pass", "Voucher Details apeared");
		commonFunctions.enterTextbox("Amount", "2000");
		commonFunctions.selectDropdown("Payment Mode", "Cash");
		commonFunctions.screenShot("GeneratePaymentVoucher", "Pass", "PaymentVoucher Details selected");
		commonFunctions.clickButtonContains("Continue ");
		
		// ---Generate Payment Voucher Verification BPC-778----
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("GeneratePaymentVoucherVerification", "Pass", "GeneratePaymentVoucherVerification page launched_ BPC-778");
		commonFunctions.clickButtonContains(" Print Voucher ");
		commonFunctions.waitForLoadingIconToDisappear();
		sleep(70000);
		commonFunctions.switchTab();
		commonFunctions.screenShot("Print_Voucher", "Pass", "Print_Voucher_PDF");
		
		//---Test Case Completed---//
	}

}
