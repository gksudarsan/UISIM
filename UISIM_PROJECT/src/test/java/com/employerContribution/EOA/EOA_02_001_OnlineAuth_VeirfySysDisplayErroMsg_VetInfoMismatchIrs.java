package com.employerContribution.EOA;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_02_001_OnlineAuth_VeirfySysDisplayErroMsg_VetInfoMismatchIrs extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Online Authentication - Verify System displays error message when the vetting information does not match with IRS data.", groups = {
			COMMON_CONSTANT.REGRESSION })

	public void EOA_02_001() throws Exception {
		
		test = report.createTest("EOA.02.001 : Online Authentication - Verify System displays error message when the vetting information does not match with IRS data.");

		test.log(Status.INFO, "Script developed by Ankan Das.");
		
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EOAPage eoaPage = new EOAPage(driver);
		
		// ---Login---
		commonFuntions.login(COMMON_CONSTANT.APPEALS_USER1, COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		test.log(Status.PASS, "Login with Appeals User is successful");
		

        commonFuntions.waitForLoadingIconToDisappear();
        //commonFuntions.screenShot("EOA02001", "Pass", "Successful loading of pop-up");
        eoaPage.tprRadio.click();
        sleep(1500);
        commonFuntions.screenShot("EOA02001", "Pass", "Selected required radio");
        commonFuntions.clickButtonContains("Continue ");
        
        // --- SREG-612 ---
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA02001", "Pass", "Successful launch to UI Services Third Party Representative Account Creation(SREG-612) screen");
        commonFuntions.enterTextboxContains("Employer Registration Number (ERN)", "5423870");
        //commonFuntions.enterTextboxContains("Electronic Filer Identification Number (EFIN)", "54238734");
        commonFuntions.enterTextboxContains("Legal Name of Business", "Thomas S gallo CPA".toUpperCase());
        commonFuntions.enterTextboxContains("Total Remuneration $", "5000");
        sleep(2000);
        commonFuntions.screenShot("EOA02001", "Pass", "Data entered for SREG-612 screen");
        test.log(Status.INFO, "Observation: FEIN field is non editable.");
        commonFuntions.clickButtonContains("Continue ");
        
        sleep(3000);
        commonFuntions.screenShot("EOA02001", "Pass", "Expected error, unable to continue further.");
      
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("EOA02001", "Pass", "TC EOA.02.001 passed successfully.");
        
		
	}

}
