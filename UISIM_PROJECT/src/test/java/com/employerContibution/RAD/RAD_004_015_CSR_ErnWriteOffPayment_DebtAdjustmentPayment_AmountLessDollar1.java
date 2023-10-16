package com.employerContibution.RAD;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class RAD_004_015_CSR_ErnWriteOffPayment_DebtAdjustmentPayment_AmountLessDollar1 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to search ERN with Adjustment Type 'Write Off Payment' and process debt adjustment payment with reason Amount Less than $1.00", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_004_015() throws Exception {
	
		test = report.createTest("RAD.004.015 : Verify CSR is able to search ERN with Adjustment Type 'Write Off Payment' and process debt adjustment payment with reason Amount Less than $1.00");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		//GET method
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT\r\n"
				+ "								EAN,\r\n"
				+ "								EMPLOYER_ID,\r\n"
				+ "								EMPLOYER_NAME,\r\n"
				+ "								PAYMENT_XREF_ID,\r\n"
				+ "								TAX_PAYMENT_ID,\r\n"
				+ "								TAX_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "								REIM_CHARGE_PAYMENT_ID,\r\n"
				+ "								REIM_CREDIT_TRANSACTION_ID,\r\n"
				+ "								OTHER_PAYMENT_ID,\r\n"
				+ "								OTHER_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "								PAYMENT_DATE,\r\n"
				+ "								PAYMENT_TYPE,\r\n"
				+ "								PAYMENT_AMOUNT,\r\n"
				+ "								STATUS\r\n"
				+ "							FROM\r\n"
				+ "								(\r\n"
				+ "								SELECT\r\n"
				+ "									te.EAN AS EAN,\r\n"
				+ "									te.EMPLOYER_ID AS EMPLOYER_ID ,\r\n"
				+ "									te.EMPLOYER_NAME AS EMPLOYER_NAME,\r\n"
				+ "									xref.PAYMENT_XREF_ID AS PAYMENT_XREF_ID,\r\n"
				+ "									xref.TAX_PAYMENT_ID AS TAX_PAYMENT_ID ,\r\n"
				+ "									tfttpat.TAX_PAYMENT_APPLIED_TRANSACTION_ID AS TAX_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "									NULL AS REIM_CHARGE_PAYMENT_ID ,\r\n"
				+ "									NULL AS REIM_CREDIT_TRANSACTION_ID,\r\n"
				+ "									NULL AS OTHER_PAYMENT_ID,\r\n"
				+ "									NULL AS OTHER_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "									xref.PAYMENT_DATE AS PAYMENT_DATE,\r\n"
				+ "									(CASE\r\n"
				+ "										WHEN tfttpat.TYPE IN ('PMNT') THEN ('Payment')\r\n"
				+ "										ELSE ('Over Payment')\r\n"
				+ "								END) AS PAYMENT_TYPE,\r\n"
				+ "									ttmspx.AVAILABLE_AMOUNT AS PAYMENT_AMOUNT,\r\n"
				+ "									xref.PAYMENT_STATUS AS STATUS\r\n"
				+ "								FROM\r\n"
				+ "									T_FTI_TX_PAYMENT_XREF xref\r\n"
				+ "								JOIN T_TX_MAP_SUSPENDED_PAYMENT_XREF ttmspx ON\r\n"
				+ "									xref.PAYMENT_XREF_ID = ttmspx.PAYMENT_XREF_ID\r\n"
				+ "								JOIN T_FTI_TX_TAX_PAYMENT tfttp ON\r\n"
				+ "									xref.TAX_PAYMENT_ID = tfttp.TAX_PAYMENT_ID\r\n"
				+ "								JOIN T_FTI_TX_TAX_PAYMENT_APPLIED_TRANSACTION tfttpat ON\r\n"
				+ "									tfttp.TAX_PAYMENT_ID = tfttpat.TAX_PAYMENT_ID\r\n"
				+ "								JOIN T_EMPLOYER TE ON\r\n"
				+ "									xref.SUBMITTED_BY_EMPLOYER_ID = te.EMPLOYER_ID\r\n"
				+ "								WHERE\r\n"
				+ "									xref.PAYMENT_STATUS IN ('SUEP',\r\n"
				+ "									'SUSP',\r\n"
				+ "									'SSHD')\r\n"
				+ "									AND tfttpat.TYPE IN ('PMNT',\r\n"
				+ "									'CRDT')\r\n"
				+ "									AND tfttpat.WRITE_OFF_FLAG NOT IN ('1')\r\n"
				+ "							UNION\r\n"
				+ "								SELECT\r\n"
				+ "									te.EAN AS EAN,\r\n"
				+ "									te.EMPLOYER_ID AS EMPLOYER_ID ,\r\n"
				+ "									te.EMPLOYER_NAME AS EMPLOYER_NAME,\r\n"
				+ "									xref.PAYMENT_XREF_ID AS PAYMENT_XREF_ID,\r\n"
				+ "									NULL AS TAX_PAYMENT_ID ,\r\n"
				+ "									NULL AS TAX_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "									xref.REIM_CHARGE_PAYMENT_ID AS REIM_CHARGE_PAYMENT_ID ,\r\n"
				+ "									ttrct.REIM_CREDIT_TRANSACTION_ID AS REIM_CREDIT_TRANSACTION_ID,\r\n"
				+ "									NULL AS OTHER_PAYMENT_ID,\r\n"
				+ "									NULL AS OTHER_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "									xref.PAYMENT_DATE AS PAYMENT_DATE,\r\n"
				+ "									(CASE\r\n"
				+ "										WHEN ttrct.CREDIT_TRANSACTION_TYPE IN ('PMNT') THEN ('Payment')\r\n"
				+ "										ELSE ('Over Payment')\r\n"
				+ "								END) AS PAYMENT_TYPE,\r\n"
				+ "									ttmspx.AVAILABLE_AMOUNT AS PAYMENT_AMOUNT,\r\n"
				+ "									xref.PAYMENT_STATUS AS STATUS\r\n"
				+ "								FROM\r\n"
				+ "									T_FTI_TX_PAYMENT_XREF xref\r\n"
				+ "								JOIN T_TX_MAP_SUSPENDED_PAYMENT_XREF ttmspx ON\r\n"
				+ "									xref.PAYMENT_XREF_ID = ttmspx.PAYMENT_XREF_ID\r\n"
				+ "								JOIN T_FTI_TX_REIM_CHARGE_PAYMENT tftrcp ON\r\n"
				+ "									xref.REIM_CHARGE_PAYMENT_ID = tftrcp.REIM_CHARGE_PAYMENT_ID\r\n"
				+ "								JOIN T_TX_REIM_CREDIT_TRANSACTION ttrct ON\r\n"
				+ "									tftrcp.REIM_CHARGE_PAYMENT_ID = ttrct.REIM_CHARGE_PAYMENT_ID\r\n"
				+ "								JOIN T_EMPLOYER TE ON\r\n"
				+ "									xref.SUBMITTED_BY_EMPLOYER_ID = te.EMPLOYER_ID\r\n"
				+ "								WHERE\r\n"
				+ "									xref.PAYMENT_STATUS IN ('SUEP',\r\n"
				+ "									'SUSP',\r\n"
				+ "									'SSHD')\r\n"
				+ "									AND ttrct.CREDIT_TRANSACTION_TYPE IN ('PMNT',\r\n"
				+ "									'CRDT')\r\n"
				+ "									AND ttrct.WRITE_OFF_FLAG NOT IN ('1')\r\n"
				+ "							UNION\r\n"
				+ "								SELECT\r\n"
				+ "									te.EAN AS EAN,\r\n"
				+ "									te.EMPLOYER_ID AS EMPLOYER_ID ,\r\n"
				+ "									te.EMPLOYER_NAME AS EMPLOYER_NAME,\r\n"
				+ "									xref.PAYMENT_XREF_ID AS PAYMENT_XREF_ID,\r\n"
				+ "									NULL AS TAX_PAYMENT_ID ,\r\n"
				+ "									NULL AS TAX_PAYMENT_APPLIED_TRANSACTION_ID,\r\n"
				+ "									NULL AS REIM_CHARGE_PAYMENT_ID ,\r\n"
				+ "									NULL AS REIM_CREDIT_TRANSACTION_ID,\r\n"
				+ "									xref.OTHER_PAYMENT_ID AS OTHER_PAYMENT_ID ,\r\n"
				+ "									tftopat.OTHER_PAYMENT_APPLIED_TRANSACTION_ID ,\r\n"
				+ "									xref.PAYMENT_DATE AS PAYMENT_DATE,\r\n"
				+ "									(CASE\r\n"
				+ "										WHEN tftopat.TYPE IN ('PMNT') THEN ('Payment')\r\n"
				+ "										ELSE ('Over Payment')\r\n"
				+ "								END) AS PAYMENT_TYPE,\r\n"
				+ "									ttmspx.AVAILABLE_AMOUNT AS PAYMENT_AMOUNT,\r\n"
				+ "									xref.PAYMENT_STATUS AS STATUS\r\n"
				+ "								FROM\r\n"
				+ "									T_FTI_TX_PAYMENT_XREF xref\r\n"
				+ "								JOIN T_TX_MAP_SUSPENDED_PAYMENT_XREF ttmspx ON\r\n"
				+ "									xref.PAYMENT_XREF_ID = ttmspx.PAYMENT_XREF_ID\r\n"
				+ "								JOIN T_FTI_TX_OTHER_PAYMENT tftop ON\r\n"
				+ "									xref.OTHER_PAYMENT_ID = tftop.OTHER_PAYMENT_ID\r\n"
				+ "								JOIN T_FTI_TX_OTHER_PAYMENT_APPLIED_TRANSACTION tftopat ON\r\n"
				+ "									tftop.OTHER_PAYMENT_ID = tftopat.OTHER_PAYMENT_ID\r\n"
				+ "								JOIN T_EMPLOYER TE ON\r\n"
				+ "									xref.SUBMITTED_BY_EMPLOYER_ID = te.EMPLOYER_ID\r\n"
				+ "								JOIN T_TX_OTHER_PAYMENT_DISTRIBUTION_SUMMARY ttopds ON \r\n"
				+ "									te.EMPLOYER_ID = ttopds.EMPLOYER_ID \r\n"
				+ "								WHERE\r\n"
				+ "									xref.PAYMENT_STATUS IN ('SUEP',\r\n"
				+ "									'SUSP',\r\n"
				+ "									'SSHD')\r\n"
				+ "									AND tftopat.TYPE IN ('PMNT',\r\n"
				+ "									'CRDT')\r\n"
				+ "									AND ttopds.PAYMENT_CATEGORY NOT IN ('FTF','FTFI','FTF ')\r\n"
				+ "									AND tftopat.WRITE_OFF_FLAG NOT IN ('1')\r\n"
				+ "									)\r\n"
				+ "							WHERE\r\n"
				+ "								EAN IS NOT NULL",
				"EAN");
		//5125848
		
