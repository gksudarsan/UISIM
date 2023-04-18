package com.employerContibution.EL;



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
public class EL_322_011 extends TestBase
{


	
	@Test(priority=1, description = "EL.322.011:  Verify CSR can register Individual PEO for Type of Legal Entity 'Limited Liability Partnership' and  type of Ownership 'Privately or Closely Held'.",groups = {"Regression"})
	public void EL_322_011() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		//commonFuntions.database_UpdateQuery("SELECT * FROM LROUIM.T_WFA_WORK_ITEM_DETAIL WHERE PROCESS_DETAIL_ID ='98742'");
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901228')");
		 test = report.createTest("EL.322.011:  Verify CSR can register Individual PEO for Type of Legal Entity 'Limited Liability Partnership' and  type of Ownership 'Privately or Closely Held'.");
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
	     PEOPage.individualPeo.click();	     
	     commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");	     
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     
	     
		 String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
	     commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", "Limited Liability Limited Partnership");
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
	     commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	     Thread.sleep(2000);
	     
	    PEOPage.queue.click();
	    Thread.sleep(15000);
	    commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Review Peo","Pass","Review Peo");
	    commonFuntions.clickOnLink("Review PEO");
	    Thread.sleep(2000);
	    commonFuntions.clickButtonContains("Open Work Item");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("Review","Pass","Review Peo Registration");
	    commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);	   
	     
	     commonFuntions.screenShot("GeneralInfo","Pass","General Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     try {
		     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}commonFuntions.screenShot("Insurance","Pass","UnemploymentInsuranceAccountDetails");
	     
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);	     
		     commonFuntions.screenShot("AddressInfo","Pass","Address Information");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     PEOPage.uspsAddress.click();
		     PEOPage.currentAdditionalAddress.click();
		     commonFuntions.screenShot("UspsAddress2","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyCurrentAdd","Pass","Verify Current Additional Address");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);	
		     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyPriorAdd","Pass","Verify Prior Address");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);	
		  /*   commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		     commonFuntions.enterTextboxContains("Ownership Percentage","40");
		     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		     commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		     commonFuntions.enterTextboxContains("City","NewYork");
		     commonFuntions.enterTextboxContains("Zip Code","13430");
		     commonFuntions.screenShot("OwnershipInformation2","Pass","Ownership Information - privately or closely held company");
		     
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);*/
		     commonFuntions.screenShot("VerifyOwnerInfo","Pass","Verify Owner Information");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);
		     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		     commonFuntions.screenShot("PriorOwner","Pass","Prior Owner Information");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     PEOPage.uspsAddress.click();
		     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();	
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyPrioerOwner","Pass","Verify Prior Ownership Information");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("Submission","Pass","Submission");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("UploadDocs","Pass","Upload Documents");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyClient","Pass","Verify Client List");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);		     
		     commonFuntions.screenShot("PeoDetails","Pass","Peo Details Review");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
		     commonFuntions.screenShot("Declaration2","Pass","Declaration");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);
		     
		     commonFuntions.selectRadio("Approved");
		     commonFuntions.screenShot("Approval","Pass","ApprovalPage");
		     commonFuntions.clickButtonContains("Submit");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("Success","Pass","SuccessPage");

	     
	   
	     
	     
	     
	}

}