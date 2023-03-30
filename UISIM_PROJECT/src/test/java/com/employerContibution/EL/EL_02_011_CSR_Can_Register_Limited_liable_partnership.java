package com.employerContibution.EL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_02_011_CSR_Can_Register_Limited_liable_partnership extends TestBase{

	@Test(priority=1, description = "EL.02.011 : Verify CSR can register  PEO Exempt for Type of Legal Entity ' Limited Liability Partnership'",groups = {"Regression"})
	public void EL_02_011() throws Exception{
		 test = report.createTest("EL.02.011 : Verify CSR can register  PEO Exempt for Type of Legal Entity ' Limited Liability Partnership'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 PEOPage.menuPeo.click();	
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 commonFuntions.clickMenu("Register PEO");			 
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 Thread.sleep(3000);
	     commonFuntions.clickButtonContains("Continue");
	     PEOPage.peoExemptRegisterRadio.click();
	     commonFuntions.screenShot("EXEMPT", "Pass", "Selecting Exempt and filling the form");
	     commonFuntions.enterTextbox("Name of Professional Employer Organization", "Test_Data"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Additional Names, if any, under", "Test_Data"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.clickButtonContains("Save & Continue");
	     commonFuntions.screenShot("address", "Pass", "Address update");
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     long number = commonFuntions.createRandomInteger(10000,99999);
		 String ernValue="12"+Long.toString(number);
		 String feinValue=Long.toString( commonFuntions.createRandomInteger(100000000,999999999));
		 commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Limited Partnership ");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     Thread.sleep(2000);
	     commonFuntions.screenShot("PEOPage", "Pass", "Entering random FEIN and ERN values");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
	     commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
	     commonFuntions.enterTextbox("State agency that issued it.", "New York");
	     commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
	     commonFuntions.enterTextbox("Registration Number ", "3458767985");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(3000);
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(2000);
	     /*------------- PEO_003 ------------------*/
	     commonFuntions.screenShot("Address2", "Pass", "Entering address 1&2");
	     PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13420");
	     commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
	     commonFuntions.clickButtonContains("Save & Continue");
	     /*------------ PEO_004 ------------- */
	     Thread.sleep(4000);
	     commonFuntions.screenShot("Address6", "Pass", "Navigated to next page");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     /* ------------------- PEO_004 --------------E*/
	     commonFuntions.clickButtonContains("Continue");
	     /* ----------------- SREG_006 --------------*/
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     /*Upload client list page --- Upload Document---- */
	     
	     
	     
	     /*Upload client list page --- Upload Document---- */
	     Thread.sleep(4000);
	     /*-------------------- LEAS_012 ------------------*/
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(4000);
	     /*-------------------- PEOR_001 ------------------*/
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     /*-------------------- PEOE_005 ------------------*/
	     commonFuntions.screenShot("Address2", "Pass", "Entering name of officer");
	     commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
	     Thread.sleep(4000);
	     commonFuntions.clickButtonContains("Save & Continue ");
	     Thread.sleep(3000);
	     /*-------------------- PEO_017 ------------------*/
	     commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
	     commonFuntions.clickButton("Accept & Submit ");	
	}
}
