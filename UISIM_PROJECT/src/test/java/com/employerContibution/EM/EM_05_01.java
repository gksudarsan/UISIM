package com.employerContibution.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

public class EM_05_01 {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase
	{
		String EAN = prop.getProperty("EAN");

		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			//claimsIntake cl = new claimsIntake();
			employerManagement em =  new employerManagement();
			System.out.println(EAN);
			test = report.createTest("EM.321.02.001 - Verify Employer is able to Edit Corporate Officer/Owner Details.");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			cf.login("NDFJP3","Admin@12345678");
			cf.screenShot("ApplicationLogin","Pass","Login is successful");
			cf.clickMenu("Menu");	
			cf.clickMenu("Inquery");sleep();
			cf.clickMenu("Contribution Inquiry");sleep(); 
			cf.clickMenu("Inquiry Employer Account");sleep();
			cf.screenShot("Menu","Pass","AccountMaintenance");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			em.Inquery(EAN);

		}
	}
}