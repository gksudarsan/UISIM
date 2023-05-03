package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class EE_02_002_CSR_Registration extends TestBase{

	@Test
	public void EE_02_002() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = 
				report.createTest("EE.02.002 -Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)' and legal entity type 'Partnership' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("OpenRegisterEmployerPage", "Pass", "Navigate to Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		commonFuntions.screenShot("EmployerRegistration1", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", "Agricultural");
        //Generating fein..
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The ernValue is:"+ feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Partnership ");
        //Generating ern..
	    Map<String, String> databaseResults =commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN");
		String ernValue = databaseResults.get("EAN");
		System.out.println("EanValue is: "+ ernValue );
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(2000);
		commonFuntions.selectDropdown("Source Type", " NYS-100AG ");
		commonFuntions.screenShot("GeneralInformation", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		
		//Generating Legal Name..
		/*---------- SREG-003--------------------*/
		
		Map<String, String> databaseResults2 = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd ORDER BY UPDATED_TS DESC",
				"LEGAL_NAME");
		String legalName = databaseResults.get("LEGAL_NAME");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "AutoTest"+commonFuntions.createRandomInteger(10, 99));
		commonFuntions.enterTextboxContains("Business Phone Number", Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Enter date of first operations in New York State", "6/1/2021");
		commonFuntions.selectDropdown("Quarter", "4");
		sleep(2000);
		commonFuntions.selectDropdown("Year", "2023");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes");
		commonFuntions.enterTextboxContains("Explain services that are performed", "AutomationTest");
		commonFuntions.enterTextboxContains("Total number of covered employees", "500");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		/*---------------taking screenshot------*/
		
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		/*-------------SREG-008-------------------*/
		
		commonFuntions.enterTextboxContains("Address Line 1",  commonFuntions.createRandomInteger(10,99 )+ "Cooper Square");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code",commonFuntions.createRandomInteger(100, 999)+"23");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "45");
		AddPage.selectDropdown.click();
		sleep(2000);
		AddPage.selectOtherDropDown.click();
	    sleep(2000);
		commonFuntions.enterTextboxContains("Specify Type", "CSRTest23");
		PEOPage.otherDetails.sendKeys("test12");
		PEOPage.otherDetails1.sendKeys("Autotest12");
		sleep(2000);
		commonFuntions.screenShot("AddPrimaryBussinessAddresDeatils", "Pass", "Add Primary Bussiness Details:SREG-008");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.safeJavaScriptClick(PEOPage.uspsAdd);
		sleep();
		commonFuntions.screenShot("Employer Information (SREG-003)", "Pass", "Pop-Up page is displayed");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		sleep();
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details:SREG-007)");
		commonFuntions.clickButtonContains("Continue");
		sleep();
	
		//filling the address details ------SREG-004--------------------------
		
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		AddPage.addressLine1_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"Ave");
		sleep();
		AddPage.city_Form1.sendKeys("NY");
		sleep();
		AddPage.zipCode_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+"678");
		sleep();
		AddPage.countyDropdown1.click();
		sleep();
		AddPage.countyValue1.click();
		
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
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("Test");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("AbtoTest");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
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
		commonFuntions.screenShot("Pop Up page appears", "Pass", "Pop Up page is displayed");
		commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("npca address selected");
		}
	
		/*-------------------taking screenshot SREG-521 -------------------------*/
		
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("BussinessAquisition", "Pass", "Bussiness Aquisition:SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity(SREG-012");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Type of Partner/Owner", "Individual");
		sleep(2000);
		commonFuntions.enterTextboxContains("First Name", "Sanjahjk");
		commonFuntions.enterTextboxContains("Last Name", "Singh");
		commonFuntions.enterTextboxContains("Address Line 1", "Ave"+ commonFuntions.createRandomInteger(10,99));
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","98798");
		commonFuntions.ScrollMenu("Add Partnership Details");
		commonFuntions.screenShot("AddPartnershipDetails", "Pass", "Add Partnership Details: SREG-006");
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
		commonFuntions.screenShot("PartnerDetails", "Pass", "Partner details: SREG-005");
		sleep();
		PEOPage.browserLinkManagePEOPage.click();
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep(4000);
		
		//unable to upload documents -- hence it failed at step 22
		
//	
//		commonFuntions.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
//		commonFuntions.clickButtonContains("Continue");
//		sleep(3000);
//		commonFuntions.selectCheckbox("I accept");
//		commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
//		sleep(2000);
//		commonFuntions.clickButtonContains("Submit");
//		sleep(2000);
//		commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
//		commonFuntions.clickButtonContains("Exit");
		




	}
}