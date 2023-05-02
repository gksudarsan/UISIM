package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_413_001_CSR_Update_BeginningLiabilityDate extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to update employer 'Beginning Liability Date' of an existing employer account.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_413_001() throws Exception {
		
		test = report.createTest("Verify CSR is able to update employer 'Beginning Liability Date' of an existing employer account.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement();
				
		//DB SELECT query
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC","EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is " + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		sleep();
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Accounts");
		commonFunction.clickMenu("Maintain Accounts");
		sleep();
		
		// --- SREG-027 ---
		sleep(2000);
		commonFunction.screenShot("EM413001", "Pass", "Successful launch to SREG-027 page");
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //9300002
		sleep();
		commonFunction.screenShot("EM413001", "Pass", "Entered ERN and clickd on Continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-030 ---
		sleep(2000);
		commonFunction.screenShot("EM413001", "Pass", "Successful launch to SREG-030 page");
		sleep();
		commonFunction.selectDropdown("Source", " IA602 ");
		commonFunction.selectDropdown("Source Type", " Coverage Exception ");
		sleep(2000);
		commonFunction.screenShot("EM413001", "Pass", "Updated data and Submitted");
		commonFunction.clickButton("Submit ");
		
		// --- SUC-002 ---
		sleep(2000);
		commonFunction.screenShot("EM413001", "Pass", "Successful launch to SUC-002 page");
		commonFunction.clickButton("Home ");
		
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "Launched to Home page");
		
		System.out.println("pass");
		
	}

}
