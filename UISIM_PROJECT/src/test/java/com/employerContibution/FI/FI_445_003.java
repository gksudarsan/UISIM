package com.employerContibution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.COL_474;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;  
import com.ui.pages.FIP_006; 
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_445_003 extends TestBase {

	@Test
	public void FI445_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		COL_474 col474 = new COL_474(driver);
		FIP_006 fip006 = new FIP_006(driver);

		test = report.createTest(
				"FI.445.003. Verify CSR can view the BCP penalty details in order to determine if it is sustained when sustain code is 'Wages Not Reported'");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Penalty");
		commonFuntions.clickMenu("Penalty");
		sleep(2000);
		commonFuntions.ScrollMenu("Penalty Menu");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Penalty Menu side bar is displayed");
		commonFuntions.clickMenu("Penalty Menu");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Penalty Menu - Enter ERN", "Pass", "FIP–001 screen is displayed");

		////
		Map<String, String> databaseResults1 = peopage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON ttebcp.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID");

		String eanValue1 = databaseResults1.get("Ean");
		System.out.println("The EAN Value is:" + eanValue1);
		test.log(Status.INFO, "Ean::" + eanValue1);

		String nameValue1 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + nameValue1);
		test.log(Status.INFO, "Ean::" + nameValue1);
		////

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue1);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Select Penalty", "Pass", "FIP-002 screen is displayed");
		
		test.info("Step: 5 -- ");
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Summary", "Pass", "FIP-005 screen is displayed");
		
		test.info("Step: 6 -- ");
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Details", "Pass", "FIP-006 screen is displayed");
		
		test.info("Step: 7 -- ");
		fip006.selectRadioWithValue(" Sustain BCP");
		commonFuntions.selectDropdown("If Sustain a BCP, Select sustain code ", " Civil ");
		fip006.selectDropdownWithTagP("If Sustain a BCP, Select sustain code ", " Wages Not Reported ");
		fip006.resolutionDetailsCommentField.sendKeys("sustain BCP with wages not responded");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Verification", "Pass", "FIP-007 screen is displayed");
		
		test.info("Step: 8 -- ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Benefit Claim Penalty Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 9 -- ");
		commonFuntions.clickButtonContains(" Home ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("HomePage", "Pass", "Home screen is displayed");
		
		

	}

}
