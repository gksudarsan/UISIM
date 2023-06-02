package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_417_001_CSR_SIDES_EResponse_SendBenefitsResponse extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is adding SIDES E-Response to send benefits notification for an employer account  ", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_417_001() throws Exception {
		
		test = report.createTest("Verify CSR is adding SIDES E-Response to send benefits notification for an employer account  ");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		 //valid EAN for Account Status -> LIAB
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		sleep(2000);
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Maintain Address");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Account Status");
		commonFunction.clickMenu("Maintain Address");
		
		//---SREG-070---
		sleep(2000);
		commonFunction.screenShot("EM417001", "Pass", "Successful launch to SREG-070 page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //5310412
		commonFunction.screenShot("EM417001", "Pass", "Searched for ERN in SREG-070 page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- SREG-486 ---
		sleep(2000);
		commonFunction.screenShot("EM417001", "Pass", "Successful launch to SREG-486 page");
		commonFunction.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		
		/*
		 * sleep(1000); empManage.sourceId_SREG435.click(); sleep();
		 * empManage.nys100_paper.click(); empManage.sourceTypeId_SREG435.click();
		 * sleep(); empManage.nys100.click();
		 */
		commonFunction.screenShot("EM411007", "Pass", "Edited Details in SREG-435 page");
		
		commonFunction.clickButtonContains("Submit ");

		// --- SUC - 002 ---
		sleep(2000);
		commonFunction.screenShot("EM411007", "Pass", "Successfully launched to SUC - 002 page");
		
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "Successfully TC passed");
	}
}
