package com.employerContibution.EM;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_04_003 extends TestBase{

	@Test()
	public void EM_310_04_003() throws Exception {

		
		/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		commonStepDefinitions cf = new commonStepDefinitions();	
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		employerManagement em =  new employerManagement();
		
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM_310_04_003");

		//-----Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	
		
		//-----DB query
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'" , "EAN"); 
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " +EAN);
		
		//-----Menu
		sleep(2000);
		cf.screenShot("Menu", "Pass", "Menu page");
			cf.clickMenu("Menu");	
			cf.clickMenu("Account Maintenance");
			sleep();
			cf.clickMenu("Business Acquisition");
			sleep(2000);
			cf.screenShot("Menu", "Pass", "Menu selected");
			cf.screenShot("Menu","Pass","Business Acquisition");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			
			//----EM-011
			sleep(2000);
			cf.screenShot("Business Acquisition – Enter ERN", "Pass", "Launched to  acquired the business page");
			cf.enterTextbox("Employer Registration Number", EAN);
			sleep(2000);
			cf.screenShot("Business Acquisition – Enter ERN", "Pass", "Entered data on EM-011");
			sleep(2000);
			cf.clickButton("Continue ");
			
			//------SREG -011
			sleep(2000);
			cf.screenShot("Business Acquisition ", "Pass", "Successfully landed on SREG -011");
			sleep(2000);
			cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
			sleep(2000);
			cf.screenShot("Business Acquisition ", "Pass", "Entered data on SREG -011");
			sleep(2000);
			cf.clickButton("Continue ");
			sleep(2000);
			//-------Home
			cf.screenShot("Home", "Pass", "Successfully landed on HomePage");
	        //cf.Label("Testcase_Completed");
			
			//TC executed by PG

	}
}