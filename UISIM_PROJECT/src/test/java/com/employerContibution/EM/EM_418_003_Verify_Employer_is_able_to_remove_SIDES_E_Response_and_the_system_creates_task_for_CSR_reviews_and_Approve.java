package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_041;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_418_003_Verify_Employer_is_able_to_remove_SIDES_E_Response_and_the_system_creates_task_for_CSR_reviews_and_Approve
		extends TestBase {

	@Test
	public void Tc_418_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		SREG_084 sreg084 = new SREG_084(driver);
		SUC_002 suc_002 = new SUC_002(driver);
		employerManagementLocators eml = new employerManagementLocators();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_041 sreg041 = new SREG_041(driver);

		test = report.createTest(
				"EM_418_003_Verify_Employer_is_able_to_remove_SIDES_E_Response_and_the_system_creates_task_for_CSR_reviews_and_Approve");
		
		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		test.info("Step: 3 -- Navigate to Main Menu -> Account Maintenance -> Maintain Address – Enter ERN");
		Thread.sleep(2000);
		// homePage.menu.click();
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Maintain Address");
		commonFuntions.clickMenu("Maintain Address");
		Thread.sleep(3000);
		commonFuntions.screenShot("Maintain Address Details", "Pass", "SREG-486 screen is visible");

		test.info(
				"Step: 4 -- SREG-486");
		commonFuntions.selectRadioQuestions("Do you wish to register for SIDES E-Response?","Yes");
		sreg084.editActionBtn.isDisplayed();
		sreg084.addActionBtn.isDisplayed();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Employer Address and Contact Person Details Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info(
				"Step: 5 -- SUC-002");
		commonFuntions.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
				"Homepage is displayed");
		
	}

}
