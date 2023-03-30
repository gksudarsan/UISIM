package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_013_CSR_Verify_CSR_can_register_PEO_Group_LegalEntity_Corporation extends TestBase{


	@Test(priority=1, description = "EL.441.013  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Privately or Closely Held'.",groups = {"Regression"})
	public void EL_441_013() throws Exception
	{
		 test = report.createTest("EL.441.013  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Privately or Closely Held'.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 System.out.println("feinValue is"+feinValue);
		 System.out.println("ernValue is"+ernValue);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 PEOPage.menuPeo.click();	
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Register PEO");
		 commonFuntions.screenShot("peo registration", "Pass", "PEO Registration - Contact Details");
		 commonFuntions.clickButtonContains("Continue");
		 sleep(2000);
		 PEOPage.groupRegPeo.click();
		 commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.screenShot("peor", "Pass", "Professional Employer Organization Registration");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
	     commonFuntions.screenShot("GI", "PASS", "General Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     //commonFuntions.selectRadio("Select");
	     try {
		     commonFuntions.selectRadioInTable(ernValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
	     commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
	     commonFuntions.clickButtonContains("Save & Continue"); 
	     sleep(2000);
	     PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13476");
	     commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
	     commonFuntions.screenShot("Address Information", "PASS", "Address Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     PEOPage.uspsAddress.click();
	     sleep(2000);
	     PEOPage.currentAdditionalAddress.click();
	     sleep(2000);
	     commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();
	     sleep(2000);
	     commonFuntions.screenShot("Verify Current Additional Address", "PASS", "Verify Current Additional Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	    // PEOPage.addressLine1.sendKeys("mailingAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.screenShot("Mailing Address", "PASS", "Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     try {
	     PEOPage.uspsSuggestedAddress.click();
	     commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();
	     sleep(2000);
	     }
	     catch(Exception e) {}
	     commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","PrioraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13429");
	     commonFuntions.enterTextboxContains("other name(s)","automation");
	     commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
	     commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
	     commonFuntions.screenShot("Prior Address","Pass","Prior Address(es) in New York");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("Verify Prior Address(es) in New York", "PASS", "Verify Prior Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
	     commonFuntions.enterTextboxContains("Ownership Percentage","40");
	     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
	     commonFuntions.enterTextboxContains("Ownership Percentage","60");
	     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     PEOPage.uspsAddress.click();
	     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();	    
	     sleep(2000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);	     
	     commonFuntions.selectCheckbox("Proof of NYS Workers");
		 commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		 sleep(2000);
		 commonFuntions.uploadDoc("Sample.docx");
		 sleep(4000);
		 commonFuntions.clickButtonContains("Upload");
		 sleep(2000);
		 commonFuntions.screenShot("Upload Documents", "PASS", "Upload Documents");
		 commonFuntions.clickButtonContains("Save & Continue");
		 sleep(2000);
		 commonFuntions.clickButtonContains("Choose File");
		 sleep(2000);
		 commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		 sleep(2000);
		 commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");
		 commonFuntions.clickButtonContains("Continue");
		 sleep(2000);
		 commonFuntions.screenShot("Verify Client List", "PASS", "client list");
		 commonFuntions.clickButtonContains("Continue");
		 sleep(2000);
	     
		 // blocked the test case at step26 as not able to search with fein number for peo member 
	     
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC","FEIN");
			
			String feinNumber=databaseResults.get("FEIN");
			commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinNumber);
			System.out.println(feinNumber);
			sleep(2000);
			//commonFuntions.clickButtonContains(" Search ");
			commonFuntions.clickButton(" Search ");
			commonFuntions.selectRadio("Select");
			commonFuntions.screenShot("PEO Member", "PASS", "Search For PEO Member");
			commonFuntions.clickButtonContains("Continue ");
			sleep(2000);
			commonFuntions.enterTextbox("PEO Member Name","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
			
			//Random fein is not working - only existing fein is working from database
			//commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
			
			commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
			commonFuntions.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "No");
			commonFuntions.enterTextboxContains("Address Line 1","ownerAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			commonFuntions.enterTextboxContains("Address Line 2","ownerAddressLine2"+commonFuntions.createRandomInteger(1000,9999));
			commonFuntions.enterTextboxContains("City","NewYork");
			commonFuntions.selectDropdown("State", "New York");
			commonFuntions.enterTextboxContains("Zip Code","13430");
			commonFuntions.selectDropdown("Country", "United States");
			commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
			commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
			sleep(2000);
			//commonFuntions.selectRadioQuestions("List the current address of each additional address the PEO Member maintains in New York", "Same As Physical Address");
			commonFuntions.selectRadio("Same As Physical Address");
			commonFuntions.screenShot("PEO member information", "Pass", "PEO Member Information");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(2000);
			try {
				PEOPage.uspsSuggestedAddress.click();
				PEOPage.mailingAddress.click();
				commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
				PEOPage.UspsContinueButton.click();
				sleep(4000);
			}
			catch(Exception e) {
				
			}
			sleep(5000);
			commonFuntions.screenShot("current additional address", "Pass", "Verify Current Additional Address(es) in New York");
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("Prior Address(es) in New York", "Pass", "Prior Address(es) in New York");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(2000);
			commonFuntions.screenShot("Verify Prior Address(es) in New York", "Pass", "Verify Prior Address(es) in New York");
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.clickButtonContains("Choose File");
			sleep(2000);
			commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
			sleep(4000);
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("List of members", "Pass", "List of Members of PEO Group");
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("PEO Details", "Pass", "PEO Details Review screen");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(2000);
			//commonFuntions.enterTextbox("Enter name of Officer","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
			commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
			   
			commonFuntions.screenShot("Declaration", "Pass", "Declaration");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(2000);
			commonFuntions.screenShot("Statement Of Acknowledgement", "Pass", "Incorrect screen ID - PEO-017");
			commonFuntions.clickButtonContains("Accept & Submit");
			sleep(2000);
			commonFuntions.screenShot("Register/Renew Confirmation", "Pass", "Register/Renew Confirmation");
			commonFuntions.clickButtonContains("Home");
			sleep(200);
		


	}
}
