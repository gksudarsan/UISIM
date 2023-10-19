package com.employerContribution.FI;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

	public class FI_169_07_001_Verify_TPR_can_submit_Failure_to_File_Protest_CSR_to_review_and_close_task_with_no_action_taken
			extends TestBase {
		@Test
		public void FI_169_07_001() throws Exception {
			test = report.createTest(
					"FI.169.07.001- Verify TPR can submit a IA198.P Failure to File Protest form online, task 'Failure to File Penalty Pro̥test' will create for CSR to review and close task with no action taken");

			commonStepDefinitions cf = new commonStepDefinitions();
			SUC_002 suc002 = new SUC_002(driver);
			PEOPage peoPage = new PEOPage(driver);
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);

			// Query
			Map<String, String> databaseResults1 = cf.database_SelectQuery(
					"SELECT * FROM t_employer WHERE EMPLOYER_ID IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE THIRD_PARTY_CDS_VENDOR_ID = '299')");

			String eanValue = databaseResults1.get("EAN");
			System.out.println("The EAN Value is:" + eanValue);

			String nameValue = databaseResults1.get("EMPLOYER_NAME");
			System.out.println("The EAN Value is:" + nameValue);

			// -----Login
			cf.login(COMMON_CONSTANT.TPR_USER.toUpperCase(), COMMON_CONSTANT.TPR_USER_PASSWORD);
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

			// -----Menu
			cf.clickMenu("Menu");
			sleep(2000);
			cf.screenShot("MenuPage", "Pass", "Launched to Menu");
			cf.ScrollMenu("Secure Messaging");
			cf.clickMenu("Secure Messaging");
			sleep(1000);
			cf.clickMenu("Write Message - Enter ERN");
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("Write Message - Enter ERN", "Pass", "Launched to SM-100");
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.clickButtonContains("Continue ");
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("Write Message", "Pass", "Launched to SM-101");
			cf.selectDropdown("Category", " Protest ");
			sleep(2000);
			cf.selectDropdown("Subcategory", " How do I protest Failure to File Penalties? ");
			cf.clickOnLinkAnchorTag("Protest Document for Failure to File Penalties");
			cf.switchTab();
			// FIS-008
			cf.waitForLoadingIconToDisappear();
			cf.screenShot("Protest Document for Failure to File Penalties", "Pass", "Launched to FIS-008");
			cf.enterTextboxContains("Employer Name", "Sam Hunt");
			cf.enterTextboxContains("ERN", "6784567");
			/*
			 * cf.selectCheckboxSection1("Section I", 3);
			 * cf.enterTextboxContains("and/or NYS-45 ATT was filed:", "");
			 * cf.selectCheckboxSection1("Section I", 2); sleep();
			 * cf.enterTextboxContains("Name", "Shanice"); cf.enterTextboxContains("ERN",
			 * "2345234"); cf.enterTextboxContains(" FEIN ", "234253453");
			 * cf.selectCheckboxSection1("Section I", 1);
			 * cf.selectCheckboxSection2("The business ceased paying wages.", 1);
			 * cf.enterTextboxContains("Enter the last payroll date", "8/2/2023");
			 * cf.selectCheckboxSection2("The business has been sold or transferred.", 1);
			 * cf.enterTextboxContains("Name of New Owner", "Theode Kyle");
			 * cf.enterTextboxContains("Address Line 1 of New Owner",
			 * "Clark Residence hall");
			 * cf.enterTextboxContains("Address Line 2 of New Owner", "Street Brooklyn");
			 * cf.enterTextboxContains("City", "New York"); cf.selectDropdown("State",
			 * "Alabama"); cf.enterTextboxContains("Zip Code", "12421"); sleep(2000); cf.
			 * enterTextboxContains("I do not believe the business is required to file the return(s) because:"
			 * , "Test Content"); cf.
			 * enterTextboxContains("Other reason you believe this penalty should not be assessed:"
			 * , "Test Content"); empRegPage.browserLink.click(); Thread.sleep(3000);
			 * cf.uploadDoc("Sample.docx"); sleep(3000);
			 * cf.enterTextboxContains("Print Name", "Sam");
			 * cf.enterTextboxContains("Title", "Miss");
			 * cf.enterTextboxContains(" Daytime Phone", "(768)-128-9768");
			 * cf.enterTextboxContains("Date", "8/2/2023");
			 */
			cf.clickButtonContains("Submit ");

			// Required
			cf.errorLabel(" Required");

			cf.selectCheckbox("I Accept");
			cf.clickButtonContains("Submit ");

			// ERN Mismatched
			cf.errorContent("Invalid Employer Registration Number. No ERN matched.");

			// Valid EAN
			cf.enterTextboxContains("ERN", eanValue);
			/*
			 * cf.errorContent("An invalid Federal Identification Number (FEIN).");
			 * cf.clickButtonContains("Submit ");
			 * 
			 * // FEIN cf.enterTextboxContains(" FEIN ", "214848198");
			 */
			cf.clickButtonContains("Submit ");

			cf.screenShot("Issue Submission Confirmation", "Pass", "Launched to SUC-002");
			cf.Label("An Issue has been successfully created and will be assigned to the Internal Staff.");

			/*
			 * cf.
			 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
			 * COMMON_CONSTANT.CSR_USER_1 +
			 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
			 * + eanValue + "' ORDER BY UPDATED_TS desc)"); Thread.sleep(4000);
			 */

			cf.logoutAndLogin(COMMON_CONSTANT.CSR_LnD_PenaltyClerical.toUpperCase(), COMMON_CONSTANT.CSR_LnD_PenaltyClerical_PASSWORD);
			peoPage.queue.click();
			cf.waitForLoadingIconToDisappear();
			sleep(1000);
			cf.forceClearTextWithElement("Employer Registration Number");
			sleep(1000);
			cf.enterTextboxContains("Employer Registration Number", eanValue);
			cf.screenShot("EAN Search", "Pass", "EAN Search");
			cf.clickButtonContains("Search");

			cf.clickOnLinkfirstItem("Failure To File Penalty Protest");
			cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
			Thread.sleep(2000);
			cf.clickButtonContains("Open Work Item ");
			Thread.sleep(2000);
			cf.screenShot("Failure to File Penalty Protest", "Pass", "PFP-002 screen is visible");

			cf.selectRadioQuestions("Close Task with No Action Taken", "No ");
			suc002.enterProtestCommentField.sendKeys("Testing");
			cf.clickButtonContains("Submit ");
			cf.waitForLoadingIconToDisappear();
			sleep(1000);
			cf.screenShot("Task Confirmation", "Pass", "Message from Webpage popup is visible");

			cf.clickButtonContains(" Yes ");
			cf.waitForLoadingIconToDisappear();
			sleep(1000);
			cf.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
			suc002.pOASucessText.isDisplayed();
			cf.clickButtonContains("Home");
			cf.screenShot("Home", "Pass", "Home Page");
			System.out.println("Worked");
			
		}
}
