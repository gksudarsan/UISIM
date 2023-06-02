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

public class EM_321_007_CSR_Inactivate_PartnerDetails extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter the term end date and Inactivate Partner Details", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EM_321_005() throws Exception {
		
		test = report.createTest("Verify CSR is able to enter the term end date and Inactivate Partner Details");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET method
		// valid EAN for Org Type -> SPRI
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE IN ('LLP','PART','JVNI','LIMP') AND EAN IS NOT NULL AND LENGTH(EAN)=7",
				//"SELECT * FROM T_employer_account WHERE ORGANIZATION_TYPE IN ('LLP','PART','JVNI','LIMP') AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("EAN value is " + eanValue);
		
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The SSN generated is " + ssnValue);
		
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
		commonFunction.enterTextbox("Employer Registration Number", eanValue); //0504110
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		// --- SREG-704 ---	
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321005", "Pass", "Successfully launched to Partner Details(SREG-704) page");
		commonFunction.clickOnLink("inactive");
		commonFunction.screenShot("EM321007", "Pass", "Clicked on Incative button on SREG-704 page");
		commonFunction.clickButton(" Yes ");
		
		// ---SREG-703 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321007", "Pass", "Successfully launched to Partner Details(SREG-703) page");
		commonFunction.enterCurrentDate("Term End Date");
		//commonFunction.enterTextboxContains("Term End Date", "06/01/2023");
				
		commonFunction.screenShot("EM321007", "Pass", "Entered Details in SREG-703 page");
		commonFunction.clickButtonContains("Submit ");	
		sleep();
		 
		
		// --- SREG-704 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EM321007", "Pass", "Successful launch to SREG-704 page > Inactivated partner details successfully");
		
		//
		sleep(2000);
		commonFunction.screenShot("SuccessPage", "Pass", "TC EM_321_007 passed succesfully");
		
		System.out.println("pass :)");
	}

}
