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
public class SmokeTest_Benefits extends TestBase{

	@Test(priority=1, description = "Benefits Smoke test",groups = {"Smoke"})
	public void SmokeTest_Benefits() throws Exception{
		 test = report.createTest("SmokeTest_Benefits");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 //commonFuntions.login(COMMON_CONSTANT.PEOSpecialist.toUpperCase(), COMMON_CONSTANT.PEOSpecialist_Password);
		 commonFuntions.benefitsLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 boolean valueSysFailure=false;
		 boolean valueInternalError=false;
		 try {
		  valueSysFailure = PEOPage.systemFailure.isDisplayed();
		  valueInternalError = PEOPage.internalError.isDisplayed();
		 }
		 catch(Exception e) {}
		 assertEquals(Boolean.FALSE, Boolean.valueOf(valueSysFailure));
		 assertEquals(Boolean.FALSE, Boolean.valueOf(valueInternalError));
		 
		 //commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");		 
		 commonFuntions.clickMenu("Menu");
		 //PEOPage.menu.click();	
		 commonFuntions.ScrollMenu("");
		 commonFuntions.clickMenu("Secure Messaging");
		 sleep(2000);
		 //commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Write Message");	
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 //commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 
		 Thread.sleep(3000);
	     	     
	}
}
