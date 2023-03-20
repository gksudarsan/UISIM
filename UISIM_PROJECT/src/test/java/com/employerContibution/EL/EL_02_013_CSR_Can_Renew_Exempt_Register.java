package com.employerContibution.EL;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_02_013_CSR_Can_Renew_Exempt_Register extends TestBase{
	
	@Test
	public void EL_02_013() throws Exception {
		 test = report.createTest("EL.02.012 : Verify CSR can register  PEO Exempt for Type of Legal Entity ' Limited Liability Partnership'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 
		 String peoName = "";
		 
		 commonFuntions.login("ndfjp3","Admin@12345678");
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 commonFuntions.clickMenu("Renew PEO");
		 Thread.sleep(2000);
		 commonFuntions.enterTextbox("PEO Name", peoName);
		 
	}
}
