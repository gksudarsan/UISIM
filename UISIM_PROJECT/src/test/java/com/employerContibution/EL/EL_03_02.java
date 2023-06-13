package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_03_02 extends TestBase{


	@Test
	public void EL_03_02() throws Exception

	{
		test = 
				report.createTest("EL.03.02:Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Group'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER'", "PEO_NAME");
		String PEOName =databaseResults.get("PEO_NAME");
		System.out.println("The PeoName is:" +PEOName);
		test.log(Status.INFO, "PeoName is::"+PEOName);
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
		commonFuntions.screenShot("SearchPEO", "Pass", "Search for PEO:MPEO-001");
		commonFuntions.enterTextboxContains("PEO Name",PEOName);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(PEOPage.Issued_RadioButton);
		commonFuntions.screenShot("SearchResultsForPeoNameRadioButton", "Pass", "Select Radio Button For PeoName:MPEO-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("PEO Conversion", "PEO Exempt to PEO Group");
		commonFuntions.screenShot("ManageExemptPEO", "Pass", "Manage Exempt PEO:MPEO-003");
		commonFuntions.clickButtonContains("CONVERT");
		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:PEO-002");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(2000);
		commonFuntions.screenShot("UnemploymentInsuranceAccountDetails", "PASS", "Unemployment Insurance Account Details:EAS-001");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
//		commonFuntions.enterTextboxContains("Address Line 1","Avenue"+commonFuntions.createRandomInteger(1000,9999));
//		commonFuntions.enterTextboxContains("City","NY");
//		commonFuntions.enterTextboxContains("Zip Code","13429");
//		commonFuntions.enterTextboxContains(" Phone Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
//		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("AddressInformation", "Pass", "Address Information:PEO-003");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.currentAdditionalAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails","Pass","UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("Only If pop up present then select the usps address");
		}
		sleep();
		commonFuntions.screenShot("VerifyCurrentAdditionalAddressNewYork", "Pass", "Verify Current Additional Address New York:PEO-005");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("MailingAddress", "Pass", "Mailing Address:PEO-004");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("USPS Suggested Address");
		}
		commonFuntions.enterTextboxContains("Address Line 1","avenue"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)","automation"); 
		commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		commonFuntions.screenShot("PriorAddressNewYork","Pass","Prior Address New York:PEO-006");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyPriorAddress", "Pass", "Verify Prior Address New York:PEO-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage","40");
		commonFuntions.enterTextboxContains("Address Line 1","avenue"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company:PEO-008");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.screenShot("VerifyOnwershipInformation","Pass","Verify Ownership Information:PEO-009");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
		commonFuntions.enterTextboxContains("Ownership Percentage","60");
		commonFuntions.enterTextboxContains("Address Line 1","PriorOwnerAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("PriorOwnershipInformation","Pass","Prior Ownership Information:PEO-010");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("Primary Physical Address");
		}
		sleep();
		commonFuntions.screenShot("VerifyPriorOwnershipInformation","Pass","Verify Prior Ownership Information:PE0-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("SubmissionInstructionsResponsibilities","Pass","Submission Instructions and Responsibilities:PEO-014");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		//Upload Documents..
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		sleep();
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(2000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
			sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Duplicate Address Exists");
		}
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
		
		/*------Search For PEO member(PEOG-001)------*/
		
		commonFuntions.screenShot("SearchForPEOMamberAgain", "Pass", "Search For PEO Member:PEOG-001");
		commonFuntions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("SearchForPEOMamberSelectRadioButton", "Pass", "PEO Member Select Radio Button:PEOG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		//To get the FEIN value from data bases 
		
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuery("SELECT PEO_NAME,FEIN FROM T_TX_PEO_ACCOUNT ttpa");
		String feinValue = databaseResults.get("Fein");
		System.out.println("The Fein Value is:"+ feinValue);
		test.log(Status.INFO, "Fein::"+feinValue);
		commonFuntions.enterTextboxContains("PEO Member Name", "AutoTest"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "No");
		//commonFuntions.enterTextboxContains("Employer Registration Number",Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Address Line 1","Address"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains(" Phone Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
        sleep();
        commonFuntions.selectRadioQuestions("List the current address of each additional address the PEO Member maintains in New York", "Different");
        PEOPage.PeoAddress1.sendKeys("Address123");
        PEOPage.PeoCity1.sendKeys("NY");
        PEOPage.PeoZipCode.sendKeys("34528");
        PEOPage.peoCountry.click();
        sleep();
        PEOPage.peoCountryvalue.click();
        commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(PEOPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(PEOPage.mailingAddress);
			commonFuntions.screenShot("VerifyPopUpContactDetails","Pass","UspsAddress");
			commonFuntions.safeJavaScriptClick(PEOPage.popContinueButton);
		}
		catch(Exception e) {
			System.out.println("Peo Address Pop Up Informtaion");
		}
		sleep();
		commonFuntions.screenShot("ApplicationforExemptionSubmissionInstructions", "Pass", "Application for Exemption Submission Instructions:PE0-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		/*--------------------*/



		commonFuntions.screenShot("PEODetailsReviewscreen", "PASS", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer", "testing123");
		commonFuntions.screenShot("Declaration", "Pass", "Declaration");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("GroupGuarantyandStatementofAcknowledgment", "PASS", "Group Guaranty and Statement of Acknowledgment");
		commonFuntions.clickButtonContains("Accept & Submit"); 
		sleep(2000);
		commonFuntions.selectRadioQuestions("Action", "Approved");
		commonFuntions.screenShot("PEORegistrationApproval", "Pass", "PEO Registration Approval");
		commonFuntions.clickButton("Submit");
		sleep(2000);
		commonFuntions.screenShot("ConversionConfirmation", "Pass", "Conversion Confirmation");
		sleep(2000);
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
	}
}