package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_462_02 extends TestBase {

	@Test(priority = 1, description = "EL.462.02:Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Exempt'", groups = {
			"Regression" })
	public void EL_462_02() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report
				.createTest("EL.462.02:Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Exempt' ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		PEOPage.menuPeo.click();
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Manage PEO");
		commonFuntions.clickMenu("Manage PEO");
		sleep();

		/*-------------------Search for PEO: MPEO-011 -----------------*/

		commonFuntions.screenShot("SearchforPEO", "Pass", "Search for PEO");
		PEOPage.advancedSearch.click();
		sleep();
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.errorContent("At least one field must be filled for search");
		commonFuntions.screenShot("ErrorMessage", "Pass",
				"Search for PEO: At least one field must be filled for search");
		sleep(2000);
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 5);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.errorContent("Invalid Federal Employer Identification Number (FEIN)");
		commonFuntions.screenShot("ErrorMessage1", "Pass", "SearchPEO:Invalid FEIN Number");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearFeinFieldSection);
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.errorContent("Invalid Employer Registration Number");
		commonFuntions.screenShot("ErrorMessage2", "Pass", "SearchPEO:Invalid ERN Number");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'",
				"PEO_NAME");
		String PeoName = databaseResults.get("PEO_NAME");
		System.out.println("Peo Name is:" + PeoName);
		commonFuntions.enterTextboxContains("PEO Name", PeoName);
		commonFuntions.screenShot("EnterPeoName", "Pass", "Entered peo name to fetch the record");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("selectRadioButton", "Pass", "Select the peo name record..");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		/*-----Manage Group PEO(MPEO-003)------*/

		commonFuntions.selectDropdown("PEO Conversion", " PEO Group to PEO Exempt ");
		sleep();
		commonFuntions.screenShot("ManageGroupPEO", "Pass", "Manage Group PEO");
		commonFuntions.clickButtonContains("  CONVERT ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"No");
		commonFuntions.enterPastDate("Fiscal Year Start Date", 365);
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alabama ");
		commonFuntions.enterTextboxContains("State agency that issued it.", "test");
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		String ernNumber = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 10);
		commonFuntions.enterTextboxContains("Registration Number ", ernNumber);
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information for PEO Exempt Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.screenShot("UnemploymentInsuranceAccountDetails", "Pass",
				"Unemployment Insurance Account Details");
		try {
			commonFuntions.selectRadio("Select");
		} catch (Exception e) {
			System.out.println("If record found then select the record and proceed");
		}
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1 ",
				"Cooper square" + commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "45679");
		commonFuntions.enterTextboxContains(" Phone Number ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		commonFuntions.screenShot("AddressInformation", "Pass", "Address Information");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.screenShot("UspsAddress1", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
			System.out.println("AddressPopUpDisplayed");
		}
		commonFuntions.screenShot("MailingAddress", "Pass", "Mailing Address");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("Application for Exemption Submission Instructions", "Pass",
				"Submission Instructions");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		/*------Upload Documents (SREG-006)-------*/

		commonFuntions.selectCheckbox("Authorization to do business in NYS");
		commonFuntions.selectLink("Authorization to do business in NYS", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(2000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
			sleep(2000);
		}catch(Exception e) {
			System.out.println("Duplicate file pop up");
		}
		commonFuntions.clickButtonContains("Upload");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UploadDocumets", "Pass", "Upload Documents:SREG-006");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(4000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		sleep(3000);
		commonFuntions.clickButtonContains("Choose File");
		sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(4000);
		commonFuntions.screenShot("UploadClientList", "Pass", "Upload Client List:PEO-015");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("verifyClient", "Pass", "Verify Client List:LEAS-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		String PeoID = AddPage.getPEOId.getText().trim();
		System.out.println("PEO ID::" + PeoID);
		test.log(Status.INFO, "ERN::" + PeoID);
		commonFuntions.screenShot("PEODetailsReviewscreen", "Pass", "PEO Details Review Screen:PEOR-001");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
				"TestAutomation" + commonFuntions.createRandomInteger(10000, 99999));
		commonFuntions.screenShot("Declaration", "Pass", "Declaration for PEO Exempt:PEOE-005");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement Of Acknowledgement");
		commonFuntions.clickButtonContains("Accept & Submit ");
		sleep(3000);
		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("PEORegistrationApproval", "Pass", "PEO Registration Approval:RPEO-003");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("ConversionConfirmation", "Pass", "Conversion Confirmation;SUC-002");
		commonFuntions.clickButton("Home");
		sleep(2000);

		// PEO Inquiry Information
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		PEOPage.menuInquiryPeo.click();
		sleep();
		commonFuntions.clickMenu("Inquiry PEO Information");
		commonFuntions.screenShot("InquiryPEOInformation", "Pass", "Navigate to Inquiry PEO Information");
		sleep();
		PEOPage.advancedSearch.click();
		sleep();
		commonFuntions.enterTextboxContains("PEO Name", PeoName);
		sleep();
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.selectRadio("Select");
		sleep();
		commonFuntions.screenShot("SearchingWithPeoName", "Pass", "Search with the PEO name record");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("PEOExemptInquiry", "Pass", "PEO Exempt Inquiry:IPEO-002");
		sleep();
		commonFuntions.clickButtonContains("Close ");
		sleep();

		//  Main Menu > Inquiry >View Correspondence

		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.ScrollMenu("View Correspondence");
		sleep();
		commonFuntions.screenShot("Contribution Inquiry", "Pass", "View Correspondence");
		commonFuntions.clickMenu("View Correspondence");
		sleep();
		commonFuntions.screenShot("SearchDocument", "Pass", "Search Document:DMS-001");
		commonFuntions.selectDropdown("Identifier", " PEO ID ");
		sleep();
		commonFuntions.enterTextboxContains("Identifier Text", PeoID);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("DocumentFound", "Pass", "Document Fount after entering PEO ID:DMS-001");
	}

}
