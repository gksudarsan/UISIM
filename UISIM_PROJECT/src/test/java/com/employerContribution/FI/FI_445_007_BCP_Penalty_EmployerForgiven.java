package com.employerContribution.FI;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_445_007_BCP_Penalty_EmployerForgiven extends TestBase {

	@Test
	public void FI_445_007() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.445.007- Verify CSR can view the BCP penalty details and take a decision to abate the BCP penalty in order to determine if it is cancelled when cancel code is 'Employer Forgiven'");
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
		cf.enterTextboxContains("Employer Registration Number", "0470006");
		cf.screenShot("ValidErnEntered", "Pass", "Enetring valid Ern");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Select Penalty
		cf.screenShot("selectPenalty", "Pass", "Select Penalty");
		fiPage.selectRadioButton.click();sleep();
		cf.screenShot("selectRadioButton", "Pass", "Radio button selected");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Benefit Claim Penalty Summary
		cf.screenShot("benefitClaimPenaltySummary", "Pass", "Benefit Claim Penalty Summary");
		fiPage.selectRadioButton.click();sleep();
		cf.screenShot("selectRadioButton1", "Pass", "Radio button selected1");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		// Benefit Claim Penalty Details
		cf.screenShot("BenefitClaimPenaltyDetails", "Pass", "Benefit Claim Penalty Details");
		fiPage.cancelBCP.click();sleep();
		fiPage.cancelBcp.click();sleep();
		fiPage.cancelBcpValue.click();sleep();
		fiPage.resolutionDetails.sendKeys("testing");
		cf.screenShot("BenefitClaimPenaltyDetailsEnterDetails", "Pass", "Benefit Claim Penalty Details1");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();

		// Benefit Claim Penalty Verification
		cf.screenShot("BenefitClaimPenaltyVerification", "Pass", "Benefit Claim Penalty Verification");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("BenefitClaimPenaltyConfirmation", "Pass", "Benefit Claim Penalty Confirmation");
		cf.clickButtonContains("Home ");
		sleep(5000);
		cf.screenShot("homePage", "Pass", "After Confirmation Home Page");

	}

}
