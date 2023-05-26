package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_004_Verify_PEO_Admin_can_register_PEO_Group extends TestBase {
	@Test(priority = 1, description = "EL.441.004 - Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Limited Liability Company' and Type of Ownership 'Privately or Closely Held' Ownership'", groups = {
			"Regression" })
	public void Testing123() throws Exception {
		test = report.createTest(
				"EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage Home = new HomePage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("feinValue is" + feinValue);
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		System.out.println("ernValue is" + ernValue);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration peoEmployer = new PEO_001_ProfessionalEmployerOrganizationRegistration(
				driver);
		// Query

		Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC",
				"FEIN");
		String feinNumber = databaseResults.get("FEIN");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
		System.out.println(feinNumber);
		sleep(2000);

		// Login
		commonFunction.login(COMMON_CONSTANT.PEO_USER_1.toUpperCase(), COMMON_CONSTANT.PEO_USER_1_PASSWORD);
		commonFunction.screenShot("Home", "Pass", "Login is successful");
		Home.navigateToPeoRegister();

		// PEO-019
		commonFunction.enterTextboxContains("First Name",
				"AutoTestFirstName" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4));
		commonFunction.enterTextboxContains("Last Name",
				"AutoTestLastName" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 4));
		commonFunction.enterTextboxContains("Job Title", "Auditor");
		commonFunction.enterTextboxContains("Contact Number",
				StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 10));
		commonFunction.enterTextboxContains("Email Address", "autoEmail"
				+ StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 11))), 4) + "@gmail.com");

		commonFunction.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		sleep(3000);
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);

		// PEO-001
		PEOPage.groupRegPeo.click();
		commonFunction.enterTextbox("Name of Professional Employer Organization",
				"Test_auto" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently",
				"auto_test" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.screenShot("peor", "Pass", "Professional Employer Organization Registration");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);

		// PEO-002
		commonFunction.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		commonFunction.enterTextboxContains("Employer Registration Number", ernValue);
		commonFunction.selectDropdown("Type of Legal Entity", "Limited Liability Company (All Types)");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFunction.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
		commonFunction.screenShot("GI", "PASS", "General Information");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		// commonFunction.selectRadio("Select");
		try {
			PEOPage.peoRadioButton.click();
			commonFunction.selectRadioInTable(ernValue, 1, 1, "Unemployment Insurance Account Details");
		} catch (Exception e) {
		}
		commonFunction.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);

		// PEO-003
		PEOPage.addressLine1.sendKeys("addressLine1" + commonFunction.createRandomInteger(1000, 9999));
		PEOPage.addressLine2.sendKeys("addressLine2" + commonFunction.createRandomInteger(1000, 9999));
		PEOPage.addressCity.sendKeys("NewYork");
		PEOPage.addressZip.sendKeys("13476");
		commonFunction.enterTextboxContains("Phone Number",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFunction.createRandomInteger(10000, 99999)) + "@gmail.com");
		commonFunction.screenShot("Address Information", "PASS", "Address Information");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);
		PEOPage.uspsAddress.click();
		sleep(2000);
		PEOPage.currentAdditionalAddress.click();
		sleep(2000);
		commonFunction.screenShot("VerifyContactDetails", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		sleep(2000);

		// PEO-005
		commonFunction.screenShot("Verify Current Additional Address", "PASS",
				"Verify Current Additional Address(es) in New York");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		// PEOPage.addressLine1.sendKeys("mailingAddressLine1"+commonFunction.createRandomInteger(1000,9999));

		// PEO-004
		commonFunction.screenShot("Mailing Address", "PASS", "Mailing Address");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);
		try {
			// PEOPage.uspsSuggestedAddress.click();
			PEOPage.CurrentUspsAddress.click();
			PEOPage.mailingUspsAddress.click();
			commonFunction.screenShot("VerifyContactDetails", "Pass", "UspsAddress");

			PEOPage.UspsContinueButton.click();
			sleep(2000);
		} catch (Exception e) {
		}

		// PEO-006
		commonFunction.enterTextboxContains("Address Line 1",
				"PrioraddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Address Line 2",
				"PrioraddressLine2" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("City", "NewYork");
		commonFunction.enterTextboxContains("Zip Code", "13429");
		commonFunction.enterTextboxContains("other name(s)", "automation");
		commonFunction.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
		commonFunction.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFunction.screenShot("Prior Address", "Pass", "Prior Address(es) in New York");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);

		// PEO-007
		commonFunction.screenShot("Verify Prior Address(es) in New York", "PASS",
				"Verify Prior Address(es) in New York");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);

		// PEO-008
		commonFunction.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFunction.enterTextboxContains("Ownership Percentage", "40");
		commonFunction.enterTextboxContains("Address Line 1",
				"owneraddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Address Line 2",
				"owneraddressLine2" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("City", "NewYork");
		commonFunction.enterTextboxContains("Zip Code", "13430");
		commonFunction.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);

		// PEO-009
		commonFunction.screenShot("VerifyOnwershipInfo", "Pass", "Verify Ownership Information");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);

		// PEO-010
		commonFunction.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFunction.enterTextboxContains("Ownership Percentage", "60");
		commonFunction.enterTextboxContains("Address Line 1",
				"PowneraddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Address Line 2",
				"PowneraddressLine2" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("City", "NewYork");
		commonFunction.enterTextboxContains("Zip Code", "13430");
		commonFunction.screenShot("PriorOwnershipInformation", "Pass",
				"PriorOwnership Information - privately or closely held company");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);
		PEOPage.uspsAddress.click();
		commonFunction.screenShot("UspsAddress1", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		sleep(2000);

		// PEO-011
		commonFunction.screenShot("verifyPriorOnwershipInfo", "Pass", "Verify Ownership Information");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);

		// PEO-014
		commonFunction.screenShot("SubmissonInstructions", "Pass", "Submission Instructions and Responsibilities");
		commonFunction.clickButtonContains("Continue ");

		// SREG-006
		sleep(2000);
		commonFunction.selectLink("Proof of NYS Workers", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("Affirmation from an independent", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("A blank client contract incorporating", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("Sample of a written notice to worksite", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.selectLink("Authorization to do business", "Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		// commonFunction.clickButtonContains("Upload");
		sleep(2000);
		commonFunction.screenShot("Upload Documents", "PASS", "Upload Documents");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);

		// PEO-015
		commonFunction.clickButtonContains("Choose File");
		sleep(2000);
		commonFunction.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(2000);
		commonFunction.screenShot("Upload Client List", "PASS", "Upload Client List");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);

		// LEAS-012
		commonFunction.screenShot("Verify Client List", "PASS", "client list");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);

		// PEOG-002
		commonFunction.enterTextbox("PEO Member Name", "Test_auto" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
		commonFunction.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?",
				"Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", ernValue);
		commonFunction.enterTextboxContains("Address Line 1",
				"ownerAddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Address Line 2",
				"ownerAddressLine2" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("City", "NewYork");
		commonFunction.selectDropdown("State", "New York");
		commonFunction.enterTextboxContains("Zip Code", "13430");
		sleep(1000);
		commonFunction.selectDropdown("Country", "United States");
		sleep(1000);
		commonFunction.enterTextboxContains("Phone Number",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFunction.createRandomInteger(10000, 99999)) + "@gmail.com");
		sleep(2000);
		// commonFunction.selectRadioQuestions("List the current address of each
		// additional address the PEO Member maintains in New York", "Same As Physical
		// Address");
		commonFunction.selectRadio("Same As Physical Address");
		commonFunction.screenShot("PEO member information", "Pass", "PEO Member Information");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.mailingAddress.click();
			commonFunction.screenShot("UspsAddress", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
			sleep(4000);
		} catch (Exception e) {

		}
		sleep(5000);
		
		// PEO-005
		commonFunction.screenShot("current additional address", "Pass",
				"Verify Current Additional Address(es) in New York");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		
		// PEO-006
		commonFunction.screenShot("Prior Address(es) in New York", "Pass", "Prior Address(es) in New York");
		commonFunction.clickButtonContains("Save & Continue ");
		sleep(2000);
		
		// PEO-007
		commonFunction.screenShot("Verify Prior Address(es) in New York", "Pass",
				"Verify Prior Address(es) in New York");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		
		// PEO-015
		commonFunction.clickButtonContains("Choose File");
		sleep(2000);
		commonFunction.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(4000);
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		
		// LEAS-012
		commonFunction.screenShot("verifyClient", "Pass", "Verify Client List");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		
		// PEOG-003
		commonFunction.screenShot("List of members", "Pass", "List of Members of PEO Group");
		commonFunction.clickOnLink1(" + ADD ANOTHER PEO MEMBER ");
		sleep(2000);
		
		commonFunction.screenShot("PEO Details", "Pass", "PEO Details Review screen");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		// commonFunction.enterTextbox("Enter name of
		// Officer","Test_auto"+commonFunction.createRandomInteger(1000,9999));
		commonFunction.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
				"TestAutomation" + commonFunction.createRandomInteger(10000, 99999));

		commonFunction.screenShot("Declaration", "Pass", "Declaration");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFunction.screenShot("Statement Of Acknowledgement", "Pass", "Incorrect screen ID - PEO-017");
		commonFunction.clickButtonContains("Accept & Submit");
		sleep(2000);
		commonFunction.screenShot("Register/Renew Confirmation", "Pass", "Register/Renew Confirmation");
		commonFunction.clickButtonContains("Home");
		sleep(2000);
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(2000);
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		PEOPage.queue.click();
		sleep(2000);
		// sleep(15000);
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.enterTextboxContains("FEIN", feinValue);
		commonFunction.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFunction.clickButtonContains("Search");
		sleep(2000);
		commonFunction.screenShot("Review Peo", "Pass", "Review Peo");
		commonFunction.clickOnLink("Review PEO");
		sleep(2000);
		commonFunction.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFunction.screenShot("Review", "Pass", "Review Peo Registration");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);

		commonFunction.screenShot("GeneralInfo", "Pass", "General Information");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.peoRadioButton.click();
			commonFunction.selectRadioInTable(feinValue, 1, 1, "Unemployment Insurance Account Details");
		} catch (Exception e) {
		}
		commonFunction.screenShot("Insurance", "Pass", "UnemploymentInsuranceAccountDetails");

		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFunction.screenShot("AddressInfo", "Pass", "Address Information");// addr1
		commonFunction.enterTextboxContains("Address Line 1",
				"AddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFunction.screenShot("UspsAddress2", "Pass", "UspsAddress");
		PEOPage.UspsContinueButton.click();
		sleep(2000);
		commonFunction.screenShot("VerifyCurrentAdd", "Pass", "Verify Current Additional Address");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.screenShot("MailingAddress", "Pass", "Mailing Address");// addr1
		commonFunction.enterTextboxContains("Address Line 1",
				"MailingaddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);// usps
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFunction.screenShot("UspsAddress3", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
			sleep(2000);
		} catch (Exception e) {
		}
		commonFunction.screenShot("VerifyPriorAdd", "Pass", "Verify Prior Address");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);

		commonFunction.screenShot("VerifyOwnerInfo", "Pass", "Verify Owner Information");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.enterTextboxContains("Address Line 1",
				"PowneraddressLine1" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.enterTextboxContains("Address Line 2",
				"PowneraddressLine2" + commonFunction.createRandomInteger(1000, 9999));
		commonFunction.screenShot("PriorOwner", "Pass", "Prior Owner Information");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsAddress.click();
			commonFunction.screenShot("UspsAddress3", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
			sleep(2000);
		} catch (Exception e) {
		}
		commonFunction.screenShot("VerifyPrioerOwner", "Pass", "Verify Prior Ownership Information");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.screenShot("Submission", "Pass", "Submission");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.screenShot("UploadDocs", "Pass", "Upload Documents");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFunction.screenShot("VerifyClient", "Pass", "Verify Client List");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.screenShot("MembersPeo", "Pass", "List Of Members Peo");
		commonFunction.clickButtonContains("Continue");
		sleep(2000);
		commonFunction.screenShot("PeoReview", "Pass", "Peo Details Review");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		// commonFunction.enterTextboxContains("Enter name of Officer, Partner,
		// Proprietor or
		// Member","TestAutomation"+commonFunction.createRandomInteger(10000,99999));
		commonFunction.screenShot("Declaration2", "Pass", "Declaration");
		commonFunction.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFunction.screenShot("Statement Of Acknowledgement", "Pass", "Acknowledgement");
		commonFunction.clickButtonContains("Accept & Submit");
		sleep(2000);
		// commonFunction.screenShot("StatementAckn","Pass","Statment of
		// Acknowledgment");
		// commonFunction.clickButtonContains("Continue");
		// sleep(2000);

		commonFunction.selectRadio("Approved");
		commonFunction.screenShot("ApprovalPage", "Pass", "ApprovalPage");
		commonFunction.clickButtonContains("Submit");
		sleep(5000);
		commonFunction.screenShot("Success", "Pass", "SuccessPage");
		commonFunction.logout(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		PEOPage.queue.click();
	    sleep(15000);
	    commonFunction.enterTextboxContains("FEIN",feinValue);
	    commonFunction.screenShot("FeinSearch","Pass","feinSearch");
	    commonFunction.clickButtonContains("Search");
	    sleep(2000);
	    commonFunction.screenShot("Review Peo","Pass","Review Peo");
	    commonFunction.clickOnLink("Review PEO");
	    sleep(2000);
	    commonFunction.clickButtonContains("Open Work Item");
	    sleep(2000);
	    commonFunction.screenShot("Review","Pass","Review Peo Registration");
	    commonFunction.clickButtonContains("Continue");
	     sleep(2000);	   
	     
	     commonFunction.screenShot("GeneralInfo","Pass","General Information");
	     commonFunction.clickButtonContains("Save & Continue");
	     sleep(2000);
	     try {
		     commonFunction.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}commonFunction.screenShot("Insurance","Pass","UnemploymentInsuranceAccountDetails");
	     
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);	     
		     commonFunction.screenShot("AddressInfo","Pass","Address Information");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     PEOPage.uspsAddress.click();
		     PEOPage.currentAdditionalAddress.click();
		     commonFunction.screenShot("UspsAddress2","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();
		     sleep(2000);
		     commonFunction.screenShot("VerifyCurrentAdd","Pass","Verify Current Additional Address");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);	
		     commonFunction.screenShot("MailingAddress","Pass","Mailing Address");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     commonFunction.screenShot("VerifyPriorAdd","Pass","Verify Prior Address");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);	
		     
		     
		     commonFunction.screenShot("VerifyOwnerInfo","Pass","Verify Owner Information");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);
		     commonFunction.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFunction.createRandomInteger(1000,9999));
		     commonFunction.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFunction.createRandomInteger(1000,9999));
		     commonFunction.screenShot("PriorOwner","Pass","Prior Owner Information");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     PEOPage.uspsAddress.click();
		     commonFunction.screenShot("UspsAddress3","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();	
		     sleep(2000);
		     commonFunction.screenShot("VerifyPrioerOwner","Pass","Verify Prior Ownership Information");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);
		     commonFunction.screenShot("Submission","Pass","Submission");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);
		     commonFunction.screenShot("UploadDocs","Pass","Upload Documents");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     commonFunction.screenShot("VerifyClient","Pass","Verify Client List");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);		     
		     commonFunction.screenShot("PeoDetails","Pass","Peo Details Review");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     commonFunction.screenShot("Declaration2","Pass","Declaration");
		     commonFunction.clickButtonContains("Save & Continue");
		     sleep(2000);
		     commonFunction.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
		     commonFunction.clickButtonContains("Continue");
		     sleep(2000);
		     
		     commonFunction.selectRadio("Approved");
		     commonFunction.screenShot("ApprovalPage","Pass","ApprovalPage");
		     commonFunction.clickButtonContains("Submit");
		     sleep(5000);
		     commonFunction.screenShot("Success","Pass","SuccessPage");

	}
}
