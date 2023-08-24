package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EM_005;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_484_002_Verify_CSR_can_search_for_warrant_vacate_with_reason_for_vacateisDepartmentError
		extends TestBase {
	@Test
	public void BCL_484_002() throws Exception {

		test = report.createTest(
				"BCL.484.002 - Verify CSR can search for warrants in an active status based on the ERN and process warrant vacate with reason for vacate is 'Department Error', task will create for manager review");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		EM_005 em005 = new EM_005(driver);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peoPage = new PEOPage(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		commonStepDefinitions cf = new commonStepDefinitions();

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn("SELECT\r\n"
				+ "                            DISTINCT EAN,\r\n" + "                            LEGAL_NAME,\r\n"
				+ "                            WARRANT_ID,\r\n" + "                            SEQ_NUM,\r\n"
				+ "                            SUM (CURRENT_AMOUNT_DUE) OVER (\r\n"
				+ "                            PARTITION BY SEQ_NUM) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                            COUNTY,\r\n" + "                            NAME_ON_WARRANT,\r\n"
				+ "                            WARRANT_STATUS,\r\n"
				+ "                            WARRANT_ISSUED_DATE,\r\n"
				+ "                            SUM(WARRANT_AMOUNT) OVER (\r\n"
				+ "                            PARTITION BY SEQ_NUM) AS WARRANT_AMOUNT,\r\n"
				+ "                            WARRANT_FILED_DATE,\r\n"
				+ "                            WARRANT_TYPE,\r\n" + "                            TRANSCRIPT_FLAG\r\n"
				+ "                        FROM\r\n" + "                            (\r\n"
				+ "                            SELECT\r\n"
				+ "                                b.AMT_ALREADY_ON_WARRANT AS WARRANT_AMOUNT,\r\n"
				+ "                                TE.EAN,\r\n"
				+ "                                TEA.ENTITY_NAME AS LEGAL_NAME,\r\n"
				+ "                                B.WARRANT_ID,\r\n"
				+ "                                TW.WARRANT_TYPE_CD AS WARRANT_TYPE,\r\n"
				+ "                                TW.WARRANT_NUMBER AS SEQ_NUM,\r\n"
				+ "                                TW.WARRANT_ISSUE_DATE AS WARRANT_ISSUED_DATE,\r\n"
				+ "                                TW.COUNTY AS COUNTY_CODE ,\r\n"
				+ "                                TMC.DESCRIPTION AS COUNTY ,\r\n"
				+ "                                B.CURRENT_AMOUNT_DUE AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                TEA.ENTITY_NAME AS NAME_ON_WARRANT,\r\n"
				+ "                                '0' AS TRANSCRIPT_FLAG,\r\n"
				+ "                                (CASE WHEN TW.WARRANT_STATUS_CD = 'ENRL' THEN 'Enrolled'\r\n"
				+ "                        END ) AS WARRANT_STATUS,\r\n"
				+ "                                TWE.WARRANT_ENROLLMENT_DATE AS WARRANT_FILED_DATE\r\n"
				+ "                            FROM\r\n" + "                                (\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((AMOUNT_DUE-AMOUNT_PAID)) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        A.WARRANT_ID,\r\n"
				+ "                                        AMOUNT_DUE,\r\n"
				+ "                                        AMOUNT_PAID,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT ttw\r\n"
				+ "                                    JOIN T_TX_WARRANT_AMOUNT_DETAIL a ON\r\n"
				+ "                                        ttw.WARRANT_ID = a.WARRANT_ID\r\n"
				+ "                                    JOIN T_TX_TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS b ON\r\n"
				+ "                                        a.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID = b.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID\r\n" + "                            UNION\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((DUE_AMOUNT -PAID_AMOUNT)+(INTEREST_DUE_AMOUNT - INTEREST_PAID_AMOUNT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM(a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        a.WARRANT_ID,\r\n"
				+ "                                        b.DUE_AMOUNT,\r\n"
				+ "                                        b.PAID_AMOUNT,\r\n"
				+ "                                        b.INTEREST_DUE_AMOUNT,\r\n"
				+ "                                        b.INTEREST_PAID_AMOUNT,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "                                    JOIN T_TX_OTHER_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "                                        a.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID\r\n" + "                            UNION\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((CHARGE_DUE_AMT -CHARGE_PAID_AMT)+(INTEREST_DUE_AMT - INTEREST_PAID_AMT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        a.WARRANT_ID,\r\n"
				+ "                                        b.CHARGE_DUE_AMT,\r\n"
				+ "                                        b.CHARGE_PAID_AMT,\r\n"
				+ "                                        b.INTEREST_DUE_AMT,\r\n"
				+ "                                        b.INTEREST_PAID_AMT,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "                                    JOIN T_TX_REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "                                        a.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID )B\r\n"
				+ "                            JOIN T_TX_WARRANT TW ON\r\n"
				+ "                                TW.WARRANT_ID = B.WARRANT_ID\r\n"
				+ "                            JOIN T_EMPLOYER TE ON\r\n"
				+ "                                TE.EMPLOYER_ID = TW.EMPLOYER_ID\r\n"
				+ "                            JOIN T_EMPLOYER_ACCOUNT TEA ON\r\n"
				+ "                                TE.EAN = TEA.EAN\r\n"
				+ "                            JOIN T_EMPLOYER_PARTNER TEP ON\r\n"
				+ "                                TEA.EMPLOYER_ACCOUNT_ID = TEP.EMPLOYER_ACCOUNT_ID\r\n"
				+ "                            JOIN T_MST_COUNTY TMC ON\r\n"
				+ "                                TW.COUNTY = TMC.\"KEY\"\r\n"
				+ "                            JOIN T_TX_WARRANT_ENROLLMENT TWE ON\r\n"
				+ "                                TW.WARRANT_ID = TWE.WARRANT_ID\r\n"
				+ "                            WHERE\r\n" + "                                --TE.EAN = ?\r\n"
				+ "                                --AND \r\n"
				+ "                                TW.WARRANT_STATUS_CD IN ('ENRL')\r\n"
				+ "                            GROUP BY\r\n" + "                                B.WARRANT_ID,\r\n"
				+ "                                TEA.ENTITY_NAME,\r\n" + "                                TE.EAN,\r\n"
				+ "                                TW.WARRANT_NUMBER,\r\n"
				+ "                                TW.WARRANT_ISSUE_DATE ,\r\n"
				+ "                                TW.COUNTY,\r\n"
				+ "                                TMC.DESCRIPTION,\r\n"
				+ "                                TEP.ENTITY_NAME ,\r\n"
				+ "                                TW.WARRANT_STATUS_CD,\r\n"
				+ "                                TWE.WARRANT_ENROLLMENT_DATE,\r\n"
				+ "                                b.AMT_ALREADY_ON_WARRANT,\r\n"
				+ "                                B.CURRENT_AMOUNT_DUE,\r\n"
				+ "                                TW.WARRANT_TYPE_CD\r\n" + "                        UNION\r\n"
				+ "                            SELECT\r\n"
				+ "                                b.AMT_ALREADY_ON_WARRANT AS WARRANT_AMOUNT,\r\n"
				+ "                                TE.EAN,\r\n"
				+ "                                TEA.ENTITY_NAME AS LEGAL_NAME,\r\n"
				+ "                                B.WARRANT_ID,\r\n"
				+ "                                TW.WARRANT_TYPE_CD AS WARRANT_TYPE,\r\n"
				+ "                                TWT.SEQUENCE_NUMBER AS SEQ_NUM,\r\n"
				+ "                                TWT.TRANSCRIPT_ISSUE_DATE AS WARRANT_ISSUED_DATE,\r\n"
				+ "                                TWT.COUNTY AS COUNTY_CODE ,\r\n"
				+ "                                TMC.DESCRIPTION AS COUNTY ,\r\n"
				+ "                                B.CURRENT_AMOUNT_DUE AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                TEA.ENTITY_NAME AS NAME_ON_WARRANT,\r\n"
				+ "                                '1' AS TRANSCRIPT_FLAG,\r\n"
				+ "                                (CASE WHEN TWT.TRANSCRIPT_STATUS = 'ENRL' THEN 'Enrolled' END ) AS WARRANT_STATUS,\r\n"
				+ "                                TWT.TRANSCRIPT_ENROLLMENT_DATE AS WARRANT_FILED_DATE\r\n"
				+ "                            FROM\r\n" + "                                (\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((AMOUNT_DUE-AMOUNT_PAID)) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        A.WARRANT_ID,\r\n"
				+ "                                        AMOUNT_DUE,\r\n"
				+ "                                        AMOUNT_PAID,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT ttw\r\n"
				+ "                                    JOIN T_TX_WARRANT_AMOUNT_DETAIL a ON\r\n"
				+ "                                        ttw.WARRANT_ID = a.WARRANT_ID\r\n"
				+ "                                    JOIN T_TX_TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS b ON\r\n"
				+ "                                        a.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID = b.TAX_AND_PAYMENT_DISTRIBUTION_SUMMARY_DETAILS_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID\r\n" + "                            UNION\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((DUE_AMOUNT -PAID_AMOUNT)+(INTEREST_DUE_AMOUNT - INTEREST_PAID_AMOUNT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM(a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        a.WARRANT_ID,\r\n"
				+ "                                        b.DUE_AMOUNT,\r\n"
				+ "                                        b.PAID_AMOUNT,\r\n"
				+ "                                        b.INTEREST_DUE_AMOUNT,\r\n"
				+ "                                        b.INTEREST_PAID_AMOUNT,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "                                    JOIN T_TX_OTHER_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "                                        a.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.OTHER_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID\r\n" + "                            UNION\r\n"
				+ "                                SELECT\r\n" + "                                    WARRANT_ID,\r\n"
				+ "                                    SUM ((CHARGE_DUE_AMT -CHARGE_PAID_AMT)+(INTEREST_DUE_AMT - INTEREST_PAID_AMT )) AS CURRENT_AMOUNT_DUE,\r\n"
				+ "                                    SUM (a.AMT_INCLUDED_IN_WARRANT) AS AMT_ALREADY_ON_WARRANT\r\n"
				+ "                                FROM\r\n" + "                                    (\r\n"
				+ "                                    SELECT\r\n"
				+ "                                        a.WARRANT_ID,\r\n"
				+ "                                        b.CHARGE_DUE_AMT,\r\n"
				+ "                                        b.CHARGE_PAID_AMT,\r\n"
				+ "                                        b.INTEREST_DUE_AMT,\r\n"
				+ "                                        b.INTEREST_PAID_AMT,\r\n"
				+ "                                        a.AMT_INCLUDED_IN_WARRANT\r\n"
				+ "                                    FROM\r\n"
				+ "                                        T_TX_WARRANT_AMOUNT_DETAIL a\r\n"
				+ "                                    JOIN T_TX_REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY b ON\r\n"
				+ "                                        a.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID = b.REIM_CHARGE_AND_PAYMENT_DISTRIBUTION_SUMMARY_ID )A\r\n"
				+ "                                GROUP BY\r\n"
				+ "                                    A.WARRANT_ID )B\r\n"
				+ "                            JOIN T_TX_WARRANT TW ON\r\n"
				+ "                                TW.WARRANT_ID = B.WARRANT_ID\r\n"
				+ "                            JOIN T_EMPLOYER TE ON\r\n"
				+ "                                TE.EMPLOYER_ID = TW.EMPLOYER_ID\r\n"
				+ "                            JOIN T_EMPLOYER_ACCOUNT TEA ON\r\n"
				+ "                                TE.EAN = TEA.EAN\r\n"
				+ "                            JOIN T_EMPLOYER_PARTNER TEP ON\r\n"
				+ "                                TEA.EMPLOYER_ACCOUNT_ID = TEP.EMPLOYER_ACCOUNT_ID\r\n"
				+ "                            JOIN T_TX_WARRANT_ENROLLMENT TWE ON\r\n"
				+ "                                TW.WARRANT_ID = TWE.WARRANT_ID\r\n"
				+ "                            JOIN T_TX_WARRANT_TRANSCRIPT TWT ON\r\n"
				+ "                                TWT.WARRANT_ID = TW.WARRANT_ID\r\n"
				+ "                            JOIN T_MST_COUNTY TMC ON\r\n"
				+ "                                TWT.COUNTY = TMC.\"KEY\"\r\n"
				+ "                            WHERE\r\n" + "                                --TE.EAN = ?\r\n"
				+ "                                --AND \r\n"
				+ "                                TWT.TRANSCRIPT_STATUS IN ('ENRL')\r\n"
				+ "                            )\r\n" + "                        GROUP BY\r\n"
				+ "                            EAN,\r\n" + "                            LEGAL_NAME,\r\n"
				+ "                            WARRANT_ID,\r\n" + "                            SEQ_NUM,\r\n"
				+ "                            COUNTY,\r\n" + "                            NAME_ON_WARRANT,\r\n"
				+ "                            WARRANT_STATUS,\r\n"
				+ "                            WARRANT_ISSUED_DATE,\r\n"
				+ "                            WARRANT_AMOUNT,\r\n"
				+ "                            WARRANT_FILED_DATE,\r\n"
				+ "                            CURRENT_AMOUNT_DUE,\r\n"
				+ "                            WARRANT_TYPE,\r\n" + "                            TRANSCRIPT_FLAG",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);
		// -----Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		// -----Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.ScrollMenu("Contribution Collection");
		cf.clickMenu("Contribution Collection");
		sleep(1000);
		cf.clickMenu("Warrant");
		cf.clickMenu("Request to Vacate Warrant");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Request to Vacate Warrant", "Pass", "Successfully launched to COL-498");

		// Valid EAN
		cf.enterTextboxContains("Employer Registration Number (ERN)", eanValue);
		cf.screenShot("Employer Registration Number (ERN)", "Pass", "Launched to COL-498");
		cf.clickButtonContains("Continue ");

		cf.screenShot("List of Warrants", "Pass", "Launched to COL_490");
		em005.selectToggle.click();
		cf.clickButtonContains("Continue ");

		// COL-499
		cf.screenShot("Request to Vacate Warrant Details", "Pass", "Launched to COL-499");
		try {
			em005.yesRadioInCircle.click();
		} catch (Exception exception) {
			em005.yesRadioOutCircle.click();
		}
		cf.selectDropdown("If yes, select the reason to Vacate a Warrant", " Department Error ");
		em005.reasonExplanation.sendKeys("ok");
		empRegPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Submit ");

		cf.screenShot("Request to Vacate Warrant Confirmation", "Pass", "Launched to SUC_002");
		suc002.pOASucessText.isDisplayed();

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ eanValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);
		peoPage.queue.click();
		Thread.sleep(15000);

		cf.enterTextboxContains("Work Item Description Free Text", "Request to Vacate");
		cf.clickButtonContains(" Search ");
		cf.clickOnLinkAnchorTag("Request To Vacate Warrant");
		cf.screenShot("Work Item Details", "Pass", "WF-091");
		cf.clickButtonContains("Open Work Item");
		sleep(4000);

		// BCL-WI-300
		cf.screenShot("Request to Vacate Warrant Approval Task", "Pass", "Launched to BCL-WI-300");
		
		// Approving
		try {
			em005.approveRadioInCircle.click();
		} catch (Exception exception) {
			em005.approveRadioOutCircle.click();
		}
		em005.reasonExplanation.sendKeys("ok");
		empRegPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Submit ");

		// SUC-002
		cf.screenShot("Request to Vacate Warrant Approval Task Confirmation", "Pass",
				"Successfully launched to SUC-002");
		cf.Label(
				"Request to Vacate the Warrant is Approved and a Vacate Warrant Follow-Up Task has been created successfully.");

		// Update Work Item
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ eanValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);
		peoPage.queue.click();
		Thread.sleep(15000);

		cf.enterTextboxContains("Work Item Description Free Text", "Vacate warrant follow up");
		cf.clickButtonContains(" Search ");
		cf.clickOnLinkAnchorTag("Vacate Warrant Follow Up Task");
		cf.screenShot("Work Item Details", "Pass", "WF-091");
		cf.clickButtonContains("Open Work Item");
		sleep(4000);

		cf.screenShot("Vacate Warrant Follow-Up Task", "Pass", "Launched to BCL-3413");
		cf.enterTextboxContains("Warrant Vacate Date", "12/31/2023");
		em005.reasonExplanation.sendKeys("ok");
		empRegPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Continue ");

		cf.screenShot("Vacate Warrant Follow-Up Task Verification", "Pass", "Launched to COL-500");
		cf.clickButtonContains("Submit ");

		// SUC-002
		cf.screenShot("Vacate Warrant Follow-Up Confirmation", "Pass", "Launched to SUC-002");
		suc002.pOASucessText.isDisplayed();
		cf.clickButtonContains("Home ");
		System.out.println("Worked");

	}
}
