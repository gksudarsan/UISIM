package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_02_001 extends TestBase{

	@Test(priority=1, description = "EE.02.001 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review",groups = {"Regression"})
	public void EE_02_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest("EE.02.001 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
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
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
        //Generating fein..
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd)",
				"FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("FeinValue is: " + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation ");
        //Generating ern..
	    Map<String, String> databaseResults2 =commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN");
		String ernValue = databaseResults2.get("EAN");
		System.out.println("EanValue is: "+ ernValue );
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
				
		//commonFuntions.enterTextboxContains("Employer Registration Number", "0506625");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(2000);
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.screenShot("General Information", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		//Generating Legal Name..
		Map<String, String> databaseResults3 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseResults.get("ENTITY_NAME");
		System.out.println("LegalName is: "+ legalName );
		commonFuntions.enterTextboxContains("Legal Name", legalName);
		
		/*---------- SREG-003--------------------*/
		
		//AddPage.legalNameTextBox.sendKeys("GHENT UNIVERSITY");
		
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "AutoTest"+commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "6/1/2023");
		commonFuntions.selectDropdown("Quarter", "2");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2023");
		sleep(2000);
		commonFuntions.selectRadioQuestions("First calendar quarter and year you have paid (or expect to pay) total cash wages of $300 or more. ", "No");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*---------------taking screeshot------*/
		
		commonFuntions.screenShot("Add Primary BussineesdDetails", "Pass", "Add Primary BussineesdDetails");
		commonFuntions.clickButtonContains("Previous");
		sleep(5000);
		commonFuntions.clickButtonContains("Continue");
		
	    /*--------------- SREG-007-------*/
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		/*commonFuntions.selectDropdown("Indicate your principal activity or farm production that produces the greatest gross sales.", "Other");
		PEOPage.otherDetails.sendKeys("test12");
		PEOPage.otherDetails1.sendKeys("Autotest12"); */
		sleep(2000);
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information (SREG-003)");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
		sleep();
		commonFuntions.screenShot("Employer Information (SREG-003)", "Pass", "Pop-Up page is displayed");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("Business Physical Address Details", "Pass", "Business Physical Address Details (SREG-007)");
		commonFuntions.clickButtonContains("Continue");
	
		//filling the address details ------
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		//AddPage.otherRadioButton.click();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		sleep();
		AddPage.addressLine1_Form2.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Cooper Square");
		sleep();
		AddPage.city_Form2.sendKeys("NY");
		sleep();
		AddPage.zipCode_Form2.sendKeys(commonFuntions.createRandomInteger(10, 99)+"345");
		sleep();
		AddPage.countyDropdown2.click();
		sleep();
		AddPage.countyValue2.click();
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Mailing");
		sleep();
		commonFuntions.screenShot("Employer Contact Details", "Pass", "Employer Contact Details (SREG-004)");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
		commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton1);
		sleep();
		}catch(Exception e) {
			System.out.println("Radio button 1 is selected ");
		}
		try {
			commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton2);
			sleep();
			}catch(Exception e) {
				System.out.println("Radio button 2 is selected ");
			}
		
		try {
		commonFuntions.safeJavaScriptClick(AddPage.adderessRadioButton3);
		sleep();
		}catch(Exception e) {
			System.out.println("Radio button 3 is selected if found");
		}
		commonFuntions.screenShot("Employer Information (SREG-004)", "Pass", "Pop-Up page 2 is displayed");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		
		//This test case is blocked at step 17
		
		/*-------------------taking screenshot -------------------------*/
		
		commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "Employer Verify Contact Details(SREG-521)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("Bussiness Aquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		commonFuntions.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
		commonFuntions.selectRadioQuestions("Have you changed legal entity?", "No ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		commonFuntions.enterTextboxContains("First Name", "Test");
		commonFuntions.enterTextboxContains("Last Name", "AutoTest");
		commonFuntions.enterTextboxContains("Address Line 1", "Ave"+ commonFuntions.createRandomInteger(10,99));
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			PEOPage.uspsAdd.click();
			commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("usps pop up dispalyed");
		}
		sleep(2000);
		commonFuntions.screenShot("Corporate Officer/Owner Details", "Pass", "Corporate Officer/Owner Details(SREG-005))");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
	    commonFuntions.selectLink("Proof of NYS Workers", "Browse");
	    sleep(3000);
	    commonFuntions.screenShot("Upload Documents", "Pass", "Upload Documents(SREG-683)");
		commonFuntions.clickButtonContains("Continue");
		sleep(5000);
		commonFuntions.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		commonFuntions.clickButtonContains("Exit");
		
		//here registration is completed....




	}
}