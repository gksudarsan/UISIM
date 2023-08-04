package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_491_04_001 extends TestBase {

	@Test(priority=1, description = "BCL.491.04.001. Verify CSR can cancel an existing Payment Plan for reason for cancelling is Bankruptcy",groups = {"Regression"})
	public void BCL_491_04_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);
		
        Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='ACTV' AND EAN LIKE '9%'","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		test = report.createTest("BCL.491.04.001. Verify CSR can cancel an existing Payment Plan for reason for cancelling is Bankruptcy");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		commonFuntions.ScrollMenu("Pay Agreement");
		commonFuntions.clickMenu("Pay Agreement");
		commonFuntions.ScrollMenu("Cancel Payment Plan");
		commonFuntions.clickMenu("Cancel Payment Plan");
		sleep();
		
		//---COL-778---
		commonFuntions.screenShot("Cancel Payment Plan – Enter ERN", "Pass", "Cancel Payment Plan – Enter ERN (COL-778)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.screenShot("Cancel Payment Plan – Enter ERN", "Pass", "Message 'Required' on COL-778");
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111111");
		commonFuntions.clickButton("Continue ");
		sleep();
		commonFuntions.screenShot("Cancel Payment Plan – Enter ERN", "Pass", "Message 'No Payment Plan is found for the ERN provided.' on COL-778");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"5253968");
		commonFuntions.screenShot("Cancel Payment Plan – Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(20000);
		
		//---COL-779---
		commonFuntions.screenShot("Cancel Payment Plan", "Pass", "Cancel Payment Plan (COL-779)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.screenShot("Cancel Payment Plan", "Pass", "Message 'Cancelation Reason is a required field' on COL-779");
		commonFuntions.selectDropdown("Cancellation Reason", " Bankruptcy ");
		sleep();
		BCL.comments_CancelPaymentPlan.sendKeys("For Testing");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		
			
		//---SUC-002---
	    commonFuntions.screenShot("Cancel Payment Plan Confirmation", "Pass", "Cancel Payment Plan Confirmation (SUC-002)screen launched");
	    commonFuntions.clickButtonContains("Home ");
		sleep(2000);
	
		//---HOME---
	    commonFuntions.screenShot("Home", "Pass", "Home screen launched");
	    sleep(2000);
	
	    //Done
	}
	
	
	
	
}
