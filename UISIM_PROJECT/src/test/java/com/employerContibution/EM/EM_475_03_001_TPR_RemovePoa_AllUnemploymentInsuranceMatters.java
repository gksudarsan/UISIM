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
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND EAN IN (SELECT EAN FROM T_EMPLOYER te WHERE employer_id IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION ttpcva WHERE DESIGNATION_TYPE = 'UIM' ))",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is" + eanValue);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		empManage.menu.click();		
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		sleep();
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> ");
		commonFunction.clickMenu("Add or Remove POA/TPR Association");
		
		// --- SREG-430 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM47503001", "Pass", "Successful launch to Add or Remove POA/TPR Association(SREG 430) page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //4600907
		sleep();
		commonFunction.screenShot("EM47503001", "Pass", "Entered ERN in SREG 430 page");
		commonFunction.clickButtonContains("Continue ");
		
		// ---SREG-537---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM47503001", "Pass", "Successful launch to Add or Remove POA/TPR Association(SREG 537) page");
		commonFunction.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		sleep();
		commonFunction.screenShot("EM47503001", "Pass", "Selected appropriate drop-down");
		empManage.commentId.sendKeys("Testing All Unemp");
		commonFunction.selectCheckbox("Additional authorization");
		commonFunction.screenShot("EM47503001", "Pass", "Added information to SREG 537 page");
		commonFunction.clickButtonContains("Submit ");
		
		// ---SUC-002---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM47503001", "Pass", "Successful launch to Add or Remove POA/Third Party Representative Association to Employer Confirmation(SUC-002) page");
		commonFunction.clickButtonContains("Home ");
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM47503001", "Pass", "Successfully passed TC EM.475.03.001");

		
		//
		System.out.println("Pass :)");
	}

}
