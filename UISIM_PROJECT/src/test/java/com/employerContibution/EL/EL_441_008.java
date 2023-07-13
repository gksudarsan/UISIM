package com.employerContibution.EL;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_008;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_008 extends TestBase {

	@Test(priority = 1, description = "EL.441.008: Verify CSR can register PEO Group  for Type of Legal Entity 'Sole Proprietorship' and Type of Ownership 'Privately or Closely Held'.", groups = {
			"Regression" })
	public void EL_441_008() throws Exception

	{

		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_008 sreg008 = new SREG_008(driver);
		// commonFuntions.database_UpdateQuery("SELECT * FROM
		// LROUIM.T_WFA_WORK_ITEM_DETAIL WHERE PROCESS_DETAIL_ID ='98742'");
		// commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET
		// USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT
		// PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901228')");
		test = report.createTest(
				"EL.441.008: Verify CSR can register PEO Group  for Type of Legal Entity 'Sole Proprietorship' and Type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_6.toUpperCase(), COMMON_CONSTANT.CSR_USER_6_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		//
		Thread.sleep(2000);
		driver.navigate().refresh();
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.enterTextboxContains("First Name", "Dev Test");
		commonFuntions.enterTextboxContains("Last Name", "nys");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test2@gmail.com");
		//
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.groupRegPeo.click();
		commonFuntions.enterTextbox("Name of Professional Employer Organization",
				"Test_auto" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently",
				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		Thread.sleep(2000);
		long number = commonFuntions.createRandomInteger(10000, 99999);
		String ernValue = "78" + Long.toString(number);
		String feinValue = Long.toString(commonFuntions.createRandomInteger(100000000, 999999999));
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Sole Proprietorship (Individual)");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Date", "04/01/2022");
		commonFuntions.screenShot("groupRegistrationPeo", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();

		// Step7
		commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
		try {
		sreg008.firstradiobuttonVerifyAddPopup.click();
		Thread.sleep(2000);
		}
		catch (Exception e) {
			Thread.sleep(2000);
		}
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Address Information", "PASS", "PEO-003 page displayed");

		// Step 8 PEO-003
		PEOPage.addressLine1.sendKeys("addressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		PEOPage.addressLine2.sendKeys("addressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.selectDropdown("State", " Alaska ");
		commonFuntions.enterTextboxContains("City ", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12010");

		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
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
		Thread.sleep(2000);
		
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

		
		commonFuntions.screenShot("Prior Address(es) in New York", "PASS", "PEO-006 Screen is diaplyed");

		// Step 11
		commonFuntions.enterTextboxContains("Address Line 1",
				"PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 2",
				"PrioraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.selectDropdown("State", " Alabama ");
		commonFuntions.enterTextboxContains("City", "albany");
		commonFuntions.enterTextboxContains("Zip Code", "12012");
		commonFuntions.enterTextboxContains("other name(s)", "automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
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
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "40");
		commonFuntions.enterTextboxContains("Address Line 1",
				"owneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 2",
				"owneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13430");
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
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_Pentity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "60");
		commonFuntions.enterTextboxContains("Address Line 1",
				"PriorOwneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 2",
				"PriorOwneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13430");
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
				"Submission Instructions and Responsibilities");
		
		// Step 18
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		// Step 19&20&21
		commonFuntions.selectCheckbox("Proof of NYS Workers' Compensation Coverage");
		commonFuntions.selectLink("Proof of NYS Workers' Compensation Coverage", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		// commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
		// commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage",
		// "Browse");
		// commonFuntions.uploadDoc("TESTINGEL.docx\"");
		// Thread.sleep(3000);
		
		//commonFuntions.clickButtonContains("Upload");
		//Thread.sleep(2000);
		//(//button[contains(.,'Upload')][1])[1]
		
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

		//26&27
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton(" Search ");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sreg008.firstradiobuttonVerifyAddPopup.click();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("PEO Member Information", "PASS", "PEOG-002 screen is displayed");
		
		sreg008.firstExtField.sendKeys("091");
		commonFuntions.enterTextboxContains("Address Line 1",
				"PriorOwneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		
		PEOPage.uspsAddress.click();
		PEOPage.mailingAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetail", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		commonFuntions.waitForLoadingIconToDisappear();
		
		//28
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Search For PEO Member", "PASS", "PEOG-001 screen is displayed");

	}
}