package com.employerContribution.ERM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_003;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class ERM_478_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ERM.478.001.Verify CSR can enter and reenter rate factor information and calculate the annual experience rate , system create the task for CSR reviews and approve.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_478_001() throws Exception {

		test = report.createTest(
				"ERM.478.001.Verify CSR can enter and reenter rate factor information and calculate the annual experience rate , system create the task for CSR reviews and approve.");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_003 sreg003 = new SREG_003(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test.info("Step: 1&2 -- Login with valid crdentials");
		commonFunction.login(COMMON_CONSTANT.CSR_USER_3.toUpperCase(), COMMON_CONSTANT.CSR_USER_3_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		test.info("Step: 3");
		commonFunction.clickMenu("menu");
		sleep(1000);
		commonFunction.ScrollMenu("Annual UI Rate");
		commonFunction.clickMenu("Annual UI Rate");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("HomePage", "Pass", "Navigate to Main Menu > Annual UI Rate > Enter Rate Factors");
		commonFunction.ScrollMenu("Enter Rate Factors");
		commonFunction.clickMenu("Enter Rate Factors");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Enter Rate Factors", "Pass", "ETR-219 Screen is displayed");
		
		test.info("Step: 4");
		sleep(1000);
		commonFunction.clickButtonContains("Home ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("HomePage", "Pass", "Hompage Screen is displayed");
		
		test.info("Step: 5");
		commonFunction.clickMenu("menu");
		sleep(1000);
		commonFunction.ScrollMenu("Annual UI Rate");
		commonFunction.clickMenu("Annual UI Rate");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("HomePage", "Pass", "Navigate to Main Menu > Annual UI Rate > Enter Rate Factors");
		commonFunction.ScrollMenu("Enter Rate Factors");
		commonFunction.clickMenu("Enter Rate Factors");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Enter Rate Factors", "Pass", "ETR-219 Screen is displayed");
		
		test.info("Step: 6");
		commonFunction.clickButton("Continue ");
		sleep(1000);
		commonFunction.screenShot("Enter Rate Factors", "Pass", "Required Error");
		
		test.info("Step: 7");
		commonFunction.enterTextboxContains("Size of Fund Index", "&$%#");
		commonFunction.enterTextboxContains("General Account Balance ($)", "&$%#");
		commonFunction.enterTextboxContains("Average Annual Wage ($)", "&$%#");
		commonFunction.clickButton("Continue ");
		sleep(1000);
		commonFunction.screenShot("Enter Rate Factors", "Pass", "Required Error");
		
		test.info("Step: 8"); //Not applicable as not able to enter invalid value in field name General Account Balance.
		
		test.info("Step: 9");
		commonFunction.forceClearTextWithElement("Size of Fund Index");
		commonFunction.forceClearTextWithElement("General Account Balance ($)");
		commonFunction.forceClearTextWithElement("Average Annual Wage ($)");
		sleep(1000);
		commonFunction.enterTextboxContains("Size of Fund Index", "706892");
		commonFunction.enterTextboxContains("General Account Balance ($)", "65200");
		commonFunction.enterTextboxContains("Average Annual Wage ($)", "657383");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Verify Rate Factors", "Pass", "ETR-220 screen is displayed");
		
		test.info("Step: 10");
		commonFunction.clickButton("Submit ");
		sleep(1000);
		commonFunction.screenShot("Enter Rate Factors", "Pass", "Required Error");
		
		test.info("Step: 11");
		commonFunction.enterTextboxContains("Size of Fund Index", "1000");
		commonFunction.enterTextboxContains("General Account Balance ($)", "200");
		commonFunction.enterTextboxContains("Average Annual Wage ($)", "3000");
		commonFunction.clickButton("Submit ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Enter Rate Factors", "Pass", "Required Error");
		
		test.info("Step: 12");
		commonFunction.clickButton(" Previous ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Enter Rate Factors", "Pass", "ETR-219 Screen is displayed");
		
		test.info("Step: 13");
		commonFunction.forceClearTextWithElement("Size of Fund Index");
		commonFunction.forceClearTextWithElement("General Account Balance ($)");
		commonFunction.forceClearTextWithElement("Average Annual Wage ($)");
		sleep(1000);
		commonFunction.enterTextboxContains("Size of Fund Index", "706893");
		commonFunction.enterTextboxContains("General Account Balance ($)", "65201");
		commonFunction.enterTextboxContains("Average Annual Wage ($)", "657382");
		commonFunction.clickButton("Continue ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Verify Rate Factors", "Pass", "ETR-220 screen is displayed");
		
		test.info("Step: 14");
		commonFunction.enterTextboxContains("Size of Fund Index", "706893");
		commonFunction.enterTextboxContains("General Account Balance ($)", "65201");
		commonFunction.enterTextboxContains("Average Annual Wage ($)", "657382");
		commonFunction.clickButton("Submit ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Factors for Rate Confirmation", "Pass", "SUC-002 screen is displayed");
		
		test.info("Step: 15");
		commonFunction.clickButtonContains("Home ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("HomePage", "Pass", "Hompage Screen is displayed");
		
		test.info("Step: 16");
		PEOPage.queue.click();
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(1000);
		
	}

}
