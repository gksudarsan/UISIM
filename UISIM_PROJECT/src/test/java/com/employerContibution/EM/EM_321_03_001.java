package com.employerContibution.EM;



import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_03_001 extends TestBase
{


	
	@Test(priority=1, description = "EM.321.03.001 - Verify CSR is able to enter ERN and update business information of an employer account.",groups = {"Regression"})
	public void EM_321_02_008() throws Exception
	{
		 
		 test = report.createTest("EM.321.03.001 - Verify CSR is able to enter ERN and update business information of an employer account.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.clickMenu("Employer Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Accounts");
		 commonFuntions.clickMenu("Maintain Accounts");			 
		//Testcase is Blocked as field and page name is coming as different
		     
	   	   
	}
}