package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_006_TPR_Can_Register_Governmental extends TestBase{
	
	@Test
	public void EE_10_006() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);

		
		test = report
				.createTest("EE.02.004 -Verify CSR can submit employer registration for employer type 'Agricultural' and legal entity type 'Guardianship' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.enterTextboxContains("First Name", "Tom");
		commonFuntions.enterTextboxContains("Last Name", "Willam");
		commonFuntions.enterTextboxContains("Job Title", "Tester");
		commonFuntions.enterTextboxContains("Email Address", "test@Test.com");
		sleep();
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-025--------------*/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Governmental ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
		/*---------------Enter Query here--------------*/
		String feinValue = "401101502";
		System.out.println(feinValue);
		/*---------------Enter Query here--------------*/
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Naruto");
		/*---------------Enter ERN here--------------*/
		String ERN = "";
		System.out.println(ERN);
		/*---------------Enter ERN here--------------*/
		commonFuntions.enterTextboxContains("Employer Registration Number", ERN);
		
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		/*---------------SREG-003--------------*/
		sleep(3000);
		empPage.legalNameTextBox.sendKeys("MAIDEN LANE");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "7687765665");
		commonFuntions.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?", "03012023");
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "325");
		commonFuntions.enterTextboxContains("Date covered employment began?", "04012023");
		commonFuntions.safeJavaScriptClick(empPage.iSyourEntityQuestion_No);
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		commonFuntions.clickButton("Continue ");
		try {
			commonFuntions.safeJavaScriptClick(empPage.liability_error_Yes);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		/*---------------SREG-008--------------*/
		
		
		commonFuntions.enterTextboxContains("Address Line 1 ", "20 cooper square");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "24986");
		commonFuntions.selectDropdown("County", " Allegany ");
		commonFuntions.selectDropdown("State", " New York ");
		commonFuntions.clickButton("Continue ");
		
		try {
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		
		/*---------------SREG-007--------------*/
		
		commonFuntions.clickButton("Continue ");
		
		
		
		
		
		
		
	}
}
