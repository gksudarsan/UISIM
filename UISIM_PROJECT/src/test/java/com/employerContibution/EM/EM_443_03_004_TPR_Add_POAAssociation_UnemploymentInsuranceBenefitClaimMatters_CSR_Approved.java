package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_443_03_004_TPR_Add_POAAssociation_UnemploymentInsuranceBenefitClaimMatters_CSR_Approved extends TestBase {

	@Test(priority=1, description = "Verify TPR is able to enter ERN and Add POA/TPR association for designation type \"Unemployment Insurance Benefit Claim Matters\" and the system creates task for CSR reviews and Approved.",groups = {"Regression"})
	public void TC_EM_443_03_004() throws Exception
	{
		
		test = report.createTest("EM.443.03.004 : Verify TPR is able to enter ERN and Add POA/TPR association for designation type \"Unemployment Insurance Benefit Claim Matters\" and the system creates task for CSR reviews and Approved.");
		
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3, COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// --- Navigation ---
		commonFuntions.waitForLoadingIconToDisappear();
		empManage.menu.click();		
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		sleep();
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> ");
		commonFuntions.clickMenu("Add or Remove POA/TPR Association");
		
		// ---SREG-430---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Add or Remove POA/TPR Association(SREG 430) page");
		commonFuntions.enterTextboxContains("Employer Registration Number", "9300106");
		sleep();
		commonFuntions.screenShot("EM44303004", "Pass", "Entered ERN in SREG 430 page");
		commonFuntions.clickButtonContains("Continue ");
		
		// ---SREG-537---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Add or Remove POA/TPR Association(SREG 537) page");
		commonFuntions.selectDropdown("Designation Type", " Unemployment Insurance Benefit Claim Matters ");
		sleep();
		commonFuntions.screenShot("EM44303004", "Pass", "Selected appropriate drop-down");
		empManage.commentId.sendKeys("Testing Unemp");
		commonFuntions.selectCheckbox("Additional authorization");
		commonFuntions.screenShot("EM44303004", "Pass", "Added information to SREG 537 page");
		commonFuntions.clickButtonContains("Submit ");
		
		// ---SUC-002---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Add or Remove POA/Third Party Representative Association to Employer Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);

		commonFuntions.waitForLoadingIconToDisappear();
		peoPage.queue.click();
	    Thread.sleep(20000);
	    
	    commonFuntions.enterTextboxContains("Employer Registration Number","9300106");
	    commonFuntions.screenShot("EanSearch","Pass","EAN Search for Task");
	    commonFuntions.clickButtonContains(" Search ");
	    
	    sleep(3000);
	    commonFuntions.ScrollMenu("Review Employer Agent Change");
	    sleep();
	    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Employer Agent Change'");
	    sleep();
	    commonFuntions.clickOnLink("Review Employer Agent Change");
	    
	    // --- WF-091 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFuntions.clickButtonContains("Open Work Item ");
	    
	    // --- SREG-499 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Approve POA/Third Party Association to Employer(SREG-499) page");
	    commonFuntions.selectDropdown("Select the action", " Approve ");
	    sleep(2000);
	    empManage.commentId.sendKeys("Unemployment Insurance Benefit Claim Matters");
	    sleep(2000);
	    commonFuntions.screenShot("EM44303004","Pass","Details entered in SREG-499 page");
	    commonFuntions.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EM44303004", "Pass", "Successful launch to Add or Remove POA/Third Party Representative Association to Employer Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		sleep();
		commonFuntions.screenShot("EM44303004", "Pass", "Successfully passed TC EM.443.03.004");
	    
		System.out.println("Pass :)");
		}
}
