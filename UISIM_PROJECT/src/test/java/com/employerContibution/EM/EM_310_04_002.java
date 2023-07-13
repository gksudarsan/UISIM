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
import com.ui.pages.SREG_011;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_310_04_002 extends TestBase{

	@Test()
	public void EE_01_004_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	
		SREG_011 sreg011 = new SREG_011(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		employerManagement em = new employerManagement();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		/*
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'" , "EAN"); 
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " +EAN);

		test = report.createTest("EM_310_04_002");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	
			cf.clickMenu("Menu");	
			cf.clickMenu("Account Maintenance");sleep();
			cf.clickMenu("Business Acquisition");sleep();
			cf.screenShot("Menu","Pass","Business Acquisition");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			cf.enterTextbox("Employer Registration Number", EAN);sleep();
			cf.screenShot("", "Pass", "Launched to  acquired the business page");
			cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
			cf.enterTextboxContains("Employer Registration Number", "5372266");
			cf.clickButton(" Search ");sleep();
			//cf.enterTextboxContains("Legal Name of Business", "hags gsg");
			//empRegPage.tradeNameId_SREG011.sendKeys("Cooking Inc");
			empRegPage.address1_SREG011.sendKeys("Clark Residence Hall Poly");
			empRegPage.city_SREG011.sendKeys("Brooklyn");
			empRegPage.zip_SREG011.sendKeys("11201");
			cf.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
			cf.enterPastDate("Acquisition Date", 12);
			cf.enterPastDate("Notification date of Transfer", 0);
			cf.selectDropdown("Source", "Correspondence/Email");
			Thread.sleep(2000);
			cf.selectDropdown("Source Type", "Correspondence/Email");
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
			cf.screenShot("", "Pass", "The request Business Acquisition transaction has been succesfully processed.\r\n"
					+ "");
			*/
		test = report.createTest("EM_310_04_002");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		Thread.sleep(5000);
		cf.clickMenu("menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Business Acquisition");
		cf.clickMenu("Business Acquisition");
		Thread.sleep(2000);
		cf.screenShot("Menu", "Pass", "Business Acquisition");
		// driver.findElement(By.xpath("//button[@class='mat-focus-indicator
		// mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);

		Thread.sleep(2000);
		cf.clickButton("Continue ");
		cf.errorLabel("Required");
		cf.screenShot("Business Acquisition", "Pass", "Required error is visible");

		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'", "EAN");
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " + EAN);

		cf.enterTextboxContains("Employer Registration Number", EAN);
		cf.clickButton("Continue ");
		Thread.sleep(2000);
		cf.screenShot("Business Acquisition", "Pass", "SREG-011 page is displayed");
		Thread.sleep(1000);

		cf.selectRadioQuestions(
				"Have you acquired the business of another employer liable for New York State Unemployment Insurance?",
				"Yes ");
		// cf.enterTextboxContains("Employer Registration Number", EAN); //5372266
		sreg011.eanField.sendKeys(EAN);
		cf.clickButton(" Search ");
		Thread.sleep(2000);
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		cf.enterPastDate("Acquisition Date", 12);
		// cf.enterPastDate("Notification date of Transfer", 0);
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		Thread.sleep(1000);
		cf.clickButton("Continue ");
		Thread.sleep(2000);
		cf.errorLabel(" Required ");
		cf.screenShot("Business Acquisition", "Pass", "Reuired error is displayed");
		cf.selectDropdown("Source Type", " NYS-100AG ");
		Thread.sleep(2000);

		cf.clickButton("Continue ");
		try {
			cf.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		Thread.sleep(2000);
		cf.clickButton("Submit");
		Thread.sleep(2000);
		cf.screenShot("Business Acquisition Confirmation", "Pass", "SUC-002 screen is displayed");

		cf.clickButton("Home ");
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		cf.screenShot("Home", "Pass", "Homepage is displayed");
			
			
		
	}
}