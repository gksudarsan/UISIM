package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_012 extends TestBase{

	
	@Test(priority=1, description = "EL.441.012  - Verify CSR can register Group PEO for Type of Legal Entity 'Other' and Type of Ownership 'Privately or Closely Held'.",groups = {"Regression"})
	public void EL_441_012() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		test = report.createTest("EL.441.012  - Verify CSR can register Group PEO for Type of Legal Entity 'Other' and Type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3","Admin@12345678");

		commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		commonFuntions.clickMenu("Menu");	
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();	
		commonFuntions.screenShot("Menu","Pass","Register PEO");
		commonFuntions.clickMenu("Register PEO");			 
		commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");		
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000); 
		PEOPage.groupRegPeo.click();
		commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.screenShot("PEO group registration", "PASS", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);  
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
		
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		
		commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Other");
		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
		commonFuntions.screenShot("General Information","Pass","General Information");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		
		commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Account Details");
		try {
			Thread.sleep(2000);
			commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		}
		catch(Exception e) {}
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		
		PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
		PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
		PEOPage.addressCity.sendKeys("NewYork");
		PEOPage.addressZip.sendKeys("13420");
		commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
		PEOPage.UspsContinueButton.click();
		Thread.sleep(2000);
		
		commonFuntions.screenShot("Current Additional Address", "PASS", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","MailingAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.screenShot("Mailing Address","Pass","Mailing Address");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			
		}
		commonFuntions.enterTextboxContains("Address Line 1", "priorAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2", "priorAddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)","automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		commonFuntions.screenShot("Prior Address","Pass","Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyPriorAddress","Pass","Verify Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage","40");
		commonFuntions.enterTextboxContains("Address Line 1","ownerAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","ownerAddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Ownership Information", "Pass", "Verify Ownership Information");
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
		try {
			PEOPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();	    
			Thread.sleep(2000);
		}
		catch(Exception e) {

		}
		commonFuntions.screenShot("Ownership Information", "Pass", "Verify Prior Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Submission Instructions and Responsibilities", "Pass", "Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		//uploading documents..
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		
		// At step 26 it is blocked due to not able to find FEIN number from search PEO memeber
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC","FEIN");
		
		String feinNumber=databaseResults.get("FEIN");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinNumber);
		System.out.println(feinNumber);
		Thread.sleep(2000);
		//commonFuntions.clickButtonContains(" Search ");
		commonFuntions.clickButton(" Search ");
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("PEO Member", "PASS", "Search For PEO Member");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
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
		Thread.sleep(2000);
		//commonFuntions.selectRadioQuestions("List the current address of each additional address the PEO Member maintains in New York", "Same As Physical Address");
		commonFuntions.selectRadio("Same As Physical Address");
		commonFuntions.screenShot("PEO member information", "Pass", "PEO Member Information");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.mailingAddress.click();
			commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			
		}
		Thread.sleep(5000);
		commonFuntions.screenShot("current additional address", "Pass", "Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Prior Address(es) in New York", "Pass", "Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Prior Address(es) in New York", "Pass", "Verify Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("List of members", "Pass", "List of Members of PEO Group");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("PEO Details", "Pass", "PEO Details Review screen");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
		commonFuntions.screenShot("Declaration", "Pass", "Declaration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Statement Of Acknowledgement", "Fail", "Incorrect screen ID - PEO-017");
		commonFuntions.clickButtonContains("Accept & Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Register/Renew Confirmation", "Pass", "Register/Renew Confirmation");
		commonFuntions.clickButtonContains("Home");
		Thread.sleep(200);
		
		
		
	
	}
}
