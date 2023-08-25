package com.employerContibution.RAD;

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
public class RAD_419_002 extends TestBase {

	@Test(priority=1, description = "RAD.419.002.Verify CSR can change the payment date of a payment for an Employer account where interest, rating timeliness and slop over available.",groups = {"Regression"})
	public void RAD_419_002() throws Exception
	{
		commonStepDefinitions commonFunctions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		
		test = report.createTest("RAD.419.002.Verify CSR can change the payment date of a payment for an Employer account where interest, rating timeliness and slop over available.");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login("ndsbb3","Brijen@1234567");
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
        sleep();
        commonFunctions.clickMenu("menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Contribution/Wage Maintenance");
		commonFunctions.clickMenu("Contribution/Wage Maintenance");
		empRegPage.Menu_Contribution_WageMaintenanceModifyPaymentDate.click();		
		sleep();
		
		//---TWR-328---
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Modify Payment Date (TWR-328)screen launched");
	    commonFunctions.enterTextboxContains("Employer Registration Number" ,"6655237");
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Entered valid ERN");
		commonFunctions.clickButtonContains("Continue ");
		sleep(2000);
		
		//---TWR-329---
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Modify Payment Date Details (TWR-329)screen launched");
		commonFunctions.selectRadio("Select");
		empRegPage.modifyPaymentDateId.sendKeys("7/3/2023");
		empRegPage.Reason_for_Update_CSR_Remarks.sendKeys("For Testing");
		empRegPage.Interest.click();
		empRegPage.Rating_Timeliness.click();
		empRegPage.Slop_Over.click();
		commonFunctions.clickButtonContains("Continue ");
		sleep();
		
		//---TWR-330---
		commonFunctions.screenShot("Modify Payment Date Verification", "Pass", "Modify Payment Date Verification (TWR-330)screen launched");
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
