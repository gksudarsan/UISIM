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

public class EM_260_02_001_Verify_Employer_is_able_to_update_employer_address {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001 extends TestBase
	{

		String EAN = prop.getProperty("EAN");

		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void accMaint() throws Exception
		{
			//claimsIntake cl = new claimsIntake();
			employerManagement em =  new employerManagement();
			System.out.println(EAN);
			test = report.createTest("EM.321.02.001 - Verify Employer is able to Edit Corporate Officer/Owner Details.");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			cf.login("Soleprop123","NewUser@123456");
			cf.screenShot("ApplicationLogin","Pass","Login is successful");
			cf.clickMenu("Menu");	
			cf.clickMenu("Account Maintenance");
			cf.clickMenu("Maintain Address");sleep();
			cf.screenShot("Menu","Pass","Maintain Address");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			em.updateAddress();



		}
	}
}