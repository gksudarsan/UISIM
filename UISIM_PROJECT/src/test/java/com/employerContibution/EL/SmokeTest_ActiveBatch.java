package com.employerContibution.EL;

import static org.testng.AssertJUnit.assertEquals;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SmokeTest_ActiveBatch extends TestBase{

	@Test(priority=1, description = "Active Batch Smoke test",groups = {"Smoke"})
	public void SmokeTest_Benefits() throws Exception{
		 test = report.createTest("SmokeTest_Benefits");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 //commonFuntions.login(COMMON_CONSTANT.PEOSpecialist.toUpperCase(), COMMON_CONSTANT.PEOSpecialist_Password);
		 commonFuntions.activeBatchLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 loginPage.batchFolder.click();
		 loginPage.devEnvironmentFolder.click();
		 loginPage.ecFolder.click();
		 loginPage.caFolder.click();
	}
}
