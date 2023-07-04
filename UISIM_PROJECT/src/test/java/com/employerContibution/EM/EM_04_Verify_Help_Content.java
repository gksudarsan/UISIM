package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_04_Verify_Help_Content extends TestBase {
	
	@Test(priority=1, description = "Verify the Help content is as per the approved Help content in EM-Appendix I Help Screens",groups = {"Regression"})
	public void TC_EM_04() throws Exception
	{
		
		test = report.createTest("EM.04 : Verify the Help content is as per the approved Help content in EM-Appendix I Help Screens");
		
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
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
		commonFuntions.screenShot("EM04", "Pass", "Successful launch to Update NAICS/County Code(SREG-091) page");
		commonFuntions.enterTextboxContains("Employer Registration Number", "0463861");
		sleep();
		commonFuntions.screenShot("EM04", "Pass", "Entered ERN in SREG-091 page");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("EM04", "Pass", "Searched with ERN");
		
		empManage.helpIcon.click();;
		commonFuntions.screenShot("EM04", "Pass", "Clicked on Help Icon");
		
		commonFuntions.ScrollMenu("Navigation");
		commonFuntions.screenShot("EM04", "Pass", "Help Screen reached");
		
		commonFuntions.screenShot("EM04", "Pass", "TC EM.04 passed successfully");
		
		System.out.println();
	}

}
