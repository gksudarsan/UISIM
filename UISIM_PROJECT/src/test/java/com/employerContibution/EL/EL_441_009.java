package com.employerContibution.EL;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_084;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_009 extends TestBase {

	@Test(priority = 1, description = "EL.441.009 - Verify CSR can register PEO Group  for Type of Legal Entity 'Partnership' and Type of Ownership 'Privately or Closely Held'.", groups = {
			"Regression" })
	public void EL_441_009() throws Exception {
		test = report.createTest(
				"EL.441.009 - Verify CSR can register PEO Group  for Type of Legal Entity 'Partnership' and Type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_084 sreg084 = new SREG_084(driver);

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		//
		/*
		 * Thread.sleep(2000); driver.navigate().refresh();
		 * commonFuntions.waitForLoadingIconToDisappear();
		 * 
		 * commonFuntions.enterTextboxContains("First Name", "Dev Test");
		 * commonFuntions.enterTextboxContains("Last Name", "nys");
		 * commonFuntions.enterTextboxContains("Job Title", "Tester");
		 * commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com"); //
		 * 
		 */
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.groupRegPeo.click();
		String peoName = "Test_auto" + commonFuntions.createRandomInteger(1000, 9999);
		System.out.println(peoName);
		commonFuntions.enterTextbox("Name of Professional Employer Organization", peoName);
		commonFuntions.enterTextbox("Additional Name(s), if any, under which the PEO currently Conducts Business",
				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		Thread.sleep(2000);
		// long number = commonFuntions.createRandomInteger(10000, 99999);
		// String ernValue = "78" + Long.toString(number);
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println(ernValue);

		// String feinValue =
		// Long.toString(commonFuntions.createRandomInteger(100000000, 999999999));
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);

		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Partnership ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Date", "01/01/2023");
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// Step7
		commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			Thread.sleep(2000);
		}
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Address Information", "PASS", "EAS-001 page displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Address Information", "PASS", "PEO-003 page displayed");

		// Step 8 PEO-003
		PEOPage.attentionCareofFieldPEO003.sendKeys("Test");
		PEOPage.addressLine1.sendKeys("123state");
		// PEOPage.addressLine2.sendKeys("addressLine2" +
		// commonFuntions.createRandomInteger(1000, 9999));
		// commonFuntions.selectDropdown("State", " Alaska ");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");

		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));

		PEOPage.extFieldList.get(0).sendKeys("091");

		// commonFuntions.enterTextboxContains("Business Email Address",
		// "autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999))
		// + "@gmail.com");

		commonFuntions.enterTextboxContains("Business Email Address", "test2@gmail.com");

		//
		PEOPage.listtheCurrentAddressaddressLine1.sendKeys("123state");
		PEOPage.listtheCurrentCity.sendKeys("albany");
		PEOPage.listtheCurrentZipcode.sendKeys("12010");
		//

		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();

		// Step 9
		Thread.sleep(2000);
		commonFuntions.screenShot("Current Additional Address", "PASS",
				"PEO-005 Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);

		try {
			// Step 10
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("Mailing Address", "PASS", "PEO-004 Mailing Address");
			commonFuntions.clickButtonContains("Save & Continue");
			Thread.sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			Thread.sleep(2000);
		}

		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.UspsContinueButton.click();
			commonFuntions.waitForLoadingIconToDisappear();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Prior Address(es) in New York", "PASS", "PEO-006 Screen is diaplyed");

		// Step 11
		// commonFuntions.enterTextboxContains("Address Line 1",
		// "PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 1", "123state");
		// commonFuntions.enterTextboxContains("Address Line 2",
		// "PrioraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		// commonFuntions.selectDropdown("State", " Alabama ");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");
		// commonFuntions.enterTextboxContains("other name(s)", "automation");
		// commonFuntions.enterTextboxContains("Predecessor(s) Name",
		// "AutoPredecessor");
		// commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFuntions.screenShot("Prior Address", "Pass", "Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Prior Details", "PASS", "PEO-007 Verify Prior Address(es) in New York");

		// Step 12
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Ownership Information - privately or closely held company", "PASS",
				"PEO-008 Screen is displayed");

		// Step 13&14
		commonFuntions.enterTextboxContains("Entity or Person", "34");
		commonFuntions.enterTextboxContains("Ownership Percentage", "43");
		/*
		 * commonFuntions.enterTextboxContains("Address Line 1", "owneraddressLine1" +
		 * commonFuntions.createRandomInteger(1000, 9999));
		 * commonFuntions.enterTextboxContains("Address Line 2", "owneraddressLine2" +
		 * commonFuntions.createRandomInteger(1000, 9999));
		 */
		commonFuntions.enterTextboxContains("Address Line 1", "123state");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Ownership Information", "PASS", "PEO-009 Verify Ownership Information");

		// Step 15
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		// Step 16
		commonFuntions.enterTextboxContains("Entity or Person", "34");
		commonFuntions.enterTextboxContains("Ownership Percentage", "43");
		/*
		 * commonFuntions.enterTextboxContains("Address Line 1",
		 * "PriorOwneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		 * commonFuntions.enterTextboxContains("Address Line 2",
		 * "PriorOwneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		 */
		commonFuntions.enterTextboxContains("Address Line 1", "123state");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");
		commonFuntions.screenShot("PriorOwnershipInformation", "Pass",
				"PriorOwnership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		Thread.sleep(2000);
		PEOPage.uspsAddress.click();
		commonFuntions.screenShot("VerifyContactDetails", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Prior Ownership Information", "PASS", "Ownership Information");

		// Step 17
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submission Instructions and Responsibilities", "PASS",
				"PEO-014 Submission Instructions and Responsibilities");

		// Step 18
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		// Step 19&20&21
		/*
		 * commonFuntions.selectCheckbox("Proof of NYS Workers' Compensation Coverage");
		 * commonFuntions.selectLink("Proof of NYS Workers' Compensation Coverage",
		 * "Browse"); commonFuntions.uploadDoc("TESTINGEL.docx");
		 */
		Thread.sleep(4000);
		// commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
		// commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage",
		// "Browse");
		// commonFuntions.uploadDoc("TESTINGEL.docx\"");
		// Thread.sleep(3000);

		// commonFuntions.clickButtonContains("Upload");
		// Thread.sleep(2000);
		// (//button[contains(.,'Upload')][1])[1]

		commonFuntions.screenShot("Upload Documents", "PASS", "Upload Documents");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");

		// Step 22&23&24
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Client List", "PASS", "LEAS-012 client list");

		// Step 25
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search For PEO Member", "PASS", "PEOG-001 screen is displayed");

		// 26&27
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton(" Search ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sreg008.firstradiobuttonVerifyAddPopup.click();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Member Information", "PASS", "PEOG-002 screen is displayed");

		// sreg008.firstExtField.sendKeys("091");
		// commonFuntions.enterTextboxContains("Address Line 1",
		/// "PriorOwneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		PEOPage.uspsAddress.click();
		PEOPage.mailingAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Member Information", "PASS", "PEO-005 screen is displayed");
		// 28
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search For PEO Member", "PASS", "PEO-006 screen is displayed");

		//
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Prior Address(es) in New York", "PASS", "PEO-007 screen is displayed");

		//
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Upload Client List", "PASS", "PEO-015 screen is displayed");

		//
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");

		// Step 22&23&24
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Verify Client List", "PASS", "LEAS-012 client list");

		//
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("List of Members of PEO Group", "PASS", "PEOG-003 client list");

		//
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Details Review screen", "PASS", "PEOR-001 page is displayed");

		//
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Declaration", "PASS", "PEO-016 screen is displayed");

		//
		PEOPage.officerPartnerField.sendKeys("devanshu");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Group Guaranty and Statement of Acknowledgment", "PASS",
				"PEOG-006 screen is displayed");

		//
		commonFuntions.clickButtonContains("Accept & Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Register/Renew Confirmation", "PASS", "SUC-002 screen is displayed");

		//
		commonFuntions.clickButtonContains("Home");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "PASS", "Home screen is displayed");

		//
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.screenShot("Review PEO", "Pass", "Review PEO");
		commonFuntions.clickOnLink("Review PEO ");

		//
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 page is displayed");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Review PEO Registration", "Pass", "CPEO-001 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("General Information", "Pass", "PEO-002 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Unemployment Insurance Account Details", "Pass", "EAS-001 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Address Information", "Pass", "PEO-003 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Current Additional Address(es) in New York", "Pass",
				"PEO-005 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Mailing Address", "Pass", "PEO-004 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Prior Address(es) in New York", "Pass", "PEO-007 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Ownership Information", "Pass", "PEO-009 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Prior Ownership Information", "Pass", "PEO-010 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Prior Ownership Information", "Pass", "PEO-011 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Submission Instructions and Responsibilities", "Pass", "PEO-014 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Upload Documents", "Pass", "SREG-006 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Verify Client List", "Pass", "LEAS-012 page is displayed");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("PEO Details Review screen", "Pass", "PEOR-001 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Declaration", "Pass", "PEO-016 page is displayed");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Group Guaranty and Statement of Acknowledgment", "Pass",
				"PEOG-006 page is displayed");
		commonFuntions.clickButtonContains("Accept & Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("PEO Registration Approval", "Pass", "RPEO-003 page is displayed");

		PEOPage.rejectedToggeleButton.click();

		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		//
		commonFuntions.screenShot("Registration Confirmation", "Pass", "SUC-002 page is displayed");

		commonFuntions.clickButtonContains("Home");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Homepage", "PASS", "Home screen is displayed");

		// Step-55
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		commonFuntions.clickMenu("Professional Employer Organization (PEO)");
		sleep(2000);
		commonFuntions.ScrollMenu("Inquiry PEO Information");
		commonFuntions.clickMenu("Inquiry PEO Information");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		sleep(2000);

		//
		commonFuntions.enterTextboxContains("PEO Name", peoName);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		Thread.sleep(5000);
		sreg084.selectradioBtn1.click();
		commonFuntions.screenShot("Search for PEO", "Pass", "MPEO-001 screen is displayed");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Group Inquiry", "Pass", "IPEO-002 screen is displayed");
		

	}
}
