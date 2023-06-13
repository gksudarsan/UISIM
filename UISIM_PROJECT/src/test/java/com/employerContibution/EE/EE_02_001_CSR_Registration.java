package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_02_001_CSR_Registration extends TestBase{

	@Test
	public void EE_02_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = 
				report.createTest("EE.02.001 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review");		
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");
		sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep();
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Employer Registration (SREG-001)'");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		/*---general information sreg-025-----*/
		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
		//				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd)",
		//				"FEIN");
		//		String feinValue = databaseResults.get("FEIN");
		//		System.out.println("FeinValue is: " + feinValue);
		//		String ernValue = databaseResults2.get("EAN");
		//		System.out.println("EanValue is: "+ ernValue );
		String Fein = prop.getProperty("FeinPresentInDolDtf");
		test.log(Status.INFO, "FEIN VALUE::" + Fein);
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		sleep();
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", Fein);
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		sleep();
		String Ern = prop.getProperty("ErnPresentInDolDtf");
		test.log(Status.INFO, "ERN VALUE::" + Ern);
		commonFuntions.enterTextboxContains("Employer Registration Number", Ern);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		/*---employer entity information:sreg-003---*/
		Map<String, String> databaseResults3 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea","ENTITY_NAME");
		String legalName = databaseResults3.get("ENTITY_NAME");
		System.out.println("LegalName is: "+ legalName );
		test.log(Status.INFO, "LEGAL NAME::" + legalName);
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "AutoTest"+commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "6/1/2023");
		commonFuntions.selectDropdown("Quarter", "2");
		sleep();
		commonFuntions.selectDropdown("Year", "2023");
		sleep();
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information (SREG-003)");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Add Primary Business Physical Address------*/
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		commonFuntions.screenShot("AddPrimaryBussineesdDetails", "Pass", "Add Primary Business Physical Address");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(2000);

		/*-----Business Physical Address Details------*/
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Employer Contact Details------*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
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
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details (SREG-004)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS-Employer Contact Details");
		}
		sleep(2000);
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Business Acquisition------*/
		commonFuntions.screenShot("BussinessAquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Change in Legal Entity------*/
		commonFuntions.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity(SREG-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*-----Add Corporate Officer/Owner----*/
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		commonFuntions.enterTextboxContains("First Name", "Test");
		commonFuntions.enterTextboxContains("Last Name", "AutoTest");
		commonFuntions.enterTextboxContains("Address Line 1", "Ave"+ commonFuntions.createRandomInteger(10,99));
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.screenShot("AddCorporateOfficer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			PEOPage.uspsAdd.click();
			commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
		}
		catch(Exception e) {
			System.out.println("usps address");
		}
		sleep(2000);
		commonFuntions.screenShot("Corporate Officer/Owner Details", "Pass", "Corporate Officer/Owner Details(SREG-005))");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*--------Upload Documents(SREG-683)------*/
		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(3000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass",
				"Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);

		//Assigning user to WI Review emp type..................
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+Fein+"' ORDER BY UPDATED_TS desc)"); 

		//Resolving WI Review emp type................
		PEOPage.queue.click(); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",Fein);
		commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("Review emp type","Pass","emp type");
		commonFuntions.clickOnLink("Review Employer Type");
		sleep(); 
		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.screenShot("Review","Pass","Review Employer Type Task Details");
		commonFuntions.enterFutureDate("Date Covered Employment began? ", 10);
		sleep();
		AddPage.commentField.sendKeys("registration  in progress");
		sleep();
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.screenShot("GeneralInfo","Pass","General Information");
		commonFuntions.clickButtonContains("Home");





	}
}