//		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn("","");
		String eanValue = databaseEanResult.get("EAN");
		
//		String eanValue = "5125848";
		
		if ((eanValue == null) || eanValue.isEmpty())
		{
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanValue + ".");
		}
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_ACCOUNT_SUPERVISOR.toUpperCase(), COMMON_CONSTANT.EMPLOYER_ACCOUNT_SUPERVISOR_PASSWORD);
		test.log(Status.PASS, "Login with Employer Account Supervisor is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		sleep(2000);
		commonFunction.ScrollMenu("Contribution/Wage Maintenance");
		commonFunction.clickMenu("Contribution/Wage Maintenance");
		commonFunction.ScrollMenu("Debt Adjustment");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution/Wage Maintenance -> Debt Adjustment");
		commonFunction.clickMenu("Debt Adjustment");
		
		// --- TWR-074 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD004015", "Pass", "Successfully launched Debt Adjustment - Enter Employer Registration Number(TWR-074) page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.selectRadioQuestions("Adjustment Type", "Write Off Payment");
		sleep();
		commonFunction.screenShot("RAD004015", "Pass", "Entered data on TWR-074 page");
		commonFunction.clickButtonContains(" Search ");
		
		commonFunction.screenShot("RAD004015", "Pass", "Data present in TWR-074 page");
		
		sleep(10000);
		radLocators.dataTableId002_checkbox.click();
		sleep(1500);
		radLocators.dataTableId002.click();
		sleep();
		radLocators.amtLess1Dollar.click();
		sleep(2000);
		commonFunction.screenShot("RAD004015", "Pass", "Selected reason as 'Amount less than $1'");
		
		commonFunction.clickButtonContains("Submit ");
		// --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD004015", "Pass", "Successfully launched Debt Adjustment Confirmation(SUC-002) page");
		commonFunction.clickButtonContains("Home ");

		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD004015", "Pass", "Successfully passed TC RAD.004.015.");
	}
}
