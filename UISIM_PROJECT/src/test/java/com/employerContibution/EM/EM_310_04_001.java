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

public class EM_310_04_001 extends TestBase{

	@Test()
	public void EM_310_04_001() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'" , "EAN"); 
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " +EAN);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		employerManagement em =  new employerManagement();
		
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM_310_04_001");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	
			cf.clickMenu("Menu");	
			cf.clickMenu("Account Maintenance");sleep();
			cf.clickMenu("Business Acquisition");sleep();
			cf.screenShot("Menu","Pass","Business Acquisition");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			cf.enterTextbox("Employer Registration Number", EAN);
			cf.clickButton("Continue ");
			cf.screenShot("acquired the business", "Pass", "Launched to  acquired the business page");
			cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");sleep();
			empRegPage.EAN.sendKeys("5372266");
			cf.clickButton(" Search ");
			cf.enterTextboxContains("Legal Name of Business", "hags gsg");
			//empRegPage.tradeNameId_SREG011.sendKeys("Cooking Inc");
			//empRegPage.address1_SREG011.sendKeys("Clark Residence Hall Poly");
			//empRegPage.city_SREG011.sendKeys("Brooklyn");
			//empRegPage.zip_SREG011.sendKeys("11201");
			cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
			cf.enterPastDate("Acquisition Date", 12);
			cf.enterPastDate("Notification date of Transfer", 0);
			cf.selectDropdown("Source", " Correspondence/Email ");
			cf.selectDropdown("Source Type", " Correspondence/Email ");
			sleep(3000);
			cf.screenShot("EE04002", "Pass", "Details entered in SREG-011 page");
			cf.clickButton("Continue ");
			try {
			cf.clickButtonContains(" Yes ");
			}
			catch (Exception e)
			{
				System.out.println("pop up not appeared");
			}
			// SREG - 012
			sleep(3000);
			cf.clickButton("Submit");
			cf.screenShot("", "Pass", "The request Business Acquisition transaction has been succesfully processed.");
			
			
		
	}
}