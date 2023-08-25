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
public class FI_445_001_BCP_Penalty_InsufficientEvidenceFurnished extends TestBase {

	@Test
	public void FI_445_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.445.001-Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is sustained when sustain code is 'Insufficient Evidence Furnished'");
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
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage", "Pass", "Penalty Menu_Enter ERNErrorPage");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		cf.enterTextboxContains("Employer Registration Number", "23457484");
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage1", "Pass", "Penalty Menu_Enter ERNErrorPage1");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		//String randomEan = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		cf.enterTextboxContains("Employer Registration Number", "6787878");
		cf.clickButtonContains("Continue ");sleep(2000);
		cf.screenShot("penaltyMenuErrorPage2", "Pass", "Penalty Menu_Enter ERNErrorPage2");
		cf.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
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

		// Benefit Claim Penalty Summary
		cf.screenShot("benefitClaimPenaltySummary", "Pass", "Benefit Claim Penalty Summary");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("benefitClaimPenaltySummaryErrorCheck", "Pass", "A selection is required1");
		cf.errorContent("A selection is required.");
		cf.selectRadio("Select");
		cf.screenShot("selectRadioButton1", "Pass", "Radio button selected1");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Benefit Claim Penalty Details
		cf.screenShot("BenefitClaimPenaltyDetails", "Pass", "Benefit Claim Penalty Details");
		cf.safeJavaScriptClick(fiPage.sustainBCP);
		fiPage.resolutionDetails.sendKeys("testing");
		cf.screenShot("BenefitClaimPenaltyDetailsEnterDetails", "Pass", "Benefit Claim Penalty Details1");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("BenefitClaimPenaltyDetailsError", "Pass", "Benefit Claim Penalty Details Error");
		fiPage.sustainBcp.click();sleep();
		fiPage.sustainBcpValue.click();sleep();
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Benefit Claim Penalty Verification
		cf.screenShot("BenefitClaimPenaltyVerification", "Pass", "Benefit Claim Penalty Verification");
		cf.clickButtonContains("Submit ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("BenefitClaimPenaltyConfirmation", "Pass", "Benefit Claim Penalty Confirmation");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage", "Pass", "After Confirmation Home Page");

	}

}
