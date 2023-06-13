package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_411_008_CSR_Update_Employer_Account_Reinstate extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to update account status of employer account 'Reinstate'", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_411_008() throws Exception {
		
		test = report.createTest("Verify CSR is able to update account status of employer account 'Reinstate'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for Account Status -> LIAB
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS = 'SUSB' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS = 'LIAB' AND ORGANIZATION_TYPE = 'BUSI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Account Status");
		commonFunction.clickMenu("Maintain Account Status");
		
		// --- SREG-434 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM411008", "Pass", "Successfully launched to Maintain Account Status - Enter ERN(SREG-434) page");
		sleep();
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //5434567
		commonFunction.clickButton("Continue ");
		
		// --- SREG-435 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM411008", "Pass", "Successfully launched to Update Account Status(SREG-434) page");
		commonFunction.selectDropdown("Status of Employer Account", " Re-Instated ");
		commonFunction.selectRadioQuestions("Send a Closure Letter (IA31.6)?", "Yes ");
		empManage.sourceId_SREG435.click();
		sleep();
		empManage.correspondence_SREG435.click();
		empManage.sourceTypeId_SREG435.click();
		sleep();
		empManage.correspondence_SREG435.click();
		commonFunction.screenShot("EM411008", "Pass", "Edited Details in SREG-435 page");
		commonFunction.clickButtonContains("Submit ");
		
		//commonFunction.screenShot("EM411008", "Fail", "Unable to submit");

		// --- SUC - 002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM411008", "Pass", "Successfully launched to SUC - 002 page");
		
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_411_008 passed succesfully");
		
		//commonFunction.Label("Employer Registration Number "+eanValue+" has been cancelled successfully effective 4/2023");
		System.out.println("pass");
		
	}

}
