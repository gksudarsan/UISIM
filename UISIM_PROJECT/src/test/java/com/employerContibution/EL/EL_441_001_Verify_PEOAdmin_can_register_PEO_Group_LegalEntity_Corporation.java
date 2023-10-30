package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_001_Verify_PEOAdmin_can_register_PEO_Group_LegalEntity_Corporation extends TestBase{


	@Test(priority=1, description = "EL.441.001  - Verify Peo Admin can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Privately or Closely Held'.",groups = {"Regression"})
	public void EL_441_001() throws Exception
	{
		
		 test = report.createTest("EL.441.001  - Verify Peo Admin can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Privately or Closely Held'.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 HomePage HomePage = PageFactory.initElements(driver, HomePage.class);
		 String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 System.out.println("feinValue is"+feinValue);
		 System.out.println("ernValue is"+ernValue);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		// Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TWAGE10 tt JOIN T_TWAGET20 t20 ON t20.T20_FEIN =tt.T10_FEIN JOIN T_EMPLOYER_ACCOUNT emp ON emp.FEIN  = tt.T10_FEIN JOIN  T_THIRD_PARTY_AGENT ttpa  ON TTPA.FEIN = emp.FEIN WHERE tt.T10_WAGE_1 >0","T10_SSN"); 
		 //String SSN=databaseResults1.get("T10_SSN");
		
		 commonFuntions.loginPeoAdmin("peouser","Admin@12345678");
		 //commonFuntions.loginPeoAdmin(COMMON_CONSTANT.PEOAdmin.toUpperCase(), COMMON_CONSTANT.PEOAdmin_Password);
		 sleep(3000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 PEOPage.peoRegister.click();
		 sleep(3000);
		 commonFuntions.waitForLoadingIconToDisappear();
		/* commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 PEOPage.menuPeo.click();	
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Register PEO");		*/	 
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 Thread.sleep(3000);
	    
		 
		 
		 
		 commonFuntions.enterTextboxContains("First Name", "AutoTestFirstName"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));		 
		 commonFuntions.enterTextboxContains("Last Name", "AutoTestLastName"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		 commonFuntions.enterTextboxContains("Job Title", "Auditor");
		 commonFuntions.enterTextboxContains("Contact Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),10));
		 commonFuntions.enterTextboxContains("Email Address","autoEmail"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),4)+"@gmail.com");
		 
		 
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 sleep(3000);
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     PEOPage.groupRegPeo.click();
		 commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Additional Name(s)","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.screenShot("peor", "Pass", "Professional Employer Organization Registration");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
	     commonFuntions.screenShot("GI", "PASS", "General Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     //commonFuntions.selectRadio("Select");
	     try {
	    	 PEOPage.peoRadioButton.click();
		     commonFuntions.selectRadioInTable(ernValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
	     commonFuntions.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
	     commonFuntions.clickButtonContains("Save & Continue"); 

	     sleep(3000);
	     commonFuntions.waitForLoadingIconToDisappear();

	     sleep(5000);

	     PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13420");
	     sleep(2000);
	     PEOPage.PeoCountry.click();
	   
	     commonFuntions.selectFromDropdown("United States");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
	     commonFuntions.screenShot("Address Information", "PASS", "Address Information");
	     commonFuntions.clickButtonContains("Save & Continue");

	     sleep(2000);
	     try {

	     sleep(5000);

	     PEOPage.uspsAddress.click();
	     sleep(5000);
	     PEOPage.currentAdditionalAddress.click();
	     sleep(5000);
	     commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();

	     sleep(2000);}
	     catch(Exception e) {}

	     sleep(5000);

	     commonFuntions.screenShot("Verify Current Additional Address", "PASS", "Verify Current Additional Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(5000);
	    // PEOPage.addressLine1.sendKeys("mailingAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.sameAsPhysicalAddress.click();
	     commonFuntions.screenShot("Mailing Address", "PASS", "Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     try {
	     //PEOPage.uspsSuggestedAddress.click();
	     PEOPage.CurrentUspsAddress.click();
	     PEOPage.mailingUspsAddress.click();
	     commonFuntions.screenShot("VerifyContactDetails","Pass","UspsAddress");
	    
	     PEOPage.UspsContinueButton.click();
	     sleep(5000);
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
	        

	     sleep(5000);

	     commonFuntions.screenShot("Verify Prior Address(es) in New York", "PASS", "Verify Prior Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(5000);
	     commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
	     commonFuntions.enterTextboxContains("Ownership Percentage","40");
	     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     try {
		     PEOPage.uspsAddress.click();
		     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();
		     sleep(2000);}
		     catch(Exception e) {}
	     sleep(5000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(5000);
	     commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
	     commonFuntions.enterTextboxContains("Ownership Percentage","60");
	     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(5000);
	     PEOPage.uspsAddress.click();
	     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();	    
	     sleep(7000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(5000);
	     commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(5000);	     
	     commonFuntions.selectLink("Proof of NYS Workers", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(5000);
	     commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(5000);
	     commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");	     
	     sleep(5000);
	     commonFuntions.selectLink("Affirmation from an independent", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(5000);
	     commonFuntions.selectLink("A blank client contract incorporating", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(5000);
	     commonFuntions.selectLink("Sample of a written notice to worksite", "Browse");
	     sleep(5000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(5000);
		 //commonFuntions.clickButtonContains("Upload");
		 sleep(5000);
		 commonFuntions.screenShot("Upload Documents", "PASS", "Upload Documents");
		 commonFuntions.clickButtonContains("Save & Continue");
		 sleep(5000);
		 commonFuntions.clickButtonContains("Choose File");
		 sleep(5000);
		 commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		 sleep(5000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("Upload Client List", "PASS", "Upload Client List");
		 commonFuntions.clickButtonContains("Continue");
		 sleep(5000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("Verify Client List", "PASS", "client list");
		 commonFuntions.clickButtonContains("Continue");
		 sleep(5000);
		 commonFuntions.waitForLoadingIconToDisappear();
	     
		 // blocked the test case at step26 as not able to search with fein number for peo member (getting PEO review instead of PEO member screen)
	     
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT Fein,* FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND LENGTH(fein)>8","FEIN");
		 //SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI'  AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC
			
			String feinNumber=databaseResults.get("FEIN");
			commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinNumber);
			System.out.println(feinNumber);
			sleep(5000);
			//commonFuntions.clickButtonContains(" Search ");
			/*commonFuntions.clickButton(" Search ");
			commonFuntions.selectRadio("Select");
			commonFuntions.screenShot("PEO Member", "PASS", "Search For PEO Member");
			commonFuntions.clickButtonContains("Continue ");*/
			sleep(5000);
			commonFuntions.enterTextbox("PEO Member Name","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
			
			//Random fein is not working - only existing fein is working from database
			//commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
			
			commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
			//commonFuntions.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "No");
			commonFuntions.selectRadioQuestions("Does this PEO member already have  a New York State Unemployment Insurance Account? and New York State Employer Registration Number", "No");
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
			sleep(5000);
			//commonFuntions.selectRadioQuestions("List the current address of each additional address the PEO Member maintains in New York", "Same As Physical Address");
			//commonFuntions.selectRadio("Same As Physical Address");
			//PEOPage.SameAsPhysicalAddress.click();
			//PEOPage.SameAsPhysicalAddress.click();
			
			PEOPage.PeoAddress1.sendKeys("currentAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			PEOPage.PeoCity1.sendKeys("NewYork");
			PEOPage.PeoZipCode.sendKeys("13430");
			/*PEOPage.peoCountry.click();
	        sleep();
	        PEOPage.peoCountryvalue.click();
			sleep(4000);*/
			commonFuntions.screenShot("PEO member information", "Pass", "PEO Member Information");
			sleep(5000);
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(5000);
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
			sleep(5000);
			commonFuntions.screenShot("Prior Address(es) in New York", "Pass", "Prior Address(es) in New York");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(5000);
			commonFuntions.screenShot("Verify Prior Address(es) in New York", "Pass", "Verify Prior Address(es) in New York");
			commonFuntions.clickButtonContains("Continue");
			sleep(5000);
			commonFuntions.clickButtonContains("Choose File");
			sleep(5000);
			commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
			sleep(4000);
			commonFuntions.clickButtonContains("Continue");
			sleep(5000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
			commonFuntions.clickButtonContains("Continue");
			sleep(5000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("List of members", "Pass", "List of Members of PEO Group");
			commonFuntions.clickButtonContains("Continue");
			sleep(5000);
			commonFuntions.screenShot("PEO Details", "Pass", "PEO Details Review screen");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(5000);
			//commonFuntions.enterTextbox("Enter name of Officer","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
			commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
			   
			commonFuntions.screenShot("Declaration", "Pass", "Declaration");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(5000);
			commonFuntions.screenShot("Statement Of Acknowledgement", "Pass", "Incorrect screen ID - PEO-017");
			commonFuntions.clickButtonContains("Accept & Submit");
			sleep(5000);
			commonFuntions.screenShot("RegisterRenewConfirmation", "Pass", "Register/Renew Confirmation");
			//commonFuntions.clickButtonContains("Home");
			sleep(5000);
			//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		     sleep(30000);
		     //commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		     commonFuntions.logoutAndLogin(COMMON_CONSTANT.PEOSpecialist.toUpperCase(), COMMON_CONSTANT.PEOSpecialist_Password);

		     sleep(4000);

		     sleep(5000);

			    PEOPage.queue.click();
			    //sleep(5000);
		    sleep(15000);
			    commonFuntions.waitForLoadingIconToDisappear();
		    commonFuntions.enterTextboxContains("FEIN",feinValue);
		    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		    commonFuntions.clickButtonContains("Search");
		    sleep(5000);
		    commonFuntions.screenShot("Review Peo","Pass","Review Peo");
		    commonFuntions.clickOnLink("Review PEO");
		    sleep(5000);
		    commonFuntions.clickButtonContains("Open Work Item");
		    sleep(5000);
		    commonFuntions.screenShot("Review","Pass","Review Peo Registration");
		    commonFuntions.enterTextboxContains("Additional names","auto_test"+commonFuntions.createRandomInteger(1000,9999));
		    commonFuntions.clickButtonContains("Continue");
		     sleep(5000);	   
		     
		     commonFuntions.screenShot("GeneralInfo","Pass","General Information");
		     commonFuntions.clickButtonContains("Save & Continue");
		     sleep(5000);
		     try {
		    	 PEOPage.peoRadioButton.click();
			     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
			     }
			     catch(Exception e) {}commonFuntions.screenShot("Insurance","Pass","UnemploymentInsuranceAccountDetails");
		     
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);	     
			     commonFuntions.screenShot("AddressInfo","Pass","Address Information");//addr1
			     commonFuntions.enterTextboxContains("Address Line 1","AddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);
			     try {
			     PEOPage.uspsAddress.click();
			     PEOPage.currentAdditionalAddress.click();
			     commonFuntions.screenShot("UspsAddress2","Pass","UspsAddress");
			     PEOPage.UspsContinueButton.click();
			     sleep(5000);}
			     catch(Exception e) {}
			     commonFuntions.screenShot("VerifyCurrentAdd","Pass","Verify Current Additional Address");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);	
			     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");//addr1
			     commonFuntions.enterTextboxContains("Address Line 1","MailingaddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);//usps
			     try {
			    	 PEOPage.uspsSuggestedAddress.click();
				     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
				     PEOPage.UspsContinueButton.click();	
				     sleep(5000);
			     }catch(Exception e){}
			     commonFuntions.screenShot("VerifyPriorAdd","Pass","Verify Prior Address");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);	
			     
			     
			     commonFuntions.screenShot("VerifyOwnerInfo","Pass","Verify Owner Information");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);
			     /*commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
			     commonFuntions.screenShot("PriorOwner","Pass","Prior Owner Information");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);
			     try {
			     PEOPage.uspsAddress.click();
			     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
			     PEOPage.UspsContinueButton.click();	
			     sleep(5000);
			     }
			     catch(Exception e) {}*/
			     commonFuntions.screenShot("VerifyPrioerOwner","Pass","Verify Prior Ownership Information");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);
			     commonFuntions.screenShot("Submission","Pass","Submission");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);
			     commonFuntions.screenShot("UploadDocs","Pass","Upload Documents");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);
			     commonFuntions.screenShot("VerifyClient","Pass","Verify Client List");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);		     
			     commonFuntions.screenShot("MembersPeo","Pass","List Of Members Peo");
			     commonFuntions.clickButtonContains("Continue");
			     sleep(5000);
			     commonFuntions.screenShot("PeoReview","Pass","Peo Details Review");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);
			     commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
			     commonFuntions.screenShot("Declaration2","Pass","Declaration");
			     commonFuntions.clickButtonContains("Save & Continue");
			     sleep(5000);
			     commonFuntions.screenShot("Statement Of Acknowledgement", "Pass", "Acknowledgement");
					commonFuntions.clickButtonContains("Accept & Submit");
					sleep(5000);
			     //commonFuntions.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
			     //commonFuntions.clickButtonContains("Continue");
			     //sleep(5000);
					PEOPage.ApprovePeo.click();
					//PEOPage.ApprovePeo.click();
			     //commonFuntions.selectRadio("Approved");
			     commonFuntions.screenShot("ApprovalPage","Pass","ApprovalPage");
			     commonFuntions.clickButtonContains("Submit");
			     sleep(5000);
			     commonFuntions.screenShot("Success","Pass","SuccessPage");
			     PEOPage.successMessage.isDisplayed();
			     //commonFuntions.Label("PEO Registration Application has been Approved");
			     commonFuntions.logout(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);

		


	}
}
