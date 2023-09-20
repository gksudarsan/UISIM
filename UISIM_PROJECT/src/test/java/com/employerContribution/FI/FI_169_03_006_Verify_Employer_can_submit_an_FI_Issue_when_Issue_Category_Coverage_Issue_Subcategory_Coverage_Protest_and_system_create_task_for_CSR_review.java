package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_006_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Coverage_Issue_Subcategory_Coverage_Protest_and_system_create_task_for_CSR_review extends TestBase {

	@Test(priority = 1, description = "FI_169_03_006_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Coverage_Issue_Subcategory_Coverage_Protest_and_system_create_task_for_CSR_review", groups = {
			"Regression" })
	public void FI_169_03_006() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		AddressPage add =new AddressPage(driver);
        FraudAndInvestigationPage FI = new FraudAndInvestigationPage(driver);
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI_169_03_006_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Coverage_Issue_Subcategory_Coverage_Protest_and_system_create_task_for_CSR_review");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.EMPLOYER_USER_8, COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		// --------Menu---------
				commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
				//commonFunctions.clickMenu("menu");
				add.menu.click();
				commonFunctions.ScrollMenu("Secure Messaging");
				commonFunctions.clickMenu("Secure Messaging");
				sleep(1000);
				commonFunctions.screenShot("menu", "Pass", "selected option");
		commonFunctions.ScrollMenu("Write Message");
		commonFunctions.clickMenu("Write Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SM-101---
		commonFunctions.screenShot("Write Message", "Pass", "Write Message (SM-101)screen launched");
		commonFunctions.selectDropdown("Category", " Protest ");
		sleep(1000);
		commonFunctions.selectDropdown("Subcategory", " How do I protest (an) Unsatisfied Judgment(s)? ");
		sleep(1000);
		commonFunctions.clickOnLinkAnchorTag("click here");
		commonFunctions.switchTab();
		commonFunctions.waitForLoadingIconToDisappear();

		// ----FIS-002---
		commonFunctions.screenShot("Submit Issue", "Pass", "Successfully launched to FIS_002 page");
		commonFunctions.enterTextbox("Warrant/Judgment balance due ($)", "testing");
		FI.reasonBasis.sendKeys("for testing");
		//FI.selectcheckbox.click();
		//commonFunctions.selectLink("Document", "Browse");
		//commonFunctions.waitForLoadingIconToDisappear();
		
		//commonFunctions.uploadDoc("Sample.docx");
		//commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit Issue", "Pass", "Details entered on (FIS-002)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---Submit Issue Verification---
		commonFunctions.screenShot("Submit Issue Verification", "Pass", "Submit Issue Verification screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ----SUC 002
				sleep(2000);
				commonFunctions.screenShot("Issue Submission Confirmation", "Pass", "Successfully landed on SUC 002");
				sleep(2000);
				commonFunctions.clickButtonContains("Home ");
				//------Home
				commonFunctions.waitForLoadingIconToDisappear();
				commonFunctions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
				commonFunctions.waitForLoadingIconToDisappear();

				// --------Login Logout-------
						commonFunctions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
						commonFunctions.waitForLoadingIconToDisappear();
				
				// --------WorkItems-------- 
						
				/*-------------- Work Item 1  --------------*/
				sleep(3000);
				commonFunctions.waitForLoadingIconToDisappear();
				PEOPage.queue.click();
				commonFunctions.waitForLoadingIconToDisappear();

				/*--------------WF-001   --------------*/
				sleep(3000);
				commonFunctions.screenShot("Individual Work Queue", "Pass", "Clicked on Work Item - WF-001 ");
				sleep(3000);
				commonFunctions.clearTextboxContains("Employer Registration Number");
				commonFunctions.enterTextboxContains("Work Item Description Free Text", "Coverage Determination Protest Task");
				sleep(3000);
				commonFunctions.screenShot("Work Item Description Free Text", "Pass", "Search for Task");
				sleep(3000);
				commonFunctions.clickButtonContains(" Search ");
				sleep(3000);
				commonFunctions.ScrollMenu("Coverage Determination Protest Task");
				sleep(3000);
				commonFunctions.screenShot("WIClick", "Pass", "Clicked on Work Item -Coverage Determination Protest");
				sleep(3000);
				commonFunctions.clickOnLink("Coverage Determination Protest Task");

				// --- WF-091 ---
				commonFunctions.waitForLoadingIconToDisappear();
				sleep(2000);
				commonFunctions.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
				sleep(3000);
				commonFunctions.clickButtonContains("Open Work Item ");

				// --- PFP-120---
				commonFunctions.waitForLoadingIconToDisappear();
				commonFunctions.screenShot("Coverage Determination Protest ", "Pass", "Successful launch to PFP-120 page");
				sleep(2000);
				commonFunctions.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
				 sleep(1000);
				//commonFunctions.selectDropdown("If Yes, Select Work Basket", " IEVF ");
				//sleep(1000);
				//commonFunctions.selectDropdown("If Cancel a BCP,choose the cancel code", " 2023 ");
				//sleep(1000);
			
				 //commonFunctions.selectRadioQuestions("Options for CSR", " Sustain BCP");
				 //sleep(1000);
				 //commonFunctions.selectRadioQuestions("Do you want to Generate the IA13.2 notice to the Employer?    ", "No ");
				// sleep(1000);
				 //commonFunctions.selectRadioQuestions("Is Fact Finding required", "No ");
				 FI.comments.sendKeys("Test Reveiw");
				sleep(2000);
				commonFunctions.screenShot("Coverage Determination Protest", "Pass", "Details entered in to PFP-120 page");
				sleep(2000);
				commonFunctions.clickButtonContains("Submit ");

				
				
				// --- SUC-002 ---
				commonFunctions.waitForLoadingIconToDisappear();
				commonFunctions.screenShot("Task Completed.", "Pass", "Successful launch (SUC-002) page");
				sleep();
				commonFunctions.clickButtonContains("Home ");
				sleep();
				commonFunctions.waitForLoadingIconToDisappear();
				commonFunctions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
				commonFunctions.waitForLoadingIconToDisappear();
				commonFunctions.screenShot("FI.03.006", "Pass", "FI.03.006 test completed  ");
	}

}
