package com.employerContribution.FI;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_445_002_Verify_CSR_can_view_BCP_penalty_details_Wages_Incorrectly_Reported extends TestBase {
	@Test
	public void FI_169_04_001() throws Exception {
		test = report.createTest(
				"FI.169.04.001- 'Verify Employer can submit a IA198.P Failure to File Protest form online, task 'Failure to File Penalty Protest' will create for CSR to review and close task with no action taken");

		commonStepDefinitions cf = new commonStepDefinitions();
		SUC_002 suc002 = new SUC_002(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = new PEOPage(driver);
		EM_005 em005 = new EM_005(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPL_BENEFIT_CLAIM_PENALTY ttebcp ON TTEBCP.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);

		// Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Application Login", "Pass", "Login Successfull");

		// Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Penalty");
		cf.clickMenu("Penalty");
		sleep(1000);
		cf.clickMenu("Penalty Menu");
		cf.screenShot("Penalty Menu - Enter ERN", "Pass", "Launched to FIP-001");

		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Select Penalty", "Pass", "Launched to FIP-002");
		em005.selectToggle.click();
		cf.clickButtonContains("Continue ");

		// FIP-005
		cf.screenShot("Benefit Claim Penalty Summary", "Pass", "Launched to FIP-005");
		em005.selectToggle.click();
		cf.clickButtonContains("Continue ");

		// FIP-006
		cf.screenShot("Benefit Claim Penalty Details", "Pass", "Launched to FIP-006");
		try {
			em005.sustainRadioInCircle.click();
		} catch (Exception exception) {
			em005.sustainRadioOutCircle.click();
		}

		cf.selectDropdown("If Sustain a BCP, Select sustain code ", " Wages Incorrectly Reported ");
		em005.enterCommentField.sendKeys("ok");
		cf.clickButtonContains("Continue ");

		// FIP-007
		cf.screenShot("Benefit Claim Penalty Verification", "Pass", "Launched to FIP-007");
		cf.clickButtonContains("Submit ");

		// SUC-002
		cf.screenShot("Benefit Claim Penalty Confirmation", "Pass", "Launched successfully to SUC-002");
		cf.Label(" The Benefit Claim Penalty has been successfully updated. ");
		cf.clickButtonContains("Home");
		cf.screenShot("Home", "Pass", "Home Page");
		System.out.println("Worked");
	}
}
