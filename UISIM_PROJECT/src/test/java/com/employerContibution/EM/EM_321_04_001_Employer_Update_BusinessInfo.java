package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_04_001_Employer_Update_BusinessInfo extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter the term end date and Inactivate Partner Details", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_321_005() throws Exception {
		
		test = report.createTest("Verify CSR is able to enter the term end date and Inactivate Partner Details");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);		
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_5.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_5_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		sleep();
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
		//empManage.employerAccountMaintanceMenu.click();
		
		// --- SREG-030 ---
		sleep(2000);
		commonFunction.screenShot("EM32104001", "Pass", "Successful launch to SREG 030 page");
		commonFunction.clickButton("Submit ");
		
		// --- SUC - 002 ---
		sleep(2000);
		commonFunction.screenShot("EM32104001", "Pass", "Successful launch to SUC 002 page");
		commonFunction.clickButtonContains("Home");
		
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_321_04_001 passed succesfully");
		
		
		
		
		System.out.println("pass");
	}

}
