package com.employerContibution.SM;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIPage;
import com.ui.pages.PEOPage;
import com.ui.pages.smPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_004_006_CSR_CloseWithoutReplyButton extends TestBase {

	@Test
	public void SM_004_006() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		//AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		smPage sm = new smPage(driver);
		test = report.createTest(
				"SM.004.006-Verify that the CSR is able to close the workflow/workitem without replying to a received message. (Close without Reply Button)");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(5000);
		PEOPage.queue.click(); 
		cf.waitForLoadingIconToDisappear();
		sm.filterOption.sendKeys("current");
		cf.clickOnLinkAnchorTag("Current UI Rates");
		sleep(3000);
		cf.clickButtonContains("Open Work Item ");
		
	}

}
