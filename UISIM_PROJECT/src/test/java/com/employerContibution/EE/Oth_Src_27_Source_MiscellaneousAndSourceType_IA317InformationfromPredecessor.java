package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class Oth_Src_27_Source_MiscellaneousAndSourceType_IA317InformationfromPredecessor extends TestBase{

	@Test
	public void Oth_Src_27() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest("Oth_Src_27:Verify that account can be created from source 'Miscellaneous' and Source Type 'IA31.7 Information from Predecessor'");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		cf.ScrollMenu("Employer Registration");
		cf.clickMenu("Employer Registration");
		cf.screenShot("NavigatigToEmployerRegistration", "Pass", "Register Employer");
		cf.clickMenu("Register Employer");sleep(2000);
		cf.screenShot("EmployerRegistrationScreen", "Pass", "Employer Registration:SREG-001");

		/*---Employer Registration----*/
		cf.selectRadioQuestions("Short Form Registration", "Yes ");sleep();
		cf.screenShot("ShortFormRegistrationAsYes", "Pass", "Short Form Registration As Yes");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*---General Information---*/
		cf.selectDropdown("Employer Type", " Business ");
//		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea", "FEIN");
//		String feinValue = databaseResults.get("FEIN");
		String feinValue = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("Fein No is:: " + feinValue);
		test.log(Status.INFO, "FEIN::" + feinValue);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.selectDropdown("Type of Legal Entity", " Partnership ");
		cf.selectDropdown("Source", " Miscellaneous ");sleep();
		cf.selectDropdown("Source Type", " IA31.7 – Information from Predecessor ");sleep();
		cf.screenShot("GeneralInformation", "Pass", "General Information (SREG-025)");
		cf.clickButtonContains("Continue");
		sleep(4000);
		
		/*---Employer Entity Information---*/
//		Map<String, String> databaseResults_LegalName = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea", "ENTITY_NAME");
//		String legalName = databaseResults_LegalName.get("ENTITY_NAME");
		String legalName = cf.enterRandomStringLegalName("Legal Name");
		System.out.println("Legal Name is:: " + legalName);
		test.log(Status.INFO, "Legal Name::" + legalName);
		cf.enterTextboxContains(" Business Phone Number  ",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.selectDropdown("Quarter ", " 3 ");sleep();
		cf.selectDropdown("Year ", " 2022 ");sleep();
		cf.enterTextboxContains("Total number of covered employees", "25");
		cf.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information");
		cf.clickButtonContains("Continue");
		sleep(3000);
		
		/*---Employer Contact Details---*/
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99)+"Cooper Square");
		cf.enterTextboxContains("City", "New York");
		cf.enterTextboxContains("Zip Code", cf.createRandomInteger(100, 999)+"67");
		cf.selectDropdown("County", "Albany");sleep();
		cf.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep();
		
		/*---Employer Verify Contact Details---*/
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		cf.clickButtonContains("Continue");
		sleep(2000);

		/*---Business Acquisition---*/
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		cf.enterTextboxContains("Employer Registration Number", prop.getProperty("ErnPresentInDolDtf"));
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", prop.getProperty("FeinPresentInDolNotInDtf"));
		AddPage.legalNameOfBussiness.sendKeys("ABC COMPANY LTD");
		cf.enterCurrentDate("Notification date of Transfer");sleep();
		cf.screenShot("BusinessAcquisition", "Pass", "Business Acquisition");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("BusinessAcquisitionDetails", "Pass", "Business Acquisition Details");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*---Change in Legal Entity---*/
		cf.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity");
		cf.clickButtonContains("Continue");
		sleep(2000);
		
		/*--------Upload Documents----*/
		AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("TESTINGEL");
		sleep(2000);
		cf.screenShot("UploadDocuments", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*---Review Registration Details---*/
		cf.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(4000);
		
		/*---Statement of Acknowledgement---*/
		cf.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");sleep();
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Home");
		sleep(5000);
		cf.screenShot("HomePage", "Pass", "Home Page Screen");
		
		//Navigating to Inquiry Employer Account 
		AddPage.menu.click();sleep();
		cf.clickMenu("Inquiry");sleep();
		cf.clickMenu("Contribution Inquiry");sleep();
		cf.screenShot("Inquiry", "Pass", "Navigating to Inquiry Employer Account ");
		cf.clickMenu("Inquiry Employer Account");sleep();
		cf.enterTextboxContains(" FEIN ", feinValue);
		cf.screenShot("InquiryEmployerAccount", "Pass", "Inquiry Employer Account");
		cf.clickButtonContains("Continue");
		sleep(4000);
		cf.screenShot("InquiryEmployerAccountInformation", "Pass", "Inquiry Employer Account Information");
	    //Assert.assertEquals(feinValue, AddPage.getFEIN);
	  
	}
}
