package com.employerContibution.EM;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_443_001 extends TestBase{

	@Test(priority=1, description = "EM.443.001.Verify CSR is able to enter ERN and Add POA/TPR association for designation type \"All Unemployment Insurance Matters\"",groups = {"Regression"})
	public void EM_443_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM.443.001.Verify CSR is able to enter ERN and Add POA/TPR association for designation type \"All Unemployment Insurance Matters\"");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.screenShot("Account Maintenance", "Pass", "Employer Account Maintenance");
		commonFuntions.clickMenu("Add or Remove POA/TPR Association");
		commonFuntions.screenShot("Add or Remove POA/TPR Association", "Pass", "Add or Remove POA/TPR Association");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.errorLabel("Required");
		commonFuntions.screenShot("Required error", "Pass", "Add or Remove POA/TPR Association – Enter ERN");
		Thread.sleep(2000);
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		commonFuntions.enterTextbox("Employer Registration Number", ernValue);
		commonFuntions.clickButtonContains("Continue");
		sleep();
		try {
		commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		}
		catch(Exception e) {
			System.out.println("Error Content Message Not Appeared");
		}

		//query to fetch ern number from database
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND ACCOUNT_STATUS='LIAB' ORDER BY UPDATED_TS DESC","EAN");
		Thread.sleep(10000);
		String ernNumber=databaseResults.get("EAN");
		System.out.println("The selected ERN is: "+ernNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number",ernNumber);
		commonFuntions.screenShot("ERN", "Pass", "Add or Remove POA/TPR Association – Enter ERN");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.clickButton(" Search POA/TPR ");
		commonFuntions.errorLabel(" The response to Question 1 is required.");
		commonFuntions.screenShot("Association to Employer", "Pass", "Add or Remove Third Party Association to Employer");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("Designation Type", " All Unemployment Insurance Matters ");
		commonFuntions.screenShot("Association to Employer", "Pass", "Add or Remove Third Party Association to Employer");
		commonFuntions.clickButton(" Search POA/TPR ");sleep();
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Search POA/Third Party Representative", "Pass", "Search POA/Third Party Representative");
		commonFuntions.errorContent("Legal name of business is required.");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("*POA/TPR Legal Name", "ABCD");
		commonFuntions.clickButton(" Search ");sleep();
		commonFuntions.clickButtonContains("Continue");sleep();
		commonFuntions.screenShot("Search POA/Third Party Representative", "Pass", "Search POA/Third Party Representative");
		commonFuntions.errorContent("Please select a record to proceed further.");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Select");sleep();
		commonFuntions.clickButtonContains("Continue");sleep();
		commonFuntions.clickButton("Submit ");
		Thread.sleep(2000);
		
		//commonFuntions.errorLabel("Required");
		
		commonFuntions.screenShot("required error message", "Pass", "Add or Remove Third Party Association to Employer");sleep();
		driver.navigate().refresh();
		//need to validate association end date
		commonFuntions.selectRadio("Select");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Comment", "Test1");
		driver.findElement(By.xpath("//span[@class='mat-checkbox-inner-container']")).click();sleep();
		commonFuntions.screenShot("Third Party Associate Employer", "Pass", "Add or Remove Third Party Association to Employer");sleep();
		commonFuntions.clickButtonContains("Submit ");sleep();
		commonFuntions.screenShot("Confirmation Message", "Pass", "Third Party Representative Association to Employer Confirmation");
        Thread.sleep(2000);
        commonFuntions.clickButton("Home ");sleep();
        
        //test cases ends here..

	}


}
