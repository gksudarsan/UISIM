package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_012 extends TestBase {

	@Test
	public void EL_441_012() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		// AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(
				"EL.441.012:Verify CSR can register Group PEO for Type of Legal Entity 'Other' and Type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		PEOPage.menuPeo.click();
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		sleep();
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		PEOPage.groupRegPeo.click();
		commonFuntions.enterTextboxContains("Name of Professional Employer Organization",
				"Test_auto" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains(
				"Additional name(s), if any, under which the PEO currently conducts business",
				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.screenShot("PEOGroupRegistration", "Pass", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");

		// Generate random ERN and FEIN
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		String feinValue1 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		String feinValue = feinValue1;
		System.out.println("The FEIN is::" + feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Other");
		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Dat", "02/01/2023");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("UnemploymentInsurance", "Pass", "Unemployment Insurance Account Details");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		PEOPage.addressLine1.sendKeys("addressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		PEOPage.addressLine2.sendKeys("addressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		PEOPage.addressCity.sendKeys("NewYork");
		PEOPage.addressZip.sendKeys("13420");
		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		commonFuntions.screenShot("AddressInformation", "Pass", "Address Information");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.currentAdditionalAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails", "Pass", "UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		} catch (Exception e) {
			System.out.println("Address Information pop up");
		}
		sleep();
		commonFuntions.screenShot("CurrentAdditionalAddress", "Pass",
				"Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		// commonFuntions.enterTextboxContains("Address Line
		// 1","MailingAddres"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.screenShot("MailingAddress", "Pass", "Mailing Address");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
		}
		commonFuntions.enterTextboxContains("Address Line 1",
				"PriorAddress" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterTextboxContains("other name(s)", "automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFuntions.screenShot("PriorAddress", "Pass", "Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("verifyPriorAddress", "Pass", "Verify Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "40");
		commonFuntions.enterTextboxContains("Address Line 1",
				"OwnerInfo" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13430");
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyOwnershipInformation", "Pass", "Verify Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_Pentity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "60");
		commonFuntions.enterTextboxContains("Address Line 1",
				"PriorInfo" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13430");
		commonFuntions.screenShot("PriorOwnershipInformation", "Pass",
				"PriorOwnership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsAddress.click();
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
		}
		sleep(2000);
		commonFuntions.screenShot("VerifyPriorOwnershipInformation", "Pass",
				"Verify Prior Ownership Information:PEO-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("SubmissionInstructionsAndResponsibilities", "Pass",
				"Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		// uploading documents..
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(2000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
			sleep(2000);
		} catch (Exception e) {
			System.out.println("Duplicate file pop up");
		}
		commonFuntions.clickButtonContains("Upload");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		sleep(2000);
		commonFuntions.clickButtonContains("Choose File");
		sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(3000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("verifyClient", "Pass", "Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		// Search with PEO Member
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT FEIN FROM T_TX_PEO_ACCOUNT ttpa ORDER BY UPDATED_TS DESC", "FEIN");
		String feinNumber = databaseResults.get("FEIN");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
		System.out.println(feinNumber);
		commonFuntions.clickButton(" Search ");
		sleep(2000);
		commonFuntions.screenShot("SearchForPEOMember", "Pass", "Search For PEO Member");
		commonFuntions.clickOnLinkAnchorTag(" + ADD PEO MEMBER ");
		sleep(2000);
		commonFuntions.enterTextboxContains("PEO Member Name",
				"Test_auto" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?",
				"No");
		PEOPage.peoMemberAddresLine.sendKeys("23 Avenue");
		PEOPage.peoMemberCity.sendKeys("ny");
		PEOPage.peoMemberState.click();
		sleep();
		PEOPage.peoMemberStateValue.click();
		PEOPage.peoMemberZipCode.sendKeys("58758");
		sleep();
		commonFuntions.enterTextboxContains(" Phone Number ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		sleep();
		commonFuntions.safeJavaScriptClick(PEOPage.peoMember_radioButton);
		// PEOPage.peoMember_radioButton.click();
		commonFuntions.screenShot("PEOMemberInformation", "Pass", "PEO Member Information");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.mailingAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails", "Pass", "UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		} catch (Exception e) {
			System.out.println("Peo Address Pop Up Informtaion");
		}
		sleep();
		commonFuntions.screenShot("VerifyCurrentAdditionalAddress1", "Pass", "Verify Current Additional Address-1");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("PriorAddress1", "Pass", "Prior Address(es) in New York-1");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyPriorAddress(es)inNewYork1", "Pass", "Verify Prior Address(es) in New York-1");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("UploadClientList", "Pass", "Upload Client List");
		commonFuntions.clickButtonContains("Choose File");
		sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(3000);
		commonFuntions.screenShot("UploadedClientListDoc", "Pass", "Upload Client List Doc");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("verifyClient1", "Pass", "Verify Client List-1");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ListofMembersofPEOGroup", "Pass", "List of Members of PEO Group");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("PEODetailsReviewScreen", "Pass", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer", "Autotest");
		commonFuntions.screenShot("Declaration", "Pass", "Declaration");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("GroupGuarantyandStatementofAcknowledgment1", "Pass",
				"Group Guaranty and Statement of Acknowledgment");
		commonFuntions.clickButtonContains("Accept & Submit");
		sleep(2000);
		commonFuntions.screenShot("RenewConfirmation", "Pass", "Register/Renew Confirmation");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
		
		// Checking for REVIEW PEO work item.............
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		driver.navigate().refresh();
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("SerachWithFeinValue", "Pass", "Searching with fein value");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewEmployerAgentChange", "Pass", "Review Employer Agent Change");
		commonFuntions.clickOnLinkAnchorTag("Review PEO ");
		sleep();
		commonFuntions.screenShot("ReviewWorkItemDetails", "Pass", "Work Item Details:WF-091");
		commonFuntions.clickButtonContains("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("ReviewPEORegistration", "Pass", "Review PEO Registration:CPEO-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
		commonFuntions.screenShot("NavigatingToGeneralInformation", "Pass",
				"Navigating To General Information:PEO-002");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.selectRadio("Select");
		} catch (Exception e) {
			System.out.println("Unemployment Insurance Account Details-To select radio button");
		}
		commonFuntions.screenShot("UnemploymentInsuranceReviewPEO", "Pass",
				"Unemployment Insurance Account Details-review peo work item");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("AddressInformationReviewPEOWI", "Pass", "Address Information-rewiew peo work item");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.currentAdditionalAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails", "Pass", "UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		} catch (Exception e) {
			System.out.println("Address Information pop up");
		}
		sleep();
		commonFuntions.screenShot("CurrentAdditionalAddressReviewPeoWI", "Pass",
				"Verify Current Additional Address(es) in New York:REWIEW PEO");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("MailingAddressReviewPEO", "Pass", "Mailing Address:ReviewPEOWI");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
		}
		commonFuntions.screenShot("verifyPriorAddressReviewPEO", "Pass",
				"Verify Prior Address(es) in New York:ReviewPEOWI");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyOwnershipInformationReviewPEO", "Pass",
				"Verify Ownership Information:ReviewPEO");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1", "Prior123");
		commonFuntions.screenShot("PriorOwnershipInformationReviewPeo", "Pass",
				"PriorOwnership Information:ReviewPeoWI");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep();
		try {
			PEOPage.uspsAddress.click();
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
		}
		sleep(2000);
		commonFuntions.screenShot("VerifyPriorOwnershipInformationReviewPEO:PEO-011", "Pass",
				"Verify Prior Ownership Information:ReviewPEO");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("SubmissionInstructionsandResponsibilitiesReviewPEO", "Pass",
				"Submission Instructions and Responsibilities ReviewPEO\"");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("UploadDocumentsReviewPeo", "Pass", "Upload Documents Review PEO");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyClientListReviewPeo", "Pass", "Verify Client List Review Peo");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ListofMembersofPEOGroupReviewPeo", "Pass",
				"List of Members of PEO Group Review Peo");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("PEODetailsReviewScreenReviewPeo", "Pass", "PEO Details Review screen Review Peo");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("DeclarationReviewPeo", "Pass", "Declaration Review Peo");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("GroupGuarantyandStatementofAcknowledgmentReviewPeo", "Pass",
				"Group Guaranty and Statement of Acknowledgment Review Peo");
		commonFuntions.clickButtonContains("Accept & Submit");
		sleep(2000);
		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("PEORegistrationApprovalRevewPeoPage", "Pass",
				"PEO Registration Approval Review Peo");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("RegistrationConfirmationAfterReviewPeo", "Pass",
				"Registration Confirmation After Review Peo");
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
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
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

		// Main Menu > Inquiry >View Correspondence

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
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("DocumentFound", "Pass", "Document Fount after entering PEO ID:DMS-001");

	}
}
