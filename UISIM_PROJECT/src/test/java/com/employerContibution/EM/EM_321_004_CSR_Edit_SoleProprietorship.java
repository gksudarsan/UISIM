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

public class EM_321_004_CSR_Edit_SoleProprietorship extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to Edit Sole Proprietorship Details.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_321_004() throws Exception {
		
		test = report.createTest("Verify CSR is able to Edit Sole Proprietorship Details.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for Org Type -> SPRI
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'SPRI' AND EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE = 'SPRI' AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
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
		commonFunction.ScrollMenu("Maintain Business Ownership");
		commonFunction.screenShot("MenuNavigation", "Pass", "Navigated to Menu -> Account Maintenance -> Maintain Business Ownership");
		commonFunction.clickMenu("Maintain Business Ownership");
		
		// --- SREG-029 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("MenuNavigation", "Pass", "Successful launch to Maintain Business Ownership Details - Enter ERN(SREG-029) Page");
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //5637697
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-706 ---		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321004", "Pass", "Successfully launched to Sole Proprietor Details(SREG-706) page");
		commonFunction.clickOnLink("Edit");
		
		// --- SREG-705 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321004", "Pass", "Successfully launched to Edit Sole Proprietor Details(SREG-705) page");
		commonFunction.enterTextboxContains("First Name ", "");
		sleep();
		commonFunction.enterTextboxContains("First Name ", "RAM Updated");
		commonFunction.enterTextboxContains("Address Line 1 ", " ABC ");
		commonFunction.selectDropdown("State", " New York ");
		
		empManage.sourceId_SREG705.click();
		sleep();
		empManage.nys100Paper_SREG705.click();
		commonFunction.selectDropdownEquals("Source Type", " NYS-100 ");
		
		sleep(2000);
		commonFunction.screenShot("EM321004", "Pass", "Edited Details in SREG-705 page");
		commonFunction.clickButtonContains("Submit ");
		commonFunction.clickButtonContains(" Yes ");
		
		try {
			
			commonFunction.clickButtonContains("Submit ");
			commonFunction.clickButtonContains(" Yes ");
		} catch(Exception exception) { }
		

		
		//--- SREG-706 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("SuccessPage", "Pass", "Succesfully launched SREG-706 page");
		
		
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_321_004 passed succesfully");
		
		System.out.println("pass :)");
	}

}
