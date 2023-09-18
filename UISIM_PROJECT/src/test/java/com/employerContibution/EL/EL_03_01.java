package com.employerContibution.EL;

import java.util.Map;

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
public class EL_03_01 extends TestBase {

	@Test
	public void EL_03_01() throws Exception

	{
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(
				"EL.03.01:Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Individual'");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("Logging Application", "PASS", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		PEOPage.menuPeo.click();
		sleep();
		commonFuntions.clickMenu("Manage PEO");
		sleep();
		
		/*----Search for PEO---MPEO-001*/
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' ORDER BY UPDATED_TS DESC",
				"PEO_NAME");
		String PEOName = databaseResults.get("PEO_NAME");
		System.out.println(PEOName);
		test.log(Status.INFO, "PeoName::" + PEOName);
		commonFuntions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFuntions.screenShot("Search PEO", "PASS", "Search for PEO");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.errorContent("At least one field must be filled for search");
		commonFuntions.screenShot("ErrorMessageForPeoName", "PASS", "Error Content Message");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "111111");
		commonFuntions.clickButtonContains("Search");
		sleep();
		commonFuntions.errorContent("Invalid Federal Employer Identification Number (FEIN)");
		commonFuntions.screenShot("InvalidFEINError", "PASS", "Search for PEO-Fein Error");
		sleep();
		commonFuntions.forceClearText(PEOPage.clearFeinFieldSection);
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.errorContent("Invalid Employer Registration Number");
		commonFuntions.screenShot("InvalidERNNumber", "PASS", "Search for PEO-Ern Error");
		sleep(20000);
		commonFuntions.forceClearText(PEOPage.clearErnFieldSection);
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		PEOPage.Issued_RadioButton.click();
		commonFuntions.screenShot("SearchResultsForPeoName", "PASS", "Search For Peo");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		
		/*---Manage Exempt PEO---MPEO-003*/
		commonFuntions.selectDropdown("PEO Conversion", "PEO Exempt to PEO Individual");
		commonFuntions.screenShot("ManageExemptPEO", "Pass", "Manage Exempt PEO:MPEO-003");
		commonFuntions.clickButtonContains("CONVERT");
		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
		commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:PEO-002");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.screenShot("UnemploymentInsuranceAccountDetails", "Pass",
				"Unemployment Insurance Account Details:EAS-001");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(3000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
			sleep();
		}catch(Exception e) {
			System.out.println("UI account pop up appears");
		}
		
		//Address Information
		commonFuntions.enterTextboxContains("Address Line 1",
				"PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13429");
		commonFuntions.enterTextboxContains(" Phone Number ",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		commonFuntions.screenShot("AddresInformation", "Pass", "Address Information:PEO-003");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(3000);
		try {
			PEOPage.uspsAddress.click();sleep();
			commonFuntions.screenShot("VerifyPopUpContactDetails", "Pass", "UspsAddress");
			PEOPage.popContinueButton.click();
		} catch (Exception e) {
			System.out.println("If pop up present on the screen");
		}
		sleep();
		
		//Verify Current Additional Address(es) in New York
		
		commonFuntions.screenShot("AdditionalAddress", "Pass", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		
		//Mailing Address
		commonFuntions.selectRadioQuestions("Mailing Address", "Same As Physical Address");
//		commonFuntions.enterTextboxContains("Address Line 1",
//				"MailingAddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
//		commonFuntions.enterTextboxContains("City","NewYork");
//		commonFuntions.enterTextboxContains("Zip Code","13429");
//		commonFuntions.selectDropdown("Country", " Albania ");
		commonFuntions.screenShot("MailingAddress", "PASS", "Mailing Address:PEO-004");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.popContinueButton.click();
		} catch (Exception e) {
			System.out.println("USPS Suggested Address");
		}
		sleep(3000);
		//Prior Address(es) in New York
		commonFuntions.enterTextboxContains("Address Line 1",
				"PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
//		commonFuntions.enterTextboxContains("City","NewYork");
//		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)", "automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFuntions.screenShot("PriorAddress", "Pass", "Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.popContinueButton.click();
		} catch (Exception e) {
			System.out.println("In case if pop up address populated");
		}
		sleep();
		commonFuntions.screenShot("AdditionalAddress", "PASS", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "40");
		commonFuntions.enterTextboxContains("Address Line 1",
				"owneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "13430");
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.popContinueButton.click();
		} catch (Exception e) {
			System.out.println("Primary Physical Address");
		}
		sleep();
		commonFuntions.screenShot("OwnershipInformation", "Pass", "Verify Public Ownership Information:PEO-013");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("SubmissonInstructions", "Pass", "Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		// Upload Documents
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		commonFuntions.uploadDoc("Sample");
		sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UploadDocuments", "PASS", "Upload Documents:SREG-006");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		commonFuntions.clickButtonContains("Choose File");
		sleep(3000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2");
		sleep(3000);
		commonFuntions.screenShot("UploadClientList", "PASS", "Upload Client List");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("VerifyClientList2", "PASS", "Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.screenShot("PEODetailsReviewScreen", "PASS", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(3000);
		commonFuntions.enterTextboxContains("EnternameofOfficePartner, Proprietor or Member", "testing123");
		commonFuntions.screenShot("Declaration", "PASS", "Declaration");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("StatementOfAcknowledgement", "PASS", "Statement Of Acknowledgement");
		commonFuntions.clickButtonContains("Accept & Submit");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep();
		PEOPage.radioButton_Approved.click();sleep();
		commonFuntions.screenShot("PEORegistrationApproval", "PASS", "PEO Registration Approval");
		commonFuntions.clickButtonContains("Submit ");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ConversionConfirmation", "PASS", "Conversion Confirmation");
		sleep(2000);
		commonFuntions.clickButtonContains("Home");
		sleep(5000);

		// Step 30 - 32 not working

		// Finding with PEO Name
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		PEOPage.PeoMenu.click();
		sleep();
		commonFuntions.screenShot("InquiryPeoInformation", "Pass", "Inquiry PEO Information");
		commonFuntions.clickMenu("Inquiry PEO Information");
		sleep();
		System.out.println("The entered PeoName is:" + PEOName);
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		commonFuntions.screenShot("PEOInquiry", "PASS", "PEO Inquiry");
		commonFuntions.clickButtonContains("Search");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.Issued_RadioButton.click();
		commonFuntions.screenShot("PEOInquirySelectRadioButton", "Pass", "PEO Inquiry Select Radio Button");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("PEOInquiryConfirmatioPage", "Pass", "PEO Inquiry");
		sleep(2000);
		commonFuntions.clickButtonContains("Close");
		sleep(3000);

	}
}