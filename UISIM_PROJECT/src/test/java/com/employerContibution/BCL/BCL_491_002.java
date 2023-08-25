package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.COL_468;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_491_002 extends TestBase {

	@Test
	public void BCL491_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_468 col468 = new COL_468(driver);

		test = report.createTest(
				"BCL.491.002. Verify CSR can request payment plans where payment plan type is 'Deferred Payment Arrangement '(DPA) and Length of Agreement in Months is less than 12");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		commonFuntions.ScrollMenu("Pay Agreement");
		commonFuntions.clickMenu("Pay Agreement");
		// col468.employerbankruptcynavBtn.click();
		// sleep(1000);
		commonFuntions.ScrollMenu("Complete Payment Plan");
		commonFuntions.screenShot("Complete Payment Plan", "Pass", "Complete Payment Plan");
		commonFuntions.clickMenu("Complete Payment Plan");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Complete Payment Plan Application", "Pass", "COL-486 screen is displayed");

		//
		Map<String, String> EANOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PAY_AGREEMENT ttpa JOIN T_EMPLOYER te ON ttpa.EMPLOYER_ID = te.EMPLOYER_ID WHERE PAY_AGREEMENT_STATUS_CD = 'REQS' AND MONTHLY_INSTALLMENT_AMOUNT>500 AND LENGTH(EAN)=7;",
				"EAN");
		String eanCorrect = EANOutput.get("EAN");
		System.out.println(eanCorrect);
		//

		test.info("Step: 4 -- ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Complete Payment Plan", "Pass", "Required - Error Message is displayed");
		sleep(1000);

		test.info("Step: 5 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanCorrect);
		commonFuntions.clickButtonContains(" Get Balance Due ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.enterTextboxContains("Length of Agreement in Months", "12");
		commonFuntions.enterFutureDate("Down Payment Due Date", 120);

		commonFuntions.enterTextboxContains("First Name", "FN");
		commonFuntions.enterTextboxContains("Last Name", "LN");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Complete Payment Plan", "Pass", "Required - Error Message is displayed");

		test.info("Step: 7 -- ");
		commonFuntions.selectRadioQuestions("Payment Plan Type", "Deferred Payment Arrangement");

		commonFuntions.forceClearTextWithElement("Length of Agreement in Months");
		sleep(1000);
		commonFuntions.enterTextboxContains("Length of Agreement in Months", "37");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Complete Payment Plan", "Pass", "Required - Error popup Message is displayed");
		sleep(2000);
		commonFuntions.clickButtonContains("No");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		////
		commonFuntions.forceClearTextWithElement("Length of Agreement in Months");
		sleep(1000);
		commonFuntions.enterTextboxContains("Length of Agreement in Months", "12");

		///
		commonFuntions.forceClearTextWithElement("Employer Registration Number (ERN)");
		sleep(1000);
		
		//
		Map<String, String> EANACTVSTATUS = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PAY_AGREEMENT ttpa JOIN T_EMPLOYER te ON ttpa.EMPLOYER_ID = te.EMPLOYER_ID WHERE PAY_AGREEMENT_STATUS_CD = 'ACTV' AND MONTHLY_INSTALLMENT_AMOUNT>500 AND LENGTH(EAN)=7;",
				"EAN");
		String eanactive = EANACTVSTATUS.get("EAN");
		System.out.println(eanactive);
		//
		
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanactive); //4402645
		commonFuntions.clickButtonContains(" Get Balance Due ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("There is already an active payment agreement set up.");
		commonFuntions.screenShot("Complete Payment Plan", "Pass", "Required - Error Message is displayed");
		///

		commonFuntions.forceClearTextWithElement("Employer Registration Number (ERN)");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanCorrect);
		commonFuntions.clickButtonContains(" Get Balance Due ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Payment Plan Verification", "Pass", "COL-481 Message is displayed");

		//
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Payment Plan terms condition", "Pass", "SUC-002 Message is displayed");

		//
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home Page", "Pass", "Home Page screen is displayed");

	}
}
