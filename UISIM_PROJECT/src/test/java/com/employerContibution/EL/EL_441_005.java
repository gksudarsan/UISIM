package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_005 extends TestBase{
	@Test(priority = 1, description = "EL_441_005_Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Limited Liability Partnership' and Type of Ownership 'Privately or Closely Held", groups = { "Regression" })
	public void Testing123() throws Exception {
		test = report.createTest("EL_441_005_Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Limited Liability Partnership' and Type of Ownership 'Privately or Closely Held");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage Home = new HomePage(driver);
		PEOPage peoPage = new PEOPage(driver);
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 System.out.println("feinValue is"+feinValue);
		 System.out.println("ernValue is"+ernValue);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration peoEmployer = new PEO_001_ProfessionalEmployerOrganizationRegistration(driver);
		
		//Login
		commonFunction.login(COMMON_CONSTANT.PEO_USER_1.toUpperCase(), COMMON_CONSTANT.PEO_USER_1_PASSWORD);
		sleep(200);
		commonFunction.screenShot("Home", "Pass", "Login is successful");
		Home.navigateToPeoRegister();
		commonFunction.enterTextboxContains("First Name", "Joseph");
		commonFunction.enterTextboxContains("Last Name", "Moral");
		commonFunction.selectDropdown("Suffix", "Sr.");
		commonFunction.screenShot("Peo Registration", "Pass", "Details entered on PEO-019 page");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Number ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		//commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address",
				"randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("Peo Registration", "Pass", "Details entered on PEO-019 page");
		commonFunction.clickButton("Save & Continue ");
		sleep(2000);
		commonFunction.screenShot("Professional Employer Organization Registration","Pass"," Successfully landed on SREG 002");
		//commonFunction.clickButtonContains(xpathParameter);
		PEOPage.groupRegPeo.click();
		commonFunction.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFunction.createRandomInteger(1000,9999));
		commonFunction.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFunction.createRandomInteger(1000,9999));
		commonFunction.screenShot("peor", "Pass", "Professional Employer Organization Registration");
		commonFunction.clickButtonContains("Save & Continue");
	     sleep(2000);
		/*
		 * commonFunction.selectRadioQuestions(xpathQuestions, xpathParameter);
		 * commonFunction.selectRadio(xpathParameter);
		 */
	     commonFunction.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     commonFunction.enterTextboxContains("Employer Registration Number", ernValue);
	     commonFunction.selectDropdown("Type of Legal Entity", "Limited Liability Limited Partnership");
	     commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     commonFunction.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
	     commonFunction.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
	     commonFunction.screenShot("GI", "PASS", "General Information");
	     commonFunction.clickButtonContains("Save & Continue");
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
	     //PEOPage.uspsSuggestedAddress.click();
	     PEOPage.CurrentUspsAddress.click();
	     PEOPage.mailingUspsAddress.click();
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
	     commonFuntions.selectLink("Proof of NYS Workers", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");	     
	     sleep(2000);
	     commonFuntions.selectLink("Affirmation from an independent", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("A blank client contract incorporating", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("Sample of a written notice to worksite", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
		 //commonFuntions.clickButtonContains("Upload");
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
	     
		 // blocked the test case at step26 as not able to search with fein number for peo member (getting PEO review instead of PEO member screen)
	     
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC","FEIN");
			
			String feinNumber=databaseResults.get("FEIN");
			commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinNumber);
			System.out.println(feinNumber);
			sleep(2000);
			//commonFuntions.clickButtonContains(" Search ");
			/*commonFuntions.clickButton(" Search ");
			commonFuntions.selectRadio("Select");
			commonFuntions.screenShot("PEO Member", "PASS", "Search For PEO Member");
			commonFuntions.clickButtonContains("Continue ");*/
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
			sleep(1000);
			commonFuntions.selectDropdown("Country", "United States");
			sleep(1000);
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
			sleep(2000);
			commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		     sleep(2000);
		     commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		     sleep(2000);
			    PEOPage.queue.click();
			    sleep(2000);
		    //sleep(15000);
			    commonFuntions.waitForLoadingIconToDisappear();
		    commonFuntions.enterTextboxContains("FEIN",feinValue);
		    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		    commonFuntions.clickButtonContains("Search");
		    sleep(2000);
		    commonFuntions.screenShot("Review Peo","Pass","Review Peo");
		    commonFuntions.clickOnLink("Review PEO");
		    sleep(2000);
		    commonFuntions.clickButtonContains("Open Work Item");
		    sleep(2000);
		    commonFuntions.screenShot("Review","Pass","Review Peo Registration");
		    commonFuntions.clickButtonContains("Continue");
		     sleep(2000);	   
		     
		     commonFuntions.screenShot("GeneralInfo","Pass","General Information");
		     commonFuntions.clickButtonContains("Save & Continue");
		     sleep(2000);
		     try {
		    	 PEOPage.peoRadioButton.click();
			     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
			     }
			     catch(Exception e) {}commonFuntions.screenShot("Insurance","Pass","UnemploymentInsuranceAccountDetails");
		     
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);	     
			     commonFuntions.screenShot("AddressInfo","Pass","Address Information");//addr1
			     commonFuntions.enterTextboxContains("Address Line 1","AddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);
			     PEOPage.uspsAddress.click();
			     PEOPage.currentAdditionalAddress.click();
			     commonFuntions.screenShot("UspsAddress2","Pass","UspsAddress");
			     PEOPage.UspsContinueButton.click();
			     sleep(2000);
			     commonFuntions.screenShot("VerifyCurrentAdd","Pass","Verify Current Additional Address");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);	
			     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");//addr1
			     commonFuntions.enterTextboxContains("Address Line 1","MailingaddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);//usps
			     try {
			    	 PEOPage.uspsSuggestedAddress.click();
				     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
				     PEOPage.UspsContinueButton.click();	
				     sleep(2000);
			     }catch(Exception e){}
			     commonFuntions.screenShot("VerifyPriorAdd","Pass","Verify Prior Address");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);	
			     
			     
			     commonFuntions.screenShot("VerifyOwnerInfo","Pass","Verify Owner Information");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);
			     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.screenShot("PriorOwner","Pass","Prior Owner Information");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);
			     try {
			     PEOPage.uspsAddress.click();
			     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
			     PEOPage.UspsContinueButton.click();	
			     sleep(2000);
			     }
			     catch(Exception e) {}
			     commonFuntions.screenShot("VerifyPrioerOwner","Pass","Verify Prior Ownership Information");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);
			     commonFuntions.screenShot("Submission","Pass","Submission");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);
			     commonFuntions.screenShot("UploadDocs","Pass","Upload Documents");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);
			     commonFuntions.screenShot("VerifyClient","Pass","Verify Client List");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);		     
			     commonFuntions.screenShot("MembersPeo","Pass","List Of Members Peo");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(2000);
			     commonFuntions.screenShot("PeoReview","Pass","Peo Details Review");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);
			   //  commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
			     commonFuntions.screenShot("Declaration2","Pass","Declaration");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(2000);
			     commonFuntions.screenShot("Statement Of Acknowledgement", "Pass", "Acknowledgement");
					commonFuntions.clickButtonContains("Accept & Submit");
					sleep(2000);
			     //commonFuntions.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
			     //commonFuntions.clickButtonContains("Continue");
			     //sleep(2000);
			     
			     commonFuntions.selectRadio("Approved");
			     commonFuntions.screenShot("ApprovalPage","Pass","ApprovalPage");
			     commonFuntions.clickButtonContains("Submit");
			     sleep(5000);
			     commonFuntions.screenShot("Success","Pass","SuccessPage");
			     commonFuntions.logout(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
                System.out.println("Passed");		


	}		
	}
