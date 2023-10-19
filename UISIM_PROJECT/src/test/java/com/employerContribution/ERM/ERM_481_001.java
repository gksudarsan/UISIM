package com.employerContribution.ERM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_481_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_481_001() throws Exception {

		test = report.createTest(
				"Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision'");

		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		Map<String, String> databaseRateYear = cf.database_SelectQuerySingleColumn(
				"SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '"
						+ eanValue + "')",
				"RATE_YEAR");
		String rateYear = databaseRateYear.get("RATE_YEAR");
		System.out.println("The Rate Year is " + rateYear);

		// --- Login ---
		cf.login(COMMON_CONSTANT.CSR_Rating_Specialist.toUpperCase(),COMMON_CONSTANT.CSR_Rating_Specialist_PASSWORD);
		sleep(2000);
		cf.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		cf.clickMenu("Menu");
		sleep();
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.clickMenu("Maintain Rate");
		sleep();
		cf.screenShot("MenuPage", "Pass","Navigate to Menu ->Account Maintenance ->Maintain Rate ->Update Contribution Rate");
		cf.clickMenu("Update Contribution Rate");
		sleep(2000);
		
		//Invalid ERN
		cf.enterTextboxContains("Employer Registration Number", "1111111");
		cf.enterTextboxContains("Rate Year", "2023");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		cf.screenShot("ERN value - 1111111, Invalid ERN", "Pass", "ERN does not exist");
		sleep(2000);
		
		//Does not exist ERN
		cf.clearTextboxContains("Employer Registration Number");
		cf.enterTextboxContains("Employer Registration Number", "8786852");
		cf.enterTextboxContains("Rate Year", rateYear);
		cf.clickButtonContains("Continue");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		cf.screenShot("Invalid ERN", "Pass", "ERN does not exist");
		sleep(2000);
		
		//Invalid Rate year
		cf.clearTextboxContains("Employer Registration Number");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.enterTextboxContains("Rate Year", "1001");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("Invalid Rate Year. Enter another rate year to proceed.");
		cf.screenShot("Invalid Rate year", "Pass", "Invalid Rate Year.");
		sleep(2000);
		
		//Not completed for Annual Rate Calculation
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.enterTextboxContains("Rate Year", "1940");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("The annual rate calculation has not been completed for thr Rate Year entered.");
		cf.screenShot("Not completed for Annual Rate Calculation", "Pass", "The annual rate calculation has not been completed for thr Rate Year entered.");
		sleep(2000);
		
		//Valid ERN and Rate Year
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.enterTextboxContains("Rate Year", rateYear);
		cf.clickButtonContains("Continue");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Employer Rate Maintenance - Update Contribution Rate", "Pass", "Launched to SREG-060");
		
		//Required - Override Reasons
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.errorLabel(" Required ");
		cf.screenShot("Required Reason", "Pass", "Error Toaster");
		sleep(1000);
		
		//Reason required
		cf.selectDropdown("Override Reasons", " Other ");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Update Contribution Page", "Pass","Reason is required if Remark is 'Other' Nothing has been entered for update on SREG-060");
		
		//Nothing updated
		sleep(1000);
		cf.selectDropdown("Override Reasons", " Administrative Decision ");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("Nothing has been entered for update.");
		cf.screenShot("Employer Rate Maintenance - Update Contribution Rate", "Pass", "Nothing has been entered for update on SREG-060");
		sleep(2000);
		
		//Step 12,13 & 14 - Not applicable as not able to enter negative value
		
		//Step-15 Exceed amount - Charges
		/*
		 * cf.enterTextboxContains("Charges ($)", "999,999,99999999.99"); //Auto-fill
		 * Account Balance cf.clickButtonContains("Continue "); cf.
		 * errorContent("The response to Adjust Taxable Wages, row Current Year Wages causes Total Taxable Wages to exceed the maximum allotted value (9999999999.99)."
		 * ); cf.waitForLoadingIconToDisappear();
		 * cf.screenShot("Exceed maximum alloted value - Charges", "Pass",
		 * "Error Toaster Warning at SREG-060");
		 */
		// Exceed amount - Current Wage
		cf.clearTextboxContains("Charges ($)");
		cf.enterTextboxContains("Current Year Wages", "999,999,999.99");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("The response to Adjust Taxable Wages, row Current Year Wages causes Total Taxable Wages to exceed the maximum allotted value (9999999999.99).");
		cf.screenShot("Exceed maximum alloted value - Current Wage", "Pass", "Error Toaster Warning at SREG-060");
		
		// Exceed amount - Contribution
		cf.clearTextboxContains("Current Year Wages");
		cf.enterTextboxContains("Contributions ($)", "999,999,999.99");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.errorContent("The response to Adjust Taxable Wages, row Current Year Wages causes Total Taxable Wages to exceed the maximum allotted value (9999999999.99).");
		cf.screenShot("Exceed maximum alloted value - Contributions", "Pass", "Error Toaster Warning at SREG-060");
		
		//Step 18 & 19 - Not applicable
		
		//Step - 20
		//Update Values
		cf.selectDropdown("Lapsed Balance Revision", " 2022 ");
		cf.enterTextboxContains("Opening Balance ($)", "925300");
		cf.enterTextboxContains("Contributions ($)", "9,088.88");
		cf.enterTextboxContains("Charges ($)", "9,998.99");
		cf.enterTextboxContains("Current Year Wages", "4,560.00");
		cf.enterTextboxContains("Account Balance ($)", "8,342.89");
		cf.enterTextboxContains("1st Prior Year Wages", "3,456.99");
		cf.enterTextboxContains("2nd Prior Year Wages", "3,487.00");
		cf.enterTextboxContains("3rd Prior Year Wages", "7,600.00");
		cf.enterTextboxContains("4th Prior Year Wages", "6,789.00");
		sleep(2000);
		cf.selectDropdown("Override Reasons", " Administrative Decision ");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		
		//Step-21
		cf.screenShot("Employer Rate Maintenance Verification", "Pass", "Launched to SREG-061");
		cf.selectRadioQuestions("Generate Rate Statement","Yes ");
		sleep(2000);
		cf.screenShot("Details Filled on SREG-061", "Pass", "Details updated");
		cf.clickButtonContains("Submit ");
		sleep(2000);
		
		//SUC-002
		cf.Label("The Employer Rate Information has been successfully modified and saved ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("SUC-002", "Pass","The Employer Rate Information has been successfully modified and saved.");
		sleep(2000);
		cf.clickButtonContains("Home ");
		cf.screenShot("Home Page", "Pass", "Launched to Home Page");
		cf.waitForLoadingIconToDisappear();
		
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_General_Inquiry.toUpperCase(), COMMON_CONSTANT.CSR_General_Inquiry_PASSWORD);
		cf.screenShot("Application Login", "Pass", "Launched to General Inquiry Account");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Inquiry");
		cf.clickMenu("Inquiry");
		cf.clickMenu("Contribution Inquiry");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Navigation", "Pass", "Inquiry Employer Account Path");
		cf.clickMenu("Inquiry Employer Account");
		cf.waitForLoadingIconToDisappear();
		sleep(2000);
		cf.screenShot("SREG-050", "Pass", "Inquiry Employer Account - Enter ERN");

		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		cf.screenShot("SREG-050", "Pass", "Inquiry Employer Account Page Details Entered");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Inquiry Employer Account Information", "Pass", "Launched to SREG-051");
		
		cf.selectTableWithoutId("Rate History", 8, 1, "Rate History");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Employer Account Rate Transaction History ", "Pass", "Launched to SREG-052");
		
		cf.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Ledger after Transaction", "Pass", "Launched to SREG-063");
		
		cf.clickOnLinkAnchorTag("Transaction Ingredients");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Transaction Ingredients", "Pass", "Launched to ERM-014");
		
		cf.selectTableWithoutId("Transaction Ingredients", 3, 2, "Transaction Ingredients");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Estimated Wages", "Pass", "Launched to ERM-030");
		cf.clickButtonContains("Previous ");
		
		cf.waitForLoadingIconToDisappear();
		cf.clickButtonContains(" Previous ");
		
		// Back to SREG-051
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Inquiry Employer Account Information", "Pass", "Launched to SREG-051");
		cf.selectTableWithoutId("2023", 1, 1, "Rate History");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Current Rating Account Status History", "Pass", "Launched to ERM-013");
		cf.clickButtonContains("Home ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Home", "Pass", "Launched to Home Page");
		System.out.println("ERM.481.001 testcase has been completed");
		
		//Step 32 and 33 - NA
		

	}
}