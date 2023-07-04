package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_001_csr_updatePrimary_address {

	@Listeners(com.ui.utilities.ListenerTest.class)
	public class EM_260_001 extends TestBase
	{

		String EAN = prop.getProperty("EAN");

		@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
			//claimsIntake cl = new claimsIntake();
			employerManagement em =  new employerManagement();
			System.out.println(EAN);
			test = report.createTest("EM.260.001 - Verify CSR is able tt update Primary address.");
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
			commonStepDefinitions cf= new commonStepDefinitions();
			cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			cf.screenShot("ApplicationLogin","Pass","Login is successful");
			sleep(3000);
			cf.clickMenu("Menu");	
			cf.ScrollMenu("Account Maintenance");
			 cf.clickMenu("Account Maintenance");
			cf.clickMenu("Maintain Address");sleep();
			cf.screenShot("Menu","Pass","AccountMaintenance");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'BUSI'","EAN");
			
			String ernValue = databaseResults.get("EAN");
		 System.out.println(ernValue);
		 cf.enterTextbox("Employer Registration Number",ernValue );
		 cf.screenShot("Menu","Pass","ERN Screen");
		 cf.clickButtonContains("Continue");
	     sleep(2000);
			cf.screenShot("Menu","Pass","Maintain Address Details");
			cf.selectTable("Primary Business Physical Address", 6, 1, "Maintain Address Details");
			sleep(2000);
			cf.enterTextboxContains("Address Line 1","updatedAddressLine1"+cf.createRandomInteger(1000,9999));
		     cf.enterTextboxContains("Address Line 2","updatedAddressLine2"+cf.createRandomInteger(1000,9999));
		     cf.enterTextboxContains("City","NewYork");
		     cf.enterTextboxContains("Zip Code","13430");
			
		     cf.selectDropdown("Source", "Correspondence/Email");
				Thread.sleep(2000);
				cf.selectDropdown("Source Type", "Correspondence/Email");
				sleep(3000);
				cf.clickButtonContains("Submit");
			     sleep(2000);
			//em.updateAddress(EAN);
			    cf.selectRadioQuestions("Do you wish to register for SIDES E-Response?","No");
			     cf.selectDropdown("Source", "Correspondence/Email");
					Thread.sleep(2000);
					cf.selectDropdown("Source Type", "Correspondence/Email");
					sleep(3000);
					cf.clickButtonContains("Submit");
				     sleep(2000);
		}
	}
}