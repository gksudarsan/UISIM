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
public class EL_02_006_PEOAdmin_Can_Register_PEOExempt_Others extends TestBase{

	@Test(priority=1, description = "EL.02.006 : Verify PEO Admin can register  PEO Exempt for Type of Legal Entity ' Other' .",groups = {"Regression"})
	public void EL_02_006() throws Exception{
		 test = report.createTest("EL.02.006 : Verify PEO Admin can register  PEO Exempt for Type of Legal Entity ' Other' .");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.loginPeoAdmin("peouser","Admin@12345678");
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 PEOPage.peoRegister.click();
		 sleep(3000);
		 commonFuntions.enterTextboxContains("First Name", "AutoTestFirstName"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));		 
		 commonFuntions.enterTextboxContains("Last Name", "AutoTestLastName"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		 commonFuntions.enterTextboxContains("Job Title", "Auditor");
		 commonFuntions.enterTextboxContains("Contact Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),10));
		 commonFuntions.enterTextboxContains("Email Address","autoEmail"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),4)+"@gmail.com");
		 
		 
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 sleep(3000);
	     commonFuntions.clickButtonContains("Save & Continue");
	     PEOPage.peoExemptRegisterRadio.click();
	     commonFuntions.screenShot("EXEMPT", "Pass", "Selecting Exempt and filling the form");
	     commonFuntions.enterTextbox("Name of Professional Employer Organization", "Test_Data"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     commonFuntions.enterTextboxContains("Additional Names, if any, under", "Test_Data"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     commonFuntions.clickButtonContains("Save & Continue");
	     commonFuntions.screenShot("address", "Pass", "Address update");
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		 System.out.println("feinValue is"+feinValue);
		 System.out.println("ernValue is"+ernValue);
		 commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
		 commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     sleep(2000);
	     commonFuntions.screenShot("PEOPage", "Pass", "Entering random FEIN and ERN values");	    
	     commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
	     commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
	     commonFuntions.enterTextbox("State agency that issued it.", "New York");
	     commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
	     commonFuntions.enterTextbox("Registration Number ", "3458767985");
	     commonFuntions.screenShot("RegistrationNumber", "Pass", "Registration number");
	     commonFuntions.clickButtonContains("Save & Continue");
	     if (driver.findElement(By.xpath("//mat-error[text()=' Required ']")).isDisplayed()) {
				commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
				commonFuntions.clickButtonContains("Save & Continue");
			}
			sleep(3000);
	     
	     
	     /*------------- PEO_003 ------------------*/
	     
	     PEOPage.addressLine1.sendKeys("addressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     PEOPage.addressLine2.sendKeys("addressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13420");
	     commonFuntions.enterTextboxContains("Phone Number",StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,11))),10));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),6)+"@gmail.com");
	     commonFuntions.screenShot("PrimaryAddress", "Pass", "Primary Physical address ");
	     commonFuntions.clickButtonContains("Save & Continue");
	     /*------------ PEO_004 ------------- */
	     sleep(4000);
	     commonFuntions.screenShot("Address6", "Pass", "Navigated to next page");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(4000);
	     /* ------------------- PEO_004 --------------E*/
	     commonFuntions.screenShot("ExemptionSubmission", "Pass", "Exemption Submission");
	     commonFuntions.clickButtonContains("Continue");
	     /* ----------------- SREG_006 --------------*/
	     commonFuntions.screenShot("Address2", "Pass", "Entering address 1&2");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(4000);
	     /*Upload client list page --- Upload Document---- */
	     //commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)");
	     commonFuntions.selectLink("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("Copy of PEO Registration", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.selectLink("Proof of Exemption from", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");	     
	     sleep(2000);
	     commonFuntions.selectLink("A blank client contract", "Browse");
	     sleep(2000);
	     commonFuntions.uploadDoc("Sample.docx");
	     sleep(2000);
	     commonFuntions.clickButtonContains("Upload");
	     sleep(15000);
	     commonFuntions.screenShot("DocumentUpload", "Pass", "Upload document");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.clickButtonContains("Choose File");
	     sleep(2000);
	     commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
	     sleep(2000);
	     commonFuntions.screenShot("UploadClient","Pass","Upload Client List");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	    
	     
	     
	     
	     /*Upload client list page --- Upload Document---- */
	     sleep(4000);
	     /*-------------------- LEAS_012 ------------------*/
	     commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(4000);
	     /*-------------------- PEOR_001 ------------------*/
	     //Blocked the testcase - getting PEO Member information instead of PEO Review screen
	     commonFuntions.screenShot("PeoReviewDetails","Pass","PEO Details Review");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(4000);
	     /*-------------------- PEOE_005 ------------------*/
	    
	     commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
	     sleep(4000);
	     commonFuntions.screenShot("PeoDeclaration","Pass","PEO Declaration ");
	     commonFuntions.clickButtonContains("Save & Continue ");
	     sleep(3000);
	     /*-------------------- PEO_017 ------------------*/
	     commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
	     commonFuntions.clickButton("Accept & Submit ");	
	     
commonFuntions.screenShot("Completion","Pass","Register/Renew Confirmation");
	     
	     commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	     sleep(2000);
	     commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	    PEOPage.queue.click();
	    sleep(15000);
	    commonFuntions.enterTextboxContains("FEIN",feinValue);
	    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
	    commonFuntions.clickButtonContains("Search");
	    sleep(2000);
	    commonFuntions.screenShot("Review Peo","Pass","Review Peo");
	    commonFuntions.clickOnLink("Review PEO");
	    sleep(2000);
	    commonFuntions.clickButtonContains("Open Work Item");
	    sleep(2000);
	    commonFuntions.screenShot("ReviewPeoRegstration","Pass","Review PEO Registration");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.screenShot("GeneralInformation","Pass","General Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Information");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("PrimaryAddress2","Pass","Primary Physical Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("ExemptionSubmission","Pass","Exemption Submission");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.screenShot("UploadDoc","Pass","Upload Documents");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.screenShot("VerifyClientList","Pass","Verify Client List");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.screenShot("PeoDetails","Pass","Peo Details Review");
	     commonFuntions.clickButtonContains("Save & Continue");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
	     sleep(4000);
	     commonFuntions.screenShot("PeoDeclaration2","Pass","PEO Declaration ");
	     commonFuntions.clickButtonContains("Save & Continue ");
	     sleep(3000);
	     commonFuntions.screenShot("StatementAckn","Pass","Statment of Acknowledgment");
	     commonFuntions.clickButtonContains("Continue");
	     sleep(2000);	     
	     commonFuntions.selectRadio("Approved");
	     commonFuntions.screenShot("Approval","Pass","ApprovalPage");
	     commonFuntions.clickButtonContains("Submit");
	     sleep(2000);
	     commonFuntions.screenShot("Success","Pass","SuccessPage");
     
	     
	}
}
