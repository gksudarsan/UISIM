package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_001_Verify_CSR_is_able_to_process_sale_of_business_and_indicate_transfer_Total extends TestBase{

	@Test
	public void Tc_EM_310_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		test = report.createTest("Verify_CSR_is_able_to_process_sale_of_business_and_indicate_transfer_Total");
	
		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		
		test.info("Step: 3 -- Navigate to Menu > Account Maintenance > Sale of Business");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Sale of Business");
		commonFuntions.clickMenu("Sale of Business");
		
		//Pending because of Sales of Business issue
		
	}
	
}
