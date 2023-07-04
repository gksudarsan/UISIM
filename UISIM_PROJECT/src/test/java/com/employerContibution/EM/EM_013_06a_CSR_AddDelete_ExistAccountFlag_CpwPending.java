package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_013_06a_CSR_AddDelete_ExistAccountFlag_CpwPending extends TestBase {
	
	@Test(priority=1, description = "Verify CSR is able to Add and Delete existing account flags information for flag type 'CPW Pending'  when flag is created in same session",groups = { COMMON_CONSTANT.REGRESSION })
	public void TC_EM_005_01() throws Exception {
		
		test = report.createTest("EM.013.06a : Verify CSR is able to Add and Delete existing account flags information for flag type 'CPW Pending'  when flag is created in same session");
		
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_5, COMMON_CONSTANT.CSR_USER_5_PASSWORD); 
		commonFuntions.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// --- Navigation ---
		commonFuntions.waitForLoadingIconToDisappear();
		empManage.menu.click();		
		commonFuntions.ScrollMenu("Update NAICS/County Code");
		commonFuntions.clickMenu("Update NAICS/County Code");
		sleep();
		commonFuntions.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Update NAICS/County Code");
		empManage.updateNAICS.click();
		
		// --- SREG-091 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM00501", "Pass", "Successful launch to Update NAICS/County Code(SREG-091) page");
		commonFuntions.enterTextboxContains("Employer Registration Number", "");
		sleep();
		commonFuntions.screenShot("EM00501", "Pass", "Searchd with blank ERN");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("EM00501", "Pass", "ERN can not be blank error");
		
		commonFuntions.enterTextboxContains("Employer Registration Number", "0525666");
		sleep();
		commonFuntions.screenShot("EM00501", "Pass", "Searched with invalid ERN");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("EM00501", "Pass", "Invalid ERN error");
		
		sleep();
		commonFuntions.enterTextboxContains("Employer Registration Number", "");
		commonFuntions.enterTextboxContains("Employer Registration Number", "0463861");
		sleep();
		commonFuntions.screenShot("EM00501", "Pass", "Entered ERN in SREG 091 page");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("EM00501", "Pass", "Searched with ERN");
		
		commonFuntions.enterTextboxContains("NAICS Code", "2541");
		empManage.accountCountyCodeId.sendKeys("124");
		empManage.commentId.sendKeys("Testing EM00501");
		empManage.ownerShipIdDropDown.click();
		sleep();
		empManage.stateGovtOwnCode.click();
		sleep(2000);
		commonFuntions.screenShot("EM00501", "Pass", "Entered details in SREG 091 page");
		commonFuntions.clickButtonContains("Submit ");
		
		// --- SREG-402 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM00501", "Pass", "Successful launch to Inquiry Business Location Details(SREG-402) page");
		//commonFuntions.clickButtonContains("Submit ");
		
		sleep(2000);
		commonFuntions.screenShot("EM00501", "Pass", "Successfully passed TC EM.005.01");
		
	}

}
