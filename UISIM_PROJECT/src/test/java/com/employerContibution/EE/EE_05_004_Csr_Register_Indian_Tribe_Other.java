package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_05_004_Csr_Register_Indian_Tribe_Other extends TestBase {

	@Test
	public void EE_05_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement em = new employerManagement();
		commonStepDefinitions cf = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		PEOPage peoPage = new PEOPage(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		SUC_002 suc002 = new SUC_002(driver);

		//
		String feinValue = "115340788";

		//

		test = report.createTest(
				"EE.05.004 - Verify CSR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("menu");
		// homePage.menu.click();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "SREG-001 screen is displayed");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		/**************** SREG-025 ************************/
		test.info("Step: 8-- ");
		commonFuntions.screenShot("General Information", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
		commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "NGO");
		/*--------------------FEIN----------------------*/
		/*
		 * Map<String, String> feinValueOutput = commonFuntions.
		 * database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
		 * , "FEIN"); String feinValue = feinValueOutput.get("FEIN");
		 * System.out.println("FEIN : : "+feinValue); test.log(Status.INFO, "FEIN : : "+
		 * feinValue);
		 */

		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100IT ");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*-----------------SREG-003----------------*/

		test.info("Step: 9-- ");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("EmpRegister4", "Pass", "Required text displayed if user do not enter Legal Name");
		empPage.legalNameTextBox.sendKeys("dev Enterprise");
		// commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		commonFuntions.enterTextboxContains(
				"What is the date of the first payroll which you withheld",
				"3/31/2023");
		commonFuntions.enterTextboxContains("Date covered employment began? ", "9/1/2022");
		commonFuntions.clickButton("Continue ");
		sleep(3000);

		/*-----------------SREG-008----------------*/
		test.info("Step: 10-11-12-- ");
		commonFuntions.screenShot("EmpRegister5", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "29 add");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		try {
			sreg008.businesssreg007.isDisplayed();
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
			commonFuntions.clickButton("Continue ");
		} catch (Exception exception) {
			sleep(2000);
		}
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
			Thread.sleep(2000);
			commonFuntions.clickButton("Continue ");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
//		try {
//			sleep(2000);
//			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_Cooper);
//			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
//		} catch(Exception e) {
//			System.out.println("Pop up not displayed");
//		}
		/*-----------------SREG-004----------------*/
		test.info("Step: 13-- ");
		sleep(3000);
		commonFuntions.screenShot("Employer Contact Details", "Pass",
				"Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		sleep();
		commonFuntions.screenShot("Employer Contact Details", "Pass",
				"Selected Same as Mailing for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Location of Books and Records");
		sleep();
		commonFuntions.screenShot("Employer Contact Details", "Pass",
				"Selected Same as Location of Books and Records for Notice of Potential Charges (LO400) Address");
		sleep();
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		sleep();
		commonFuntions.selectRadioQuestions("Agent (C/O) address", "Same as Notice of Potential Charges");
		sleep();
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-521----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		// commonFuntions.safeJavaScriptClick(empPage.browserLink);
		// commonFuntions.uploadDoc("Sample.docx");
		// sleep(4000);
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-800----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");

		/*-----------------SREG-043----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister13", "Pass",
				"Navigated to SREG-043 page and click on I accept and submit");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.clickButton("Submit ");
		sleep(15000);

		/*-----------------SREG-013----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-013 page and click on exit");
		commonFuntions.clickButton("Home ");
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		Thread.sleep(3000);
//		commonFuntions.clickOnLink("Review Employer Type");
//		commonFuntions.safeJavaScriptClick(empPage.review_employer_My_Q);
		// empPage.review_employer_My_Q.click();
		sreg084.reviewemployertypelink.click();
		sleep(3000);
		/*-----------------WF-091----------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(2000);
		commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is displayed");
		commonFuntions.enterTextboxContains("Date Covered Employment began? ", "9/1/2022");
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		sleep();
		commonFuntions.clickButton("Submit ");
		Thread.sleep(5000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page");

		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		cf.screenShot("DOL DTF Discrepancy", "Pass", "emp type");
		// cf.clickOnLink("DOL DTF Discrepancy");

		Thread.sleep(2000);
		cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("DOL/DTF Discrepency Task", "Pass", "EEWI-005 screen DOL DTF ");
		// cf.selectDropdown("Quarter", "1");sleep();
		// cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("Suppress Correspondence?", "Yes");
		// cf.selectDropdown("*Account Status ", "Liable");
		// cf.enterTextboxContains("Comment", "registration in process");
		Thread.sleep(2000);
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		Thread.sleep(2000);
		cf.clickButtonContains("Submit ");
		Thread.sleep(2000);
		cf.screenShot("Work Item Completed.", "Pass", "SUC-002");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		// Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

		// Verify Registered employer in Inquery page ...........
		// em.Inquery_fein(feinValue);
		// test.log(Status.PASS, "Clicked on Home button");

		test.info("Step: 32 -- ");
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Inquiry");
		commonFuntions.clickMenu("Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Contribution Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFuntions.ScrollMenu("Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		sleep(2000);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");
		sleep(2000);

		test.info("Step: 33 -- ");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		test.info("Step: 34 -- ");
		Thread.sleep(5000);
		commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 screen is displayed");

		test.info("Step: 35 -- ");
		Thread.sleep(1000);
		commonFuntions.clickButtonContains("Previous ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");

		test.info("Step: 36 -- ");
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");

	}

}
