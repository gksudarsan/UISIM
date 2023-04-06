package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_009 extends TestBase{

	@Test
	public void EE_02_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.01.009 - Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Estate' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Business ");
		sleep();
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " ESTATE ");
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println(ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		sleep(2000);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFuntions.screenShot("General Information", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		AddPage.legalNameTextBox.sendKeys("-FUL STUDIO INC");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "AutoTest"+commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "10/1/2021");
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "1/1/2018");
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		commonFuntions.selectDropdown("Quarter", "1");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2023");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "OthersTest");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		
		
//		try {
//			commonFuntions.clickButtonContains(" Yes ");
//		}catch(Exception e) {
//			System.out.println("Verify date pop up is displayed");
//		}
		
		/*---------------SREG-008----------------*/

		sleep();
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.selectDropdown("Principal Business Activity", "Manufacturing");
		sleep();
		AddPage.productsName.sendKeys("Automation Testing");
		commonFuntions.enterTextboxContains("Percent of Total Sales Value", "50");
		AddPage.rawMaterialName.sendKeys("SteelTest");
		commonFuntions.screenShot("Add Primary Bussiness Physical Address", "Pass", "Add Primary Bussiness Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("Verify Address Pop -Up", "Pass", "Verify Address Pop Up displayed");
		commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
		sleep();
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("Business Physical Address Details", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");

		/*---------------SREG-004-------------------------*/

		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("Employer Contact Details", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
			sleep();
		}catch(Exception e) {
			System.out.println("bmad address selected");
		}
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
			sleep();
		}catch(Exception e) {
			System.out.println("ibra address selected");
		}

		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
			sleep();
		}catch(Exception e) {
			System.out.println("npca address selected");
		}
		commonFuntions.screenShot("Verify Address", "Pass", "Verify Address Pop-Up");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		
		/*----------------------- SREG-011--------------------------*/
		//test case is blocked ate step 14 due to different screen is getting
		
		commonFuntions.screenShot("Business Acquisition:SREG-011", "Fail", "Wrong screen ID displayed");

	}
}
