package com.employerContibution.EE;

import java.util.Map;

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
public class EE_07_004_EmployerType extends TestBase {

	@Test
	public void EE_07_004() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.07.004- Verify employer can submit employer registration for employer type 'Governmental' and legal entity type "
						+ "'School District' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_4.toUpperCase(), COMMON_CONSTANT.CSR_USER_4_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Registration_page", "Pass", "Navigatte employer register page");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.enterTextboxContains("First Name", "AutoTest");
		commonFuntions.enterTextboxContains("Last Name", "AutoSanjay");
		commonFuntions.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("Registration_page2", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Governmental");
		sleep();
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd)", 
				"FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("FeinValue is: " + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "School District");
		
		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd)", 
				"EAN");
		String ernValue = databaseResults1.get("EAN");
		System.out.println("ErnValue is: " + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("General Information", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*--------sreg-003------------------------*/
		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)", 
				"ENTITY_NAME");
		String entityName = databaseResults2.get("ENTITY_NAME");
		System.out.println("entityName is: " + entityName);
		AddPage.legalNameTextBox.clear();
		sleep();
		AddPage.legalNameTextBox.sendKeys(entityName);
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "6/1/2023");
		commonFuntions.enterTextboxContains("Estimated or approximate number", "764");
		commonFuntions.enterTextboxContains("Date covered employment began?", "1/1/2018");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information  (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("Business Physical Address Details", "Pass", "Business Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
		commonFuntions.enterTextboxContains("Address Line 1", "Cooper Square"+commonFuntions.createRandomInteger(10,99));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.clickButtonContains("Continue");
		}catch(Exception e) {
			System.out.println("SREG-008 Page is not displayed");
		}
		sleep();
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Add Primary Business Physical Address:SREG-008");
		try {
			PEOPage.uspsAdd.click();
			commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
			AddPage.continueButton_popUp.click();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("usps pop up dispalyed");
		}
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_locationOfBooksAndrecords.clear();
		AddPage.firstName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Jhon");
		AddPage.lastName_noticeOfPotentialCharges.clear();
		AddPage.lastName_locationOfBooksAndrecords.sendKeys(commonFuntions.createRandomInteger(10, 99)+"AutoTets");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		AddPage.firstName_noticeOfPotentialCharges.clear();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Jhiny");
		AddPage.lastName_noticeOfPotentialCharges.clear();
		AddPage.lastName_noticeOfPotentialCharges.sendKeys(commonFuntions.createRandomInteger(10, 99)+"TestAuto");
		sleep();
		
		commonFuntions.clickButtonContains("Continue");
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
		AddPage.continueButton_popUp.click();
		sleep();
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No");
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		PEOPage.browserLinkManagePEOPage.click();
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.screenShot("Upload Documents", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("Review Registration Details", "Pass", "Review Registration Details:SREG-800)");
		commonFuntions.clickButtonContains("Continue");
		sleep(3000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement:SREG-043)");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation:SREG-013)");
		commonFuntions.clickButtonContains("Exit");
	}
}
