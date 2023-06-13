package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_475_03_001_TPR_RemovePoa_AllUnemploymentInsuranceMatters extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR is able to enter ERN and remove POA/TPR association for designation type 'All Unemployment insurance Matters' ", groups = {COMMON_CONSTANT.REGRESSION} )
	public void TC_EM_475_03_001() throws Exception {
		
		test = report.createTest("EM.475.03.001: Verify TPR is able to enter ERN and remove POA/TPR association for designation type \"All Unemployment insurance Matters\" ");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for Designation Type -> UIM
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE EAN IS NOT NULL AND EAN IN (SELECT EAN FROM T_EMPLOYER te WHERE employer_id IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION ttpcva WHERE DESIGNATION_TYPE = 'UIM' ))",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_2.toUpperCase(), COMMON_CONSTANT.TPR_USER_2_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Add or Remove POA/TPR Association");
		commonFunction.clickMenu("Add or Remove POA/TPR Association");
		
		// --- SREG-430 ---
		commonFunction.screenShot("EM47501001", "Pass", "Successful launch to Add or Remove POA/TPR Association – Enter ERN(SREG-430) b page");
		commonFunction.enterTextboxContains("Employer Registration Number", "4862216"); //4862216
		commonFunction.clickButtonContains("Continue ");
		
		//
		System.out.println("Need calrification with BA/EC Team for TPR-ERN association");
	}

}
