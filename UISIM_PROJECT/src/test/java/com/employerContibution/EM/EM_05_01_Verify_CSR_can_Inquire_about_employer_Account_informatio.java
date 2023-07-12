package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_05_01_Verify_CSR_can_Inquire_about_employer_Account_informatio {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class TC_CM_002_001_Verify_CSR_can_Inquire_about_employer_Account_information extends TestBase
	{
		String EAN = prop.getProperty("EAN");

		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			//claimsIntake cl = new claimsIntake();
			employerManagement em =  new employerManagement();
			System.out.println(EAN);
			test = report.createTest("Verify CSR can Inquire about employer Account information");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
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