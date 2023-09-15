package com.employerContibution.CA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class CA_067_03_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can add Deposit Details of DTF for deposit type 'E-File' and update interface status 'Reconciled'", groups = COMMON_CONSTANT.REGRESSION)
	public void TC_CA_067_03_001() throws Exception {

		test = report.createTest("CA.067.03.001 : Verify CSR can add Deposit Details of DTF for deposit type 'E-File' and update interface status 'Reconciled'");
		
		test.log(Status.INFO, "TC Script developed by Ankan Das");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		
		//GET Query
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
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
		System.out.println("The Deposit Date is" + depositDate);
		
		if ((depositDate == null) || depositDate.isEmpty())
		{
			System.out.println("Deposit Date is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched Deposit Date is " + depositDate + ".");
		}
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_7.toUpperCase(), COMMON_CONSTANT.CSR_USER_7_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		
	}
}
