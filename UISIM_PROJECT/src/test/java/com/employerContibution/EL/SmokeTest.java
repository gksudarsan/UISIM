package com.employerContibution.EL;

import org.apache.commons.lang3.StringUtils;
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
public class SmokeTest extends TestBase{

	@Test(priority=1, description = "EL.02.007 : Verify CSR can register  PEO Exempt for Type of Legal Entity 'Corporation'",groups = {"Regression"})
	public void EL_02_007() throws Exception{
		 test = report.createTest("EL.02.007 : Verify CSR can register  PEO Exempt for Type of Legal Entity 'Corporation'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.PEOSpecialist.toUpperCase(), COMMON_CONSTANT.PEOSpecialist_Password);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");		 
		 commonFuntions.clickMenu("Menu");
		 //PEOPage.menu.click();	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 applicationLoginResults = "Success";
				
		 PEOPage.menuPeo.click();	
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Register PEO");	
		 sleep(2000);
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 
		 Thread.sleep(3000);
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     PEOPage.peoExemptRegisterRadio.click();
	     commonFuntions.screenShot("EXEMPT", "Pass", "Selecting Exempt and filling the form");
	     commonFuntions.enterTextbox("Name of Professional Employer Organization", "Test_Data"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     commonFuntions.enterTextboxContains("Additional Name(s)", "Test_Data"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     commonFuntions.clickButtonContains("Save & Continue");
	     commonFuntions.screenShot("address", "Pass", "Address update");
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 System.out.println("feinValue is"+feinValue);
		 System.out.println("ernValue is"+ernValue);
		 commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
		 sleep(2000);
	     commonFuntions.selectDropdownUsingSearch("Type of Legal Entity", " Corporation ");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     Thread.sleep(2000);
	     commonFuntions.screenShot("PEOPage", "Pass", "Entering random FEIN and ERN values");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
	     sleep(1000);
	     commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alaska ");
	     commonFuntions.enterTextbox("State agency that issued it.", "New York");
	     commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
	     commonFuntions.enterTextbox("Registration Number ", "3458767985");
	     commonFuntions.screenShot("RegistrationNumber", "Pass", "Registration number");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(3000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     commonFuntions.screenShot("UnemplymentInsurance", "Pass", "Unemployment Insurance Account Details");
	     try {
	    	 PEOPage.peoRadioButton.click();
		     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     
	     PEOPage.addressLine1.sendKeys("addressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     PEOPage.addressLine2.sendKeys("addressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     sleep(2000);
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13420");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Phone Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),10));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),6)+"@gmail.com");
	     commonFuntions.screenShot("PrimaryAddress", "Pass", "Primary Physical address ");
	     commonFuntions.clickButtonContains("Save & Continue");
	     
	     Thread.sleep(4000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     PEOPage.sameAsPhysicalAddress.click();
	     commonFuntions.screenShot("Address6", "Pass", "Navigated to next page");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     commonFuntions.screenShot("ExemptionSubmission", "Pass", "Exemption Submission");
	     commonFuntions.clickButtonContains("Continue");
	    sleep(2000);
	    commonFuntions.waitForLoadingIconToDisappear();
	     commonFuntions.screenShot("Address2", "Pass", "Entering address 1&2");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     //commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)");
	     //commonFuntions.selectLink("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)", "Browse");
	     //Thread.sleep(2000);
	     //commonFuntions.uploadDoc("Sample.docx");
	     //commonFuntions.clickButtonContains("Upload");
	     Thread.sleep(2000);
	     //commonFuntions.screenShot("DocumentUpload", "Pass", "Upload document");
	     //commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.clickButtonContains("Choose File");
	     Thread.sleep(2000);
	     commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("UploadClient","Pass","Upload Client List");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.waitForLoadingIconToDisappear();
	    
	     
	     
	     
	     
	     Thread.sleep(4000);
	     
	     commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(4000);
	     commonFuntions.waitForLoadingIconToDisappear();
	     commonFuntions.screenShot("PeoReviewDetails","Pass","PEO Details Review");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	    
	    
	     commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
	     Thread.sleep(4000);
	     commonFuntions.screenShot("PeoDeclaration","Pass","PEO Declaration ");
	     commonFuntions.clickButtonContains("Save & Continue ");
	     Thread.sleep(3000);
	     
	     commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
	     commonFuntions.clickButton("Accept & Submit ");	
	     sleep(2000);
commonFuntions.screenShot("Completion","Pass","Register/Renew Confirmation");
RegistrationResults = "Success";
	    // commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	     Thread.sleep(30000);
	     
	    PEOPage.queue.click();
	    Thread.sleep(15000);
	    commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("ReviewPeo","Pass","Review Peo");
	    commonFuntions.clickOnLink("Review PEO");
	    Thread.sleep(2000);
	    WorkItemCreatedResults="Success";
	    commonFuntions.clickButtonContains("Open Work Item");
	    Thread.sleep(2000);
	    commonFuntions.screenShot("ReviewPeoRegstration","Pass","Review PEO Registration");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("GeneralInformation","Pass","General Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Information");
	     try {
	    	 PEOPage.peoRadioButton.click();
		     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("PrimaryAddress2","Pass","Primary Physical Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("ExemptionSubmission","Pass","Exemption Submission");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("UploadDoc","Pass","Upload Documents");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("VerifyClientList","Pass","Verify Client List");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("PeoDetails","Pass","Peo Details Review");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
	     Thread.sleep(4000);
	     commonFuntions.screenShot("PeoDeclaration2","Pass","PEO Declaration ");
	     commonFuntions.clickButtonContains("Save & Continue ");
	     Thread.sleep(3000);
	     commonFuntions.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(2000);	     
	     //commonFuntions.selectRadio("Approved");
	     PEOPage.ApprovePeo.click();
	     commonFuntions.screenShot("Approval","Pass","ApprovalPage");
	     commonFuntions.clickButtonContains("Submit");
	     Thread.sleep(2000);
	     commonFuntions.screenShot("Success","Pass","SuccessPage");
	     PeoIdGeneratedResults="Success";
	     PEOPage.successMessage.isDisplayed();
	     //commonFuntions.Label("PEO Registration Application has been Approved");
	     commonFuntions.logout(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	     
	}
}
