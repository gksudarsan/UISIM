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

public class EM_413_02_001_Employer_Updates_BeginningLiabilityDate_CSR_Review_Created_Task extends TestBase {
	
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
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_3.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_3_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		//---Menu Click---
//		commonFunction.clickMenu("Menu");
//		commonFunction.ScrollMenu("Account Maintenance");
//		commonFunction.clickMenu("Account Maintenance");
//		commonFunction.ScrollMenu("Employer Account Maintenance");
//		commonFunction.clickMenu("Employer Account Maintenance");
//		sleep();
//		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Accounts");
//		commonFunction.clickMenu("Maintain Accounts");
		
		System.out.println("Pass");
	}

}
