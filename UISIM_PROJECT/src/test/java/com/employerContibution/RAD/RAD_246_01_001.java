
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
public class RAD_246_01_001 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can transfer payment when dues available for multiple quarter/year within the same account.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_246_01_001() throws Exception {
		
		test = report.createTest("RAD.246.01.001 : Verify CSR can transfer payment when dues available for multiple quarter/year within the same account.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		test.log(Status.INFO, "Script developed by Ankan Das.");
		
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
		
		
//		String eanValue = "1111223";
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.Employer_Account_Specialist.toUpperCase(), COMMON_CONSTANT.Employer_Account_Specialist_PASSWORD);
		test.log(Status.PASS, "Login with Employer Account Specialist role is successful");
		
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
		commonFunction.enterTextboxContains("From Employer Registration Number", "9310967"); //9311967
		commonFunction.enterTextboxContains("To Employer Registration Number", "9311067");
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
		
		radLocators.Selct_Transfer_Payment_Overpayment.click();
		sleep(2000);
		commonFunction.selectRadioQuestions("Do you want to Suspend the payment? ", "No ");
		commonFunction.selectCheckbox("Interest ");
commonFunction.screenShot("RAD24601001", "Pass", "Entered the information on  TWR-205 page");
		
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on Continue in TWR-205 page, No amount due selected");
		radLocators.Selct_Due_Amount.click();
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on Continue in TWR-205 page, Payment Amount($) entered for due type Original for Period 3/2007 should be greater than 0 for selected Record.");
		radLocators.Enter_contribtion.sendKeys("2222222");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("RAD24601001", "Pass", "Error on Continue in TWR-205 page, Payment Amount($) entered for due type Original for Period 3/2007 cannot be greater than Due Amount($) for selected Record.");
		radLocators.Enter_contribtion.sendKeys("11887");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		
		
	}

}

