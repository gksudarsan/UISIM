package com.employerContibution.CA;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.CaPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class CA_067_03_001_Smoke extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can add Deposit Details of DTF for deposit type 'E-File' and update interface status 'Reconciled'", groups = COMMON_CONSTANT.REGRESSION)
	public void TC_CA_067_03_001() throws Exception {

		test = report.createTest("CA.067.03.001 : Verify CSR can add Deposit Details of DTF for deposit type 'E-File' and update interface status 'Reconciled'");
		
		test.log(Status.INFO, "TC Script developed by Ankan Das");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		CaPage caPage = new CaPage(driver);
		
		//GET Query
		/*Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT ISNULL(RECON_STATUS,'Not Recieved')\r\n" + 
				"	AS RECONCILATION_STATUS ,ISNULL(POSTED_STATUS,'No')\r\n" + 
				"	AS POSTED, D.*,ABC.*\r\n" + 
				"	FROM\r\n" + 
				"	(SELECT DAILY_DEPOSIT_TYPE_CD,DAILY_DEPOSIT_TYPE_CD_DESCRIPTION,VENDOR_NAME,TRANSACTION_TYPE FROM T_MST_TAX_DAILY_DEPOSIT_TYPE\r\n" + 
				"	UNION\r\n" + 
				"	SELECT 'NYS-02BANK1' AS DAILY_DEPOSIT_TYPE_CD, 'Paper NYS-45/IA' AS DAILY_DEPOSIT_TYPE_CD_DESCRIPTION , 'JPMCHASE' AS VENDOR_NAME , 'Credit Memo' AS TRANSACTION_TYPE FROM T_MST_TAX_DAILY_DEPOSIT_TYPE UNION\r\n" + 
				"	SELECT 'NYS-02BANK2' AS DAILY_DEPOSIT_TYPE_CD, 'Paper NYS-45/IA' AS DAILY_DEPOSIT_TYPE_CD_DESCRIPTION , 'JPMCHASE' AS VENDOR_NAME , 'Debit Memo' AS TRANSACTION_TYPE  FROM T_MST_TAX_DAILY_DEPOSIT_TYPE UNION\r\n" + 
				"	SELECT 'NYS-02BANK3' AS DAILY_DEPOSIT_TYPE_CD, 'Paper NYS-45/IA' AS DAILY_DEPOSIT_TYPE_CD_DESCRIPTION ,'JPMCHASE' AS VENDOR_NAME , 'Dishonors' AS TRANSACTION_TYPE FROM T_MST_TAX_DAILY_DEPOSIT_TYPE\r\n" + 
				"	ORDER BY VENDOR_NAME,DAILY_DEPOSIT_TYPE_CD_DESCRIPTION desc) D\r\n" + 
				"LEFT JOIN (\r\n" + 
				"	SELECT (CASE WHEN R.INTERFACE_FILE_STATUS IN ('RCLD') THEN ('Reconciled')\r\n" + 
				"				WHEN R.INTERFACE_FILE_STATUS IN ('RJCT') THEN ('Rejected')\r\n" + 
				"				WHEN R.INTERFACE_FILE_STATUS IN ('FTER') THEN ('Reported Fatal Error')\r\n" + 
				"				WHEN R.INTERFACE_FILE_STATUS IN ('OVRD') THEN ('Overridden')\r\n" + 
				"				WHEN R.INTERFACE_FILE_STATUS IN ('VALD') AND R.FIX_FILE_RECEIVED  IN ('1')THEN ('Fix File Recieved')\r\n" + 
				"				WHEN R.INTERFACE_FILE_STATUS IN ('VALD') AND R.FIX_FILE_RECEIVED  IN ('0')THEN ('Unreconciled')\r\n" + 
				"	END) AS RECON_STATUS WHERE,\r\n" + 
				"			(CASE\r\n" + 
				"				WHEN B.BATCH_STATUS IN ('PSTD') THEN ('Yes') ELSE ('No')            \r\n" + 
				"	END) AS POSTED_STATUS, T.* , R.* FROM T_TX_TAX_REPORT_IMPORT_BATCH_TEMP T\r\n" + 
				"LEFT JOIN T_TX_TAX_REPORT_IMPORT_BATCH B ON (B.RETURN_PAYMENT_ADJUSTMENT_INTERFACE_FILE_ID = T.RETURN_PAYMENT_ADJUSTMENT_INTERFACE_FILE_ID)     JOIN T_TX_RETURN_PAYMENT_ADJUSTMENT_INTERFACE_FILE R ON (T.RETURN_PAYMENT_ADJUSTMENT_INTERFACE_FILE_ID = R.RETURN_PAYMENT_ADJUSTMENT_INTERFACE_FILE_ID)\r\n" + 
				" WHERE R.INTERFACE_FILE_STATUS NOT IN ('RCVD','IMPT')) AS ABC ON ( D.DAILY_DEPOSIT_TYPE_CD = ABC.INTERFACE_FILE_NAME) ","DEPOSIT_DATE");
		String depositDate = databaseResults.get("DEPOSIT_DATE");
		System.out.println("The Deposit Date is" + depositDate);*/
		
		String depositDate = COMMON_CONSTANT.NULL;
		
		if ((depositDate == null) || depositDate.isEmpty())
		{
			System.out.println("Deposit Date is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched Deposit Date is " + depositDate + ".");
		}
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.BANK_UNIT_SPECIALIST.toUpperCase(), COMMON_CONSTANT.BANK_UNIT_SPECIALIST_PASSWORD);
		test.log(Status.PASS, "Login with Bank Unit Specialist role is successful.");
		
		// ---Menu Click---
		commonFuntions.waitForLoadingIconToDisappear();
		caPage.menu.click();
		commonFuntions.ScrollMenu("Add Daily Deposits");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Add Daily Deposits");
		commonFuntions.clickMenu("Add Daily Deposits");
		
		// --- CAS-029 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful launch to Add Daily Deposits (CAS-029) screen.");
		
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("CA06703001", "Pass", "Error on blank Search without deposit date");
		
		commonFuntions.enterTextboxContains("Deposit Date", "01-15-2023");//depositDate
		sleep(2000);
		commonFuntions.screenShot("CA06703001", "Pass", "Entered valid Deposit date and clicked on Search");
		commonFuntions.clickButtonContains(" Search ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		
		// -- to click on Cancel at print priview, pop up not handled
		//commonFuntions.clickButtonContains(" Print ");
		//sleep(2000);
		//commonFuntions.screenShot("CA06703001", "Pass", "Clicked on Print button, Print preview launched");
		//commonFuntions.clickButtonContains(""); 
		
		commonFuntions.ScrollMenu(" View Weekly Deposit History ");
		sleep();
		commonFuntions.screenShot("CA06703001", "Pass", "Page scrolled to click on View Weekly Deposit History hyperlink");
		commonFuntions.clickOnLinkAnchorTag(" View Weekly Deposit History ");
		
		// --- CAS-032 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful launch to Weekly Deposit History (CAS-032) screen.");
		commonFuntions.clickButtonContains("Close ");
		
		// --- CAS-029 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful launch to Add Daily Deposits (CAS-029) screen.");
		
		commonFuntions.enterTextboxContains("Deposit Date", "");
		commonFuntions.enterTextboxContains("Deposit Date", "01-12-2023");//depositDate
		sleep(2000);
		commonFuntions.screenShot("CA06703001", "Pass", "Entered valid Deposit date and clicked on Search");
		commonFuntions.clickButtonContains(" Search ");
		
		commonFuntions.ScrollMenu(" Daily Deposit Reconciliation ");
		sleep();
		commonFuntions.screenShot("CA06703001", "Pass", "Page scrolled to click on Daily Deposit Reconciliation hyperlink");
		commonFuntions.clickOnLinkAnchorTag(" Daily Deposit Reconciliation ");
		
		// --- CAS-031 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful launch to Daily Deposit Reconciliation (CAS-031) screen.");
		caPage.remitanceInputBox2.clear();
		sleep(1200);
		caPage.remitanceInputBox2.sendKeys("83.03");
		caPage.remitanceInputBox3.clear();
		sleep(1200);
		caPage.remitanceInputBox3.sendKeys("50");
		caPage.remitanceInputBox4.clear();
		sleep(1200);
		caPage.remitanceInputBox4.sendKeys("83.03");
		sleep(2000);
		commonFuntions.screenShot("CA06703001", "Pass", "Entered Remittance amount accordingly");
		commonFuntions.clickButtonContains("Save ");
		
		// --- SUC-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful launch to Daily Deposit Reconciliation - Confirmation(SUC-002) screen.");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CA06703001", "Pass", "Successful passed TC CA.067.03.001.");
		System.out.println("Smoke");
		
	}
}
