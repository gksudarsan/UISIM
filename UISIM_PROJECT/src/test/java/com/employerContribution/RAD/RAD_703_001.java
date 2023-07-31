package com.employerContribution.RAD;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class RAD_703_001 extends TestBase {

	@Test(priority=1, description = "RAD.703.001. Verify CSR can update the disaster flag on employer account(s) and adjust the due date.",groups = {"Regression"})
	public void RAD_703_001() throws Exception
	{
		commonStepDefinitions commonFunctions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("RAD.703.001. Verify CSR can update the disaster flag on employer account(s) and adjust the due date.");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login("ndsbb3","Brijen@1234567");
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        commonFunctions.clickMenu("menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Contribution/Wage Maintenance");
		commonFunctions.clickMenu("Contribution/Wage Maintenance");
		commonFunctions.ScrollMenu("Maintain Due Dates");
		commonFunctions.clickMenu("Maintain Due Dates");		
		sleep();
		
		//---TWR-078---
		commonFunctions.screenShot("Maintain Due Dates", "Pass", "Maintain Due Dates (TWR-078)screen launched");
	    commonFunctions.clickButtonContains("Submit ");
		sleep(2000);
		commonFunctions.screenShot("Maintain Due Dates", "Pass", "Message 'Reason for Action' on (TWR-078)screen");
		commonFunctions.selectDropdown("Reason for Action", " Disaster ");
		commonFunctions.clickButtonContains("Submit ");
		sleep();
		commonFunctions.screenShot("Maintain Due Dates", "Pass", "Message 'Required' on (TWR-078)screen");
		commonFunctions.selectDropdown("Account Type(s)", " Contributory ");
		commonFunctions.selectDropdown("Quarter ", " 4 "); 
		commonFunctions.selectDropdown("Year ", " 2021 "); 
		commonFunctions.enterTextboxContains("Return Due Date" ,"7/3/2023"); 
		empRegPage.Comments_Box.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.screenShot("Maintain Due Dates", "Pass", "Message 'Please select a record to proceed.' on (TWR-078)screen");
		commonFunctions.selectRadio("Select");
		commonFunctions.clickButtonContains("Submit ");
        sleep(2000);
        
		//---SUC-002---
		commonFunctions.screenShot("Modify Payment Date Confirmation", "Pass", "Modify Payment Date Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		sleep();
		
		//---Home---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		sleep();
		
		//Done
	}
}
