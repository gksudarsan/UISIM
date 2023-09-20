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
import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class RAD_419_01 extends TestBase {

	@Test(priority=1, description = "RAD.419.01. Verify CSR can change the payment date of a payment for an Employer account.",groups = {"Regression"})
	public void RAD_419_01() throws Exception
	{
		commonStepDefinitions commonFunctions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		SMPage sm = new SMPage(driver);
		ReturnAdjustmentDeterminationLocators rad = new ReturnAdjustmentDeterminationLocators(driver);
		test = report.createTest("RAD.419.01. Verify CSR can change the payment date of a payment for an Employer account.");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunctions.waitForLoadingIconToDisappear();
		//-----Menu
        commonFunctions.clickMenu("Menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Contribution/Wage Maintenance");
		commonFunctions.clickMenu("Contribution/Wage Maintenance");
		empRegPage.Menu_Contribution_WageMaintenanceModifyPaymentDate.click();		
		sleep();
		
		//---TWR-328---
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Modify Payment Date (TWR-328)screen launched");
	    commonFunctions.clickButtonContains("Continue ");
	    commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Message 'Required' on (TWR-328)screen");
		commonFunctions.enterTextboxContains("Employer Registration Number" ,"1111111");
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Entered invalid ERN");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Message 'The Employer Registration Number provided is invalid' on (TWR-328)screen");
		commonFunctions.enterTextboxContains("Employer Registration Number" ,"6655237");
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Entered valid ERN");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		//---TWR-329---
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Modify Payment Date Details (TWR-329)screen launched");
		commonFunctions.clickButtonContains("Continue ");
		
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Message 'No payment record was selected for payment date modification'&'Required' on (TWR-329)screen");
		//commonFunctions.selectRadio("Select");
		sm.dataTableIdRadio1.click();
		empRegPage.modifyPaymentDateId.sendKeys("7/10/2024");
		rad.ReasonforUpdateCSRRemarks.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Continue ");
		sleep();
		
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Message 'Modify Payment Date cannot be a future date.' on (TWR-329)screen");
		sm.dataTableIdRadio1.click();
		empRegPage.modifyPaymentDateId.sendKeys("10/25/2007");
		rad.ReasonforUpdateCSRRemarks.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Continue ");
		sleep();
		
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Message 'Modify Payment Date cannot be same as the current payment date.' on (TWR-329)screen");
		sm.dataTableIdRadio1.click();
		empRegPage.modifyPaymentDateId.sendKeys("24/02/2009");
		rad.ReasonforUpdateCSRRemarks.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Continue ");
		sleep();
		
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Message 'Please enter a valid date.' on (TWR-329)screen");
		sm.dataTableIdRadio1.click();
		empRegPage.modifyPaymentDateId.sendKeys("7/3/2023");
		rad.ReasonforUpdateCSRRemarks.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
	
		//---TWR-330---
		commonFunctions.screenShot("Modify Payment Date Verification", "Pass", "Modify Payment Date Verification (TWR-330)screen launched");
		commonFunctions.clickButtonContains("Previous ");
		commonFunctions.waitForLoadingIconToDisappear();

	    commonFunctions.enterTextboxContains("Employer Registration Number" ,"6655237");
		commonFunctions.screenShot("Modify Payment Date", "Pass", "Entered valid ERN");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
	    //---TWR-329---
		commonFunctions.screenShot("Modify Payment Date Details", "Pass", "Modify Payment Date Details (TWR-329)screen launched");
		sm.dataTableIdRadio4.click();
		empRegPage.modifyPaymentDateId.sendKeys("7/3/2023");
		rad.ReasonforUpdateCSRRemarks.sendKeys("For Testing");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		//---TWR-330---
		commonFunctions.screenShot("Modify Payment Date Verification", "Pass", "Modify Payment Date Verification (TWR-330)screen launched");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();
	    
		//---SUC-002---
		commonFunctions.screenShot("Modify Payment Date Confirmation", "Pass", "Modify Payment Date Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		//---Home---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		sleep();
		
		//Done
	}
}
