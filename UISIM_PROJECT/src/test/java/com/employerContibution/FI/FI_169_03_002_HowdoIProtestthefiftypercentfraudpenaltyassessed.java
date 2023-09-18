package com.employerContibution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_002_HowdoIProtestthefiftypercentfraudpenaltyassessed extends TestBase {

	@Test
	public void FI_169_03_002() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.169.03.002-Verify Employer can submit an FI Issue when Issue Category is Review Fraud Penalty Protest,Issue Subcategory is 'How do I Protest the fifty percent fraud penalty assessed' and system create task for CSR review ");
		cf.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		cf.clickMenu("Secure Messaging");sleep();
		cf.screenShot("NavigateToWriteMessage", "Pass", "Navigating to Write Message");
		cf.clickMenu("Write Message");sleep();
		cf.screenShot("WriteMessage", "Pass", "Write Message");
		cf.selectDropdown("Category", " Protest ");sleep(2000);
		cf.selectDropdown("Subcategory", " How do I protest the fifty percent fraud penalty assessed? ");sleep(2000);
		cf.screenShot("WriteMessage1", "Pass", "Write Message1");
		cf.clickOnLinkAnchorTag("click here");
		sleep();cf.waitForLoadingIconToDisappear();
		
		/*---- Submit Issue ----*/
		
		Set<String> handles =  driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);sleep();
		cf.screenShot("SubmitIssue", "Pass", "Submit Issue");
		cf.clickButtonContains("Continue ");sleep();
		cf.screenShot("SubmitIssueRequiredError", "Pass", "Submit Issue Required Error");
		fiPage.remarksReason.sendKeys("test test");sleep();
		fiPage.quarter1.click();sleep();
		fiPage.quarterValue1.click();sleep();
		fiPage.year1.click();sleep();
		fiPage.yearValue1.click();sleep();
		fiPage.quarter2.click();sleep();
		fiPage.quarterValue2.click();sleep();
		fiPage.year2.click();sleep();
		fiPage.yearValue2.click();sleep();
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("TESTINGEL");
		sleep(3000);
		fiPage.removeLink.click();
		cf.clickButtonContains(" No ");
		String eanValue = cf.retrieveValue("Employer Registration Number").trim();
		eanValue = eanValue.replace("-", "");
		System.out.println("Selected ERN is:" +eanValue);
		test.log(Status.INFO, "ERN::" +eanValue);
		cf.screenShot("SubmitIssue1", "Pass", "Submit Issue1");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		/*------ Submit Issue Verification -----*/
		
		cf.screenShot("SubmitIssueVerification", "Pass", "Submit Issue Verification");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("IssueSubmissionConfirmation", "Pass", "Issue Submission Confirmation");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage", "Pass", "After Confirmation Home Page");
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
			}catch(Exception e) {}
			cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+eanValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		PEOPage.queue.click(); 
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Work Item Description Free Text","Review Fraud Penalty Protest Task");
		cf.clickButtonContains("Search");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.clickOnLinkAnchorTag("Review Fraud Penalty Protest Task");
		sleep();cf.waitForLoadingIconToDisappear();sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewFraudPenaltyProtest", "Pass", "Review Fraud Penalty Protest");
		cf.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		cf.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		cf.selectRadioQuestions("Abate this Penalty?", "No ");
		fiPage.comments.sendKeys("testing");
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("TESTINGEL");
		sleep(3000);
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("TaskConfirmation", "Pass", "Task Confirmation");
		cf.clickButtonContains("Home ");cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage1", "Pass", "Home Page1");
		
	}

}