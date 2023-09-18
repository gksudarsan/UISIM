package com.employerContibution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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
public class FI_444_001_ReviewPenaltyAbatementRequest extends TestBase {

	@Test
	public void FI_444_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.444.001-Verify CSR can view the Fraud penalty details and take a decision to abate the Fraud penalty.\r\n" + 
				"(When CSR approved task '“Review Penalty Abatement Request”)");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.ScrollMenu("Penalty");
		sleep();
		cf.clickMenu("Penalty");
		sleep();
		cf.screenShot("NavigateToPenaltyMenu", "Pass", "Navigating to penalty menu");
		cf.clickMenu("Penalty Menu");
		sleep();

		// Penalty Menu - Enter ERN	
		cf.screenShot("PenaltyMenu-EnterERN", "Pass", "Penalty Menu - Enter ERN");
		cf.enterTextboxContains("Employer Registration Number", "11-11111");
		cf.screenShot("enterInvalidErn", "Pass", "Invalid Ern Entered");
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage", "Pass", "Penalty Menu_Enter ERNErrorPage");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		cf.enterTextboxContains("Employer Registration Number", "234574");
		cf.screenShot("enterInvalidErn1", "Pass", "Invalid Ern Entered1");
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage1", "Pass", "Penalty Menu_Enter ERNErrorPage1");
		
		//String randomEan = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		cf.enterTextboxContains("Employer Registration Number", "6787878");
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage2", "Pass", "Penalty Menu_Enter ERNErrorPage2");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_FRAUD_PENALTY ttefp ON ttefp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");
		String eanValue = databaseResults.get("EAN");
		System.out.println("Selected ean value is:" + eanValue);
		test.log(Status.INFO, "EAN::" + eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.screenShot("ValidErnEntered", "Pass", "Enetring valid Ern");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Select Penalty
		cf.screenShot("selectPenalty", "Pass", "Select Penalty");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("selectPenaltyErrorCheck", "Pass", "A selection is required");
		cf.errorContent("A selection is required.");
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton", "Pass", "Radio button selected");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Fraud Penalty Summary
		cf.selectCheckbox("Abate Penalty?");
		fiPage.resDetails.sendKeys("testing");sleep();
		cf.screenShot("FraudPenaltySummary", "Pass", "Fraud Penalty Summary");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("FraudPenaltySummarySelectionRequired", "Pass", "A selection is required1");
		cf.errorContent("A selection is required.");
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton1", "Pass", "Radio button selected1");
		cf.forceClearText(fiPage.resDetails);
		cf.clickButtonContains("Continue ");
		sleep(2000);cf.screenShot("FraudPenaltySummaryRequiredError", "Pass", "Fraud Penalty Summary Error Required");
		fiPage.resDetails.sendKeys("testing");sleep();
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();

		// Fraud Penalty Verification
		cf.screenShot("FraudPenaltyVerification", "Pass", "Fraud Penalty Verification");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("FraudPenaltyConfirmation", "Pass", "Fraud Penalty Confirmation");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage", "Pass", "After Confirmation Home Page");
		
		//Search the work item
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+eanValue+"' ORDER BY UPDATED_TS desc)");
		PEOPage.queue.click(); 
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("IndividualWorkQueue", "Pass", "Individual Work Queue");
		cf.enterTextboxContains("Work Item Description Free Text","Review Penalty Abatement Request Task");
		cf.clickButtonContains(" Search ");sleep(2000);
		cf.clickOnLinkAnchorTag("Review Penalty Abatement Request Task");sleep(2000);
		cf.screenShot("WorkItemDetails", "Pass", "Work Item Details");
		cf.clickButtonContains("Open Work Item ");sleep(2000);
		cf.screenShot("ReviewPenaltyAbatementRequest", "Pass", "Review Penalty Abatement Request");
		cf.selectRadioQuestions("Do you want to reroute this task to another Work Basket/User?", "No ");
		cf.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		cf.selectRadioQuestions("Select Action", "Approve");
		fiPage.comments.sendKeys("testing");
		cf.screenShot("ReviewPenaltyAbatementRequest1", "Pass", "Review Penalty Abatement Request1");
		cf.clickButtonContains("Submit ");sleep(2000);
		cf.screenShot("TaskConfirmation", "Pass", "Task Confirmation");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage1", "Pass", "Home Page1");
		
		//Navigating to penalty menu 
		AddPage.menu.click();
		sleep();
		cf.ScrollMenu("Penalty");
		sleep();
		cf.clickMenu("Penalty");
		sleep();
		cf.screenShot("NavigateToPenaltyMenu1", "Pass", "Navigate to penalty menu");
		cf.clickMenu("Penalty Menu");
		sleep();
		
		//Penalty menu
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.screenShot("ValidErnEntered", "Pass", "Enetring valid Ern");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		
		//select penalty
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton2", "Pass", "Radio button selected2");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		
		//Fraud Penalty Summary
		cf.selectRadio("Select");
		cf.selectCheckbox("Abate Penalty?");
		fiPage.resDetails.sendKeys("testing");sleep();
		cf.screenShot("FraudPenaltySummary1", "Pass", "Fraud Penalty Summary1");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		
		// Fraud Penalty Verification
		cf.screenShot("FraudPenaltyVerification1", "Pass", "Fraud Penalty Verification1");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePageScreen", "Pass", "home page screen");
		

	}

}
