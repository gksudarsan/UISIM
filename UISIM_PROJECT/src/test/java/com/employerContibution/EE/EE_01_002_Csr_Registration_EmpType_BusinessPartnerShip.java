package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_01_002_Csr_Registration_EmpType_BusinessPartnerShip extends TestBase {

	@Test()
	public void EE_01_002_csr_registration() throws Exception {
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagementLocators eml = new employerManagementLocators();
		employerManagement em = new employerManagement();
		test = report.createTest(
				"EE.01.002 -  Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Partnership' and work items will be created for CSR to review.");

		commonFunction.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Register Employer");
		commonFunction.clickMenu("Register Employer");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-001 ---
		commonFunction.screenShot("Employer Registration", "Pass", "Employer Registration (SREG-001)screen launched");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-025 ---//
		commonFunction.screenShot("General Information", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "279873084");
		commonFunction.selectDropdown("Type of Legal Entity", " Partnership ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0463950");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("General Information", "Pass", "Message enter Required field  on SREG-025 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-003 ---
		commonFunction.screenShot("Employer Entity Information", "Pass",
				"Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("ST BERNARDINE OF SIENA COLLEGE");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Tester");
		commonFunction.screenShot("Employer Entity Information", "Pass",
				"Details entered and clicked on CONTINUE button");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// ---SREG-008---
		commonFunction.screenShot("Additional Business Physical Location(s)", "Pass",
				"Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "Test 1");
		commonFunction.enterTextboxContains("City ", "Albany");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("Additional Business Physical Location(s)", "Pass",
				"Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();
		/*
		 * try { try { empRegPage.uspsBusinessAddress.click();
		 * 
		 * } catch (Exception exception) {
		 * 
		 * empRegPage.uspsBusinessAddressInnerCircle.click();
		 * 
		 * } } catch (Exception ae) { }
		 * 
		 * commonFunction.screenShot("USPS Business address selection", "Pass",
		 * "USPS Business address selection on SREG-008");
		 * empRegPage.continueButton_popUp.click();
		 * commonFunction.waitForLoadingIconToDisappear();
		 */

		// --- SREG-007 ---
		commonFunction.screenShot("Business Physical Address Details", "Pass",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag(" Add Another Business Location ");
		commonFunction.waitForLoadingIconToDisappear();

		// ---SREG-008---
		commonFunction.screenShot("Additional Business Physical Location(s)", "Pass",
				"Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "Test 2");
		commonFunction.enterTextboxContains("City ", "Albany");
		commonFunction.enterTextboxContains("Zip Code", "10018");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("Additional Business Physical Location(s)", "Pass",
				"Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-007 ---
		commonFunction.screenShot("Business Physical Address Details", "Pass",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-004 ---
		commonFunction.screenShot("Employer Contact Details", "Pass",
				"Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Primary Business Physical Address");
		commonFunction.screenShot("Employer Contact Details", "Pass", "Successfully entered details in SREG-004 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-521 ---
		commonFunction.screenShot("Employer Verify Contact Details", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-011 ---
		commonFunction.screenShot("Business Acquisition", "Warning", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0463971");
		commonFunction.enterTextboxContains("Address Line 1 ", "Test");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFunction.enterTextboxContains("Acquisition Date", "4/1/2023");
		commonFunction.enterTextboxContains("Notification date of Transfer", "4/21/2023");
		commonFunction.screenShot("Business Acquisition", "Pass", "Successfully entered details in SREG-011 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-012 ---
		commonFunction.screenShot("Business Acquisition Details", "Warning", "Launched to  SREG-012 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-713 ---
		commonFunction.screenShot("Change in Legal Entity", "Warning", "Launched to  SREG-713 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-006 ---
		commonFunction.screenShot("Add Partnership Details", "Warning", "Launched to  SREG-006 page");
		commonFunction.selectRadioQuestions("Type of Partner/Owner", "Individual");
		commonFunction.enterTextboxContains("SSN", "099820363");
		commonFunction.enterTextboxContains("First Name", "FN");
		commonFunction.enterTextboxContains("Last Name", "LN");
		commonFunction.enterTextboxContains("Address Line 1 ", "1st");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "12012");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("Add Partnership Details", "Pass", "Successfully entered details in SREG-006 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-005 ---
		commonFunction.screenShot("Partner Details", "Pass", "Launched to  SREG-005 page");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-683 ---
		commonFunction.screenShot("Upload Documents", "Pass", "USPS Business address selection on SREG-683");
		commonFunction.selectLink(
				" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.",
				"Browse");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.uploadDoc("Sample.docx");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Upload Documents", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-800 ---
		commonFunction.screenShot("Review Registration Details", "Pass", "USPS Business address selection on SREG-800");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-043 ---
		commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.selectCheckbox("I accept");
		commonFunction.clickButton("Submit ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-800 ---
		commonFunction.screenShot("Employer Registration Confirmation", "Pass",
				"Successfully launched to SREG-800 page");
		commonFunction.clickButton("Home ");
		commonFunction.waitForLoadingIconToDisappear();

		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");

		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("Work Item Description Free Text", "Dol");
		commonFunction.clickButton(" Search ");
		commonFunction.clickHyperlink("DOL DTF Discrepancy");

		// --- WF-091 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
		commonFunction.clickButton("Open Work Item ");

		// --- EEWI-005 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");
		commonFunction.selectDropdown("Account Status", " Liable ");
		commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
		empRegPage.commentBox_MyQ.sendKeys("for testing");
		commonFunction.clickButton("Submit ");

		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");

		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("FEIN", "363735912");
		commonFunction.clickButton(" Search ");
		commonFunction.clickOnLink("Review potential Duplicates");

		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButtonContains("Open Work Item ");

		// ---EEWI-012---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Message 'Required field'on WF-001 page");
		commonFunction.clearTextboxContains("Legal Name Of Business");
		commonFunction.enterTextboxContains("Legal Name Of Business", "AUTO TESTING LEGAL NAME 843303756");
		commonFunction.enterTextboxContains("First date of operation", "7/5/1940");
		commonFunction.selectDropdown("Quarter ", " 4 ");
		commonFunction.selectDropdown("Year ", " 1940 ");
		commonFunction.selectDropdown("Account Status", " Liable ");
		empRegPage.commentBox_MyQ.sendKeys("for testing");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Popup 'Verify Date'on WF-001 page");
		commonFunction.clickButton(" Yes ");

		// ---SUC-002---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
		commonFunction.clickButton("Home ");

		// --- Home ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to Home page");
		commonFunction.clickButton(" Go to My Q ");

		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.enterTextboxContains("FEIN", "363735912");
		commonFunction.clickButton(" Search ");
		commonFunction.clickOnLink("Review Employer Type");

		// --- WF-001 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButtonContains("Open Work Item ");

		// ---EEWI-012---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
		commonFunction.clickButton("Submit ");
		commonFunction.screenShot("EE01007", "Pass", "Message 'Required field'on WF-001 page");

		// Blocked at Step 70

	}

}
