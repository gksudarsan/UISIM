package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

public class EM_317_001_CSR_Can_Update_Fein extends TestBase {
	
	@Test
	public void EM_319_001() throws Exception {
		
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("EM.317.001. Verify CSR is able to search and update FEIN and Source of FEIN update  'NYBE NYS100 form'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		cf.login("ndfjp3", "Admin@12345678");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
//		sleep();
		cf.ScrollMenu("Account Maintenance");
//		sleep();
		cf.clickMenu("Account Maintenance");
//		sleep();
		cf.ScrollMenu("Employer Account Maintenance");
//		sleep();
		cf.clickMenu("Employer Account Maintenance");
//		sleep();
		cf.clickMenu("Maintain FEIN");
//		sleep(40000);
		
		/*----------------SREG-436------------------*/
		cf.screenShot("ChangeFein", "Pass", "Navigated to SREG-436 page");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.errorLabel("Required");
		cf.enterTextboxContains("Employer Registration Number", "0000000");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		sleep();
		cf.screenShot("ChangeFein2", "Pass", "Validated error message");
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE CORRESPONDENCE_MODE='USPS' ORDER BY UPDATED_TS", "EAN");
//		String ernValue = ernOutput.get("EAN");
		String ernValue = "3100212";
		
		Map<String, String> feinOutput = cf.database_SelectQuerySingleColumn("SELECT FEIN  FROM T_EMPLOYER_ACCOUNT tea ORDER BY UPDATED_TS", "FEIN");
		String feinValue = feinOutput.get("FEIN");
		
		test.log(Status.INFO, "ERN : : "+ernValue);
		test.log(Status.INFO, "New FEIN : : "+feinValue);
		
		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(4000);
		
		/*----------------SREG-437------------------*/
		cf.screenShot("ChangeFein3", "Pass", "Navigated SREG-437 page");
		cf.clickButton("Submit ");
		sleep(2000);
		try {
			cf.clickButton(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(3000);
		cf.errorLabel(" Required ");
		sleep();
		cf.enterTextboxContains("New FEIN", "6767");
		cf.enterTextboxContains("Please Re-enter FEIN", "6767");
		cf.errorLabel(" Minimum 9 Characters ");
		sleep();
		cf.enterTextboxContains("New FEIN", "111111111");
		cf.enterTextboxContains("Please Re-enter FEIN", "111111111");
		sleep();
		cf.selectDropdown("Source", " NYBE NYS-100 form ");
		sleep();
		cf.selectDropdown("Source Type", " NYS-100AG ");
		empPage.commentBox_MyQ.sendKeys("Testing");
		cf.clickButton("Submit ");
		sleep(2000);
		try {
			cf.clickButton(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(2000);
		cf.errorContent("Federal Employer Identification Number is invalid.");
		cf.enterTextboxContains("New FEIN", "111111111");
		cf.enterTextboxContains("Please Re-enter FEIN", "111111112");
		cf.clickButton("Submit ");
		sleep(2000);
		try {
			cf.clickButton(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(2000);
		cf.secondErrorContent("The values New FEIN and Re-Enter FEIN fields do not match");
		sleep();
		cf.enterTextboxContains("New FEIN", feinValue);
		cf.enterTextboxContains("Please Re-enter FEIN", feinValue);
		cf.selectDropdown("Suffix", " UP ");
		sleep();
		cf.selectDropdown("Confirm Suffix", " UP ");
		sleep();
		cf.safeJavaScriptClick(empPage.browserLink);
		sleep(2000);
		cf.uploadDoc("Sample");
		sleep(2000);
		cf.screenShot("ChangeFein4", "Pass", "Document uploaded");
		cf.clickButton("Submit ");
		sleep(3000);
		try {
			cf.clickButton(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep();
		cf.waitForLoadingIconToDisappear();
		/*----------------SUC-002------------------*/
		cf.screenShot("ChangeFein5", "Pass", "Navigated to SUC-002 and click on Home button");
		cf.clickButton("Home ");
		
		/*----------------Home Page ----------------*/
		cf.screenShot("ChangeFein6", "Pass", "Navigating to Inquiry Employer Account");
		cf.clickMenu("Menu");
		sleep();
		cf.clickMenu("Inquiry");
		sleep();
		cf.clickMenu("Contribution Inquiry");
		sleep();
		cf.clickMenu("Inquiry Employer Account");
		sleep(4000);
		
		
		/*----------------SREG-050------------------*/
		cf.screenShot("ChangeFein7", "Pass", "Navigated to SREG-050 page");
		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(4000);
		
		/*----------------SREG-051------------------*/
		cf.screenShot("ChangeFein8", "Pass", "Navigated to SREG-051 page");
		String feinSREG051 = empPage.FEIN_Value_Text_SREG_051.getText().replace("-", "");
		Assert.assertEquals(feinValue, feinSREG051);
		cf.clickButton("Previous ");
		
		
		
	}
}



