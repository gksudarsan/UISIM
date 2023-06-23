package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_02_012_CSR_Can_Register_Others extends TestBase {

	@Test(priority = 1, description = "EL.02.012 : Verify CSR can register  PEO Exempt for Type of Legal Entity ' Limited Liability Partnership'", groups = {
			"Regression" })
	public void EL_02_012() throws Exception {

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

//		Map<String, String> databaseResults = PEOPage.database_SelectQuery(
//				"SELECT FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS desc");
//	
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);

		test = report.createTest(
				"EL.02.012 : Verify CSR can register  PEO Exempt for Type of Legal Entity ' Limited Liability Partnership'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(Status.INFO, "FEIN : : "+feinValue);
		test.log(Status.INFO, "ERN : : "+ernValue);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		PEOPage.peoExemptRegisterRadio.click();
		commonFuntions.screenShot("EXEMPT", "Pass", "Selecting Exempt and filling the form");
		commonFuntions.enterTextbox("Name of Professional Employer Organization",
				"Test_Data1" + commonFuntions.createRandomInteger(1000, 99999));
		sleep(2000);
		commonFuntions.enterTextboxContains("Additional name(s), if any, under which the PEO currently conduct",
				"Test_Data1" + commonFuntions.createRandomInteger(1000, 99999));
		commonFuntions.clickButtonContains("Save & Continue");
		commonFuntions.screenShot("address", "Pass", "Address update");
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		
		/*---------------------PEOE-002--------------*/
//		long number = commonFuntions.createRandomInteger(10000, 99999);
//		String ernValue = "12" + Long.toString(number);
//		String feinValue = Long.toString(commonFuntions.createRandomInteger(100000000, 999999999));
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		Thread.sleep(2000);
		commonFuntions.screenShot("PEOPage", "Pass", "Entering random FEIN and ERN values");
		sleep(2000);
		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
		commonFuntions.enterTextboxContains("Fiscal Year Start Dat", "02/01/2023");
		sleep(2000);
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
		commonFuntions.enterTextbox("State agency that issued it.", "New York");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		sleep(2000);
		String ernValueNew=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		commonFuntions.enterTextbox("Registration Number ", ernValueNew);
		sleep(4000);
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(2000);
		if (driver.findElement(By.xpath("//mat-error[text()=' Required ']")).isDisplayed()) {
			sleep();
			commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
			commonFuntions.clickButtonContains("Save & Continue");
			sleep(3000);
		}
		/*-------------EAS-001-------------*/
		sleep(3000);
		commonFuntions.clickButtonContains("Save & Continue");
		try {
			commonFuntions.clickButton(" Yes ");
			sleep();
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		/*--------------PEO-003-------------*/
		Thread.sleep(5000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(4000);
		commonFuntions.screenShot("Address2", "Pass", "Entering address 1&2");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 2 ", "Fake Address 2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City ","New York" );
		commonFuntions.enterTextboxContains("Zip Code", "12343");
		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		sleep(2000);
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(4000);
		try {
			WebElement radio = driver.findElement(By.xpath("//strong[text()='USPS Suggested Address']/../following-sibling::div/mat-radio-button/label/span/span[@class='mat-radio-outer-circle']"));
			commonFuntions.safeJavaScriptClick(radio);
			sleep();
			WebElement continuE = driver.findElement(By.xpath("//span[text()='Continue ']"));
			commonFuntions.safeJavaScriptClick(continuE);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(4000);
		commonFuntions.screenShot("Address6", "Pass", "Navigated to next page");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(6000);
		/*----------------PEOE-004--------------*/
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		/*----------------SREG-004-------------*/
		/* Upload files here */
//		Thread.sleep(3000);
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)");

		Thread.sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
		Thread.sleep(4000);
//		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();

		/* Upload files here */
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(4000);
		/*----------------PEO-015--------------*/
		commonFuntions.waitForLoadingIconToDisappear();
//		WebElement chooseFile = driver.findElement(By.xpath("//span[text()='Choose File']"));
//		commonFuntions.safeJavaScriptClick(chooseFile);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.uploadDoc("PEO Client List template_TestData2");
		/* Upload client list page --- Upload Document---- */
		Thread.sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		/*--------------LEAS-012-------------*/
		commonFuntions.waitForLoadingIconToDisappear();
//		commonFuntions.clickButtonContains("Save & Continue");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*------------PEOR-001--------------*/
		commonFuntions.clickButtonContains("Save & Continue");
		sleep(4000);
		/*----------------PEOE-005---------------*/
		commonFuntions.screenShot("Address2", "Pass", "Entering name of officer");
		commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Save & Continue");
		/*------------PEOE-017---------------*/
		commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
		commonFuntions.clickButton("Accept & Submit ");
		
		/* After submitting the flow */
		
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	     Thread.sleep(2000);
	     
	    PEOPage.queue.click();
	    sleep(2000);
	    commonFuntions.waitForLoadingIconToDisappear();
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
		 	commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "Other");
		     Thread.sleep(2000);
//		     PEOPage.uspsAddress.click();
//		     PEOPage.currentAdditionalAddress.click();
//		     commonFuntions.screenShot("UspsAddress2","Pass","UspsAddress");
//		     PEOPage.UspsContinueButton.click();
		     Thread.sleep(2000);
		     commonFuntions.clickButtonContains("Save & Continue");
		     commonFuntions.screenShot("VerifyCurrentAdd","Pass","Verify Current Additional Address");
//		     commonFuntions.clickButtonContains("Continue");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);	
		     commonFuntions.screenShot("MailingAddress","Pass","Mailing Address");
		     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		     try {
					WebElement radio = driver.findElement(By.xpath("//strong[text()='USPS Suggested Address']/../following-sibling::div/mat-radio-button/label/span/span[@class='mat-radio-outer-circle']"));
					commonFuntions.safeJavaScriptClick(radio);
					sleep();
					WebElement continuE = driver.findElement(By.xpath("//span[text()='Continue ']"));
					commonFuntions.safeJavaScriptClick(continuE);
				}catch(Exception e) {
					System.out.println("Pop up not displayed");
				}
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyPriorAdd","Pass","Verify Prior Address");
		     commonFuntions.clickButtonContains("Save & Continue");
		     Thread.sleep(2000);	
//		     commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
//		     commonFuntions.enterTextboxContains("Ownership Percentage","40");
//		     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
//		     commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
//		     commonFuntions.enterTextboxContains("City","NewYork");
//		     commonFuntions.enterTextboxContains("Zip Code","13430");
		     commonFuntions.screenShot("OwnershipInformation2","Pass","Ownership Information - privately or closely held company");
		     
		     commonFuntions.clickButtonContains("Continue");
		     Thread.sleep(2000);
		     commonFuntions.screenShot("VerifyOwnerInfo","Pass","Verify Owner Information");
		     commonFuntions.clickButtonContains("Save & Continue");
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
