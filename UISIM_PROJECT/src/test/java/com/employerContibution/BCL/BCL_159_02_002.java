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
public class BCL_159_02_002 extends TestBase {

	@Test(priority=1, description = "BCL.159.02.002  Verify CSR can select more than one collection notices and update the status by selecting the appropriate Status option",groups = {"Regression"})
	public void BCL_159_02_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);
		
        Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='ACTV' AND EAN LIKE '9%'","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		test = report.createTest("BCL.159.02.002  Verify CSR can select more than one collection notices and update the status by selecting the appropriate Status option");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		commonFuntions.ScrollMenu("Collections");
		commonFuntions.clickMenu("Collections");
		commonFuntions.ScrollMenu("Update Collection Notices");
		commonFuntions.clickMenu("Update Collection Notices");
		sleep();
		
		//---COL-589---
		commonFuntions.screenShot("Update Collection Notices", "Pass", "Update Collection Notices (COL-589)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,"0464364");
		commonFuntions.screenShot("Update Collection Notices", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(20000);
		
		//---COL-700---
		commonFuntions.screenShot("Update Collection Notice Status", "Pass", "Update Collection Notice Status (COL-700)screen launched");
		BCL.select_updateCollectionNoticeStatus.click();
		commonFuntions.selectDropdown("Status to be changed", " Returned");
		BCL.comment_updateCollectionNoticeStatus.sendKeys("for testing");
		commonFuntions.screenShot("Update Collection Notice Status", "Pass", "Record selected and Updated on (COL-549)screen");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		
		//---SUC-002---
	    commonFuntions.screenShot("Update Collection Notice Status - Confirmation", "Pass", "Update Collection Notice Status - Confirmation (SUC-002)screen launched");
	    commonFuntions.clickButtonContains("Home ");
		sleep(2000);
	
		//---HOME---
	    commonFuntions.screenShot("Home", "Pass", "Home screen launched");
	    sleep(2000);
	
	    //Done
	}
	
	
	
	
}
