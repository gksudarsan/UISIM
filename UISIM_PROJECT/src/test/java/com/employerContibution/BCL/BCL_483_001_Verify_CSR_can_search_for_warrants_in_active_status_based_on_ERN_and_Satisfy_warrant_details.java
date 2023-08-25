
package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_483_001_Verify_CSR_can_search_for_warrants_in_active_status_based_on_ERN_and_Satisfy_warrant_details
		extends TestBase {
	@Test
	public void BCL_483_001() throws Exception {

		test = report.createTest(
				"483_001 - Verify CSR can search for warrants in active status based on ERN and Satisfy warrant details");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage BclPage = PageFactory.initElements(driver, BclPage.class);

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		// -----Login
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT\r\n"
				+ "							DISTINCT \r\n" + "							EAN,\r\n"
				+ "							LEGAL_NAME,\r\n" + "							WARRANT_ID,\r\n"
				+ "							SEQ_NUM,\r\n"
				+ "							SUM (CURRENT_AMOUNT_DUE) OVER (\r\n"
				+ "							PARTITION BY SEQ_NUM) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "							COUNTY,\r\n" + "							NAME_ON_WARRANT,\r\n"
				+ "							WARRANT_STATUS,\r\n"
				+ "							WARRANT_ISSUED_DATE,\r\n"
				+ "							SUM(WARRANT_AMOUNT) OVER (\r\n"
				+ "							PARTITION BY SEQ_NUM) AS WARRANT_AMOUNT,\r\n"
				+ "							WARRANT_FILED_DATE,\r\n" + "							WARRANT_TYPE,\r\n"
				+ "							TRANSCRIPT_FLAG\r\n" + "						FROM\r\n"
				+ "							(\r\n" + "							SELECT\r\n"
				+ "								b.AMT_ALREADY_ON_WARRANT AS WARRANT_AMOUNT,\r\n"
				+ "								TE.EAN,\r\n"
				+ "								TEA.ENTITY_NAME AS LEGAL_NAME,\r\n"
				+ "								B.WARRANT_ID,\r\n"
				+ "								TW.WARRANT_TYPE_CD AS WARRANT_TYPE,\r\n"
				+ "								TW.WARRANT_NUMBER AS SEQ_NUM,\r\n"
				+ "								TW.WARRANT_ISSUE_DATE AS WARRANT_ISSUED_DATE,\r\n"
				+ "								TW.COUNTY AS COUNTY_CODE ,\r\n"
				+ "								TMC.DESCRIPTION AS COUNTY ,\r\n"
				+ "								B.CURRENT_AMOUNT_DUE AS CURRENT_AMOUNT_DUE,\r\n"
				+ "								TEA.ENTITY_NAME AS NAME_ON_WARRANT,\r\n"
				+ "								'0' AS TRANSCRIPT_FLAG,\r\n"
				+ "								(CASE WHEN TW.WARRANT_STATUS_CD = 'ACTV' THEN 'Active'\r\n"
				+ "								ELSE 'Enrolled'\r\n"
				+ "						END ) AS WARRANT_STATUS,\r\n"
				+ "								TWE.WARRANT_ENROLLMENT_DATE AS WARRANT_FILED_DATE\r\n"
				+ "							FROM\r\n" + "								(\r\n"
				+ "								SELECT\r\n" + "									WARRANT_ID,\r\n"
				+ "									SUM ((AMOUNT_DUE-AMOUNT_PAID)) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										A.WARRANT_ID,\r\n"
				+ "										AMOUNT_DUE,\r\n"
				+ "										AMOUNT_PAID,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT ttw\r\n"
				+ "									JOIN T_TX_WARRANT_AMOUNT_DETAIL a ON\r\n"
				+ "										ttw.WARRANT_ID = a.WARRANT_ID\r\n"
				+ "									JOIN T_TX_TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS b ON\r\n"
				+ "										a.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID = b.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID\r\n"
				+ "							UNION\r\n" + "								SELECT\r\n"
				+ "									WARRANT_ID,\r\n"
				+ "									SUM ((DUE_AMOUNT -PAID_AMOUNT)+(INTEREST_DUE_AMOUNT - INTEREST_PAID_AMOUNT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM(a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										a.WARRANT_ID,\r\n"
				+ "										b.DUE_AMOUNT,\r\n"
				+ "										b.PAID_AMOUNT,\r\n"
				+ "										b.INTEREST_DUE_AMOUNT,\r\n"
				+ "										b.INTEREST_PAID_AMOUNT,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "									JOIN T_TX_OTHER_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "										a.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID\r\n"
				+ "							UNION\r\n" + "								SELECT\r\n"
				+ "									WARRANT_ID,\r\n"
				+ "									SUM ((CHARGE_DUE_AMT -CHARGE_PAID_AMT)+(INTEREST_DUE_AMT - INTEREST_PAID_AMT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										a.WARRANT_ID,\r\n"
				+ "										b.CHARGE_DUE_AMT,\r\n"
				+ "										b.CHARGE_PAID_AMT,\r\n"
				+ "										b.INTEREST_DUE_AMT,\r\n"
				+ "										b.INTEREST_PAID_AMT,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "									JOIN T_TX_REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "										a.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID )B\r\n"
				+ "							JOIN T_TX_WARRANT TW ON\r\n"
				+ "								TW.WARRANT_ID = B.WARRANT_ID\r\n"
				+ "							JOIN T_EMPLOYER TE ON\r\n"
				+ "								TE.EMPLOYER_ID = TW.EMPLOYER_ID\r\n"
				+ "							JOIN T_EMPLOYER_ACCOUNT TEA ON\r\n"
				+ "								TE.EAN = TEA.EAN\r\n"
				+ "							JOIN T_EMPLOYER_PARTNER TEP ON\r\n"
				+ "								TEA.EMPLOYER_ACCOUNT_ID = TEP.EMPLOYER_ACCOUNT_ID\r\n"
				+ "							JOIN T_MST_COUNTY TMC ON\r\n"
				+ "								TW.COUNTY = TMC.\"KEY\"\r\n"
				+ "							JOIN T_TX_WARRANT_ENROLLMENT TWE ON\r\n"
				+ "								TW.WARRANT_ID = TWE.WARRANT_ID\r\n"
				+ "							WHERE\r\n" + "								--TE.EAN = ?\r\n"
				+ "								--AND \r\n"
				+ "								TW.WARRANT_STATUS_CD IN ('ACTV',\r\n"
				+ "								'ENRL')\r\n" + "							GROUP BY\r\n"
				+ "								B.WARRANT_ID,\r\n"
				+ "								TEA.ENTITY_NAME,\r\n" + "								TE.EAN,\r\n"
				+ "								TW.WARRANT_NUMBER,\r\n"
				+ "								TW.WARRANT_ISSUE_DATE ,\r\n"
				+ "								TW.COUNTY,\r\n" + "								TMC.DESCRIPTION,\r\n"
				+ "								TEP.ENTITY_NAME ,\r\n"
				+ "								(CASE WHEN TW.WARRANT_STATUS_CD = 'ACTV' THEN 'Active'\r\n"
				+ "								ELSE 'Enrolled'\r\n" + "						END ) ,\r\n"
				+ "								TWE.WARRANT_ENROLLMENT_DATE,\r\n"
				+ "								b.AMT_ALREADY_ON_WARRANT,\r\n"
				+ "								B.CURRENT_AMOUNT_DUE,\r\n"
				+ "								TW.WARRANT_TYPE_CD\r\n" + "						UNION\r\n"
				+ "							SELECT\r\n"
				+ "								b.AMT_ALREADY_ON_WARRANT AS WARRANT_AMOUNT,\r\n"
				+ "								TE.EAN,\r\n"
				+ "								TEA.ENTITY_NAME AS LEGAL_NAME,\r\n"
				+ "								B.WARRANT_ID,\r\n"
				+ "								TW.WARRANT_TYPE_CD AS WARRANT_TYPE,\r\n"
				+ "								TWT.SEQUENCE_NUMBER AS SEQ_NUM,\r\n"
				+ "								TWT.TRANSCRIPT_ISSUE_DATE AS WARRANT_ISSUED_DATE,\r\n"
				+ "								TWT.COUNTY AS COUNTY_CODE,\r\n"
				+ "								TMC.DESCRIPTION AS COUNTY,\r\n"
				+ "								B.CURRENT_AMOUNT_DUE AS CURRENT_AMOUNT_DUE,\r\n"
				+ "								TEA.ENTITY_NAME AS NAME_ON_WARRANT,\r\n"
				+ "								'1' AS TRANSCRIPT_FLAG,\r\n"
				+ "								(CASE WHEN TWT.TRANSCRIPT_STATUS = 'ACTV' THEN 'Active'\r\n"
				+ "								ELSE 'Enrolled'\r\n"
				+ "						END ) AS WARRANT_STATUS,\r\n"
				+ "								TWT.TRANSCRIPT_ENROLLMENT_DATE AS WARRANT_FILED_DATE\r\n"
				+ "							FROM\r\n" + "								(\r\n"
				+ "								SELECT\r\n" + "									WARRANT_ID,\r\n"
				+ "									SUM ((AMOUNT_DUE-AMOUNT_PAID)) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										A.WARRANT_ID,\r\n"
				+ "										AMOUNT_DUE,\r\n"
				+ "										AMOUNT_PAID,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT ttw\r\n"
				+ "									JOIN T_TX_WARRANT_AMOUNT_DETAIL a ON\r\n"
				+ "										ttw.WARRANT_ID = a.WARRANT_ID\r\n"
				+ "									JOIN T_TX_TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS b ON\r\n"
				+ "										a.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID = b.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID\r\n"
				+ "							UNION\r\n" + "								SELECT\r\n"
				+ "									WARRANT_ID,\r\n"
				+ "									SUM ((DUE_AMOUNT -PAID_AMOUNT)+(INTEREST_DUE_AMOUNT - INTEREST_PAID_AMOUNT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM(a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										a.WARRANT_ID,\r\n"
				+ "										b.DUE_AMOUNT,\r\n"
				+ "										b.PAID_AMOUNT,\r\n"
				+ "										b.INTEREST_DUE_AMOUNT,\r\n"
				+ "										b.INTEREST_PAID_AMOUNT,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "									JOIN T_TX_OTHER_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "										a.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID\r\n"
				+ "							UNION\r\n" + "								SELECT\r\n"
				+ "									WARRANT_ID,\r\n"
				+ "									SUM ((CHARGE_DUE_AMT -CHARGE_PAID_AMT)+(INTEREST_DUE_AMT - INTEREST_PAID_AMT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "									SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "								FROM\r\n" + "									(\r\n"
				+ "									SELECT\r\n"
				+ "										a.WARRANT_ID,\r\n"
				+ "										b.CHARGE_DUE_AMT,\r\n"
				+ "										b.CHARGE_PAID_AMT,\r\n"
				+ "										b.INTEREST_DUE_AMT,\r\n"
				+ "										b.INTEREST_PAID_AMT,\r\n"
				+ "										a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "									FROM\r\n"
				+ "										T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "									JOIN T_TX_REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "										a.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "								GROUP BY\r\n" + "									A.WARRANT_ID )B\r\n"
				+ "							JOIN T_TX_WARRANT TW ON\r\n"
				+ "								TW.WARRANT_ID = B.WARRANT_ID\r\n"
				+ "							JOIN T_EMPLOYER TE ON\r\n"
				+ "								TE.EMPLOYER_ID = TW.EMPLOYER_ID\r\n"
				+ "							JOIN T_EMPLOYER_ACCOUNT TEA ON\r\n"
				+ "								TE.EAN = TEA.EAN\r\n"
				+ "							JOIN T_EMPLOYER_PARTNER TEP ON\r\n"
				+ "								TEA.EMPLOYER_ACCOUNT_ID = TEP.EMPLOYER_ACCOUNT_ID\r\n"
				+ "							JOIN T_TX_WARRANT_ENROLLMENT TWE ON\r\n"
				+ "								TW.WARRANT_ID = TWE.WARRANT_ID\r\n"
				+ "							JOIN T_TX_WARRANT_TRANSCRIPT TWT ON\r\n"
				+ "								TWT.WARRANT_ID = TW.WARRANT_ID\r\n"
				+ "							JOIN T_MST_COUNTY TMC ON\r\n"
				+ "								TWT.COUNTY = TMC.\"KEY\"\r\n" + "							WHERE\r\n"
				+ "								--TE.EAN = ?\r\n" + "								--AND \r\n"
				+ "								TWT.TRANSCRIPT_STATUS IN ('ACTV',\r\n"
				+ "								'ENRL')\r\n" + "								)\r\n"
				+ "						GROUP BY\r\n" + "							EAN,\r\n"
				+ "							LEGAL_NAME,\r\n" + "							WARRANT_ID,\r\n"
				+ "							SEQ_NUM,\r\n" + "							COUNTY,\r\n"
				+ "							NAME_ON_WARRANT,\r\n" + "							WARRANT_STATUS,\r\n"
				+ "							WARRANT_ISSUED_DATE,\r\n" + "							WARRANT_AMOUNT,\r\n"
				+ "							WARRANT_FILED_DATE,\r\n"
				+ "							CURRENT_AMOUNT_DUE,\r\n" + "							WARRANT_TYPE,\r\n"
				+ "							TRANSCRIPT_FLAG", "EAN");

		String eanNumber = databaseResults.get("EAN");// 0464364

		// -----Menu
		commonFuntions.clickMenu("menu");
		sleep(2000);
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(1000);
		commonFuntions.clickMenu("Warrant");
		sleep(1000);
		commonFuntions.screenShot("Menu", "Pass", "Menu selected");
		commonFuntions.clickMenu("Satisfy Warrant");
		sleep(1000);
		// ----COL 501
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Successfully launched to COL 501 page");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered nothing on ERN:  COL 501 page");
		sleep(2000);
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "0400027");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass",
				"Entered  ERN with no record on  COL 501 page");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "1111111");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered invalid ERN on  COL 501 page");
		sleep(2000);
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass",
				"Entered  ERN with no record on  COL 501 page");
		// commonFuntions.enterTextboxContains("Employer Registration Number (ERN)",
		// "0464364");
		commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", eanNumber);
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Satisfy Warrant - Enter ERN", "Pass", "Entered valid ERN on  COL 501 page");

		// --- COL-490 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Warrants", "Pass",
				"Successfully launched to List of Warrants(COL-490) screen");
		BclPage.dataTableIdRadio.click();
		sleep(2000);
		commonFuntions.screenShot("List of Warrants", "Pass", "Successfully selected data on COL-490 screen");
		commonFuntions.clickButtonContains("Continue ");

		// --- COL 501---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant Details", "Pass",
				"Successfully launched to Satisfy Warrant Details COL 501 screen");
		try {
			BclPage.yesRadioInCircle.click();
		} catch (Exception exception) {
			BclPage.yesRadioOutCircle.click();
		}
		BclPage.reasonExplanation.sendKeys("Yes want to statisy ");
		commonFuntions.screenShot("Satisfy Warrant Details", "Pass", "Entered details COL 501 screen");
		commonFuntions.clickButtonContains("Continue ");
		// --- COL 502 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Satisfy Warrant Verification", "Pass",
				"Successfully launched to Warrant Satisfy Verification COL 502 screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		// --- SUC 002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Warrant Satisfy Confirmation", "Pass",
				"Successfully launched to Warrant Satisfy Confirmation(SUC -002) screen");
		sleep(2000);
		commonFuntions.clickButtonContains("Home ");
		sleep(4000);
		commonFuntions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		// --- Home ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("BCL483001", "Pass", "Successfully passed TC BCL.483.001");

		System.out.println("Pass :)");

		// Completed Palak

	}

}
