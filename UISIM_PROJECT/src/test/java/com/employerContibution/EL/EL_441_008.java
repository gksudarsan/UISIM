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
public class EL_441_008 extends TestBase{

	@Test(priority=1, description = "EL.441.008: Verify CSR can register PEO Group  for Type of Legal Entity 'Sole Proprietorship' and Type of Ownership 'Privately or Closely Held'.",groups = {"Regression"})
	public void EL_441_008() throws Exception

	{

		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		//commonFuntions.database_UpdateQuery("SELECT * FROM LROUIM.T_WFA_WORK_ITEM_DETAIL WHERE PROCESS_DETAIL_ID ='98742'");
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL "+COMMON_CONSTANT.CSR_USER_1+" WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901228')");
		test = report.createTest("EL.441.008: Verify CSR can register PEO Group  for Type of Legal Entity 'Sole Proprietorship' and Type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
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
		PEOPage.groupRegPeo.click();
		commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
	    commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	    commonFuntions.screenShot("groupRegistrationPeo","Pass","Professional Employer Organization Registration");
	    commonFuntions.clickButtonContains("Save & Continue");
	    Thread.sleep(2000);
	    commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	    long number = commonFuntions.createRandomInteger(10000,99999);
		String ernValue="78"+Long.toString(number);
		String feinValue=Long.toString( commonFuntions.createRandomInteger(100000000,999999999));
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Sole Proprietorship (Individual)");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
		commonFuntions.screenShot("groupRegistrationPeo","Pass","Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
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
	    commonFuntions.screenShot("VerifyContactDetail","Pass","UspsAddress");
	    PEOPage.UspsContinueButton.click();
	    
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Current Additional Address", "PASS", "Verify Current Additional Address(es) in New York");
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
	    commonFuntions.screenShot("Verify Prior Details", "PASS", "Verify Prior Address(es) in New York");
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
	    commonFuntions.screenShot("Verify Ownership Information", "PASS", "Verify Ownership Information");
	    commonFuntions.clickButtonContains("Continue");
	    Thread.sleep(2000);
	    commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
	    commonFuntions.enterTextboxContains("Ownership Percentage","60");
	    commonFuntions.enterTextboxContains("Address Line 1","PriorOwneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	    commonFuntions.enterTextboxContains("Address Line 2","PriorOwneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	    commonFuntions.enterTextboxContains("City","NewYork");
	    commonFuntions.enterTextboxContains("Zip Code","13430");
	    commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
	    commonFuntions.clickButtonContains("Save & Continue");
	    Thread.sleep(2000);
	    PEOPage.uspsAddress.click();
	    commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	    PEOPage.UspsContinueButton.click();	    
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Verify Prior Ownership Information", "PASS", "Ownership Information");
	    commonFuntions.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Submission Instructions and Responsibilities", "PASS", "Submission Instructions and Responsibilities");
	    commonFuntions.clickButtonContains("Continue");
	    Thread.sleep(2000);
	    commonFuntions.selectCheckbox("Proof of NYS Workers' Compensation Coverage");
	    commonFuntions.selectLink("Proof of NYS Workers' Compensation Coverage", "Browse");
	    commonFuntions.uploadDoc("TESTINGEL.docx");
	    Thread.sleep(4000);
		//commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
		//commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
		//commonFuntions.uploadDoc("TESTINGEL.docx\"");
		//Thread.sleep(3000);
	    commonFuntions.clickButtonContains("Upload");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Upload Documents", "PASS", "Upload Documents");
	    commonFuntions.clickButtonContains("Save & Continue");
	    Thread.sleep(2000);
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
	    
	 // blocked the test case at step26 as not able to search with fein number for peo member 
	    
	     
		
		
	}
}