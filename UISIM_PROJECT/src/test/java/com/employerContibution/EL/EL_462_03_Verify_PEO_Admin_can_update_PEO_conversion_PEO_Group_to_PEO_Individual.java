package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_462_03_Verify_PEO_Admin_can_update_PEO_conversion_PEO_Group_to_PEO_Individual extends TestBase {
	@Test(priority = 1, description = "EL.462.03 - Verify PEO Admin can update PEO conversion 'PEO Group to PEO Individual'", groups = {
			"Regression" })
	public void EL_462_03() throws Exception {
		test = report
				.createTest("EL.462.03 - Verify PEO Admin can update PEO conversion 'PEO Group to PEO Individual'");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage Home = new HomePage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		
		// Login
		commonFunction.login(COMMON_CONSTANT.PEO_USER_1.toUpperCase(), COMMON_CONSTANT.PEO_USER_1_PASSWORD);
		commonFunction.screenShot("Home", "Pass", "Login is successful");
		Home.navigateToManagePeo();
		
		// MPEO-003
		sleep(2000);
		commonFunction.selectDropdown("PEO Conversion", " PEO Group to PEO Individual ");
		commonFunction.clickButtonContains("  CONVERT ");
		// Data Issue

	}
}
