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

public class EM_317_002_CSR_Can_Update_Fein extends TestBase {
	
	@Test
	public void EM_319_001() throws Exception {
		commonStepDefinitions cf= new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("EM.317.002.Verify CSR is able to search and update FEIN and Source of FEIN update 'NYS-100 (paper)'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		cf.login("ndfjp3", "Admin@12345678");
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.clickMenu("Maintain FEIN");
		//sleep(40000);

		/*----------------SREG-436------------------*/
		cf.screenShot("ChangeFein", "Pass", "Navigated to SREG-436 page");
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE CORRESPONDENCE_MODE='USPS' ORDER BY UPDATED_TS", "EAN");
		//String ernValue = ernOutput.get("EAN");
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
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");
		empPage.commentBox_MyQ.sendKeys("Testing");
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
		cf.waitForLoadingIconToDisappear();

		/*----------------Home Page ----------------*/
		cf.screenShot("ChangeFein6", "Pass", "Navigating to Home Page");
		
		
		
		
		
		
		
		
		
		
		
	}
}
