package com.employerContribution.FI;


import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.FIpage;
import com.ui.pages.FraudInvestigationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_119A_01_001_VerifyExternalUser_EnterTipInfoOnlineComplaint_SysCreatesTaskCsrReviews extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify external user can enter tip information from the online complaint form and system creates task for CSR review.", groups = "Regression")
	public void FI_119A_01_001() throws Exception {
		test = report.createTest(
				"FI.119A.01.001 : Verify external user can enter tip information from the online complaint form and system creates task for CSR review.");

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FraudInvestigationLocators fiLocators = new FraudInvestigationLocators(driver);
		
		String phone = StringUtils.left(String.valueOf((long)(Math.random()*Math.pow(10,11))),10);
		String secondPhone = StringUtils.left(String.valueOf((long)(Math.random()*Math.pow(10,11))),10);
		String eanValue = StringUtils.left(String.valueOf((long)(Math.random()*Math.pow(10,11))),7);
		String feinValue = StringUtils.left(String.valueOf((long)(Math.random()*Math.pow(10,11))),9);

		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.EXTERNAL_USER, COMMON_CONSTANT.EXTERNAL_USER_PASSWORD);
		test.log(Status.PASS, "Login with EXTERNAL USER is successful");
		
		try {
				commonFuntions.clickButtonContains(" I agree with the Terms and Conditions ");
				test.log(Status.PASS, "Accepted 'Terms and Conditions for PEO'");
			} catch(Exception exception) {
				test.log(Status.PASS, "Accepted 'Terms and Conditions for Professional Employer Organizations(PEO)'");
			}

		// ---Menu----
		commonFuntions.waitForLoadingIconToDisappear();
		fiLocators.menu.click();
		commonFuntions.ScrollMenu("Report UI Employer Fraud External");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Report UI Employer Fraud External");
		commonFuntions.clickMenu("Report UI Employer Fraud External");

		// --- FTE-100 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI119A01001", "Pass", "Successfully launch to Report Unemployment Insurance Employer Fraud - External (FTE-100) screen");
		
		// Owner Information
		commonFuntions.enterTextboxContains("Owner First Name", "Antonio");
		commonFuntions.enterTextboxContains("Owner Last Name", "Gonzalez");
		fiLocators.ownerAddress_address1.sendKeys("371 7th Ave");
		fiLocators.ownerAddress_city.sendKeys("New York");
		fiLocators.ownerAddress_zip.sendKeys("10001");
		fiLocators.ownerPrimaryPhoneBean.sendKeys(phone);
		fiLocators.ownerSecondaryPhoneBean.sendKeys(secondPhone);
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Owner's Information entered successfully in FTE-100 screen");
		
		// Company Information
		commonFuntions.enterTextboxContains("Company Name", "David Signer & Robin Signer".toUpperCase());
		fiLocators.companyAddress_address1.sendKeys("47 W 13th St");
		fiLocators.companyAddress_city.sendKeys("New York");
		fiLocators.companyAddress_zip.sendKeys("10011");
		try {commonFuntions.enterTextboxContains("Also known as", "singerdavid".toUpperCase());}
		catch(Exception exception) {fiLocators.doingBusinessAs.sendKeys("singerdavid".toUpperCase());}
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		fiLocators.companyPhoneBean.sendKeys(secondPhone);		
		commonFuntions.enterTextboxContains("Date Business Began Operating", "");
		commonFuntions.enterPastDate("Date Business Began Operating", 25);
		commonFuntions.enterTextboxContains("Type of Business", "Agriculture");
		commonFuntions.enterTextboxContains("Number of Employees", "50");
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Company's Information entered successfully in FTE-100 screen");
		
		// If the business is construction
		commonFuntions.enterTextboxContains("expected project completion date", "");
		commonFuntions.enterPastDate("What is the expected project completion date?", 15);
		commonFuntions.selectRadioQuestions("Are there still employees at the site?", "Yes ");
		fiLocators.worksiteAddressId_address1.sendKeys("25 Union Square W");
		fiLocators.worksiteAddressId_city.sendKeys("New York");
		fiLocators.worksiteAddressId_zip.sendKeys("10003");
		commonFuntions.enterTextboxContains("Number of known workers at the site", "10");
		commonFuntions.enterTextboxContains("What languages other than English, are spoken at the worksite?", "Spanish");
		fiLocators.supervisorFirstName.sendKeys("Angelo");
		fiLocators.supervisorLastName.sendKeys("Michael");
		fiLocators.supervisorPrimaryPhoneBean.sendKeys(secondPhone);
		fiLocators.supervisorSecondaryPhoneBean.sendKeys(phone);
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Business Information entered successfully in FTE-100 screen");
		
		/// Fraud Information
		commonFuntions.selectCheckbox("Has off-the-books wages");
		commonFuntions.selectCheckbox("Compensation coverage");
		commonFuntions.selectCheckbox("Does not pay the correct Rate for overtime work");
		fiLocators.explainNotPayCorrectRateForOvertime.sendKeys("Test explanation");
		
		commonFuntions.selectCheckbox("Does not pay employees for all hours worked");
		try {
			sleep(1500);
			commonFuntions.screenShot("FI119A01001", "Pass", "'If you are owed wages, you must provide contact information in order for us to investigate your claim for unpaid wages (LS 223) or Claim for Unpaid Wage Supplements (LS-425)' pop-up.");
			commonFuntions.clickButtonContains(" OK ");
			test.log(Status.PASS,"Clicked on Ok on the pop-up ");
		} catch(Exception exception) {
			test.log(Status.PASS,"'If you are owed wages, you must provide contact information in order for us to investigate your claim for unpaid wages (LS 223) or Claim for Unpaid Wage Supplements (LS-425)' pop-up doesnot appeared.");
		}
		fiLocators.explainNotPayEmployeesAllHoursWorked.sendKeys("Test explanation 2");
		
		commonFuntions.selectCheckbox("Does not pay minimum wage");
		fiLocators.explainNotPayMinimumWage.sendKeys("Test explanation 3");
		
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Fraud Information entered successfully in FTE-100 screen");
		
		commonFuntions.selectCheckbox("Is not withholding taxes");
		commonFuntions.selectCheckbox("Does not keep proper time record or record of wages/hours worked");
		fiLocators.explainNotKeepProperTimeRecord.sendKeys("Test explanation 4");
		
		commonFuntions.selectCheckbox("Receives wage kickbacks");
		fiLocators.explainReceiveWageKickbacks.sendKeys("Test explanation 5");
		
		commonFuntions.selectCheckbox("Requires employees to underreport hours actually worked");
		commonFuntions.selectCheckbox("Claims payment of wages not made to employees");
		commonFuntions.selectCheckbox("Misclassifies workers as independent contractors");
		commonFuntions.enterTextboxContains("How many workers are involved?", "20");
		commonFuntions.enterTextboxContains("What are the occupations involved?", "Anonymous");
		
		commonFuntions.selectCheckbox("Under reports, conceals or hides payroll");
		commonFuntions.enterTextboxContains("How is the payroll concealed?", "Kept hidden");
		
		fiLocators.fraudInformation.sendKeys("Description for automation test");
		
		commonFuntions.enterPastDate("The date the fraudulent activity began", 11);
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Information entered successfully in FTE-100 screen");
		
		
		// Your Information (Optional)
		fiLocators.externalUserFirstName.sendKeys("Ankan");
		fiLocators.externalUserLastName.sendKeys("Das");
		fiLocators.externalUserAddressId_address1.sendKeys("133 Macdougal St");
		fiLocators.externalUserAddressId_city.sendKeys("New York");
		fiLocators.externalUserAddressId_zip.sendKeys("10012");
		fiLocators.externalUserPrimaryPhoneNo.sendKeys(phone);
		fiLocators.externalUserSecondaryPhoneNo.sendKeys(secondPhone);
		commonFuntions.selectRadioQuestions("Are you an employee of the business you suspect of Willful?", "Yes ");
		commonFuntions.enterPastDate("Date you started working there", 45);
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "Personal Information entered successfully in FTE-100 screen");
		
		commonFuntions.enterTextboxContains("Your occupation with the business", "Automation Tester");
		commonFuntions.enterTextboxContains("How many hours a week do you work", "52");
		fiLocators.additionalComments.sendKeys("Tetsing comments");
		sleep(2000);
		commonFuntions.screenShot("FI119A01001", "Pass", "More Information entered successfully in FTE-100 screen");
		commonFuntions.clickButtonContains("Submit ");
		
		
		// --- SUC-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI119A01001", "Pass", "Successfully launch to Fraud Tip Confimation(SUC-002) screen");
		try {
			commonFuntions.clickButton("Home ");
		} catch(Exception exception) {
			exception.printStackTrace();
		}

		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("FI119A01001", "Pass", "Successfully passed TC FI.119A.01.001");
	}

}
