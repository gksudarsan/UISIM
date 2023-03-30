package com.employerContibution.EL;



import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_322_010 extends TestBase
{

	
	@Test(priority=1, description = "EL.322.010: Verify CSR can register Individual PEO for Type of Legal Entity 'Limited Liability Company' and  type of Ownership 'Privately or Closely Held'",groups = {"Regression"})
	public void EL_322_010() throws Exception
	{
		 
		 test = report.createTest("EL.322.010: Verify CSR can register Individual PEO for Type of Legal Entity 'Limited Liability Company' and  type of Ownership 'Privately or Closely Held'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 PEOPage.menuPeo.click();	
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Register PEO");			 
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");		
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     PEOPage.individualPeo.click();	     
	     commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");	     
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     long number = commonFuntions.createRandomInteger(10000,99999);
		 String ernValue="78"+Long.toString(number);
		 String feinValue=Long.toString( commonFuntions.createRandomInteger(100000000,999999999));
	     commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", "Limited Liability Company (All Types)");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
	     commonFuntions.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Account Details");
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
	     commonFuntions.screenShot("CurrentAdditionalAddress","Pass","Verify Current Additional Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Mailing Address","Pass","Mailing Address");
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
	     commonFuntions.screenShot("verifyPriorAddress","Pass","Verify Prior Address(es) in New York");
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
	     commonFuntions.selectCheckbox("Proof of NYS Workers");
	     commonFuntions.selectLink("Proof of NYS Workers", "Browse");
	     Thread.sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
	     commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
	     Thread.sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.selectCheckbox("Proof of $75k net worth or bond or a letter of credit for $75k");
	     commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
	     Thread.sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.clickButtonContains("Upload");
	     Thread.sleep(2000);
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.clickButtonContains("Choose File");
	     Thread.sleep(2000);
	     commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
	     Thread.sleep(2000);
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("peoDetails","Pass","Peo Details Review");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
	     commonFuntions.screenShot("Declaration","Pass","Declaration");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Acknowledgement","Pass","Statement Of Acknowledgement");
	     commonFuntions.clickButtonContains("Accept & Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Completion","Pass","Register/Renew Confirmation");
	     
	   
	     
	     
	     
	}

}