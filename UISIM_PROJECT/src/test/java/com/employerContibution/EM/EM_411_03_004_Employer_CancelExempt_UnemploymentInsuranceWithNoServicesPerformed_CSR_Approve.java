package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_411_03_004_Employer_CancelExempt_UnemploymentInsuranceWithNoServicesPerformed_CSR_Approve extends TestBase {

	@Test(priority=1, description = "Verify Employer is able to select option Cancel Exempt for Unemployment Insurance with No services being performed in New York State and the system creates a task and CSR reviews and Approve",groups = {"Regression"})
	public void TC_EM_411_03_004() throws Exception
	{
		
		test = report.createTest("EM.411.03.004 : Verify Employer is able to select option Cancel Exempt for Unemployment Insurance with No services being performed in New York State and the system creates a task and CSR reviews and Approve");
		
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_8, COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login with Employer is successful");			    
		
		// --- Navigation ---
		commonFuntions.waitForLoadingIconToDisappear();
		empManage.menu.click();		
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		sleep();
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.ScrollMenu("Close or Cancel Account");
		sleep(2000);
		commonFuntions.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Close or Cancel Account");
		commonFuntions.clickMenu("Close or Cancel Account");
		
		// --- SREG-438 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM41103004", "Pass", "Successful launch to Close or Cancel Account(SREG-438) page");
		commonFuntions.selectRadio("Do you want to cancel your account?");
		sleep();
		commonFuntions.selectRadio("Cancel Exempt for Unemployment Insurance");
		sleep();
		commonFuntions.selectDropdownEquals("Close or Cancel Account", " No services being performed in New York State (NYS withholding tax only for employee residing in New York State) ");
		
		sleep(2000);
		commonFuntions.selectLink("Document", "Browse");
 		sleep(2000);
 		commonFuntions.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFuntions.screenShot("EM41103004", "Pass", "Sample document uploaded");
 		
 		commonFuntions.clickButtonContains("Continue ");
 		
 		// --- SUC-002 ---
 		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM41103004", "Pass", "Successful launch to Account Close/Cancel Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_6, COMMON_CONSTANT.CSR_USER_6_PASSWORD);
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login with CSR is successful");	
		
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_6+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='5667234' ORDER BY UPDATED_TS desc)");
		//FEIn-> 409446450, EAN -> 5667234
		Thread.sleep(5000);
	     
		peoPage.queue.click();
	    Thread.sleep(15000);
		commonFuntions.screenShot("EM41103004","Pass","Update query to bind WI to user : UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = 'NDSBB3' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='5667234' ORDER BY UPDATED_TS desc)");
	    commonFuntions.waitForLoadingIconToDisappear();
	    
	    commonFuntions.enterTextboxContains("Employer Registration Number","5667234");
	    sleep();
	    commonFuntions.screenShot("wiSearch","Pass","Searched with EAN");
	    commonFuntions.clickButtonContains("Search");
	    
	    sleep(3000);
	    commonFuntions.ScrollMenu("Account Cancellation Request");
	    sleep();
	    commonFuntions.screenShot("WIClick","Pass","Clicked on Work Item - 'Account Cancellation Request'");
	    sleep();
	    commonFuntions.clickOnLink("Account Cancellation Request");
	    
	    // --- WF-091 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EM41103004", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFuntions.clickButtonContains("Open Work Item ");
		
		// --- EMWI-010 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    sleep(5000);
	    commonFuntions.screenShot("EM41103004", "Pass", "Successful launch to Account Cancellation Request Task(EMWI-010) page");
	    commonFuntions.selectDropdown("Select the Action", " Approve ");
	    sleep(2000);
	    empManage.commentId.sendKeys("Ok to Approve");
	    sleep(2000);
	    commonFuntions.screenShot("EM41103004","Pass","Details entered in SREG-499 page");
	    commonFuntions.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFuntions.waitForLoadingIconToDisappear();
	    commonFuntions.screenShot("EM41103004", "Pass", "Successful launch to Task Confirmation Screen(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		sleep();
		commonFuntions.screenShot("EM41103004", "Pass", "Successfully passed TC EM.411.03.004");
	    
		System.out.println("Pass :)");
	}
}
