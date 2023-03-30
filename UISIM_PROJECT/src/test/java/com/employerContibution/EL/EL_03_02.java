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
public class EL_03_02 extends TestBase{


	@Test(priority=1, description = "EL.03.02 - Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Group'",groups = {"Regression"})
	public void EL_03_02() throws Exception

	{
		
		 test = report.createTest("EL.03.02 - Verify CSR can search PEO and update PEO conversion 'PEO Exempt to PEO Group'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' ORDER BY UPDATED_TS DESC", "PEO_NAME");
		String PEOName =databaseResults.get("PEO_NAME");
			
		
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("Logging Application", "PASS", "Login is successful");
		 commonFuntions.clickMenu("Menu");
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 PEOPage.menuPeo.click();
		 commonFuntions.clickMenu("Manage PEO");
		 commonFuntions.screenShot("Search PEO", "PASS", "Search for PEO");
		 Thread.sleep(2000);
		 commonFuntions.enterTextboxContains("PEO Name",PEOName);
		 System.out.println(PEOName);
		 commonFuntions.clickButtonContains("Search");
		 Thread.sleep(3000);
		 commonFuntions.selectRadio("Select");
		 commonFuntions.screenShot("Searching PEO", "PASS", "Search for PEO");
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 commonFuntions.selectDropdown("PEO Conversion", "PEO Exempt to PEO Group");
		 commonFuntions.screenShot("Manage Exempt PEO", "PASS", "Manage Exempt PEO");
		 commonFuntions.clickButtonContains("CONVERT");
		 long number = commonFuntions.createRandomInteger(10000,99999);
		 String ernValue="03"+Long.toString(number);
		 commonFuntions.clearTextboxContains("Employer Registration Number");
		 commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		 Thread.sleep(2000);
		 commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		 commonFuntions.screenShot("General Information", "PASS", "General Information");
		 commonFuntions.clickButtonContains("Save & Continue");
		 Thread.sleep(2000);
		 commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
		 commonFuntions.clickButtonContains("Save & Continue");
		 Thread.sleep(2000);
		 commonFuntions.enterTextboxContains("Address Line 1","InformationAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		 commonFuntions.screenShot("Address Information", "PASS", "Address Information");
		 commonFuntions.clickButtonContains("Save & Continue");
		 Thread.sleep(2000);
		 PEOPage.uspsAddress.click();
	     PEOPage.currentAdditionalAddress.click();
	     commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Verify Current Additional Address(es) in New York", "PASS", "Verify Current Additional Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.enterTextboxContains("Address Line 1","MailingAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.screenShot("Mailing Address", "PASS", "Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     try {
	    	 Thread.sleep(2000);
	    	 PEOPage.uspsSuggestedAddress.click();
	     }
	     catch(Exception e ) {
	    	 
	    	 PEOPage.UspsContinueButton.click(); 
	     }
		 
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
	     commonFuntions.enterTextboxContains("Address Line 1","PriorOwnerAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","PriorOwnerAddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     PEOPage.uspsAddress.click();
	     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();	    
	     Thread.sleep(2000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
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
	     driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']\"")).click();
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
		 commonFuntions.screenShot("Group Guaranty and Statement of Acknowledgment", "PASS", "Group Guaranty and Statement of Acknowledgment");
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
	}
}