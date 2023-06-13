package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_019_04_CSR_SearchErn_Close_BusinessLocationHeadquarterDetails_JointEmploymentAgreement extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to search ERN and close account in related Business Location/ Headquarters Details’ of an joint employment agreement.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_019_02() throws Exception {
		
		test = report.createTest("EM.019.04: Verify CSR is able to search ERN and close account in related Business Location/ Headquarters Details’ of an joint employment agreement.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for 9 series
		
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND EAN LIKE '9%' AND LENGTH(EAN)=7 AND FEIN IS NOT NULL ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is " + eanValue);
		
		// FEIN to the above EAN
		sleep();
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IS NOT NULL AND LENGTH(FEIN)=9 AND EAN = '," + eanValue + "' ORDER BY UPDATED_BY",
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IS NOT NULL AND EAN = '" + eanValue + "'",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("FEIN value is " + feinValue);
		 
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Joint Employment/Management Agreement Creation");
		commonFunction.clickMenu("Joint Employment/Management Agreement Creation");
		
		// --- SREG-520 ---
		sleep(2000);
		commonFunction.screenShot("EM01904", "Pass", "Successful launch to Joint Employment/Management Agreement Creation(SREG-520) screen");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //4956762
		commonFunction.clickButtonContains("Continue ");
		
		// --- SREG-110 ---
		sleep(2000);
		commonFunction.screenShot("EM01904", "Pass", "Successful launch to Joint Employment/Management Agreement(SREG-110) screen");
		//empManage.employerFeinId_SREG110.sendKeys(feinValue);
		//commonFunction.enterTextboxContains("FEIN", feinValue); //633475366
		commonFunction.clickButtonContains(" Search ");
		
		sleep();
		commonFunction.selectRadio("");
		commonFunction.screenShot("EM01904", "Pass", "Navigated in SREG 110 screen.");
		commonFunction.clickButtonContains("Continue ");
		
		// --- SREG-007 ---
		sleep(2000);
		commonFunction.screenShot("EM01904", "Pass", "Successful launch to Related Business Details(SREG-007) screen");
		commonFunction.selectRadio("Joint Employment");
		commonFunction.selectRadioQuestions("Are businesses financially related, with the same principal(s) owning 50% or more of each business?", "Yes ");
		commonFunction.selectRadioQuestions("Joint Employment - Are Employees concurrently employed by both businesses?", "Yes ");
		commonFunction.selectRadioQuestions("Are businesses required to report separately for FUTA?", "No ");
		//empManage.remarksId_SREG435.sendKeys("Test Remarks");
		sleep();
		commonFunction.screenShot("EM01904", "Pass", "Details enetered on SREG-007 screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- SREG-114 ---
		sleep(2000);
		commonFunction.screenShot("EM01904", "Pass", "Successful launch to Verify Joint Employment or Management Agreement(SREG-114) screen");
		commonFunction.clickButtonContains("Continue ");
		
		// --- SREG-524 ---
		sleep(2000);
		commonFunction.screenShot("EM01904", "Pass", "Successful launch to Verify Joint Employment or Management Agreement(SREG-524) screen");
		commonFunction.clickButtonContains("Continue ");
		
		
		System.out.println("xxx");
	}
}
