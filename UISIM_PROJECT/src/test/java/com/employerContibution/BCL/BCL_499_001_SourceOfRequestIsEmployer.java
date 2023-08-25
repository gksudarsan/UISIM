package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_499_001_SourceOfRequestIsEmployer extends TestBase {

	@Test
	public void BCL_499_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage bclPage = new BclPage(driver);
		test = report
				.createTest("BCL.499.001-Verify CSR can search ERN and submit settlement request when Source of Request is Employer, task is created for manager review and take action approved");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
        cf.ScrollMenu("Employer Collection");
        cf.clickMenu("Employer Collection");sleep();
        cf.screenShot("SettlementRequest", "Pass", "Navigating to settlement request");
        cf.clickMenu("Settlement Request");sleep();
        cf.screenShot("SettlementRequest1", "Pass", "SettlementRequest");
        cf.clickButtonContains(" Search ");sleep(2000);
        cf.screenShot("SettlementRequest2", "Pass", "The response to the Employer Registration Number is required.");
        cf.errorContent("The response to the Employer Registration Number is required.");
        cf.enterTextboxContains("Employer Registration Number", "1111111");
        cf.clickButtonContains(" Search ");sleep(2000);
        cf.screenShot("SettlementRequest3", "Pass", "The Employer Registration Number provided is invalid.");
        cf.errorContent("The Employer Registration Number provided is invalid.");
        cf.enterTextboxContains("Employer Registration Number", "5176987");
        cf.clickButtonContains(" Search ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("SettlementRequest4", "Pass", "No Settlement Set up on Account....");
        cf.enterTextboxContains("Employer Registration Number", "5440975");
        cf.clickButtonContains("Add Settlement ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("SettlementRequest5", "Pass", "Settlement can only be added if there is an outstanding due on the Account.");
        cf.errorContent("Settlement can only be added if there is an outstanding due on the Account.");
        //Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("","EAN");
		//String eanValue = databaseResults.get("EAN");
        String eanValue = prop.getProperty("BCLOutstandingBal");
        cf.enterTextboxContains("Employer Registration Number", eanValue);
        cf.clickButtonContains(" Search ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("SettlementRequest6", "Pass", "Settlement Details Displayed");
        cf.clickButtonContains(" View History ");sleep(3000);
        cf.screenShot("SettlementRequest7", "Pass", "Select one of the records to proceed.");
        cf.errorContent("Select one of the records to proceed.");sleep();
        cf.selectRadio("Select");
        cf.clickButtonContains(" View History ");sleep(2000);
        cf.screenShot("SettlementRequest8", "Pass", "Settlement History Details");
        cf.clickButtonContains("Add Settlement ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("SettlementRequestforReview", "Pass", "Settlement Request for Review");
        bclPage.followUpNote.sendKeys("Follow up");
        cf.enterFutureDate("Request Date", 10);sleep();
        cf.selectDropdown("Source of Request", " Employer ");sleep();
        bclPage.nameFeild.sendKeys("Harry Porter");
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview1", "Pass", "Settlement Amount ($)_RequiredError");
        bclPage.settlementAmount.sendKeys("1000");
        cf.forceClearText(bclPage.followUpNote);
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview2", "Pass", "Follow up Note (max. 500 characters)_RequiredError");
        bclPage.followUpNote.sendKeys("Follow up");
        cf.selectDropdown("Source of Request", "--SELECT--");sleep();
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview3", "Pass", "Source of Request_RequiredError");
        cf.selectDropdown("Source of Request", " Employer ");sleep();
        cf.forceClearText(bclPage.nameFeild);
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview5", "Pass", "Name_RequiredError");
        bclPage.nameFeild.sendKeys("Harry Porter");
        cf.forceClearTextWithElement("Request Date");
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview6", "Pass", "Request Date_RequiredError");
        cf.enterFutureDate("Request Date", 10);sleep();
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("SettlementRequestforReview7", "Pass", "Request date must be a current date not future date.");
        cf.forceClearTextWithElement("Request Date");
        cf.enterCurrentDate("Request Date");
        AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("Sample2");sleep(4000);
		cf.screenShot("fileSizeToLarge", "Pass", "File size cannot exceed more than 5MB");
		cf.clickButtonContains("OK");sleep();
		AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("chrome");sleep(4000);
		cf.screenShot("fileNotSupported", "Pass", "File format not supported");
		cf.clickButtonContains("OK");sleep();
		AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("TESTINGEL");sleep(4000);
		cf.screenShot("uploadingDocuments", "Pass", "Uploading Documents");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("RequestforSettlementConfirmation", "Pass", "Request for Settlement Confirmation");
		cf.clickButtonContains("Home ");sleep(3000);
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
		}catch(Exception e) {}
		cf.screenShot("homePage", "Pass", "After Confirmation Home Page");
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+eanValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		PEOPage.queue.click(); 
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Work Item Description Free Text","Settlement Request to be acted by Manager - Approve or Reject");
		cf.clickButtonContains("Search");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.clickOnLinkAnchorTag("Settlement Request to be acted by Manager - Approve or Reject");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.clickButtonContains("Open Work Item");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("Task-SettlementRequest", "Pass", "Task - Settlement Request");
		cf.clickButtonContains("Submit ");sleep(2000);
		cf.screenShot("Task-SettlementRequest1", "Pass", "Decision is a required field");
        cf.errorContent("Decision is a required field");sleep();
        cf.selectDropdown("Decision", " Others ");
        cf.clickButtonContains("Submit ");sleep(2000);
        cf.screenShot("Task-SettlementRequest2", "Pass", "Counteroffer is a required field if âDecisionâ is Others");
        cf.errorContent("Counteroffer is a required field if âDecisionâ is Others");sleep();
        cf.selectDropdown("Decision", " Approved ");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("requestConfirmation", "Pass", "Request Confirmation");
		cf.clickButtonContains("Home ");sleep(3000);
		try {
			loginPage.okPopUpButton.click();
			sleep(2000);
			cf.waitForLoadingIconToDisappear();
		}catch(Exception e) {}
		cf.screenShot("homePage1", "Pass", "After Confirmation Home Page1");
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+eanValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		PEOPage.queue.click(); 
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Work Item Description Free Text","FYI Request Settlement");
		cf.clickButtonContains("Search");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.clickOnLinkAnchorTag("FYI Request Settlement");
		sleep();cf.waitForLoadingIconToDisappear();sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("RequestSettlementFYITask", "Pass", "Request Settlement FYI Task");
		cf.clickButtonContains("Home ");cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage2", "Pass", "Home Page2");
	}

}
