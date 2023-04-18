package com.employerContibution.EM;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.SREG_507;

import stepDefinitions.commonStepDefinitions;

public class EM_449_001_Establish_joint_account_for_two_or_more_account extends TestBase {

	@Test(priority=1, description = "Test",groups = {"Regression"})
	public void EM_321_02_001() throws Exception
	{	 
		 test = report.createTest("EM.449.001- Verify CSR is able to establish joint account for two or more account  with  'Principal Business Activity'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 HomePage homePage = new HomePage(driver);
		 SREG_507 sreg507 = new SREG_507(driver);
		 	 
		 commonFuntions.login(prop.getProperty("CSR_UserID"),prop.getProperty("CSR_Pass"));
		 test.log(Status.INFO, "Navigating to Joint Account Menu");
		 homePage.navigateToEstablishJointAccount();
		 test.log(Status.PASS, "Sucessfully navigated to the Menu");
		 test.log(Status.INFO, "verify page Title as : Establish Joint Account Administrator");
		 sreg507.verifyPageTitle();
		 test.log(Status.PASS, "Page verified");
		 test.log(Status.INFO, "Entering the Details");
		 sreg507.fillForm("9300016", "ABCDEFGH", "Abhinav", "Sharma", "302 Test Address", "Test City", "32147", "3522343234", "TestAdmin@gmail.com");
		 test.log(Status.PASS, "Entered the details");
		 
		 
		 
}
}
