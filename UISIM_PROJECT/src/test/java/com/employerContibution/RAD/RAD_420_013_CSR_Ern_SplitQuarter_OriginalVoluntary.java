package com.employerContibution.RAD;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import com.ui.pages.ReturnAdjustmentDeterminationLocators;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class RAD_420_013_CSR_Ern_SplitQuarter_OriginalVoluntary extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to enter ERN details and split quarter for return type 'Original - Voluntary'.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_RAD_420_013() throws Exception {
	
		test = report.createTest("RAD.420.013 : Verify CSR is able to enter ERN details and split quarter for return type 'Original - Voluntary'.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		ReturnAdjustmentDeterminationLocators radLocators = new ReturnAdjustmentDeterminationLocators(driver);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		radLocators.menu.click();
		commonFunction.ScrollMenu("Contribution/Wage Maintenance");
		commonFunction.clickMenu("Contribution/Wage Maintenance");
		commonFunction.ScrollMenu("Enter Split Rate Quarter");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Contribution/Wage Maintenance -> Enter Split Rate Quarter");
		commonFunction.clickMenu("Enter Split Rate Quarter");
		
		// --- TWR-901 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD420013", "Pass", "Successfully launched Enter Split Rate Quarter(TWR-901) page");
		commonFunction.enterTextboxContains("Employer Registration Number", "0500041");
		commonFunction.selectDropdownEquals("Quarter", " 4 ");
		commonFunction.enterTextboxContains("Year", "2017");
		commonFunction.selectDropdownEquals("Return Type", " Original - Voluntary ");
		sleep();
		commonFunction.screenShot("RAD420013", "Pass", "Entered data on TWR-901 page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- TWR-902 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(1500);
		commonFunction.screenShot("RAD420013", "Pass", "Successfully launched Enter Split Rate Quarter - Information Submitted By(TWR-902) page");
		commonFunction.clickButtonContains("Continue ");
		
		// --- TWR-904 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("RAD420013", "Pass", "Successfully launched Split Rate Quarter Details(TWR-904) page");
		commonFunction.ScrollMenu(" Payment Details ");
		sleep();
		commonFunction.screenShot("RAD420013", "Pass", "Payment Details present in TWR-904 page");
		
		radLocators.wagesSubjectToCotributionTextBox0.sendKeys("2000");
		sleep();
		radLocators.normalPaymentAmtTextBox0.clear();
		sleep();
		radLocators.normalPaymentAmtTextBox0.sendKeys("20");
		sleep();
		
		radLocators.wagesSubjectToCotributionTextBox1.sendKeys("2448");
		sleep();
		radLocators.normalPaymentAmtTextBox1.clear();
		sleep();
		radLocators.normalPaymentAmtTextBox1.sendKeys("20");
		sleep();
		commonFunction.screenShot("RAD420013", "Pass", "Data entered in TWR-904 page for calculation");
		
		commonFunction.clickButtonContains(" Calculate ");
		sleep(3000);
		
		radLocators.comment.sendKeys("Ok Tested");
		sleep(1500);
		commonFunction.screenShot("RAD420013", "Pass", "Data after calculation and COmment passed");
		
		commonFunction.clickButtonContains("Submit ");
		
		//put breakpoint after submit, dynamic waiting time from 5s to 180s
		
		// --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("RAD420013", "Pass", "Successful launch to Split Rate Quarter Details Confirmation(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");	    
	 
	   
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("RAD420013", "Pass", "Successfully passed TC RAD.420.013.");
	    
		
		System.out.println("Pass :)");
	}
}
