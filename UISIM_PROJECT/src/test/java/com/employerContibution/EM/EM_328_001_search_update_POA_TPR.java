package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_328_001_search_update_POA_TPR {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class EM_328_001 extends TestBase
	{
		

		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			//claimsIntake cl = new claimsIntake();
		
			
			test = report.createTest("EM.328.001 Verify CSR is able to search POA/TPR Legal name of business and update POA/TPR profile details without additional address");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			cf.screenShot("ApplicationLogin","Pass","Login is successful");
			cf.clickMenu("Menu");	sleep();
			cf.clickMenu("Account Maintenance");sleep();
			cf.screenShot("Menu","Pass","AccountMaintenance");
			cf.clickMenu("Maintain Reporting Service Details");sleep(); 
			cf.screenShot("Menu","Pass","AccountMaintenance");
			cf.enterTextbox("*POA/TPR Legal Name", "ABCD");sleep();
			cf.screenShot("Menu","Pass","");
			cf.clickButtonContains("Search");sleep();
			cf.screenShot("Menu","Pass","");
			cf.selectRadio("");sleep();
			
			cf.clickButtonContains("Continue");sleep();
			cf.screenShot("Menu","Pass","");
			cf.enterTextbox("Middle Initial","A");sleep();
			cf.selectDropdown("Source", " Correspondence/Email ");sleep(2000);
			cf.selectDropdown("Source Type", " Correspondence/Email ");sleep(2000);
			cf.screenShot("Menu","Pass","");
			cf.clickButtonContains("Submit");sleep();
			cf.screenShot("Menu","Pass","");
			test.log(Status.PASS, "Clicked on close button");

		}
	}
}