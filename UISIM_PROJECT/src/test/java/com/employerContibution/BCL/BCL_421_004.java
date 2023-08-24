package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.COL_468;
import com.ui.pages.COL_527;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_421_004 extends TestBase{
	
	@Test
	public void BCL421_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_468 col468 = new COL_468(driver);
		COL_527 col527 = new COL_527(driver);

		test = report.createTest(
				"BCL.421.004 -  Verify CSR can add a collection hold on the account with reason for hold is Pending Payment");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Contribution Collection");
		commonFuntions.clickMenu("Contribution Collection");
		sleep(2000);
		//commonFuntions.ScrollMenu("Pay Agreement");
		//commonFuntions.clickMenu("Pay Agreement");
		col468.maintainCollectionHoldBtn.click();
		// sleep(1000);
		//commonFuntions.ScrollMenu("Complete Payment Plan");
		//commonFuntions.screenShot("Complete Payment Plan", "Pass", "Complete Payment Plan");
		//commonFuntions.clickMenu("Complete Payment Plan");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "COL-527 screen is displayed");
		//
		Map<String, String> EANOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV';",
				"EAN");
		String eanCorrect = EANOutput.get("EAN");
		System.out.println(eanCorrect);
		//
		
		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanCorrect);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Maintain Collection Hold", "Pass", "COL-527 screen is displayed");
		
		col527.addCollectionHoldLink.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold", "Pass", "COL-528 screen is displayed");
		
		test.info("Step: 5 -- ");
		commonFuntions.enterCurrentDate("Hold Start Date");
		commonFuntions.selectDropdown("Reason For Hold", " Pending Payment ");
		col527.otherReasonField.sendKeys("other test");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold Verification", "Pass", "COL-529 screen is displayed");
		
		test.info("Step: 6 -- ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Add Collection Hold Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 7 -- ");
		commonFuntions.clickButtonContains("Home ");
        commonFuntions.waitForLoadingIconToDisappear();
        commonFuntions.screenShot("HomePage", "Pass", "Home Page screen is displayed");
		
		
	}

}
