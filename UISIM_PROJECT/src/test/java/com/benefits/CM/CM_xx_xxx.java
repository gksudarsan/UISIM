package com.benefits.CM;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class CM_xx_xxx extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Benfeits URL check. :)", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_BRD_XX_XXX() throws Exception {
		
test = report.createTest("CM.XX.XXX : This is a test to Benefits");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		
		commonFunction.benefitsLogin("BasicBenefitsInquiry", "NewUser@123456");
		test.log(Status.PASS, "Login with Basic Benefits Inquiry is successful");
		
	}

}
