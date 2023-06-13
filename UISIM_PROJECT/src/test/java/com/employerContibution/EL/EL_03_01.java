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
public class EL_03_01 extends TestBase{


	@Test
	public void EL_03_01() throws Exception

	{
		test = 
				report.createTest("EL.03.01:Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Individual'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER'","PEO_NAME");
		String PEOName=databaseResults.get("PEO_NAME");
		System.out.println(PEOName);
		test.log(Status.INFO, "PeoName::"+PEOName);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("Logging Application", "PASS", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		PEOPage.menuPeo.click();
		sleep();
		commonFuntions.clickMenu("Manage PEO");
		sleep();
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
		commonFuntions.safeJavaScriptClick(PEOPage.Issued_RadioButton);
		commonFuntions.screenShot("SearchResultsForPeoName", "PASS", "Search For Peo");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("PEO Conversion", "PEO Exempt to PEO Individual");
		commonFuntions.screenShot("ManageExemptPEO", "Pass", "Manage Exempt PEO:MPEO-003");
		commonFuntions.clickButtonContains("CONVERT");
		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
		commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:PEO-002");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.screenShot("UnemploymentInsuranceAccountDetails", "Pass", "Unemployment Insurance Account Details:EAS-001");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains(" Phone Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("AddresInformation", "Pass", "Address Information:PEO-003");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.currentAdditionalAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails","Pass","UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("If pop up present on the screen");
		}
		sleep();
		commonFuntions.screenShot("AdditionalAddress", "Pass", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Mailing Address", "Other");
		commonFuntions.enterTextboxContains("Address Line 1","MailingAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		//commonFuntions.enterTextboxContains("City","NewYork");
		//commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.screenShot("MailingAddress", "PASS", "Mailing Address:PEO-004");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep();
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("USPS Suggested Address");
		}
		commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		//commonFuntions.enterTextboxContains("City","NewYork");
		//commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)","automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		commonFuntions.screenShot("PriorAddress","Pass","Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("In case if pop up address populated");
		}
		sleep();
		commonFuntions.screenShot("AdditionalAddress", "PASS", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage","40");
		commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("Primary Physical Address");
		}
		sleep();
		commonFuntions.screenShot("OwnershipInformation","Pass","Verify Public Ownership Information:PEO-013");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("SubmissonInstructions","Pass","Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		//Upload Documents
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("UploadDocuments", "PASS", "Upload Documents:SREG-006");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		commonFuntions.clickButtonContains("Choose File");
		sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(2000);
		commonFuntions.screenShot("UploadClientList", "PASS", "Upload Client List");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyClientList2", "PASS", "Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("PEODetailsReviewScreen", "PASS", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member", "testing123");
		commonFuntions.screenShot("Declaration", "PASS", "Declaration");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("Statement Of Acknowledgement", "PASS", "Statement Of Acknowledgement");
		commonFuntions.clickButtonContains("Accept & Submit");
		sleep(2000);
		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("PEO Registration Approval", "PASS", "PEO Registration Approval");
		commonFuntions.clickButton("Submit");
		sleep(2000);
		commonFuntions.screenShot("Conversion Confirmation", "PASS", "Conversion Confirmation");
		sleep(2000);
		commonFuntions.clickButtonContains("Home");
		sleep(2000);

		// Step 30 - 32 not working 
		
		//Finding with PEO Name
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.clickMenu("Inquiry");
		sleep();
		PEOPage.PeoMenu.click();
		sleep();
		commonFuntions.screenShot("InquiryPeoInformation", "Pass", "Inquiry PEO Information");
		commonFuntions.clickMenu("Inquiry PEO Information");
		sleep();
		System.out.println("The entered PeoName is:" +PEOName);
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		commonFuntions.screenShot("PEOInquiry", "PASS", "PEO Inquiry");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(PEOPage.Issued_RadioButton);
		commonFuntions.screenShot("PEOInquirySelectRadioButton", "Pass", "PEO Inquiry Select Radio Button");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("PEOInquiryConfirmatioPage", "Pass", "PEO Inquiry");
		sleep(2000);
		commonFuntions.clickButtonContains("Close");
		sleep(1000);

	}
}