package com.employerContibution.RAD;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_246_01_001_Smoke extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can transfer payment when dues available for multiple quarter/year within the same account.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_246_01_001() throws Exception {
		
		test = report.createTest("RAD.246.01.001 : Verify CSR can transfer payment when dues available for multiple quarter/year within the same account.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		//GET method
		// valid ERN where employer has existing Bankruptcy record
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT\r\n" + 
				"                            te.EMPLOYER_ID AS EMPLOYER_ID ,\r\n" + 
				"                            te.EAN AS EAN,\r\n" + 
				"                            tea.ENTITY_NAME AS ENTITY_NAME,\r\n" + 
				"                            ttr.TAX_REPORT_ID AS TAX_REPORT_ID ,\r\n" + 
				"                            ttrd.TAX_REPORT_DETAIL_ID AS TAX_REPORT_DETAIL_ID,\r\n" + 
				"                            ttrd.EFFECTIVE_START_DATE AS EFFECTIVE_START_DATE,\r\n" + 
				"                            ttrd.EFFECTIVE_END_DATE AS EFFECTIVE_END_DATE,\r\n" + 
				"                            ttrd.REPORT_CATEGORY AS REPORT_CATEGORY,\r\n" + 
				"                            tmqy.QUARTER AS QUARTER,\r\n" + 
				"                            tmqy.YEAR AS YEAR,\r\n" + 
				"                            ttrd.TOTAL_WAGES AS TOTAL_REMUNERATION,\r\n" + 
				"                            ttrd.TAXABLE_WAGES AS WAGES_SUB_TO_CONT,\r\n" + 
				"                            (ttrd.TOTAL_WAGES - ttrd.TAXABLE_WAGES) AS WAGES_PAID_IN_EXCESS,\r\n" + 
				"                            ttrd.REPORT_FILED_DATE AS REPORT_FILED_DATE\r\n" + 
				"                        FROM\r\n" + 
				"                            T_TX_TAX_REPORT ttr\r\n" + 
				"                        JOIN T_TAX_REPORT_DETAIL ttrd ON\r\n" + 
				"                            ttr.TAX_REPORT_ID = ttrd.TAX_REPORT_ID\r\n" + 
				"                        JOIN T_EMPLOYER te ON\r\n" + 
				"                            te.EMPLOYER_ID = ttr.EMPLOYER_ID\r\n" + 
				"                        JOIN T_MST_QUARTER_YEAR tmqy ON\r\n" + 
				"                            tmqy.QUARTER_YEAR_ID = ttr.QUARTER_YEAR_ID\r\n" + 
				"                        JOIN T_EMPLOYER_ACCOUNT tea ON\r\n" + 
				"                            te.EAN = tea.EAN\r\n" + 
				"                        WHERE\r\n" + 
				"                            ttrd.IS_ACTIVE_FLAG IN ('1')\r\n" + 
				"                            AND te.EMPLOYER_ID LIKE '%%'\r\n" + 
				"                            AND tmqy.QUARTER IN ('1','2')\r\n" + 
				"                            AND tmqy.YEAR = '2023'\r\n" + 
				"                            AND ttrd.REPORT_CATEGORY = 'AMD';",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		
		if ((eanValue == null) || eanValue.isEmpty())
		{
			System.out.println("EAN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched ERN is " + eanValue + ".");
		}
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		commonFunction.ScrollMenu("Contribution/Wage Maintenance");
		commonFunction.clickMenu("Contribution/Wage Maintenance");
		commonFunction.ScrollMenu("Transfer Payment / Overpayment");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution/Wage Maintenance -> Transfer Payment / Overpayment");
		commonFunction.clickMenu("Transfer Payment / Overpayment");
		
		// --- TWR-205 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD24601001", "Pass", "Successfully launched Transfer Payment / Overpayment(TWR-205) page");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on blank Search of TWR-205 page");
		
		commonFunction.enterTextboxContains("From Employer Registration Number", "1111111");
		commonFunction.enterTextboxContains("To Employer Registration Number", "1111111");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Invalid ERN entered in TWR-205 page");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on Invalid ERN Search in TWR-205 page");
		
		commonFunction.enterTextboxContains("From Employer Registration Number", "");
		commonFunction.enterTextboxContains("To Employer Registration Number", "");
		sleep();
		commonFunction.enterTextboxContains("From Employer Registration Number", "9311967");
		commonFunction.enterTextboxContains("To Employer Registration Number", "9311967");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Different ERN entered in TWR-205 page");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on No payments/overpayment exist-ERN Search in TWR-205 page");
		
		commonFunction.enterTextboxContains("From Employer Registration Number", "");
		commonFunction.enterTextboxContains("To Employer Registration Number", "");
		sleep();
		commonFunction.enterTextboxContains("From Employer Registration Number", eanValue);
		commonFunction.enterTextboxContains("To Employer Registration Number", eanValue);
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Valid ERN entered in TWR-205 page");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Data fetched on ERN Search in TWR-205 page");
		
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on Continue in TWR-205 page, without any radio selection");
		
		commonFunction.selectRadioInTable("Payment", 1, 1, "");
		sleep(2000);
		
		commonFunction.selectRadioQuestions("Do you want to Suspend the payment? ", "No ");
		
		
		
		
		
		
		//  Need to update query, provied by Alladi, Rohit.
		
		
		
		
		
		
		commonFunction.enterTextboxContains("Employer Registration Number", "9311967");
		commonFunction.selectDropdownEquals("Return Type", " Amended ");
		commonFunction.selectDropdownEquals("Quarter", " 1 ");
		commonFunction.enterTextboxContains("Year", "1988");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on wrong year combination before Search");
		
		commonFunction.selectDropdownEquals("Quarter", " 1 ");
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "8888");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid Quarter-Year combination at Search");
		
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "2024");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid Quarter-Year combination at Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Year", "");
		commonFunction.enterTextboxContains("Year", "2023");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on blank ERN Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Employer Registration Number", "0000001");
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Error on invalid ERN Search");
		
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "No returns posted Error on wrong ERN-Quater/Year combination Search");
		
		sleep(1500);
		commonFunction.screenShot("RAD246001", "Pass", "Entered data in TWR-237 page");
		
		commonFunction.clickButtonContains(" Search ");
		sleep(2000);
		commonFunction.screenShot("RAD246001", "Pass", "Data present with the above ERN after search in TWR-901 page");
		
		
		commonFunction.waitForLoadingIconToDisappear();
		
		
		commonFunction.selectDropdown("Reason for Adjustment", " Fix Return Type ");
		radLocators.reasonForAdjustmentComment.sendKeys("Testing for smoke");
		sleep();
		try {
		commonFunction.selectRadioInTable("Amended", 1, 1, "");
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		
	    sleep(2000);
	    commonFunction.screenShot("RAD246001", "Fail", "Unable to click radio button form list");
	    
		
		System.out.println("Fail :(");
	}

}
