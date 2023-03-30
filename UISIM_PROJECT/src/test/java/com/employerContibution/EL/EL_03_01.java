package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_03_01 extends TestBase{


	@Test(priority=1, description = "EL.03.01 - Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Individual'",groups = {"Regression"})
	public void EL_03_01() throws Exception

	{

		test = report.createTest("EL.03.01 - Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Individual'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonStepDefinitions commonFuntions= new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' ORDER BY UPDATED_TS DESC","PEO_NAME");
		//String feinValue =databaseResults.get("Fein");
		//String ernValue = databaseResults.get("Ean");
		//System.out.println("feinValue is"+feinValue);

		String PEOName=databaseResults.get("PEO_NAME");

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("Logging Application", "PASS", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.clickMenu("Manage PEO");
		commonFuntions.screenShot("Search PEO", "PASS", "Search for PEO");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(3000);
		commonFuntions.errorContent("At least one field must be filled for search");
		commonFuntions.screenShot("Error Message", "PASS", "Error Content Message");
		Thread.sleep(2000);
		PEOPage.advancedSearch.click();
		/* commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "111111");
		 commonFuntions.clickButtonContains("Search");
		 commonFuntions.errorContent("Invalid Federal Employer Identification Number (FEIN)");
		 commonFuntions.screenShot("Invalid FEIN error", "PASS", "Search for PEO");
		 Thread.sleep(2000);
		 commonFuntions.clearTextboxContains("Federal Employer Identification Number (FEIN)");
		 commonFuntions.enterTextboxContains("Employer Registration Number", "1111");
		 commonFuntions.clickButtonContains("Search");
		 commonFuntions.errorContent("Invalid Employer Registration Number");
		 commonFuntions.screenShot("Invalid ERN Number", "PASS", "Search for PEO");
		 Thread.sleep(2000);
		 commonFuntions.clearTextboxContains("Employer Registration Number"); */
		commonFuntions.enterTextboxContains("PEO Name", PEOName);
		System.out.println("PEO Name is :"+PEOName);
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("select results", "PASS", "Search PEO");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("PEO Conversion", "PEO Exempt to PEO Individual");
		commonFuntions.clickButtonContains("CONVERT");
		commonFuntions.screenShot("General Information", "PASS", "General Information");
		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");

		try {
			commonFuntions.selectRadioQuestions("Privately or Closely Held", "Public");
		}
		catch (Exception e) {

		}
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
			commonFuntions.selectRadio("Select");
		}
		catch (Exception e) {

		}
		commonFuntions.screenShot("Unemployment Insurance Account Details", "PASS", "Unemployment Insurance Account Details");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Address Information", "PASS", "Address Information");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
		PEOPage.UspsContinueButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("Additional Address", "PASS", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Mailing Address", "PASS", "Mailing Address");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","PrioraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)","automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		commonFuntions.screenShot("Prior Address","Pass","Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Prior Address", "PASS", "Verify Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage","40");
		commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
		commonFuntions.enterTextboxContains("Ownership Percentage","60");
		commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
		PEOPage.uspsSuggestedAddress.click();
		}
		catch(Exception e) {
			System.out.println("Primary Physical Address");
		}
		commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
		PEOPage.UspsContinueButton.click();	    
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		// commonFuntions.selectCheckbox("Proof of NYS Workers' Compensation Coverage");
		commonFuntions.selectLink("Proof of NYS Workers' Compensation Coverage", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.selectCheckbox("Affirmation from an independent CPA that the PEO is up to date on remittance of federal and state payroll tax withholdings through the last completed financial quarter");
		commonFuntions.selectLink("Affirmation from an independent CPA that the PEO is up to date on remittance of federal and state payroll tax withholdings through the last completed financial quarter", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.selectCheckbox("Sample of a written notice to worksite employees notifying them of the nature of the relationship between the employer and the PEO");
		commonFuntions.selectLink("Sample of a written notice to worksite employees notifying them of the nature of the relationship between the employer and the PEO", "Browse");
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Documents", "PASS", "Upload Documents");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Client List", "PASS", "client list");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("PEO Details Review screen", "PASS", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member", "testing123");
		commonFuntions.screenShot("Declaration", "PASS", "Declaration");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Statement Of Acknowledgement", "PASS", "Statement Of Acknowledgement");
		commonFuntions.clickButtonContains("Accept & Submit");
		Thread.sleep(2000);
		commonFuntions.selectRadioQuestions("Action", "Approved");
		commonFuntions.screenShot("PEO Registration Approval", "PASS", "PEO Registration Approval");
		commonFuntions.clickButton("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Conversion Confirmation", "PASS", "Conversion Confirmation");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Home");
		Thread.sleep(2000);


		//finding with peo name
		commonFuntions.clickMenu("Menu");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.clickMenu("Professional Employer Organization (PEO)");
		commonFuntions.clickMenu("Inquiry PEO Information");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("PEO Name", "Test_auto8237");
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("PEO Inquiry", "PASS", "PEO Inquiry");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Close");
		Thread.sleep(1000);

	}
}