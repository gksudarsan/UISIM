package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_413_02_002_Employer_Updates_BeginningLiabilityDate_CSR_Review_Created_Task_Deny extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to update employer 'Beginning Liability Date' of an existing employer account.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EM_413_02_002() throws Exception {
		
		test = report.createTest("Verify CSR is able to update employer 'Beginning Liability Date' of an existing employer account.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
				
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		sleep();
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
		empManage.employerAccountMaintanceMenu.click();
		
		
		// --- SREG-030 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("SREG30", "Pass", "Successful launch to SREG 030 page");
		commonFunction.clickButton("Submit ");
		
		// --- SUC - 002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("SUC002", "Pass", "Successful launch to SUC 002 page");
		commonFunction.clickButtonContains("Home");
		
		sleep(2000);
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("Employer Registration Number","9082249");
	    commonFunction.screenShot("EanSearch","Pass","EAN Search for Task");
	    commonFunction.clickButtonContains("Search");
	    
	    sleep(2000);
	    commonFunction.ScrollMenu("Review Employer Agent Change");
	    commonFunction.screenShot("WIClick","Pass","Click on Task");
	    commonFunction.clickOnLink("Review Employer Agent Change");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("WF091","Pass","Launched to WF-091 page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	    // --- EMWI-006 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EMWI006","Pass","Launched to EMWI-006 page");
	    commonFunction.selectDropdown("Select the Action", " Deny ");
	    sleep(2000);
	    empManage.commentId.sendKeys("Not Approved");
	    sleep(2000);
	    commonFunction.screenShot("EMWI006","Pass","Launched to EMWI-006 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC 002 ---
	    
	    commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("SUC002", "Pass", "Successful launch to SUC 002 page");
		commonFunction.clickButtonContains("Home");
		
		commonFunction.waitForLoadingIconToDisappear();
		
		commonFunction.screenShot("SUccessPage", "Pass", "TC EM.413.02.002 Passed");

	    System.out.println("Pass");
	}

}
