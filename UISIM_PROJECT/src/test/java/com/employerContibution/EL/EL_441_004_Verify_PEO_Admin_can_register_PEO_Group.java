package com.employerContibution.EL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_004_Verify_PEO_Admin_can_register_PEO_Group extends TestBase{
	@Test(priority = 1, description = "EL.441.004 - Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Limited Liability Company' and Type of Ownership 'Privately or Closely Held' Ownership'", groups = { "Regression" })
	public void Testing123() throws Exception {
		test = report.createTest("EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		HomePage Home = new HomePage(driver);
		PEOPage peoPage = new PEOPage(driver);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration peoEmployer = new PEO_001_ProfessionalEmployerOrganizationRegistration(driver);
		
		//Login
		commonFunction.login(COMMON_CONSTANT.PEO_USER_1.toUpperCase(), COMMON_CONSTANT.PEO_USER_1_PASSWORD);
		commonFunction.screenShot("Home", "Pass", "Login is successful");
		Home.navigateToPeoRegister();
		commonFunction.enterTextboxContains("First Name", "Joseph");
		commonFunction.enterTextboxContains("Last Name", "Moral");
		commonFunction.selectDropdown("Suffix", "Sr.");
		commonFunction.screenShot("Peo Registration", "Pass", "Details entered on PEO-019 page");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ",
				Long.toString(commonFunction.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address",
				"randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("Peo Registration", "Pass", "Details entered on PEO-019 page");
		commonFunction.clickButton("Save & Continue ");
		
		// PEO-001
		
}
}
