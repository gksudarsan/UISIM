package com.employerContibution.BCL;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.COL_468;
import com.ui.pages.COL_592;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_06_001 extends TestBase{
	
	@Test
	public void BCL855_06_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_468 col468 = new COL_468(driver);
		COL_592 col592 = new COL_592(driver);  
		SUC_002 suc002 = new SUC_002(driver);

		test = report.createTest(
				"BCL.855.06.001 - Verify CSR can view and fixed exception list with select option 'Yes'");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		commonFuntions.ScrollMenu("Confidential Source");
		commonFuntions.clickMenu("Confidential Source");
		sleep(2000);
		commonFuntions.ScrollMenu("Exception List");
		commonFuntions.clickMenu("Exception List");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Exception List", "Pass", "COL-591 screen is displayed");
		
		//
		commonFuntions.clickButtonContains("Update ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("Update ‘Fixed’ column on at least one record to proceed.");
		commonFuntions.screenShot("Exception List", "Pass", "Error is displayed");
		
		//
		col592.dropDowndataTableIdFixedListCOL591.get(0).click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col592.selectYesFromDropdown.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Exception List", "Pass", "COL-591 screen is displayed");
		commonFuntions.clickButtonContains("Update ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Employer Referral Release/Removal Confirmation", "Pass", "SUC-002 screen is displayed");
		Assert.assertTrue(suc002.saleOfBusinessSuccessText.isDisplayed());
		
	}

}
