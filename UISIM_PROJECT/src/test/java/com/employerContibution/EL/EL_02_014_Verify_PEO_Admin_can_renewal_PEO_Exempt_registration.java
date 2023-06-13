package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_02_014_Verify_PEO_Admin_can_renewal_PEO_Exempt_registration extends TestBase {
	@Test(priority = 1, description = "EL.02.014 - Verify PEO Administrator can renewal PEO Exempt registration", groups = {
			"Regression" })
	public void EL_02_014() throws Exception {
		test = report.createTest("EL.02.014 - Verify PEO Administrator can renewal PEO Exempt registration");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage Home = new HomePage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		
		//Login
		commonFunction.login(COMMON_CONSTANT.PEO_USER_1.toUpperCase(), COMMON_CONSTANT.PEO_USER_1_PASSWORD);
		commonFunction.screenShot("Home", "Pass", "Login is successful");
		Home.navigateToRenewPeo();
		
		// RPEOE-001
		commonFunction.enterTextboxContains("Name of Professional Employer Organization", "Joseph");
		commonFunction.enterTextboxContains("Additional names if any under which", "Melson");
		commonFunction.clickButton("Continue ");
		// Data Conditioning Issue
	}
}