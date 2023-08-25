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
public class BCL_855_08_004 extends TestBase {

	@Test(priority=1, description = "BCL.855.08.004 -   Verify CSR can search ERN details and add new prosecution for Prosecution Type is 'Other' with comments and status is 'Judgment'",groups = {"Regression"})
	public void BCL_855_08_004() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);
		
        Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea  WHERE EAN IS NOT NULL ","EAN");
        String eanValue = databaseEanResult.get("EAN");
        System.out.println(eanValue);
        
		test = report.createTest("BCL.855.08.004 -   Verify CSR can search ERN details and add new prosecution for Prosecution Type is 'Other' with comments and status is 'Judgment'");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1,COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        
        commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		commonFuntions.ScrollMenu("Prosecution");
		commonFuntions.clickMenu("Prosecution");
		sleep();
		
		//---COL-593---
		commonFuntions.screenShot("Prosecution", "Pass", "Prosecution (COL-593)screen launched");
		commonFuntions.enterTextboxContains("Employer Registration Number" ,eanValue);
		commonFuntions.screenShot("Prosecution", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(20000);
		
		//---COL-594---
		commonFuntions.screenShot("List of Prosecutions", "Pass", "List of Prosecutions (COL-594)screen launched");
		commonFuntions.clickButtonContains(" Add Prosecution ");
		sleep(2000);
		
		//---COL-595---
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "Enter Prosecution Details (COL-595)screen launched");
		commonFuntions.enterTextboxContains("a. First Name", "AINE");
		commonFuntions.enterTextboxContains("c. Last Name", "UNGAR");
		commonFuntions.selectDropdown("d. Suffix", " Sr. ");
		commonFuntions.enterTextboxContains("3. Prosecution Case Number", "9000007");
		commonFuntions.selectDropdown("4. Prosecution Type", " Other ");
		commonFuntions.enterTextboxContains("5. Date Prosecution Proceedings Commenced", "6/19/2023");
		commonFuntions.enterTextboxContains("6. Assigned To", "AINE");
		commonFuntions.selectDropdown("7. Status", " Judgement ");
		BCL.comment_EnterProsecutionDetails.sendKeys("For Testing");
		commonFuntions.screenShot("Enter Prosecution Details", "Pass", "Entered Prosecution Details on (COL-595)screen");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		
		//---SUC-002---
	    commonFuntions.screenShot("Prosecution Confirmation", "Pass", "Prosecution Confirmation (SUC-002)screen launched");
	    commonFuntions.clickButtonContains("Home ");
		sleep(2000);
	
		//---HOME---
	    commonFuntions.screenShot("Home", "Pass", "Home screen launched");
	    sleep(2000);
	
	    //Done
	}
	
	
	
	
}
