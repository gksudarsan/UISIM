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
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_433_001_FailuretoFileAndStatusofthePenaltyisOpen extends TestBase {

	@Test
	public void FI_433_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//FIPage fiPage = new FIPage(driver);
		test = report.createTest(
				"FI.433.001-Verify CSR can view the  Failure to File  (FTF) penalty details when the return is filed and the Employer’s prior filing history, the assessed penalty is cancelled and Status of the Penalty is Open");
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
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");
		String eanValue = databaseResults.get("EAN");
		System.out.println("Selected ean value is:" + eanValue);
		test.log(Status.INFO, "EAN::" + eanValue);
		cf.enterTextboxContains("Employer Registration Number", "5644886");
		cf.screenShot("ValidErnEntered", "Pass", "Enetring valid Ern");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Select Penalty
		cf.screenShot("selectPenalty", "Pass", "Select Penalty");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton", "Pass", "Radio button selected");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Failure to File Penalty Summary
		cf.screenShot("failuretoFilePenaltySummary", "Pass", "Failure to File Penalty Summary");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("failuretoFilePenaltySummary", "Pass", "A selection is required1");
		cf.errorContent("A selection is required.");
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton1", "Pass", "Radio button selected1");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Failure to File Penalty Details
		cf.screenShot("FailuretoFilePenaltyDetails", "Pass", "Failure to File Penalty Details");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage", "Pass", "Home Page");

	}

}
