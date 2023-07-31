package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_314_007 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "EM.314.007 - Verify CSR is able to enter request for change method of financing to Contributory for employer type Indian Tribe.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void EM_314_007() throws Exception {
		
		test = report.createTest("EM.314.007 - Verify CSR is able to enter request for change method of financing to Contributory for employer type Indian Tribe.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		
		// DB Query
		// Valid ERN
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT WHERE EAN = '0728425'",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);
				
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.ScrollMenu("Change in Method of Financing");
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Change in Method of Financing");
		commonFunction.clickMenu("Change in Method of Financing");
		
		
		// --- ETR-228 ---
		
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing(ETR-228) page");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.clickButton("Continue ");
		
		// --- ETR-229 ---
		
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change in Method of Financing(ETR-229) page");
		commonFunction.selectRadio("Yes ");
		commonFunction.enterTextboxContains("Enter the receipt date of written request.", "6/14/2023");
		commonFunction.enterTextbox("Requested Effective Date", "6/8/2023");
		commonFunction.enterTextbox("New ERN", "7766639");
		empPage.commentBox_MyQ.sendKeys("For Testing");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.screenShot("EM314001", "Pass", "Details entered On Change in Method of Financing(ETR-229) page");
		commonFunction.clickButton("Continue ");	
	
		// --- ETR-230 ---
		
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change Method of Financing Verification(ETR-230) page");
		commonFunction.selectRadio("Yes ");
		commonFunction.clickButton("Submit ");
	
		// --- SUC-002 ---
		
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Change Method of Financing Confirmation(SUC-002) page");
		commonFunction.clickButton("Home ");
		
		// --- Home ---
		
		commonFunction.screenShot("EM314001", "Pass", "Successful launch to Home page");
	}

}
