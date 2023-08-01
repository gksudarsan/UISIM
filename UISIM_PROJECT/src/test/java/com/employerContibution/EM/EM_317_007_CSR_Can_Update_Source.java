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
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_317_007_CSR_Can_Update_Source extends TestBase {
	
	@Test
	public void EM_317_007() throws Exception {
		commonStepDefinitions cf= new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("EM.317.007. Verify CSR is able to search and update FEIN and Source of FEIN update correspondence/Email'");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.clickMenu("Maintain FEIN");
		//sleep(40000);

		/*----------------SREG-436------------------*/
		cf.screenShot("ChangeFein", "Pass", "Navigated to SREG-436 page");
		Map<String, String> ernOutput = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '9%' AND REGISTRATION_STATUS = 'C' ORDER BY UPDATED_TS", "EAN");
		String ernValue = ernOutput.get("EAN");

		Map<String, String> feinOutput = cf.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IS NOT NULL ORDER BY UPDATED_TS", "FEIN");
		String feinValue = feinOutput.get("FEIN");

		test.log(Status.INFO, "ERN : " +ernValue);
		test.log(Status.INFO, "New FEIN : " +feinValue);
		
		System.out.println("EAN is :" + ernValue);
		System.out.println("FEIN is :" + feinValue);

		cf.enterTextboxContains("Employer Registration Number", ernValue);
		cf.clickButtonContains("Continue ");
		sleep(4000);

		/*----------------SREG-437------------------*/
		cf.screenShot("ChangeFein3", "Pass", "Navigated SREG-437 page");
		cf.enterTextboxContains("New FEIN", feinValue);
		cf.enterTextboxContains("Please Re-enter FEIN", feinValue);
		cf.selectDropdown("Suffix", " UR ");
		sleep();
		cf.selectDropdown("Confirm Suffix", " UR ");
		sleep();
		cf.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		cf.selectDropdown("Source Type", " Correspondence/Email ");
		empPage.commentBox_MyQ.sendKeys("Update FEIN");
		empPage.browserLink.click();
		cf.uploadDoc("Sample.docx");
		sleep();
		cf.screenShot("ChangeFein4", "Pass", "Document uploaded");
		
		cf.clickButton("Submit ");
		sleep(3000);
		try {
			cf.clickButton(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		cf.waitForLoadingIconToDisappear();
		/*----------------SUC-002------------------*/
		cf.screenShot("ChangeFein5", "Pass", "Navigated to SUC-002 and click on Home button");
		cf.clickButton("Home ");
		cf.waitForLoadingIconToDisappear();

		/*----------------Home Page ----------------*/
		cf.screenShot("ChangeFein6", "Pass", "Navigating to Home Page");
		
		
		
		
		
		
		
		
		
		
		
	}
}
