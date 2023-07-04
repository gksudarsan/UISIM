package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_418_005_TPR_RemovesESides_CSR_Approves extends TestBase {
	
	@Test(priority=1, description = "Verify TPR is able to remove their own SIDES E-Response and the system creates task for CSR reviews and Approve",groups = {"Regression"})
	public void TC_EM_418_005() throws Exception
	{
		
		test = report.createTest("EM.418.005 : Verify TPR is able to remove their own SIDES E-Response and the system creates task for CSR reviews and Approve");
		
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
		commonFuntions.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance -> Maintain Address");
		commonFuntions.clickMenu("Maintain Address");
		
		// --- SREG-070 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM418005", "Pass", "Successfully launched Maintain Address – Enter ERN(SREG-070) page");
		commonFuntions.enterTextboxContains("Employer Registration Number", "9300106");
		sleep();
		commonFuntions.screenShot("EM418005", "Pass", "Entered ERN in SREG 430 page");
		commonFuntions.clickButtonContains("Continue ");
		
		// --- SREG-486 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM418005", "Pass", "Successful launch to Maintain Address Details(SREG-486) page");
		commonFuntions.selectRadioQuestions("Do you wish to register for SIDES E-Response?", "Yes ");
		commonFuntions.screenShot("EM418005", "Pass", "Selected Yes for SIDES E_reponse radio on SREG-486 page");
		commonFuntions.clickButtonContains("Submit ");
		
		// ---SUC-002---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EM418005", "Pass", "Successful launch to Employer Address aAnd Contact Person Details Confirmation(SUC-002) page");
		commonFuntions.clickButtonContains("Home ");
		
		commonFuntions.waitForLoadingIconToDisappear();
		sleep();
		commonFuntions.screenShot("EM418005", "Pass", "Successfully passed TC EM.418.005");
	    
		System.out.println("Pass :)");
	}

}
